package Tests.Metier;

import BaseDeDonnees.DAOs.CarteAboDAO;
import BaseDeDonnees.Session;
import Controle.AL2000;
import Metier.Exception.FondsInsuffisants;
import Metier.GestionLocation.FilmEtFormat;
import Metier.GestionLocation.FiltreTri;
import Metier.GestionLocation.Support;
import Metier.GestionLocation.Tri;

import java.util.List;
import java.util.Scanner;

public class LocationAbo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = 0, scInt;
        char scChar;
        Support support;


        // init AL200
        AL2000 al2000 = new AL2000(new Session());

        //récupérer les films
        List<FilmEtFormat> catalogue = al2000.donnerCatalogue(new FiltreTri(Tri.TITRE, ""));

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
            al2000.validerPanier();
        } catch (FondsInsuffisants e) {
            System.out.println("Les fonds de la cb ne sont pas suffisants !");
            throw new RuntimeException(e);
        }
    }
}

