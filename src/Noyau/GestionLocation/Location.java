package Noyau.GestionLocation;

import Noyau.GestionClient.Client;

/**
 *
 * @author Armand GRENIER
 * @version 0.0
 */
public class Location {
    String date;
    Etat etat;
    Support support;
    Client client;

    public Location(String date, Etat etat, Support support, Client client) {
        this.date = date;
        this.etat = etat;
        this.support = support;
        this.client = client;
    }

    /**
     * Calcul les fonds nécéssaire pour pouvoir louer un location.
     * Si promo est vrai alors les tarifs de compte abonné sont appliqués
     *
     * @param l
     * @param promo
     * @return les fonds minimum pour la location
     */
    public static float fondsNecessaire(Location l, boolean promo){
        if (l.support instanceof BluRay) return 15.F;
        else if (promo) return 4;
        return 5;
    }

    /**
     * calcul les fonds nécéssaire pour pouvoir louer toute les location de la liste.
     * Si promo est vrai alors les tarifs de compte abonné sont appliqués
     *
     * @param l
     * @param promo
     * @return les fonds minimum pour les locations
     */
    public static float fondsNecessaire(Location[] l, boolean promo){
        float sum = 0.F;
        for (Location loc : l) {
            sum += fondsNecessaire(loc, promo);
        }
        return sum;
    }
}
