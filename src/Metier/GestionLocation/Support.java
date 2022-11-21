package Metier.GestionLocation;

import Metier.GestionMachine.Distributeur;

public abstract class Support {
    /**
     * Classe abstraite, elle se spécialise en QrCode ou en BluRay.
     * Contient seulement un id (int).
     */

    int id;
    Film film;

    public abstract void sortirFilm(Distributeur distributeur);

    public int getId() {
        return id;
    }

    public Film getFilm() {
        return film;
    }
}