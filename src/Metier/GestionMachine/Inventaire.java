package Metier.GestionMachine;

import Metier.GestionLocation.BluRay;

import java.util.ArrayList;
import java.util.List;

// TODO: Implémenter avec BluRayDAO et Session

@SuppressWarnings("unused")
public class Inventaire {
    private List<BluRay> liste_BluRays;

    public Inventaire(){
        liste_BluRays = new ArrayList<>();
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
