package Metier.GestionLocation;

import Metier.GestionClient.Client;

import java.sql.Date;

/**
 *
 * @author Armand GRENIER
 * @version 0.0
 */
public class Panier {

    Location[] locations;
    int nextLocInd = 0;

    /**
     * Ajoute une nouvelle location dans la liste des locations. La nouvelle
     * location est créée à partir de la date du jour et des paramètres.
     * @param s un support
     * @param c un Client
     */
    public void ajouter(Support s, Client c){
        Date sqlDate = new Date(System.currentTimeMillis());
        Location loc = new Location(sqlDate, Etat.ENCOURS, s, c);
        if (nextLocInd >= locations.length) {
            // agrandir la liste de location
            Location[] newLocation = new Location[locations.length*2];
            System.arraycopy(locations, 0, newLocation, 0, locations.length);
            locations = newLocation;
        }
        locations[nextLocInd++] = loc;
    }

    public void supprimer(Location l) {
        int i = 0;
        // chercher loc
        while (i < locations.length){
            if (locations[i] == l) break;
            i++;
        }
        if  (i == locations.length) return;
        // retirer l de la liste

    }


}
