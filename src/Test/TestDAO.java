package Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

// Programme de test du fonctionnement du DAO
public class TestDAO {
    public static void main(String[] args) {
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            System.out.println("Tentative de connexion...");
            DriverManager.setLoginTimeout(2);
            Connection bd = DriverManager.getConnection("jdbc:oracle:thin:@im2ag-oracle.univ-grenoble-alpes.fr:1521:im2ag",
                    "davidge",
                    "97c7fa9f41");
            System.out.println("Connexion établie");
            bd.close();
        }catch (Exception e) {
            System.out.println("Connexion impossible à la base :");
            e.printStackTrace();
        }
    }
}