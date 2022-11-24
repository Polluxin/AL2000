package BaseDeDonnees.DAOs;

import Metier.GestionClient.Abonne;
import Metier.GestionClient.CarteAbo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AbonneDAO extends DAO<Abonne> {
    public AbonneDAO(Connection conn) {
        super(conn);
    }

    @Override
    public boolean creer(Abonne obj) {
        return false;
    }

    @Override
    public Abonne lire(int id){
        return null;
    }

    public Abonne lireDepuisCarte(CarteAbo c) {
        // TODO A TESTER
        Abonne a = null;
        try {
            ResultSet res = connect.createStatement().executeQuery("SELECT * FROM LESCARTESABONNES WHERE IDABO="+c.getId());
            res.next();
            a = new Abonne(null, c,res.getString("nom"),res.getString("prenom"),
                    res.getString("mail"), res.getString("adressePostale"), res.getString("mdp"));
        } catch (SQLException e){
            e.printStackTrace();
        }
        return a;
    }

    @Override
    public boolean modifier(Abonne obj) {
        return false;
    }

    @Override
    public boolean supprimer(Abonne obj) {
        return false;
    }
}
