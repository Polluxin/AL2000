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
        prixMax = 15.f;
        prixAboJour = 4.F;
        prixBaseJour = 5.F;
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

    @Override
    public Support supportInverse(Distributeur dist) {
        // TODO
        // comment créer un qrCode ? que id choisir ?
        return new QrCode(-999, film);
    }
}