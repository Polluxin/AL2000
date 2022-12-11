package Metier.GestionLocation;

import Metier.GestionMachine.Distributeur;

public class FabriqueSupport {
    Distributeur dist;

    public FabriqueSupport(Distributeur d) {
        this.dist = d;
    }


    public Support Bluray(Film film){
        return dist.chercherBluRay(film);
    }

    public Support QrCode(Film film){
        return new QrCode(-1, film);
    }


}
