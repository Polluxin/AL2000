package Tests.Metier;

import BaseDeDonnees.Session;
import Controle.AL2000;
import Metier.Exception.CarteIllisible;
import Metier.Exception.ConnexionImpossible;
import Metier.Exception.FondsInsuffisants;
import Metier.Exception.MauvaisMotDePasse;
import Metier.GestionClient.CarteAbo;
import Metier.GestionLocation.FilmEtFormat;
import Metier.GestionLocation.FiltreTri;
import Metier.GestionLocation.Support;

import java.util.List;
import java.util.Scanner;

public class LocationAbo {

    public static void main(String[] args) throws ConnexionImpossible, CarteIllisible, MauvaisMotDePasse {
        Scanner sc = new Scanner(System.in);
        int i = 0, scInt;
        char scChar;
        Support support;


        // init AL200
        AL2000 al2000 = new AL2000(new Session());
        CarteAbo c = al2000.simulerInsertionCA("1");
        // connexion
        al2000.connexion(c, "CHARLESbogoss2002");

        //récupérer les films
        List<FilmEtFormat> catalogue = al2000.donnerCatalogue(new FiltreTri(null, null));

        // affichage

        System.out.println("Catalogue : ");
        for (FilmEtFormat f : catalogue) {
            System.out.println(i + " - " + f);
            i++;
        }
        i--;

        // choisir un film
        System.out.println("choisir un film entre 0 et " + i);
        scInt = sc.nextInt();

        //choisir son support
        if (catalogue.get(scInt).estDispoEnPhysique()){
            System.out.println("formats disponibles : BluRay / Qrcode");
        } else {
            System.out.println("formats disponibles : Qrcode");
        }
        System.out.println("voulez vous un format BluRay ? (y/n)");
        scChar = sc.next().charAt(0);
        if (scChar == 'y' && !catalogue.get(scInt).estDispoEnPhysique()) {
            System.out.println("/!\\ format BluRay indisponible changement de format vers Qrcode");
            scChar = 'n';
        }


        // acheter le film
        support = al2000.getSupport(catalogue.get(scInt).getFilm(), scChar == 'y');
        al2000.ajouterPanier(support);
        System.out.println(al2000.consulterPanier());
        try {
            al2000.louerFilms();
        } catch (FondsInsuffisants e) {
            System.out.println("Les fonds ne sont pas suffisants !");
            throw new RuntimeException(e);
        }
    }
}

