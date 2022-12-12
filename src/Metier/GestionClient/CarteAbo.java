package Metier.GestionClient;

import Metier.Exception.PaiementRefuse;

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

    public CarteAbo(int id, float solde){
        this.id = id;
        this.solde = solde;
    }

    /**
     *
     * @param montant
     * expliquer exception
     */
    @Override
    void payer(float montant) throws PaiementRefuse{
        if (solde - montant < 0) {
            throw new PaiementRefuse();
        }
        this.solde -= montant;
    }

    @Override
    public boolean verifier_fonds(float montant) {
        return solde >= montant;
    }

    void recharger(float montant) {
        solde += montant;
    }

}
