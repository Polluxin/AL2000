package Metier.GestionLocation;

import Metier.GestionClient.Abonne;
import Metier.GestionClient.Client;

import java.util.ArrayList;
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
        // TODO
        // DAO truc lire la BD
        return null;
    }

    /**
     * Donne l'historique des locations du client abonné a, en consultant la base de données.
     * @param a l'abonné
     * @return la liste des locations
     */
    public List<Location> voirHistoriqueClient(Client a){
        List<Location> res = new ArrayList<>();
        for (Location l: voirHistorique()){
            if (l.client == a) {
                res.add(l);
            }
        }
        return res;
    }
    public List<Location> voirLocationEnCours(Client a){
        List<Location> res = new ArrayList<>();
        for (Location l: voirHistorique()){
            if (l.client == a && l.etat == Etat.ENCOURS) {
                res.add(l);
            }
        }
        return res;
    }

    /**
     * Ajoute les locations l dans l'historique de locations du client abonné a, en modifiant la base de données.
     * @param l la liste des locations
     */
    public void ajouterLocations(List<Location> l){
        // TODO
        // DAO truc faire un insert dans la BD
        for (Location loc : l){
            // ajouter dans la bdd ?
        }

    }

    /**
     * Met à jour la base de données : la location associée au BluRay b passe dans l'état TERMINEE.
     * @param b le BluRay rendu
     */
    public void rendreBLuRay(BluRay b){
        // TODO
        // DAO truc: récupérer la transaction, l'Etat passe à APAYER. Police demandera un paiement.

    }

    public void rendreBluRay(List<BluRay> lb){
        for (BluRay br: lb) {
            rendreBLuRay(br);
        }
    }

}
