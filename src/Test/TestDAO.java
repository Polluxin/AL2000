package Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// Programme de test du fonctionnement du DAO
public class TestDAO {
    public static void main(String[] args) {
        int nb;
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            System.out.println("Tentative de connexion...");
            DriverManager.setLoginTimeout(2);
            Connection bd = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:im2ag",
                    "davidge",
                    "97c7fa9f41");
            System.out.println("Connexion établie");
            System.out.println("Test d'une requête");
            Statement requete = bd.createStatement(); // création du descripteur de requête
            nb = requete.executeUpdate(
                    "INSERT INTO test_bd(nom,age) VALUES ('Marc',13)");
            System.out.println("Nombre de lignes insérées = " + nb);
            ResultSet resultat = requete.executeQuery( // exécution d'une requête
                    "SELECT * " +
                            "FROM test_bd ");
            while(resultat.next()) { // récupération des résultats
                System.out.println("Nom = " + resultat.getString("nom")
                        + ", Age = " + resultat.getString("age"));
            }
            requete.close();
            resultat.close();
            bd.close();
        }catch (Exception e) {
            System.out.println("Connexion impossible à la base :");
            e.printStackTrace();
        }
    }
}