package Noyau.GestionClient;

import Noyau.GestionLocation.Genre;

/**
 *
 * @author Armand GRENIER
 * @version 0.0
 */
public abstract class Client {

    Genre[] interdits;
    Carte carte;

    public Client(Genre[] interdits, Carte carte) {
        this.interdits = interdits;
        this.carte = carte;
    }

    public Genre[] getInterdits() {
        return interdits;
    }

    public void setInterdits(Genre[] interdits) {
        this.interdits = interdits;
    }
}
