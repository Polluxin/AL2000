package Metier.GestionLocation;

import Metier.GestionMachine.Distributeur;

/**
 *
 * @author Armand GRENIER
 * @version 0.0
 */
public class BluRay extends Support {
    int id;
    float prixMax = 15.f;

    float prixAboJour = 4.F;
    float prixBaseJour = 5.F;
    Film film;

    public BluRay(int id, Film film) {
        this.id = id;
        this.film = film;
    }

    public Film getFilm() {
        return film;
    }

    public int getId() {
        return id;
    }

    @Override
    public void sortirFilm(Distributeur distributeur) {
        distributeur.livrerBluRay(this);
    }
}