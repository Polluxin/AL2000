package BaseDeDonnees.DAOs;

import Metier.GestionLocation.BluRay;
import Metier.GestionLocation.Film;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

// TODO: Tester

@SuppressWarnings("unused")
public class BluRayDAO extends DAO<BluRay>{

    public BluRayDAO(Connection conn) {
        super(conn);
    }

    @Override
    public boolean creer(BluRay obj) {
        // Impossible car pas le rôle de l'application
        return false;
    }

    @Override // Utilisé lors du rendu de BluRay
    public BluRay lire(int id) {
        // TODO A TESTER
        BluRay b = null;
        try {
            FilmDAO dao = new FilmDAO(connect);
            ResultSet res = connect.createStatement().executeQuery("SELECT * FROM LESBLURAYS WHERE IDBLURAY="+id);
            res.next();
            Film f = dao.lire(res.getInt("idFilm"));
            assert(f != null);
            b = new BluRay(res.getInt("idBluRay"), f);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public boolean modifier(BluRay obj) {
        // Impossible car pas le rôle de l'application
        return false;
    }

    @Override
    public boolean supprimer(BluRay obj) {
        // Impossible car pas le rôle de l'application
        return false;
    }
}
