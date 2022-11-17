package Noyau.GestionMachine;

import Noyau.Exception.CarteIllisible;
import Noyau.GestionClient.CB;
import Noyau.GestionClient.CarteAbo;
import Noyau.GestionLocation.FilmFormat;

import java.util.List;

/**
 *
 * @author Geoffrey DAVID
 * @version 0
 */
public interface Distributeur {
    /**
     * Crée un objet CB à partir d'une carte bancaire insérée dans le lecteur.
     * @return la CB créée
     */
    CB lireCB() throws CarteIllisible;

    /**
     * Crée un objet Technicien à partir d'une carte de technicien insérée dans
     * le lecteur.
     * @return l'objet Technicien créé
     */
    Technicien lireCTechnicien() throws CarteIllisible;

    /**
     * Crée un objet CarteAbo à partir d'une carte d'abonné insérée dans
     * le lecteur.
     * @return l'objet CarteAbo créé
     */
    CarteAbo lireCarteAbo() throws CarteIllisible;

    /**
     * Ouvre la trappe et livre les films BLuRay et imprime les codes des films
     * QR Code.
     * @param l la liste des films
     */
    boolean livrerFilms(List<FilmFormat> l);

    /**
     * Met à jour l'inventaire des films grâce aux films ingurgités.
     * @param i l'inventaire
     */
    boolean avalerBluRays(Inventaire i);

}
