package Controle;

import BaseDeDonnees.Session;
import Metier.Exception.MauvaisMotDePasse;
import Metier.GestionClient.*;
import Metier.GestionLocation.*;
import Metier.GestionMachine.*;

import java.util.List;
import java.util.prefs.Preferences;

/**
 * Contrôleur de l'application, tout passe par ici.
 * @author Geoffrey DAVID
 * @version 0
 */
@SuppressWarnings("unused")
public class AL2000 {

    private final int idMachine = 1;
    private final int delaisPolice = 300;

    private Compte compte;

    private Panier panier;

    private HistoLoc histo;

    private Catalogue catalogue;

    private Signalement signalement;

    private Distributeur machine;

    private Police police;

    private final Session session;

    public AL2000() {
        session = new Session();
    }

    /**
     * Initialise le logiciel à partir de certains paramètres.
     */
    private void initialisation(Session session) {
        // dans le constucteur de Inventair passer session ?
        // il faudrait pouvoir initialiser l'inventaire un fonction de la bd et idMachine ?
        Inventaire inv = new Inventaire(idMachine);

        // lire les stats de puis la bd ? ou quelle données mettres ?
        Statistiques stat = new Statistiques(idMachine, "");

        // attributs
        compte = new Compte(session);
        panier = new Panier();
        histo = new HistoLoc(idMachine);
        catalogue = new Catalogue(inv, session, idMachine);
        signalement = new Signalement();
        machine = new Machine(inv, stat, session);
        police = new Police(histo, delaisPolice);
    }

    /**
     * Initialise le logiciel à partir de certains paramètres.
     *
     */
    public void louerFilms() {
    }

    /**
     * Initialise le logiciel à partir de certains paramètres.
     *
     */
    public void rendreFilms() {
    }

    /**
     * Donne la liste des films et leur disponibilité en BluRay grâce au type couple FilmFormat.
     *
     * @param filtre le filtre utilisé
     */
    public void donnerCatalogue(FiltreTri filtre) {

    }

    /**
     * Tente de connecter l'abonné grâce au mot de passe (lié à la classe Compte).
     *
     * @param mdp le mot de passe de connexion
     */
    public void connexion(String mdp) {
        // lecture de carte
        // TODO
        CarteAbo c = null;
        // connexion
        try {
            compte.connexion(c, mdp);
        } catch (MauvaisMotDePasse e) {
            // mauvais mdp
            // TODO
        }
    }

    /**
     * Authentifie le technicien grâce à une carte lue dans le
     * lecteur de la machine.
     */

    public void connexionTechnicien() {
    }

    /**
     * Déconnecte l'abonné du logciel (lié à la classe Compte).
     */
    public void deconnexion() {

    }

    /**
     * Ajoute le film au panier (lié à la classe Panier).
     *
     * @param s le film à ajouter
     */
    public void ajouterPanier(Support s) {

    }

    /**
     * Change le support du film lié à la location l du panier (lié à Panier).
     *
     * @param l la location à modifier
     */
    public void changerSupportPanier(Location l) {

    }

    /**
     * Donne la liste des locations présentes dans le panier.
     *
     * @return la liste des locations
     */
    public List<Location> consulterPanier() {
        return null;
    }

    /**
     * Valide le panier courant, en ajoutant toutes les locations à l'historique client et/ou machine.
     *
     */
    public void validerPanier() {
        // Vérifier les fonds
        float fondMin = panier.evaluerPrix();
        fondMin += compte.getClient().fondsReserves(histo);
        if (compte.getClient().getCarte().verifier_fonds(fondMin)) {
            // ajouter le panier dans histoLoc
            panier.viderPanier();
        } else {
            // ne peut pas payer
            // Renvoyer une exception ?
        }

    }


    /**
     * Renvoie la liste des locations effectuées sur la machine.
     *
     * @return liste de locations
     */
    public List<Location> voirHistoMachine() {
        return null;
    }

    /**
     * Renvoie la liste des locations effectuées par un client abonné actuellement connecté.
     *
     * @return liste de locations
     */
    public List<Location> voirHistoClient() {
        return null;
    }

    /**
     * Donne les statistiques de la machine (fonctionnalité du technicien).
     *
     * @return les statistiques
     */
    public Statistiques voirStatistiques() {
        return null;
    }

    /**
     * Ouvre la trappe physique de la machine (fonctionnalité du technicien).
     */
    public void ouvrirMachine() {

    }

    /**
     * Ferme la trappe physique de la machine (fonctionnalité du technicien).
     */
    public void fermerMachine() {

    }

    /**
     * Crédite le compte du client courant en lisant une CB dans le lecteur (lié à la machine).
     * @param montant la somme à créditer
     */
    public void recharger(float montant){
    }

    /**
     * Vide le compte du client courant en créditant la CB dans le lecteur (lié à la machine).
     * Si le client n'a pas de location en cours (lié à HistoLoc).
     */
    public void retirerSolde(){
    }

    /**
     * Envoie à CyberVidéo un formulaire décrivant un bug, une réclamation, etc...
     * @param f le formulaire de signalement
     */
    public void signalerProbleme(FormulaireSignalement f){

    }

    /**
     * Permet de modifier les préférences du compte connecté.
     * @param p les préférences du compte
     */
    public void reglerPreferences(Preferences p){

    }

}