package Noyau.GestionClient;

/**
 *
 * @author Armand GRENIER
 * @version 0.0
 */
public abstract class Carte {
    /**
     * paye le montant avec la carte
     * @param montant
     */
    void payer(float montant) {}

    /**
     * verifie que la carte peut payer le montant
     * @param montant
     * @return
     */
    boolean verifier_fonds(float montant) { return false;}

}
