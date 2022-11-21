package Metier.GestionLocation;

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

}