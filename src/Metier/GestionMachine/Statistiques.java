package Metier.GestionMachine;


import BaseDeDonnees.DAOs.StatistiquesDAO;
import BaseDeDonnees.Session;

/**
 * Objet regroupant les différentes statistiques d'une machine.
 * @author Geoffrey DAVID
 * @version 0
 */
@SuppressWarnings("unused")
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

    public static Statistiques getInstance(int idMachine, Session s){
        Statistiques st = new StatistiquesDAO(s.getSession()).lire(idMachine);
        System.out.println("Statistiques initialisées : "+st);
        return st;
    }

    public void mettreAJourBD(Session s){
        new StatistiquesDAO(s.getSession()).modifier(this);
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
        return "{ "+idMachine+" "+nbLocations + " " + nbOuvertures + " }";
    }

}
