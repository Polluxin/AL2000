package BaseDeDonnees.DAOs;

import Metier.GestionLocation.Film;
import Metier.GestionLocation.Genre;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmDAO extends DAO<Film>{
    public FilmDAO(Connection conn) {
        super(conn);
    }

    @Override
    public Film lire(int id) {
        // TODO A TESTER
        Film f = null;
        try {
            ResultSet res = connect.createStatement().executeQuery("SELECT * FROM LESFILMS WHERE IDFILM="+id);
            if (!res.next()) return null;
            f = new Film(res.getString("titre"),res.getString("realisateur"),
                    res.getDate("datefilm"), res.getString("duree"),
                    Genre.toGenre(res.getString("genre")));
        } catch (SQLException e){
            e.printStackTrace();
        }
        return f;
    }
}
