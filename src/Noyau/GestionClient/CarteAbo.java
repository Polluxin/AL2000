package Noyau.GestionClient;

import Noyau.Exception.PaiementRefuse;

/**
 *
 * @author Armand GRENIER
 * @version 0.0
 */
public class CarteAbo extends Carte {


    private float solde;

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

    /**
     *
     * @param montant
     * expliquer exception
     */
    @Override
    void payer(float montant) {
        this.solde -= montant;
        if (solde < 0) {
            //TODO
            throw new IllegalStateException("le Solde est négatif");
        }
    }

    @Override
    boolean verifier_fonds(float montant) {
        return solde >= montant;
    }

    void recharger(float montant) {
        solde += montant;
    }

}
