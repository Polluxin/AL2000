package BaseDeDonnees.DAOs;

import Metier.GestionClient.CB;

import java.sql.Connection;

// TODO

public class CBDAO extends DAO<CB>{
    public CBDAO(Connection conn) {
        super(conn);
    }

    @Override
    public boolean creer(CB obj) {
        return false;
    }

    @Override
    public CB lire(int id) {
        return null;
    }

    @Override
    public boolean modifier(CB obj) {
        return false;
    }

    @Override
    public boolean supprimer(CB obj) {
        return false;
    }
}
