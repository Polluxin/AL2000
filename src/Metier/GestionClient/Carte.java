package Metier.GestionClient;

import Metier.Exception.PaiementRefuse;

/**
 *
 * @author Armand GRENIER
 * @version 0.0
 */
public abstract class Carte {
    int id = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Paye le montant indiqué en utilisant la carte
     * @param montant valeur en euros à payer
     * @throws PaiementRefuse si le paiement ne fonction pas (fond bancaire ou solde insuffisant)
     */
    void payer(float montant) throws PaiementRefuse {}

    /**
     * Recharge la carte d'un certain montant.
     * @param montant valeur en euros à payer
     */
    void recharger(float montant) {}

    /**
     * Verifie que la carte peut payer le montant
     * @param montant valeur en euros demandé
     * @return true si le fonds sont suffisants pour payer le montant
     */
    public boolean verifier_fonds(float montant) { return false;}

}
