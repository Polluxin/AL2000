package Noyau.GestionClient;

import Noyau.GestionLocation.Genre;
/**
 *
 * @author Armand GRENIER
 * @version 0.0
 */
public class Anonyme extends Client{
    /**
     *
     * @param interdits
     * @param carte
     *
     * La carte doit être une carte banquaire et la liste interdits doit être vide.
     */

    public Anonyme(Genre[] interdits, Carte carte) {
        super(interdits, carte);
    }


}

