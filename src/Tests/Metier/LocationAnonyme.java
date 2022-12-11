package Tests.Metier;

import BaseDeDonnees.Session;
import Controle.AL2000;
import Metier.Exception.CarteIllisible;
import Metier.Exception.FondsInsuffisants;
import Metier.GestionClient.CB;
import Metier.GestionClient.Client;
import Metier.GestionLocation.*;

import java.util.List;
import java.util.Scanner;

public class LocationAnonyme {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = 0, scInt;
        char scChar;
        Support support;
        CB cb;


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


        // ajouter le film au panier
        support = al2000.getSupport(catalogue.get(scInt).getFilm(), scChar == 'y');
        al2000.ajouterPanier(support);

        // valider
        try {
            cb = al2000.simulerInsertionCB("5341 2154 2225 4448-04 25-Paul Fort-888-");
        } catch (CarteIllisible e) {
            System.out.println("Pb de carte illisible");
            throw new RuntimeException(e);
        }
        try {
            al2000.louerFilms(cb);
            System.out.println("Location validé");
        } catch (FondsInsuffisants e) {
            System.out.println("Les fonds de la cb ne sont pas suffisants !");
            throw new RuntimeException(e);
        }

        // affichage de l'histoLoc
        System.out.println("Affichage historique location");
        for (Location l :al2000.voirHistoMachine()){
            Client client = l.getClient();
            System.out.println(l+" par "+ client.getNom() +" "+client.getPrenom());
        }
    }
}
