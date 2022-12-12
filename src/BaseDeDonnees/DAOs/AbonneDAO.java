package BaseDeDonnees.DAOs;

import Metier.GestionClient.Abonne;
import Metier.GestionClient.CarteAbo;
import Metier.GestionLocation.Genre;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AbonneDAO extends DAO<Abonne> {
    public AbonneDAO(Connection conn) {
        super(conn);
    }

    //Utilisé lors de l'inscription d'un nouvel abonné
    @Override
    public boolean creer(Abonne obj) {
        // Creation de la carte d'abonné
        // TODO A TESTER
        try {
            ResultSet res = connect.createStatement().executeQuery("" +
                    "SELECT MAX(IDCARTE) as idabo " +
                    "FROM LESCARTES");
            if (!res.next()) return false;
            int id = res.getInt("idabo") + 1;
            this.connect.createStatement().executeUpdate(
                    "INSERT INTO LESCARTES VALUES("+id+", "+obj.getNom()+", "+obj.getPrenom()+")");
            this.connect.createStatement().executeUpdate(
                    "INSERT INTO LESCA VALUES("+id+", "+
                    obj.getAddresseMail()+", "+ obj.getAdressePostale()+", 0, "+obj.getMotDePasse()+")");
            CarteAbo c = new CarteAbo(id, 0);
            obj.setCarte(c);
            // Insertion des genres dans la table LesInterdits
            //    Conversion des genres en string
            Genre[] genres = obj.getInterdits();
            StringBuilder genresS = new StringBuilder();
            for (int i=0; i< genres.length; i++){
                genresS.append(genres[i].toString());
                if (i<genres.length-1) genresS.append(" ");
            }
            //    Ajout en BD
            connect.createStatement().executeUpdate(
                    "INSERT INTO LESINTERDITS VALUES("+id+", "+genresS+")");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Utilisé pour la connexion d'un abonné à la borne.
    public Abonne lireDepuisCarte(CarteAbo c) {
        // TODO A TESTER
        Abonne a;
        try {
            ResultSet res = connect.createStatement().executeQuery("" +
                    "SELECT * " +
                    "FROM LESCA a, LESCARTES c " +
                    "WHERE c.IDCarte = a.IDCarte AND a.IDCarte="+c.getId());
            res.next();
            a = new Abonne(null, c,res.getString("nom"),res.getString("prenom"),
                    res.getString("mail"), res.getString("adressePostale"), res.getString("mdp"));
            // Ajout des interdits
            ResultSet res2 = connect.createStatement().executeQuery("" +
                    "SELECT genres " +
                    "FROM LESINTERDITS " +
                    "WHERE IdAbo="+c.getId());
            res2.next();
            String g = res2.getString("genres");
            if (g != null) {
                String[] interdits = g.split(" ");
                Genre[] genres = new Genre[interdits.length];
                // Conversion des strings en genres
                for (int i = 0; i < interdits.length; i++) {
                    genres[i] = Genre.toGenre(interdits[i]);
                }
                a.setInterdits(genres);
            }
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return a;
    }

    public void modifierSolde(int id, float nouveau_solde){
        try {
            connect.createStatement().executeUpdate("" +
                    "UPDATE LESCA " +
                    "SET SOLDE="+nouveau_solde+" " +
                    "where IDCARTE="+id);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
