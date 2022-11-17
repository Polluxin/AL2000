package Noyau;

import Vue.InterfaceUtilisateur;

import java.util.List;
import java.util.prefs.Preferences;

/**
 *
 * @author Geoffrey DAVID
 * @version 0
 */
public class Catalogue {

    List<Film> liste_films;

    Inventaire inventaire;

    Preferences pref;

    FiltreTri filtre;

    Catalogue(){

    }

    /**
     * Donne la liste des films disponibles et leur support (QRCode et/ou BluRay) selon un filtre f et les
     * préférences de l'abonné a. Cela implique une consultation de la base de données.
     * @param a l'abonné
     * @param f le filtre à appliquer
     * @return la liste des films du catalogue
     */
    public List<FilmFormat> donnerFilms(Abonne a, FiltreTri f){
        return null;
    }

    /**
     * Donne les préférences de l'abonné a.
     * @param a l'abonné
     * @return les préférences
     */
    private Preferences recupererPreferences(Abonne a){
        return null;
    }

    /**.
     * En fonction du filtre et des préférences en attribut, consulte la base de données pour trouver les films
     * disponibles, et recoupe avec l'inventaire en attribut.
     * @return la liste des films disponibles
     */
    private List<FilmFormat> recupererFilmsBD(){
        return null;
    }
}
