package Metier.GestionLocation;

import Metier.GestionClient.Carte;
import Metier.GestionClient.Client;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Armand GRENIER
 * @version 0.0
 */
public class Panier {

    List<Location> locations;

    public Panier() {
        locations = new ArrayList<>();
    }

    /**
     * Ajoute une nouvelle location dans la liste des locations. La nouvelle
     * location est créée à partir de la date du jour, d'un client
     * et d'un support généré à partir d'un film.
     * @param f le film sur le support
     * @param c le Client
     * @param physique true pour un BluRay, false pour un QrCode
     */
    public void ajouter(Film f, Client c, boolean physique){
        Date sqlDate = new Date(System.currentTimeMillis());
        // TODO
        // créer le support sue le film
        Support s = null;
        Location loc = new Location(sqlDate, Etat.ENCOURS, s, c);
        locations.add(loc);
    }

    public List<Location> getLocations() {
        return locations;
    }

    public List<Support> getSupports(){
        List<Support> l = new ArrayList<>();
        for (Location location: locations)
            l.add(location.getSupport());
        return l;
    }

    /**
     * Retire une location de la liste tout en gardant l'odre des éléments.
     * Ne fais rien si la location n'est pas dans la liste
     * @param l la location a supprimer
     */
    public void supprimer(Location l) {
        locations.remove(l);
    }

    /**
     * créer un support physique TODO
     * @param l
     */
    public void setSupportBluRay(Location l){
        // TODO
        // créer support physique sur le film de la location
        l.support = new BluRay(-1, l.support.film);
    }
    public void setSupportQrCode(Location l){
        // TODO
        // créer support qrcode sur le film de la location
        l.support = new QrCode(-1, l.support.film);
    }

    public float evaluerPrix(){
        return Location.fondsNecessaire(locations);
    }

    public void viderPanier(){
        locations = new ArrayList<>();
    }




}
