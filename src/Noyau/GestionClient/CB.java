package Noyau.GestionClient;

/**
 *
 * @author Armand GRENIER
 * @version 0.0
 */
public class CB extends Carte {
    @Override
    void payer(float montant) {
        super.payer(montant);
    }

    @Override
    boolean verifier_fonds(float montant) {
        //TODO
        return false;
    }
}
