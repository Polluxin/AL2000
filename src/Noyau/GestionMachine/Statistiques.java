package Noyau.GestionMachine;

public class Statistiques {

    int nbLocations = 0;
    int nbOuvertures = 0;

    Statistiques(){

    }

    public void ajouterLocation() {
        nbLocations++;
    }

    public void ajouterOuverture() {
        nbOuvertures++;
    }

    public int getNbLocations() {
        return nbLocations;
    }

    public int getNbOuvertures() {
        return nbOuvertures;
    }

}
