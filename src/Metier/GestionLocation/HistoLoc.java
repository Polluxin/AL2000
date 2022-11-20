package Metier.GestionLocation;

import Metier.GestionClient.Abonne;

import java.util.List;

/**
 * API permettant de communiquer avec la base de données pour consulter/modifier
 * les historiques de locations.
 * @author Geoffrey DAVID
 * @version 0
 */
public class HistoLoc {

    HistoLoc(){

    }

    /**
     * Donne l'historique des locations de la machine sous forme d'une liste de locations, en consultant la base de données.
     * @return la liste des locations
     */
    public List<Location> voirHistorique(){
        return null;
    }

    /**
     * Donne l'historique des locations du client abonné a, en consultant la base de données.
     * @param a l'abonné
     * @return la liste des locations
     */
    public List<Location> voirHistoriqueClient(Abonne a){
        return null;
    }

    /**
     * Ajoute les locations l dans l'historique de locations du client abonné a, en modifiant la base de données.
     * @param l la liste des locations
     * @param a l'abonné
     */
    public void ajouterLocations(List<Location> l, Abonne a){

    }

    /**
     * Met à jour la base de données : la location associée au BluRay b passe dans l'état TERMINEE.
     * @param b le BluRay rendu
     */
    public void rendreBluRay(BluRay b){

    }

    public void rendreBluRay(List<BluRay> lb){
    }

    /**
     * Donne le montant à payer pour régler la location reliée au BluRay b, en consultant la base de données.
     * @param b le BluRay
     * @return le montant à débiter
     */
    public float calculerMontant(BluRay b){
        return 0;
    }

    /**
     * Vérifie si l'abonné a a une location non rendue (utilisé pour retirer le solde de son compte).
     * @param a l'abonné
     * @return vrai s'il y a des locations en cours
     */
    public boolean aLocationEnCours(Abonne a){
        return false;
    }
}
