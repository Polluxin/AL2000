package Metier.GestionClient;

import Metier.Exception.PaiementRefuse;

/**
 *
 * @author Armand GRENIER
 * @version 0.0
 */
public class CB extends Carte {

    String informationsBancaires;

    public CB(String informationsBancaires){
        this.informationsBancaires = informationsBancaires;
    }

    @Override
    void payer(float montant) throws PaiementRefuse {
        // TODO
        throw new PaiementRefuse();
    }

    @Override
    void recharger(float montant) throws PaiementRefuse {
        // TODO
        throw new PaiementRefuse();
    }

    @Override
    public boolean verifier_fonds(float montant) {
        // TODO
        return false;
    }
}
