package Noyau.GestionClient;

import Noyau.GestionLocation.Genre;


public class Abonne extends Client {
    /**
     * la carte d'un Abonne est une CarteAbo.
     * En plus des methodes d'un client, il peut recharger et retirer le solde
     * de sa carte.
     */
    CarteAbo carte;

    String nom, prenom, addresseMail, adressePostale;
    public Abonne(Genre[] interdits, Carte carte) {
        super(interdits, carte);
    }

    /**
     * newInterdits devient la liste des interdits.
     * @param newInterdits
     */
    void reglerInterdits(Genre[] newInterdits){
        this.interdits = newInterdits;
    }

    void recharger(float montant, CB cb){
        carte.recharger(montant, cb);
    }

    void retirer_solde(CB cb){
        carte.retirer_solde(cb);
    }
}
