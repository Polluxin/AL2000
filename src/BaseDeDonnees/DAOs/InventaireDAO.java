package BaseDeDonnees.DAOs;

import Noyau.GestionMachine.Inventaire;

import java.sql.Connection;

public class InventaireDAO extends DAO<Inventaire>{
    public InventaireDAO(Connection conn) {
        super(conn);
    }

    @Override
    public boolean creer(Inventaire obj) {
        return false;
    }

    @Override
    public Inventaire lire(int id) {
        return null;
    }

    @Override
    public boolean modifier(Inventaire obj) {
        return false;
    }

    @Override
    public boolean supprimer(Inventaire obj) {
        return false;
    }
}
