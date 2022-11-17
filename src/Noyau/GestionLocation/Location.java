package Noyau.GestionLocation;

import Noyau.GestionClient.Client;

/**
 *
 * @author
 * @version
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
}
