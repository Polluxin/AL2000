package Metier.GestionLocation;

import Metier.GestionClient.Client;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Armand GRENIER
 * @version 0.0
 */
public class Location {
    Date date;
    Etat etat;
    Support support;
    Client client;

    public Location(Date date, Etat etat, Support support, Client client) {
        this.date = date;
        this.etat = etat;
        this.support = support;
        this.client = client;
    }


    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }

    public Client getClient() {
        return client;
    }

    /**
     * Calcul les fonds nécéssaire pour pouvoir payer une location.
     * Si promo est vraie alors les tarifs de compte abonné sont appliqués
     *
     * @param l liste de locations
     * @param promo vrai si prix abonné
     * @return les fonds minimum pour la location
     */
    public static float fondsNecessaire(Location l, boolean promo){
        if (l.support instanceof BluRay) return 15.F;
        else if (promo) return 4;
        return 5;
    }

    /**
     * Calcul les fonds nécéssaire pour pouvoir louer toutes les locations de la liste.
     * Si promo est vraie alors les tarifs de compte abonné sont appliqués
     *
     * @param L liste de locations
     * @param promo vrai si prix abonné
     * @return les fonds minimum pour les locations
     */
    public static float fondsNecessaire(Location[] L, boolean promo){
        float sum = 0.F;
        for (Location loc : L) {
            sum += fondsNecessaire(loc, promo);
        }
        return sum;
    }

    public static float fondsNecessaire(List<Location> locations, boolean promo) {
        return fondsNecessaire((Location[]) locations.toArray(), promo);
    }



}
