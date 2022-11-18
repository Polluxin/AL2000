package Noyau.GestionClient;

import Noyau.Exception.PaiementRefuse;

/**
 *
 * @author Armand GRENIER
 * @version 0.0
 */
public class CarteAbo extends Carte {


    float solde;

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
        solde -= montant;
        if (solde < 0) {
            //TODO
            throw new IllegalStateException("le Solde est négatif");
        }
    }

    @Override
    boolean verifier_fonds(float montant) {
        return solde >= montant;
    }

    /**
     * Le montant est viré de la cb vers le solde de la carte abonnée
     * @param montant
     */
    void recharger(float montant) {
        solde += montant;
    }

    /**
     * vire tout le solde sur une CB (! ne doit pas avoir de locations en cours)
     * @param cb
     */
    void retirer_solde(CB cb){
        //TODO
    }
}
