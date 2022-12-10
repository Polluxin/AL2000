import BaseDeDonnees.Session;
import Controle.AL2000;
import Vue.InterfaceUtilisateur;

public class Main {

    public static void main(String[] args) {
        System.out.println("Version : 1");

        System.out.println("Lancement de la Base de Donnée");
        // Creation de la session
        Session bd = new Session();
        // La base est supposée peuplée

        System.out.println("Lancement du logiciel AL2000");
        AL2000 logiciel = new AL2000(bd);
        // code pour la class AL2000

        System.out.println("Lancement de l'interface");
        InterfaceUtilisateur iu = new InterfaceUtilisateur();
    }
}
