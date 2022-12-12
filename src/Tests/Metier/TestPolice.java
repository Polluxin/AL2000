package Tests.Metier;

import BaseDeDonnees.Session;
import Controle.AL2000;
import Metier.Exception.CarteIllisible;
import Metier.Exception.ConnexionImpossible;
import Metier.Exception.MauvaisMotDePasse;
import Metier.GestionClient.CarteAbo;
import Metier.GestionLocation.Support;

import java.util.Scanner;

public class TestPolice {

    public static void main(String[] args) throws ConnexionImpossible, CarteIllisible, MauvaisMotDePasse {
        Scanner sc = new Scanner(System.in);
        int i = 0, scInt;
        char scChar;
        Support support;


        // init AL200
        AL2000 al2000 = new AL2000(new Session());
        CarteAbo c = al2000.simulerInsertionCA("1");

    }
}
