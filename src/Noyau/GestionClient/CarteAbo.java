package Noyau.GestionClient;

public class CarteAbo extends Carte {
    /**
     *
     */

    float solde;

    @Override
    void payer(float montant) {
        solde -= montant;
        if (solde < 0) {
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
     * @param cb
     */
    void recharger(float montant, CB cb) {
        //TODO
    }

    /**
     * vire tout le solde sur une CB (! ne doit pas avoir de locations en cours)
     * @param cb
     */
    void retirer_solde(CB cb){
        //TODO
    }
}
