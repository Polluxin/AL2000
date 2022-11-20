package BaseDeDonnees.DAOs;

import Metier.GestionLocation.BluRay;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

// TODO: Tester cette classe

@SuppressWarnings("unused")
public class BluRayDAO extends DAO<BluRay>{

    private int idMachineAssocie;

    public BluRayDAO(Connection conn) {
        super(conn);
    }

    public void setIdMachineAssocie(int idMachineAssocie) {
        this.idMachineAssocie = idMachineAssocie;
    }

    @Override
    public boolean creer(BluRay obj) {
        try {
            // Récupération de l'id du film (on suppose qu'il n'y a pas deux films du même nom)
            ResultSet res = connect.createStatement().executeQuery(
                    "SELECT IdFilm FROM LESFILMS WHERE TITRE='"+obj.getFilm().getTitre()+"'");
            res.next();
            int idFilm = res.getInt("idFilm");
            connect.createStatement().executeUpdate(
                    "INSERT INTO LesBluRays values("+obj.getId()+", "+idFilm+", "+idMachineAssocie);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public BluRay lire(int id) {
        // Impossible
        return null;
    }

    @Override
    public boolean modifier(BluRay obj) {
        // Impossible
        return false;
    }

    @Override
    public boolean supprimer(BluRay obj) {
        try {
            connect.createStatement().executeUpdate(
                    "DELETE FROM LesBlurays WHERE IDBLURAY='"+obj.getId()+"'");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
