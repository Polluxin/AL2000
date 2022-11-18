package Noyau.GestionClient;

import Noyau.Exception.PaiementRefuse;
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

    //<editor-fold desc="Setter/Getter">
    public Genre[] getInterdits() {
        return interdits;
    }

    public void reglerInterdits(Genre[] interdits) {
        this.interdits = interdits;
    }
    //</editor-fold>

    void recharger(float montant) throws PaiementRefuse {
        carte.recharger(montant);
    }

    void payer(float montant) throws PaiementRefuse {
        carte.recharger(montant);
    }
}
