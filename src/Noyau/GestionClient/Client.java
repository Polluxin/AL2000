package Noyau.GestionClient;

import Noyau.GestionLocation.Genre;

/**
 *
 * @author
 * @version
 */
public abstract class Client {
    /**
     *
     */

    Genre[] interdits;
    Carte carte;

    public Client(Genre[] interdits, Carte carte) {
        this.interdits = interdits;
        this.carte = carte;
    }
}
