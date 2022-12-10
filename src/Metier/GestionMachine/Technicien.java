package Metier.GestionMachine;

import Metier.GestionLocation.HistoLoc;
import Metier.GestionLocation.Location;

import java.util.List;

/**
 * Objet regroupant les différentes informations concernant un technicien.
 * @author Geoffrey DAVID
 */
@SuppressWarnings("unused")
public class Technicien {

    private final int id;

    private final String nom;
    private final String prenom;

    private HistoLoc histoLoc;

    private Maintenance machine;

    public Technicien(int id, String nom, String prenom){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public void setHistoLoc(HistoLoc histoLoc) {
        this.histoLoc = histoLoc;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public List<Location> voirHistorique(){
        assert(histoLoc != null);
        return histoLoc.voirHistorique();
    }

    public void ouvrirMachine(){
        machine.ouvrir();
    }

    public void fermerMachine(){
        machine.fermer();
    }

    public Statistiques voirStatistiques(){
        return machine.voirStatistiques();
    }

    public Inventaire donnerInventaire(){
        return machine.donnerInventaire();
    }

    @Override
    public String toString(){
        return "{ "+ id + " " + nom + " "+ prenom + " }";
    }

}
