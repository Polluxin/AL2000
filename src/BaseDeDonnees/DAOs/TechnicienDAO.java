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
    public Technicien lire(int id) {
        Technicien technicien;
        try {
            ResultSet res = this.connect.createStatement().executeQuery(
                    "SELECT * FROM LesTechniciens WHERE id="+id);
            if (!res.next()) return null;
            technicien = new Technicien(res.getInt("id"), res.getString("nom"), res.getString("prenom"));

        } catch (SQLException e) {
            e.printStackTrace();
            return null;}
        return technicien;
    }
}
