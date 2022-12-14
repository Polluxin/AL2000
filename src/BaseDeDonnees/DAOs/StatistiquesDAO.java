package BaseDeDonnees.DAOs;

import Metier.GestionMachine.Statistiques;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatistiquesDAO extends DAO<Statistiques> {
    public StatistiquesDAO(Connection conn) {
        super(conn);
    }

    @Override
    public Statistiques lire(int idMachine) {
        Statistiques stats = null;
        try {
            ResultSet res = this.connect.createStatement().executeQuery(
                    "SELECT * FROM LesStatistiques WHERE idMachine="+idMachine);
            if (!res.next()) return null;
            // res.getString("donnees") sous le format "nbLocation-nbOuvertures"
            stats = new Statistiques(res.getInt("idMachine"), res.getString("donnees"));

        } catch (SQLException e) { e.printStackTrace(); }
        return stats;
    }

    @Override
    public boolean modifier(Statistiques obj) {
        try {
            this.connect.createStatement().executeUpdate(
                    "UPDATE LesStatistiques " +
                            "SET dateStat=current_date, donnees ='"+obj.getDonnees()+"'" +
                            "WHERE idMachine="+obj.getId());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
