package BaseDeDonnees.DAOs;

import Metier.GestionLocation.Film;

import java.sql.Connection;

public class FilmDAO extends DAO<Film>{
    public FilmDAO(Connection conn) {
        super(conn);
    }

    @Override
    public boolean creer(Film obj) {
        return false;
    }

    @Override
    public Film lire(int id) {
        return null;
    }

    @Override
    public boolean modifier(Film obj) {
        return false;
    }

    @Override
    public boolean supprimer(Film obj) {
        return false;
    }
}
