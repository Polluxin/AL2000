package BaseDeDonnees;

import java.sql.Connection;
import BaseDeDonnees.DAOs.*;

@SuppressWarnings("unused")
public class FabriqueDAO {
    protected static Connection conn;
    public FabriqueDAO(Connection c){
        conn = c;
    }
    public static CarteAboDAO getCarteAboDAO(){ return new CarteAboDAO(conn); }

    public CBDAO getCBDAO(){ return new CBDAO(conn); }

    public LocationAboDAO getLocationAboDAO(){ return new LocationAboDAO(conn); }

    public LocationAnoDAO getLocationAnoDAO(){ return new LocationAnoDAO(conn); }

    public AbonneDAO getAbonneDAO(){ return new AbonneDAO(conn); }

    public TechnicienDAO getTechnicienDAO(){ return new TechnicienDAO(conn); }

    public FilmDAO getFilmDAO(){ return new FilmDAO(conn); }

    public StatistiquesDAO getStatistiquesDAO(){ return new StatistiquesDAO(conn); }

    public BluRayDAO getBluRayDAO(){ return new BluRayDAO(conn); }

}
