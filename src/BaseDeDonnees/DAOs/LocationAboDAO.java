package BaseDeDonnees.DAOs;

import Metier.GestionLocation.Location;

import java.sql.Connection;

// TODO

public class LocationAboDAO extends DAO<Location>{

    public LocationAboDAO(Connection conn) {
        super(conn);
    }

    @Override
    public boolean creer(Location loc) {
        return false;
    }

    @Override
    public Location lire(int id) {
        return null;
    }

    @Override
    public boolean modifier(Location obj) {
        return false;
    }

    @Override
    public boolean supprimer(Location obj) {
        return false;
    }
}