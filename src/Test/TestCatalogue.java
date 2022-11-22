package Test;

import BaseDeDonnees.Session;
import Metier.GestionLocation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class TestCatalogue {
    public static void main(String[] args) {
        Session bd = new Session();
        FiltreTri filtre = new FiltreTri(null, null);
        Genre[] interdits = {};

        // Récupération des films QRCode (=tout les films répondant au filtre et au genre interdits)
        bd.open();
        StringBuilder lesFilmsEnQRCode = new StringBuilder("SELECT * FROM LESFILMS");

        if (filtre.getTri() == Tri.RECHERCHE){ // Cas d'une recherche d'un film particulier
            lesFilmsEnQRCode.append(" WHERE titre='").append(filtre.getValeurDeRecherche()).append("'");
        }else {
            // Si j'ai des interdits :
            if (interdits.length > 0) {
                lesFilmsEnQRCode.append(" WHERE ");
                for (int i = 0; i < interdits.length; i++) {
                    lesFilmsEnQRCode.append("genre!='").append(interdits[i].toString()).append("' ");
                    if (i != interdits.length - 1)
                        lesFilmsEnQRCode.append("AND ");
                }
            }
            // Dans l'état, cette requête donne tout les films avec des genres autorisés.
            if (filtre.getTri() != null)
                // il ne reste plus qu'à la trier
                lesFilmsEnQRCode.append(" ORDER BY ").append(filtre.getTri().toString());
        }
        // Construction de la requête des films en BluRay de la machine
        String lesFilmsEnBluRay = "SELECT LESSTOCKS.IDBLURAY, IDFILM, IDMACHINE FROM LESSTOCKS " +
                "JOIN LESBLURAYS L on LESSTOCKS.IDBLURAY = L.IDBLURAY " +
                "WHERE IDMACHINE="+1;
        @SuppressWarnings("SqlResolve") String requete = "WITH LesFilmsEnQRCode AS ("+lesFilmsEnQRCode+"),\n" +
                "    LesFilmsEnBluRay AS ("+lesFilmsEnBluRay+")\n" +
                "SELECT LesFilmsEnQRCode.IDFILM,LesFilmsEnQRCode.TITRE,LesFilmsEnQRCode.DATEFILM,LesFilmsEnQRCode.REALISATEUR,LesFilmsEnQRCode.GENRE,LesFilmsEnQRCode.DUREE, idBluRay\n" +
                "FROM LesFilmsEnQRCode\n" +
                "LEFT JOIN LesFilmsEnBluRay ON LesFilmsEnQRCode.IDFILM = LesFilmsEnBluRay.IDFILM";

        List<FilmEtFormat> liste_films = null;
        try {
            ResultSet res = bd.getSession().createStatement().executeQuery(requete);
            // Changement du format du résultat de la requête sql
            int idBR, idFilm;
            Film f;
            FilmEtFormat filmEtFormat;
            liste_films = new ArrayList<>();
            while(res.next()){
                idBR = res.getInt("IDBLURAY");
                idFilm = res.getInt("idFilm");
                f = new Film(res.getString("TITRE"), res.getString("REALISATEUR"),
                        res.getDate("DATEFILM"),
                        res.getString("DUREE"),
                        Genre.toGenre(res.getString("GENRE")));
                filmEtFormat = new FilmEtFormat(f, idBR != 0);
                liste_films.add(filmEtFormat);
            }
        } catch (SQLException e){
            System.out.println("Requete : "+requete);
            e.printStackTrace();
            bd.close();
        }

        bd.close();
        for (FilmEtFormat f: liste_films){
            System.out.println("Film: "+f.getFilm().getTitre()+" et Format: "+ f.estDispoEnPhysique());
        }
    }
}
