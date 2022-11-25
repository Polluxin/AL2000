package Metier.GestionMachine;

import Metier.GestionLocation.Genre;

/**
 * @author Geoffrey DAVID
 */
@SuppressWarnings("unused")
public class FormulaireInscription {
    private final String nom, prenom, adresse, adresseMail, mdp;
    private final Genre[] interdits;
    FormulaireInscription(String nom, String prenom, String adresse, String adresseMail, Genre[] interdits, String mdp){
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.adresseMail =adresseMail;
        this.interdits = interdits;
        this.mdp = mdp;
    }

    public String getMdp() {
        return mdp;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public String getAdresse() {
        return adresse;
    }

    public Genre[] getInterdits() {
        return interdits;
    }
}
