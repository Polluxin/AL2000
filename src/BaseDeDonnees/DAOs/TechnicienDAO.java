package BaseDeDonnees.DAOs;

import Metier.GestionMachine.Technicien;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TechnicienDAO extends DAO<Technicien>{
    public TechnicienDAO(Connection conn) {
        super(conn);
    }

    @Override
    public boolean creer(Technicien obj) {
        // Impossible, base déjà peuplée
        return false;
    }

    @Override
    public Technicien lire(int id) {
        Technicien technicien = null;
        try {
            ResultSet res = this.connect.createStatement().executeQuery(
                    "SELECT * FROM LesTechniciens WHERE id="+id);
            res.next();
            // res.getString("donnees") sous le format "nbLocation-nbOuvertures"
            technicien = new Technicien(res.getInt("id"), res.getString("nom"), res.getString("prenom"));

        } catch (SQLException e) { e.printStackTrace(); }
        return technicien;
    }

    @Override
    public boolean modifier(Technicien obj) {
        // Impossible, pas le rôle de l'application
        return false;
    }

    @Override
    public boolean supprimer(Technicien obj) {
        // Impossible, pas le rôle de l'application
        return false;
    }
}
