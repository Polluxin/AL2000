package BaseDeDonnees.DAOs;

import Noyau.GestionMachine.Technicien;

import java.sql.Connection;

public class TechnicienDAO extends DAO<Technicien>{
    public TechnicienDAO(Connection conn) {
        super(conn);
    }

    @Override
    public boolean creer(Technicien obj) {
        return false;
    }

    @Override
    public Technicien lire(int id) {
        return null;
    }

    @Override
    public boolean modifier(Technicien obj) {
        return false;
    }

    @Override
    public boolean supprimer(Technicien obj) {
        return false;
    }
}
