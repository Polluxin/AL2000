package Noyau.GestionClient;

import Noyau.Exception.PaiementRefuse;

/**
 *
 * @author Armand GRENIER
 * @version 0.0
 */
public abstract class Carte {
    /**
     * Paye le montant indiqué en utilisant la carte
     * @param montant
     * @throws PaiementRefuse
     */
    void payer(float montant) throws PaiementRefuse {}

    /**
     * Recharge la carte d'un certain montant.
     * @param montant
     * @throws PaiementRefuse
     */
    void recharger(float montant) throws PaiementRefuse {}

    /**
     * Verifie que la carte peut payer le montant
     * @param montant
     * @return
     */
    boolean verifier_fonds(float montant) { return false;}

}
