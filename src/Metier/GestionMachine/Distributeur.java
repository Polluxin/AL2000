package Metier.GestionMachine;

import Metier.Exception.CarteIllisible;
import Metier.GestionClient.CB;
import Metier.GestionClient.CarteAbo;
import Metier.GestionLocation.BluRay;
import Metier.GestionLocation.QrCode;
import Metier.GestionLocation.Support;

import java.util.List;

/**
 * Opérations de la machine que le logiciel AL2000 a à sa disposition pour
 * distribuer/récupérer physiquement les BluRays lors des locations.
 * @author Geoffrey DAVID
 * @version 0
 */
@SuppressWarnings("unused")
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
    void livrerFilms(List<Support> l);

    void livrerBluRay(BluRay b);

    void imprimerQRCode(QrCode qr);

                        /**
     * Met à jour l'inventaire des films grâce aux films ingurgités.
     * @param i l'inventaire
     */
    void avalerBluRays(Inventaire i);

}
