package BaseDeDonnees.DAOs;

import Metier.GestionClient.CB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

// TODO

@SuppressWarnings("unused")
public class CBDAO extends DAO<CB>{
    public CBDAO(Connection conn) {
        super(conn);
    }

    // Utilisé lors d'une location par CB
    @Override
    public boolean creer(CB obj) {
        try {
            ResultSet res = connect.createStatement().executeQuery(
                    "SELECT count(idCarte) as idCarte FROM LESCARTES");
            if (!res.next()) return false;
            int id = res.getInt("idCarte")+1;
            connect.createStatement().executeUpdate(
                    "INSERT INTO LESCARTES VALUES ("+id+", '"+obj.getNom()+"', '"+obj.getPrenom()+"')");
            connect.createStatement().executeUpdate(
                    "INSERT INTO LESCB VALUES ("+id+", '"+obj.getInformationsBancaires()+"')");
            obj.setId(id);
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    // Utilisé pour vérifier si la carte est déjà en bd
    // Renvoie l'id de la carte si elle existe en bd, -1 sinon.
    public int lireID(CB cb){
        try {
            ResultSet res = connect.createStatement().executeQuery(
                    "SELECT IDCARTE FROM LESCB WHERE infos='"+cb.getInformationsBancaires()+"'");
            if (res.next()) return res.getInt("idCarte");
        } catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }
}
