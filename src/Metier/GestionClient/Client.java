package Metier.GestionClient;

import Metier.Exception.PaiementRefuse;
import Metier.GestionLocation.Genre;
import Metier.GestionLocation.Support;

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

    //<editor-fold desc="Setter/Getter">
    public Genre[] getInterdits() {
        return interdits;
    }

    public void reglerInterdits(Genre[] interdits) {
        this.interdits = interdits;
    }

    public Carte getCarte() {
        return carte;
    }

//</editor-fold>

    void recharger(float montant) throws PaiementRefuse {
        carte.recharger(montant);
    }

    /**
     * Fait payer un support à un client
     *
     * @param s le support rendu
     * @param jours le nombre de jours à payer
     * @throws PaiementRefuse
     */
    public void payer(Support s, int jours) throws PaiementRefuse {
    }
}
