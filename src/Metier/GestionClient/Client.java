package Metier.GestionClient;

import Metier.Exception.PaiementRefuse;
import Metier.GestionLocation.Genre;
import Metier.GestionLocation.HistoLoc;
import Metier.GestionLocation.Location;
import Metier.GestionLocation.Support;

import java.util.List;

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

    /**
     * Fait payer un support à un client
     *
     * @param s le support rendu
     * @param jours le nombre de jours à payer
     * @throws PaiementRefuse
     */
    public void payer(Support s, int jours) throws PaiementRefuse {
    }

    /**
     * Fait âyer un support à un client avec le tarif max
     * @param support
     * @throws PaiementRefuse
     */
    public void payerRetard(Support support) throws PaiementRefuse {
        carte.payer(support.getPrixMax());
    }

    /**
     * rajoute de l'argent à un client
     * @param montant
     * @throws PaiementRefuse
     */
    void recharger(float montant) throws PaiementRefuse {
        carte.recharger(montant);
    }

    /**
     * Calcul les fond réservé pour les locations en cours
     * @param histo
     * @return
     */
    public float fondsReserves(HistoLoc histo) {
        float res = 0.F;
        List<Location> locations = histo.voirLocationEnCours(this);
        for (Location l: locations) {
            res += l.getSupport().getPrixMax();
        }
        return res;

    }
}
