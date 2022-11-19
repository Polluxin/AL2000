package Test;

import BaseDeDonnees.DAOs.StatistiquesDAO;
import BaseDeDonnees.Session;
import Metier.GestionMachine.Statistiques;

import java.sql.Connection;

// Programme de test du fonctionnement du DAO
public class TestDAO {
    public static void main(String[] args) {
        System.out.println("--- Test des DAO ---");
        Session s = new Session();


        System.out.println("-- Statistiques --");
        s.open();
        Connection connection = s.getSession();
        StatistiquesDAO dao = new StatistiquesDAO(connection);
        System.out.println("- Lecture d'un objet : -");
        Statistiques recues = dao.lire(2);
        System.out.println(recues);

        Statistiques modif = new Statistiques(1, "42-42-");
        System.out.println("- Modification d'une entrée : -");
        dao.modifier(modif);
        recues = dao.lire(1);
        System.out.println(recues);

        
        s.close();
        System.out.println("-------------------");


    }
}