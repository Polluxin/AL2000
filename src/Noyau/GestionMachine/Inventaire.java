package Noyau.GestionMachine;

import Noyau.GestionLocation.BluRay;

import java.util.List;

public class Inventaire {
    private List<BluRay> liste_BluRays = null;

    Inventaire(){

    }

    public void ajouterBlueRays(BluRay b){
        liste_BluRays.add(b);
    }

    public void ajouterBlueRays(List<BluRay> b){
        liste_BluRays.addAll(b);
    }

    public void supprimerBluRay(BluRay b){
        liste_BluRays.remove(b);
    }

    public List<BluRay> getListeBluRays(){
        return liste_BluRays;
    }
}
