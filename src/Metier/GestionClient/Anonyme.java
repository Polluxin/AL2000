package Metier.GestionClient;

import Metier.Exception.PaiementRefuse;
import Metier.GestionLocation.Genre;
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
     * @param interdits
     * @param carte
     *
     * La carte doit être une carte banquaire et la liste interdits doit être vide.
     */

    public Anonyme(Genre[] interdits, Carte carte) {
        super(interdits, carte);
    }

    public void payer(Support s, int jours) throws PaiementRefuse {
        float prix = s.getPrixBaseJour() * jours;
        carte.payer(prix);
    }
}

