package Noyau.GestionClient;

import Noyau.Exception.PaiementRefuse;
import Noyau.GestionLocation.Genre;

/**
 *
 * @author Armand GRENIER
 * @version 0.0
 */
public class Abonne extends Client {

    CarteAbo carte;

    String nom, prenom, addresseMail, adressePostale, motDePasse;

    //<editor-fold desc="Constructor">
    public Abonne(Genre[] interdits, Carte carte, CarteAbo carte1, String nom, String prenom, String addresseMail, String adressePostale, String motDePasse) {
        super(interdits, carte);
        this.carte = carte1;
        this.nom = nom;
        this.prenom = prenom;
        this.addresseMail = addresseMail;
        this.adressePostale = adressePostale;
        this.motDePasse = motDePasse;
    }
    //</editor-fold>

    //<editor-fold desc="getter/setter">
    public CarteAbo getCarte() {
        return carte;
    }

    public void setCarte(CarteAbo carte) {
        this.carte = carte;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAddresseMail() {
        return addresseMail;
    }

    public void setAddresseMail(String addresseMail) {
        this.addresseMail = addresseMail;
    }

    public String getAdressePostale() {
        return adressePostale;
    }

    public void setAdressePostale(String adressePostale) {
        this.adressePostale = adressePostale;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    //</editor-fold>



}
