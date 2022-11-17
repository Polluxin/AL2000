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
}
