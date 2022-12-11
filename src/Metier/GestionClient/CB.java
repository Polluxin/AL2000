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

    // infos sous le format "5341 2154 2225 4448-04 25-Paul Fort-888-"
    public CB(String infos){
        String[] parsed = infos.split("-");
        informationsBancaires = infos;
        String[] np = parsed[2].split(" ");
        nom = np[1];
        prenom = np[0];
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

    }

    @Override
    void recharger(float montant) throws PaiementRefuse {

    }

    @Override
    public boolean verifier_fonds(float montant) {
        return true;
    }

}
