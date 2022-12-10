package Metier.GestionLocation;

import BaseDeDonnees.Session;
import Metier.GestionClient.Abonne;
import Metier.GestionMachine.Inventaire;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Geoffrey DAVID
 * @version 0
 */
@SuppressWarnings("unused")
public class Catalogue {

    Genre[] interdits;

    Inventaire inventaire;

    FiltreTri filtre;

    Session bd;

    int idMachine;

    public Catalogue(Inventaire i, Session s, int idMachine){
        inventaire = i;
        bd = s;
        this.idMachine = idMachine;
    }

    /**
     * Donne la liste des films disponibles et leur support (QRCode et/ou BluRay) selon un filtre f et les
     * préférences de l'abonné a. Cela implique une consultation de la base de données.
     * @param a l'abonné
     * @param f le filtre à appliquer
     * @return la liste des films du catalogue
     */
    public List<FilmEtFormat> donnerFilms(Abonne a, FiltreTri f){
        filtre = f;
        interdits = recupererPreferences(a);
        return recupererFilmsBD();
    }

    /**
     * Donne les préférences de l'abonné a.
     * @param a l'abonné
     * @return les préférences (genres interdits)
     */
    private Genre[] recupererPreferences(Abonne a){
        return  a.getInterdits();
    }

    /**.
     * En fonction du filtre et des préférences en attribut, consulte la base de données pour trouver les films
     * disponibles, et recoupe avec l'inventaire en attribut.
     * @return la liste des films disponibles et leur disponibilité en physique
     */
    private List<FilmEtFormat> recupererFilmsBD(){
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
                "WHERE IDMACHINE="+idMachine;
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
                f = new Film(res.getString("TITRE"), res.getString("REALISATEUR"),
                        res.getDate("DATEFILM"),
                        res.getString("DUREE"),
                        Genre.toGenre(res.getString("GENRE")));
                filmEtFormat = new FilmEtFormat(f, idBR != 0);
                liste_films.add(filmEtFormat);
            }
        } catch (SQLException e){
            System.out.println("Requete : "+lesFilmsEnQRCode);
            e.printStackTrace();
            bd.close();
        }

        bd.close();
        return liste_films;
    }
}
