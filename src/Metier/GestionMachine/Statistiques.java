package Metier.GestionMachine;


/**
 * Objet regroupant les différentes statistiques d'une machine.
 * @author Geoffrey DAVID
 * @version 0
 */
public class Statistiques {

    int nbLocations;
    int nbOuvertures;
    int idMachine;

    public Statistiques(int idMachine, String donnees){
        assert(donnees != null);
        this.idMachine = idMachine;
        // donnees sous le format "nbLocation-nbOuvertures"
        String [] d = donnees.split("-");
        nbLocations = Integer.parseInt(d[0]);
        nbOuvertures = Integer.parseInt(d[1]);
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

    public String getDonnees(){
        return nbLocations +"-"+ nbOuvertures;
    }

    public int getId(){ return idMachine;}

    @Override
    public String toString(){
        return "Machine numéro " + idMachine +
                "\n-> " + nbLocations + " locations" +
                "\n-> " + nbOuvertures + " ouvertures";
    }

}
