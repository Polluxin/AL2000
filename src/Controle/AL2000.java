package Controle;

import BaseDeDonnees.Session;
import Metier.Exception.CarteIllisible;
import Metier.Exception.ConnexionImpossible;
import Metier.Exception.FondsInsuffisants;
import Metier.Exception.MauvaisMotDePasse;
import Metier.GestionClient.*;
import Metier.GestionLocation.*;
import Metier.GestionMachine.*;

import java.util.List;

/**
 * Contrôleur de l'application, tout passe par ici.
 * @author Geoffrey DAVID
 * @author Armand GRENIER
 * @version 0
 */
@SuppressWarnings("unused")
public class AL2000 {

    private Compte compte;

    private Panier panier;

    private HistoLoc histo;

    private Catalogue catalogue;

    private Signalement signalement;

    private Distributeur machine;

    private Police police;

    private Technicien technicien;

    public AL2000(Session s) {
        initialisation(s);
    }

    /**
     * Initialise le logiciel à partir de certains paramètres.
     */
    private void initialisation(Session session) {
        session.open();
        // Initialisation de l'inventaire
        int idMachine = 1;
        Inventaire inv = new Inventaire(idMachine, session);
        inv.initialiser();

        // Initialisation des statistiques
        Statistiques statistiques = Statistiques.getInstance(idMachine, session);

        // attributs
        compte = new Compte(session);
        panier = new Panier();
        histo = new HistoLoc(idMachine);
        catalogue = new Catalogue(inv, session, idMachine);
        signalement = new Signalement();
        machine = new Machine(inv, statistiques, session);
        int delaisPolice = 300;
        police = new Police(histo, delaisPolice);
        technicien = null;
        session.close();
    }

    public void louerFilms() throws FondsInsuffisants {
        List<Support> liste_films = panier.getSupports();
        validerPanier();
        machine.livrerFilms(liste_films);
    }

    public void rendreFilms() {
    }

    /**
     * Donne la liste des films et leur disponibilité en BluRay grâce au type couple FilmEtFormat.
     *
     * @param filtre le filtre utilisé
     */
    public List<FilmEtFormat> donnerCatalogue(FiltreTri filtre) {
        return catalogue.donnerFilms(compte.getClient().getInterdits(),filtre);
    }

    /**
     * Authentifie le technicien grâce à une carte lue dans le
     * lecteur de la machine.
     * @param t le technicien à authentifier
     */

    public void connexionTechnicien(Technicien t) {
        technicien = t;
        t.setMachine((Machine) machine);
    }

    /**
     * Tente de connecter l'abonné grâce au mot de passe (lié à la classe Compte).
     *
     * @param c la carte tentant de connexion
     * @param mdp le mot de passe de connexion
     */
    public void connexion(CarteAbo c, String mdp) throws MauvaisMotDePasse {
        compte.connexion(c, mdp);
    }

    public CarteAbo simulerInsertionCA(String idCarte) throws CarteIllisible, ConnexionImpossible {
        return machine.lireCarteAbo(idCarte);
    }

    public CB simulerInsertionCB(String infosCarte) throws CarteIllisible {
        return machine.lireCB(infosCarte);
    }

    public Technicien simulerInsertionCT(String id) throws CarteIllisible, ConnexionImpossible {
        return machine.lireCTechnicien(id);
    }

    /**
     * Déconnecte l'abonné du logiciel (lié à la classe Compte).
     */
    public void deconnexion() {
        compte.deconnexion();
    }

    /**
     * Ajoute le film au panier (lié à la classe Panier).
     *
     * @param s le film à ajouter
     */
    public void ajouterPanier(Support s) {

    }

    /**
     * Supprime le film du panier (lié à la classe Panier).
     *
     * @param s le film à supprimer
     */
    public void supprimerPanier(Support s) {

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
        return panier.getLocations();
    }

    /**
     * Valide le panier courant, en ajoutant toutes les locations à l'historique client et/ou machine.
     *
     */
    public void validerPanier() throws FondsInsuffisants {
        // Vérifier les fonds
        float fondMin = panier.evaluerPrix();
        fondMin += compte.getClient().fondsReserves(histo);
        if (compte.getClient().getCarte().verifier_fonds(fondMin)) {
            histo.ajouterLocations(panier.getLocations());
            panier.viderPanier();
        } else {
            // ne peut pas payer
            throw new FondsInsuffisants();
        }

    }


    /**
     * Renvoie la liste des locations effectuées sur la machine.
     *
     * @return liste de locations
     */
    public List<Location> voirHistoMachine() {
        return histo.voirHistorique();
    }

    /**
     * Renvoie la liste des locations effectuées par un client abonné actuellement connecté.
     *
     * @return liste de locations
     */
    public List<Location> voirHistoClient() {
        return histo.voirHistoriqueClient(compte.getClient());
    }

    /**
     * Donne les statistiques de la machine (fonctionnalité du technicien).
     *
     * @return les statistiques
     */
    public Statistiques voirStatistiques() {
        return technicien.voirStatistiques();
    }

    /**
     * Ouvre la trappe physique de la machine (fonctionnalité du technicien).
     */
    public void ouvrirMachine() {
        if (technicien != null)
            technicien.ouvrirMachine();
    }

    /**
     * Ferme la trappe physique de la machine (fonctionnalité du technicien).
     */
    public void fermerMachine() {
        if (technicien != null)
            technicien.fermerMachine();
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
        System.out.println("Signalement envoyé à CyberVidéo");
    }

    /**
     * Permet de modifier les genres proscrits du compte connecté.
     * @param interdits les genres interdits du compte
     */
    public void reglerInterdits(Genre[] interdits){
        compte.getClient().reglerInterdits(interdits);
    }

}