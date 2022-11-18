package BaseDeDonnees.DAOs;

import Noyau.GestionMachine.Statistiques;

import java.sql.Connection;

public class StatistiquesDAO extends DAO<Statistiques> {
    public StatistiquesDAO(Connection conn) {
        super(conn);
    }

    @Override
    public boolean creer(Statistiques obj) {
        return false;
    }

    @Override
    public Statistiques lire(int id) {
        return null;
    }

    @Override
    public boolean modifier(Statistiques obj) {
        return false;
    }

    @Override
    public boolean supprimer(Statistiques obj) {
        return false;
    }
}
