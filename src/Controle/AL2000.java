package Controle;

import BaseDeDonnees.Session;
import Metier.Exception.*;
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

    /**
     * Fonction utilisée lors de la finalisation de la commande. Elle valide le panier et livre
     * les films.
     * @throws FondsInsuffisants si le client n'a pas un solde suffisant sur son compte
     */
    public void louerFilms() throws FondsInsuffisants {
        List<Support> liste_films = panier.getSupports();
        validerPanier();
        machine.livrerFilms(liste_films);
    }

    /**
     * Fonction fantôme car on utilise plutôt simulerInsertionBluRay directement.
     * @param b le BluRay rendu
     */
    public void rendreFilm(BluRay b) {
        System.out.println("BluRay numéro "+b.getId()+" rendu");
        histo.rendreBLuRay(b);
    }

    /**
     * Donne la liste des films et leur disponibilité en BluRay grâce au type couple FilmEtFormat.
     * @param filtreTri le filtre utilisé
     */
    public List<FilmEtFormat> donnerCatalogue(FiltreTri filtreTri) {
        return catalogue.donnerFilms(compte.getClient().getInterdits(),filtreTri);
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
        if (compte.getClient() != null)  System.out.println("Bonjour " + ((Abonne) compte.getClient()).getPrenom() + " " + ((Abonne) compte.getClient()).getNom());
    }

    /**
     * Fonction de simulation d'insertion de carte d'abonné.
     * @param idCarte le numéro de la carte
     * @return la carte insérée
     * @throws CarteIllisible si le numéro est incorrect
     * @throws ConnexionImpossible si la connexion à la base est coupée
     */
    public CarteAbo simulerInsertionCA(String idCarte) throws CarteIllisible, ConnexionImpossible {
        return machine.lireCarteAbo(idCarte);
    }

    /**
     *  Fonction de simulation d'insertion de carte bancaire.
     * @param infosCarte les infos présentes sur la carte sous le format "5341 2154 2225 4448-04 25-Paul Fort-888-"
     * @return la carte insérée
     * @throws CarteIllisible si les infos de la carte sont incorrectes
     */
    public CB simulerInsertionCB(String infosCarte) throws CarteIllisible {
        return machine.lireCB(infosCarte);
    }

    /**
     * Fonction de simulation d'insertion de carte de technicien.
     * @param id le numéro de la carte
     * @return l'objet technicien créé à partir de la carte
     * @throws CarteIllisible s'il n'y a pas de carte avec ce numéro
     * @throws ConnexionImpossible si la connexion avec la BD est coupée
     */
    public Technicien simulerInsertionCT(String id) throws CarteIllisible, ConnexionImpossible {
        return machine.lireCTechnicien(id);
    }

    /**
     * Fonction de simulation d'insertion d'un BluRay dans le lecteur, lors d'un rendu.
     * @param id le numéro du BluRay (unique)
     * @throws BluRayInvalide si le numéro n'est pas reconnu
     */
    public void simulerInsertionBluRay(String id) throws BluRayInvalide {
         machine.avalerBluRay(id);
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
        panier.ajouter(s, compte.getClient());
    }

    /**
     * Supprime le film du panier (lié à la classe Panier).
     *
     * @param s le film à supprimer
     */
    public void supprimerPanier(Support s) {
        panier.supprimer(s);
    }

    /**
     * Change le support du film lié à la location l du panier (lié à Panier).
     *
     * @param l la location à modifier
     */
    public void changerSupportPanier(Location l) {
        l.changerSupport(machine);
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
        // TODO
        // quel gestion pour l'erreur ?
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
    public void recharger(float montant, String infosCarte){
        // TODO
        // quel comportement pour les exceptions ?
        CB cb;
        // lire la carte
        try {
            cb = machine.lireCB(infosCarte);
        } catch (CarteIllisible e) {
            throw new RuntimeException(e);
        }
        // vérifier les montants
        try {
            compte.rechargerSolde(montant, cb);
        } catch (PaiementRefuse e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Vide le compte du client courant en créditant la CB dans le lecteur (lié à la machine).
     * Si le client n'a pas de location en cours (lié à HistoLoc).
     */
    public void retirerSolde(String infosCarte){
        // TODO
        // quel comportement pour les exceptions ?
        CB cb;
        // lire la carte
        try {
            cb = machine.lireCB(infosCarte);
        } catch (CarteIllisible e) {
            throw new RuntimeException(e);
        }
        // vider le compte
        try {
            compte.retirerSolde(cb);
        } catch (PaiementRefuse e) {
            throw new RuntimeException(e);
        }
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
        //Test
    }

}