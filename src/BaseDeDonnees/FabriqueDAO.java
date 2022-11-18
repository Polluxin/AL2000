package BaseDeDonnees;

import java.sql.Connection;
import BaseDeDonnees.DAOs.*;

public class FabriqueDAO {
    protected static Connection conn;
    FabriqueDAO(Connection c){
        conn = c;
    }
    public static DAO getCarteAboDAO(){ return new CarteAboDAO(conn); }

    public static DAO getCBDAO(){ return new CBDAO(conn); }

    public static DAO getLocationAboDAO(){ return new LocationAboDAO(conn); }

    public static DAO getLocationAnoDAO(){ return new LocationAnoDAO(conn); }

    public static DAO getAbonneDAO(){ return new AbonneDAO(conn); }

    public static DAO getTechnicienDAO(){ return new TechnicienDAO(conn); }

    public static DAO getFilmDAO(){ return new FilmDAO(conn); }

    public static DAO getStatistiquesDAO(){ return new StatistiquesDAO(conn); }

    public static DAO getInventaireDAO(){ return new InventaireDAO(conn); }

}
