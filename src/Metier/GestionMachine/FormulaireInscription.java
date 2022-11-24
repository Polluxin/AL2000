package Metier.GestionMachine;

import Metier.GestionLocation.Genre;

import java.util.List;

/**
 * @author Geoffrey DAVID
 */
@SuppressWarnings("unused")
public class FormulaireInscription {
    private final String nom, prenom, adresse, adresseMail;
    private final List<Genre> interdits;
    FormulaireInscription(String nom, String prenom, String adresse, String adresseMail, List<Genre> interdits){
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.adresseMail =adresseMail;
        this.interdits = interdits;
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

    public List<Genre> getInterdits() {
        return interdits;
    }
}
