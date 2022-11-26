package BaseDeDonnees.DAOs;

import Metier.GestionClient.CarteAbo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

// TODO

public class CarteAboDAO extends DAO<CarteAbo> {
    public CarteAboDAO(Connection conn) {
        super(conn);
    }

    @Override
    public CarteAbo lire(int id) {
        CarteAbo carteAbo;
        try {
            ResultSet res = this.connect.createStatement().executeQuery(
                    "SELECT solde FROM LesCA WHERE idAbo="+id);
            res.next();
            carteAbo = new CarteAbo(id, res.getFloat("solde"));

        } catch (SQLException e) {
            e.printStackTrace();
            return null;}
        return carteAbo;
    }
}
