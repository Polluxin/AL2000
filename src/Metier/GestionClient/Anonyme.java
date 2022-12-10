package Metier.GestionClient;

import Metier.Exception.PaiementRefuse;
import Metier.GestionLocation.Support;

/**
 *
 * @author Armand GRENIER
 * @version 0.0
 */
public class Anonyme extends Client{

    CB carte;

    /**
     *
     * @param carte
     *
     * La carte doit être une carte banquaire.
     */

    public Anonyme(Carte carte) {
        super(null, carte);
    }

    public void payer(Support s, int jours) throws PaiementRefuse {
        float prix = s.getPrixBaseJour() * jours;
        carte.payer(prix);
    }
}

