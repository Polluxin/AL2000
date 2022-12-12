package Tests.Metier;

import BaseDeDonnees.Session;
import Controle.AL2000;
import Metier.Exception.*;
import Metier.GestionClient.CarteAbo;
import Metier.GestionLocation.Location;

import java.util.Scanner;

public class RendreBluRay {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = 0, scInt;


        // init AL200
        AL2000 al2000 = new AL2000(new Session());

        //connexion
        CarteAbo c = null;
        try {
            c = al2000.simulerInsertionCA("1");
        } catch (CarteIllisible | ConnexionImpossible e) {
            throw new RuntimeException(e);
        }
        try {
            al2000.connexion(c, "CHARLESbogoss2002");
        } catch (MauvaisMotDePasse e) {
            throw new RuntimeException(e);
        }

        // affichage des location en cours
        System.out.println("Locations : ");
        for (Location l : al2000.voirHistoClient()) {
            System.out.println(i + " - " + l);
            i++;
        }
        i--;

        // simuler un rendu
        System.out.println("simuler le rendu d'un BluRay");
        System.out.println("Choix de l'id :");
        scInt = sc.nextInt();

        try {
            al2000.simulerInsertionBluRay(String.valueOf(scInt));
        } catch (BluRayInvalide | BluRayNonLoue e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // affichage des locations en cours
        System.out.println("Locations : ");
        for (Location l : al2000.voirHistoClient()) {
            System.out.println(i + " - " + l);
            i++;
        }

    }

}
