package BaseDeDonnees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Session {
    private static Connection connect;
    public void open(){
        String url = "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:im2ag";
        String user = "pavlovm  ";
        System.out.println("Session : Connexion à la base de données à "+ url +" par "+ user);
        try
        {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            String passwd = "365214";
            connect = DriverManager.getConnection(url, user, passwd);
            System.out.println("Session : Connecté");
        }
        catch (SQLException e) {
            System.out.println("Session : Connexion impossible");
            e.printStackTrace();
        }
    }
    public Connection getSession(){ return connect; }

    public void commit() {
        try {connect.commit(); }
        catch (SQLException e) { e.printStackTrace(); }
    }

    public void rollback() {
        try {connect.rollback(); }
        catch (SQLException e) { e.printStackTrace(); }
    }

    public void close() {
        try {connect.close(); }
        catch (SQLException e) { e.printStackTrace(); }
    }
}
