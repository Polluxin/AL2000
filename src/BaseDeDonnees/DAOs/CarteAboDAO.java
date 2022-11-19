package BaseDeDonnees.DAOs;

import Metier.GestionClient.CarteAbo;

import java.sql.Connection;

public class CarteAboDAO extends DAO<CarteAbo> {
    public CarteAboDAO(Connection conn) {
        super(conn);
    }

    @Override
    public boolean creer(CarteAbo obj) {
        return false;
    }

    @Override
    public CarteAbo lire(int id) {
        return null;
    }

    @Override
    public boolean modifier(CarteAbo obj) {
        return false;
    }

    @Override
    public boolean supprimer(CarteAbo obj) {
        return false;
    }
}
