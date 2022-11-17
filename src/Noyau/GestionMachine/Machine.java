package Noyau.GestionMachine;

import Noyau.Exception.CarteIllisible;
import Noyau.GestionClient.CB;
import Noyau.GestionClient.CarteAbo;
import Noyau.GestionLocation.FilmFormat;

import java.util.List;

/**
 *
 * @author
 * @version
 */
public class Machine implements Distributeur, Maintenance {
    Inventaire inventaire;

    Statistiques statistiques;
    Machine(Inventaire i, Statistiques s){
        inventaire = i;
        statistiques = s;
    }
    @Override
    public CB lireCB() throws CarteIllisible {
        return null;
    }

    @Override
    public Technicien lireCTechnicien() throws CarteIllisible {
        return null;
    }

    @Override
    public CarteAbo lireCarteAbo() throws CarteIllisible {
        return null;
    }

    @Override
    public boolean livrerFilms(List<FilmFormat> l) {
        return false;
    }

    @Override
    public boolean avalerBluRays(Inventaire i) {
        return false;
    }

    @Override
    public void ouvrir() {

    }

    @Override
    public void fermer() {

    }

    @Override
    public Statistiques voirStatistiques() {
        return null;
    }

    @Override
    public Inventaire donnerInventaire() {
        return null;
    }
}
