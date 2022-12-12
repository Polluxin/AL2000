package Metier.GestionLocation;

import Metier.GestionClient.Anonyme;
import Metier.GestionClient.CB;
import Metier.GestionClient.Client;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Armand GRENIER
 * @version 0.0
 */
@SuppressWarnings("unused")
public class Panier {

    List<Location> locations;

    public Panier() {
        locations = new ArrayList<>();
    }

    /**
     * Ajoute une nouvelle location dans la liste des locations.
     * @param s  le support à louer
     * @param c le Client
     */
    public void ajouter(Support s, Client c) {
        Date sqlDate = new Date(System.currentTimeMillis());
        // création de la location
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
     * Attribue la carte cb à toutes les locations du panier.
     * @param cb la carte avec laquelle louer
     */
    public void setLocationsToCB(CB cb){
        for (Location l: locations)
            l.setClient(new Anonyme(cb));
    }

    public void supprimer(Support s) {
        for (Location loc : locations){
            if (loc.support == s) {
                locations.remove(loc);
                break;
            }
        }
    }

    /**
     * créer un support physique TODO
     * @param l location
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

    /**
     * calcul le prix du panier en utilisant les coûts maximum pour prévoir les fonds requis
     * @return le coût nécessaire à réserver pour être certain de pouvoir payer toutes les locations
     */
    public float evaluerPrix(){
        return Location.fondsNecessaire(locations);
    }

    public void viderPanier(){
        locations = new ArrayList<>();
    }
}
