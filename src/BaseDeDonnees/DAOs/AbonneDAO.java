package BaseDeDonnees.DAOs;

import Metier.GestionClient.Abonne;

import java.sql.Connection;

public class AbonneDAO extends DAO<Abonne> {
    public AbonneDAO(Connection conn) {
        super(conn);
    }

    @Override
    public boolean creer(Abonne obj) {
        return false;
    }

    @Override
    public Abonne lire(int id) {
        return null;
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
