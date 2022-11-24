package Metier.GestionMachine;

@SuppressWarnings("unused")
public class FormulaireSignalement {

    private final String nom, prenom, adresseMail, description;

    FormulaireSignalement(String nom, String prenom, String adresseMail, String description){
        this.nom = nom;
        this.prenom = prenom;
        this.description = description;
        this.adresseMail =adresseMail;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public String getDescription() {
        return description;
    }
}
