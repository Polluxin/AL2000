package Metier.GestionMachine;

import Metier.Exception.CarteIllisible;
import Metier.GestionClient.CB;
import Metier.GestionClient.CarteAbo;
import Metier.GestionLocation.BluRay;
import Metier.GestionLocation.QrCode;
import Metier.GestionLocation.Support;

import java.util.List;

/**
 * Infrastructure physique du distributeur de films.
 * @author Geoffrey DAVID
 * @version 0
 */
@SuppressWarnings("RedundantThrows")
public class Machine implements Distributeur, Maintenance {

    Inventaire inventaire;

    Statistiques statistiques;
    Machine(Inventaire i, Statistiques s){
        inventaire = i;
        statistiques = s;
    }
    @Override
    public CB lireCB() throws CarteIllisible {
        return null;
    }

    @Override
    public Technicien lireCTechnicien() throws CarteIllisible {
        return null;
    }

    @Override
    public CarteAbo lireCarteAbo() throws CarteIllisible {
        return null;
    }

    @Override
    public void livrerFilms(List<Support> l) {
        for (Support f: l){
            f.sortirFilm(this);
        }
        System.out.println("-> "+l.size()+" films ont été livrés");
    }

    @Override
    public void livrerBluRay(BluRay b) {
        System.out.println("-> Livraison du BluRay de '"+b.getFilm()+"'");
    }

    @Override
    public void imprimerQRCode(QrCode qr) {
        System.out.println("-> Impression du QR Code de '"+qr.getFilm()+"'");
    }

    @Override
    public void avalerBluRays(Inventaire i) {
        System.out.println("-> Rendu de BluRays");
    }

    @Override
    public void ouvrir() {
        System.out.println("-> Trappe de la machine ouverte");
    }

    @Override
    public void fermer() {
        System.out.println("-> Trappe de la machine fermée");
    }

    @Override
    public Statistiques voirStatistiques() {
        return statistiques;
    }

    @Override
    public Inventaire donnerInventaire() {
        return inventaire;
    }
}
