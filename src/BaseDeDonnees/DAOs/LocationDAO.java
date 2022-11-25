package BaseDeDonnees.DAOs;

import Metier.GestionLocation.Location;

import java.sql.Connection;

// TODO

public class LocationDAO extends DAO<Location>{

    public LocationDAO(Connection conn) {
        super(conn);
    }

    @Override
    public boolean creer(Location loc) {
        return false;
    }

    public Location lireLocQRCode(int id){
        return null;
    }

    public Location lireLocBluRay(int id){
        return null;
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
