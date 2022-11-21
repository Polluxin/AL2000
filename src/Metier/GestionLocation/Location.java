package Metier.GestionLocation;

import Metier.Exception.PaiementRefuse;
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
     *
     * @param l liste de locations
     * @return les fonds minimum pour la location
     */
    public static float fondsNecessaire(Location l){
        return l.support.getPrixMax();
    }

    /**
     * Calcul les fonds nécéssaire pour pouvoir louer toutes les locations de la liste.
     *
     * @param L liste de locations
     * @return les fonds minimum pour les locations
     */
    public static float fondsNecessaire(Location[] L){
        float sum = 0.F;
        for (Location loc : L) {
            sum += fondsNecessaire(loc);
        }
        return sum;
    }

    /**
     * Calcul les fonds nécéssaire pour pouvoir louer toutes les locations de la liste.
     *
     * @param locations
     * @return
     */
    public static float fondsNecessaire(List<Location> locations) {
        return fondsNecessaire((Location[]) locations.toArray());
    }

    public void payer(){
        int joursAPayer;
        // TODO
        Date sqlDate = new Date(System.currentTimeMillis());
        joursAPayer = 2;
        try {
            client.payer(support, joursAPayer);
        } catch (PaiementRefuse e) {
            // le paiement est refusé
            throw new RuntimeException(e);
        }

    }



}
