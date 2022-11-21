package Metier.GestionLocation;

import Metier.GestionMachine.Distributeur;

/**
 *
 * @author Armand GRENIER
 * @version 0.0
 */
public class BluRay extends Support {

    public BluRay(int id, Film film) {
        this.id = id;
        this.film = film;
    }

    @Override
    public void sortirFilm(Distributeur distributeur) {
        distributeur.livrerBluRay(this);
    }
}