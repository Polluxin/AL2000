package Controleur;

import Noyau.*;
import Vue.InterfaceUtilisateur;

import java.util.List;

/**
 * Contrôleur de l'application, tout passe par ici.
 * @author Geoffrey DAVID
 * @version 1
 */
public class AL2000 {
    AL2000(){

    }
    /**
     * Initialise le logiciel à partir de certains paramètres.
     */
    private void initialisation(){

    }
    /**
     * Initialise le logiciel à partir de certains paramètres.
     * @return vrai si la location s'est bien déroulée
     */
    public boolean louerFilms(){
        return true;
    }
    /**
     * Initialise le logiciel à partir de certains paramètres.
     * @return vrai si les films ont bien été rendu
     */
    public boolean rendreFilms(){
        return true;
    }

    /**
     * Donne la liste des films, et leur disponibilité en BluRay grâce au type couple FilmFormat.
     * @param filtre le filtre utilisé
     * @return la liste des films et leur disponibilité
     */
    public List<FilmFormat> donnerCatalogue(FiltreTri filtre){
        return null;
    }

    /**
     * Tente de connecter l'abonné grâce au mot de passe (lié à la classe Compte).
     * @param mdp le mot de passe de connexion
     * @return vrai si la connexion est réussie
     */
    public boolean connexion(String mdp){
        return true;
    }

    /**
     * Déconnecte l'abonné du logciel (lié à la classe Compte).
     */
    public  void deconnexion(){

    }

    /**
     * Ajoute le film s au panier (lié à la classe Panier).
     * @param s le film à ajouter
     */
    public void ajouterPanier(Support s){

    }

    /**
     * Change le support du film lié à la location l du panier (lié à Panier).
     * @param l la location à modifier
     */
    public void changerSupportPanier(Location l){

    }

    /**
     * Donne la liste des locations présentes dans le panier.
     * @return la liste des locations
     */
    public List<Location> consulterPanier(){
        return null;
    }

    /**
     * Valide la panier courant, en ajoutant toutes les locations à l'historique client et/ou machine.
     * @return vrai si la connexion est réussie
     */

}
