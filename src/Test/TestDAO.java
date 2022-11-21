package Test;

import BaseDeDonnees.DAOs.StatistiquesDAO;
import BaseDeDonnees.DAOs.TechnicienDAO;
import BaseDeDonnees.FabriqueDAO;
import BaseDeDonnees.Session;
import Metier.GestionMachine.Statistiques;
import Metier.GestionMachine.Technicien;


// Programme de test du fonctionnement du DAO
public class TestDAO {

    static Session s;
    static FabriqueDAO fabriqueDAO;

    public static void main(String[] args) {
        System.out.println("--- Test des DAO ---");
        s = new Session();
        s.open();
        fabriqueDAO = new FabriqueDAO(s.getSession());

        System.out.println("-- Statistiques --");
        testStatistiques();
        System.out.println("-- Technicien --");
        testTechnicien();

        s.close();
        System.out.println("-------------------");

    }

    public static void testTechnicien(){
        TechnicienDAO dao = fabriqueDAO.getTechnicienDAO();
        System.out.println("- Lecture d'un objet : -");
        Technicien recu = dao.lire(1);
        System.out.println(recu);
    }

    public static void testStatistiques(){
        StatistiquesDAO dao = fabriqueDAO.getStatistiquesDAO();
        System.out.println("- Lecture d'un objet : -");
        Statistiques recues = dao.lire(2);
        System.out.println(recues);

        Statistiques modif = new Statistiques(1, "42-42-");
        System.out.println("- Modification d'une entrée : -");
        dao.modifier(modif);
        recues = dao.lire(1);
        System.out.println(recues);
    }

    public static void testInventaire(){
//        Inventaire inventaire = new Inventaire(1);
//        List<BluRay> liste = new ArrayList<>();
//        liste.add(new BluRay(42, new Film))
//        inventaire.ajouterBlueRays();
    }
}