package Metier.GestionClient;

import Metier.Exception.PaiementRefuse;

/**
 *
 * @author Armand GRENIER
 * @version 0.0
 */
public class CB extends Carte {

    String nom;
    String prenom;
    String informationsBancaires;

    public CB(String nom, String prenom, String informationsBancaires){
        this.nom = nom;
        this.prenom = prenom;
        this.informationsBancaires = informationsBancaires;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getInformationsBancaires() {
        return informationsBancaires;
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
        return true;
    }

}
