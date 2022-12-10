package Metier.GestionLocation;

import BaseDeDonnees.DAOs.LocationDAO;
import BaseDeDonnees.Session;
import Metier.GestionClient.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * API permettant de communiquer avec la base de données pour consulter/modifier
 * les historiques de locations.
 * @author Geoffrey DAVID
 * @version 0
 */
@SuppressWarnings("unused")
public class HistoLoc {

    int idMachine;

    public HistoLoc(int idMachine){
        this.idMachine = idMachine;
    }

    /**
     * Donne l'historique des locations de la machine sous forme d'une liste de locations, en consultant la base de données.
     * @return la liste des locations
     */
    public List<Location> voirHistorique(){
        Session s = new Session();
        s.open();
        List<Location> l = (new LocationDAO(s.getSession(), idMachine)).lireLocationsMachine();
        s.close();
        return l;
    }

    /**
     * Donne l'historique des locations du client a, en consultant la base de données.
     * @param a l'abonné
     * @return la liste des locations
     */
    public List<Location> voirHistoriqueClient(Client a){
        Session s = new Session();
        s.open();
        List<Location> l = (new LocationDAO(s.getSession(), idMachine)).lireLocationsCarte(a);
        s.close();
        return l;
    }
    public List<Location> voirLocationEnCours(Client a){
        List<Location> res = new ArrayList<>();
        for (Location l: voirHistoriqueClient(a)){
            if (l.etat == Etat.ENCOURS) {
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
        Session s = new Session();
        LocationDAO dao = new LocationDAO(s.getSession(), idMachine);
        s.open();
        for (Location loc : l){
            assert(dao.creer(loc));
        }
        s.commit();
        s.close();
    }

    /**
     * Met à jour la base de données : la location associée au BluRay b passe dans l'état TERMINEE.
     * @param b le BluRay rendu
     */
    public void rendreBLuRay(BluRay b){
        Session s = new Session();
        LocationDAO dao = new LocationDAO(s.getSession(), idMachine);
        s.open();
        assert(dao.modifierDepuisBluRay(b));
        s.commit();
        s.close();
    }

    public void rendreBluRay(List<BluRay> lb){
        for (BluRay br: lb) {
            rendreBLuRay(br);
        }
    }

}
