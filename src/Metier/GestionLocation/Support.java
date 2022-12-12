package Metier.GestionLocation;

import Metier.GestionMachine.Distributeur;

public abstract class Support {
    /**
     * Classe abstraite, elle se spécialise en QrCode ou en BluRay.
     * Contient seulement un id (int).
     */

    int id;

    float prixMax;
    float prixAboJour;
    float prixBaseJour;
    Film film;

    public float getPrixMax() {
        return prixMax;
    }

    public abstract void sortirFilm(Distributeur distributeur);

    public int getId() {
        return id;
    }

    public Film getFilm() {
        return film;
    }

    public float getPrixAboJour() {
        return prixAboJour;
    }

    public float getPrixBaseJour() {
        return prixBaseJour;
    }

    /**
     * renvoie un support sur le même film mais remplace QrCode par BluRay et vice-versa
     * @param dist Distributeur utilisé pour trouver les BluRays
     * @return le support "inverse"
     */
    public Support supportInverse(Distributeur dist) {
        return null;
    }

    @Override
    public String toString() {
        return "Support{" +
                "id=" + id +
                ", film=" + film +
                '}';
    }
}