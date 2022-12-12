package Vue;

import BaseDeDonnees.Session;
import Controle.AL2000;
import Controle.Mediateur;
import Metier.GestionClient.CarteAbo;
import Metier.GestionLocation.FilmEtFormat;

import javax.swing.*;
import java.awt.*;

public class InterfaceUtilisateur {
    private final AL2000 logiciel;
    private final AfficherPanier afficherPanier;
    private final AjouterAuPanier ajouterAuPanier;
    private final Bienvenue ecranDeBienvenue;
    private CarteAbo carteAbonne;
    private final ConfirmerAjouterPanier confirmerAjouterPanier;
    private Connexion connexion;
    private ETAT_IU etatCourant;
    private FilmEtFormat filmActuel;
    private Inscription inscription;
    private final InscriptionReussie inscriptionReussie;
    private final JFrame ecran;
    private Panneau panneauCourant;
    private final Mediateur mediateur;
    private final NavigationBar navBar;
    private final ParametreAbonne parametreAbonne;
    private final ParametreTechnicien parametreTechnicien;
    private PreConnexion preConnexion;
    private final Recharger recharger;
    private RendreBluray rendrebluray;
    private final ReglerPanierCB reglerPanierCB;
    private VoirFilms voir_films;
    private int nombreDelementsDansLePanier;

    /**
     * Creation de l'interface utilisateur et initialisation des panneaux
     */
    public InterfaceUtilisateur(){
        OurTools.setFont();
        // Controle
        mediateur = new Mediateur();
        logiciel = new AL2000(new Session());
        mediateur.setLogiciel(logiciel);
        // Initialisations des variables
        ecran = new JFrame();
        BackgroundPanel fondDEcran = new BackgroundPanel();
        etatCourant = ETAT_IU.AUCUN;

        // Initialisation des panneaux
        afficherPanier = new AfficherPanier(this);
        ajouterAuPanier = new AjouterAuPanier(this);
        confirmerAjouterPanier = new ConfirmerAjouterPanier(this);
        connexion = new Connexion(this);
        ecranDeBienvenue = new Bienvenue(this);
        inscription = new Inscription(this);
        inscriptionReussie = new InscriptionReussie();
        navBar = new NavigationBar(this);
        parametreAbonne = new ParametreAbonne(this);
        parametreTechnicien = new ParametreTechnicien(this);
        preConnexion = new PreConnexion(this);
        recharger = new Recharger(this);
        reglerPanierCB = new ReglerPanierCB(this);
        rendrebluray = new RendreBluray(this);
        voir_films = new VoirFilms(this);

        // panneau de départ
        panneauCourant = ecranDeBienvenue;
        navBar.ajouterEtat(ETAT_IU.BIENVENUE);


        // Parametrage du contentPane et de l'ecran principal
        ecran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ecran.setMinimumSize(new Dimension(1300, 1000)); //Taille minimales pour le bon fonctionnement des differents elements visuels
        ecran.setPreferredSize(new Dimension(1300,1000));
        ecran.setContentPane(fondDEcran);
        ecran.setResizable(true);

        ecran.setLayout(new BorderLayout());
        ecran.add(navBar, BorderLayout.NORTH);

        // ajout du panneau de départ (bienvenue)
        ecran.add(panneauCourant);

        // afficher
        ecran.pack();
        ecran.setVisible(true);
        ecran.setLocationRelativeTo(null);
    }

    /**
     * Changer l'état courant et donc le panneau affiché. Modifie egalement la barre de navigation en fonction des cas.
     * @param nouvelEtat etat suivant
     */
    public void changerEtat(ETAT_IU nouvelEtat) {
        // enlever l'ecran precedent
        panneauCourant.desactiver();
        ecran.remove(panneauCourant);
        navBar.reset();
        // modifier l'etat courant
        if (nouvelEtat != etatCourant) {
            switch (nouvelEtat) {
                case BIENVENUE -> {
                    navBar.cacher();
                    panneauCourant = ecranDeBienvenue;
                }
                case INSCRIPTION -> {
                    navBar.retourSeulement(true);
                    panneauCourant = inscription;
                }
                case INSCRIPTION_REUSSIE -> {
                    navBar.retourSeulement(true);
                    panneauCourant = inscriptionReussie;
                }
                case CONNEXION -> {
                    navBar.retourSeulement(true);
                    panneauCourant = connexion;
                } case PARAMETRES_ABONNE -> panneauCourant = parametreAbonne;
                case PARAMETRES_TECHNICIEN -> panneauCourant = parametreTechnicien;
                case PRE_CONNEXION -> {
                    navBar.retourSeulement(true);
                    panneauCourant = preConnexion;
                }
                case RENDRE_DVD -> {
                    navBar.retourSeulement(true);
                    panneauCourant = rendrebluray;
                }
                case VOIR_FILMS -> panneauCourant = voir_films;
                case RECHARGER -> panneauCourant = recharger;
                case REGLER_PANIER_CB -> panneauCourant = reglerPanierCB;
                case AJOUTER_AU_PANIER -> panneauCourant = ajouterAuPanier;
                case AFFICHER_PANIER -> panneauCourant = afficherPanier;
                case CONFIRMER_AJOUTER_AU_PANIER -> {
                    navBar.retourSeulement(true);
                    panneauCourant = confirmerAjouterPanier;
                }
                default -> System.out.println("ERROR -- Unknown new state !");
            }
            // parametrage de l'ecran courant
            panneauCourant.activer();
            ecran.add(panneauCourant);
            etatCourant = nouvelEtat;
            navBar.ajouterEtat(nouvelEtat);
            panneauCourant.setVisible(true);
            ecran.pack();
            ecran.repaint();
        }
    }


    /**
     * Reinitialise tout en cas de deconnexion pour eviter d'avoir des données de connexion qui se propagent entre les sessions
     */
    public void deconnexion(){
        carteAbonne=null;
        changerEtat(ETAT_IU.BIENVENUE);
        inscription = new Inscription(this);
        connexion = new Connexion(this);
        preConnexion = new PreConnexion(this);
        rendrebluray = new RendreBluray(this);
        voir_films = new VoirFilms(this);
        navBar.setConnecte(false);
        navBar.setAdmin(false);
        System.out.println("Déconnexion réussie !");
        getLogiciel().deconnexion();
    }

    /**
     * Indique si un abonné est connecté
     * @return True si un abonné est connecté, false sinon
     */
    public boolean estConnecte(){
        return this.navBar.estConnecte();
    }

    /**
     *
     * @return La barre de Navigation
     */
    public NavigationBar getNavBar(){
        return this.navBar;
    }

    /**
     *
     * @return le mediateur
     */
    public Mediateur getMediateur(){
        return this.mediateur;
    }

    /**
     *
     * @return le logiciel
     */
    public AL2000 getLogiciel(){
        return logiciel;
    }

    /**
     *
     * @return La CarteAbo du compte actif
     */
    public CarteAbo getCarteAbonne() {
        return carteAbonne;
    }

    /**
     * Definit la CarteAbo du compte actif
     * @param ca la nouvelle CarteAbo
     */
    public void setCarteAbonne(CarteAbo ca){
        this.carteAbonne = ca;
    }

    /**
     * Incrémente le panier et renvoie vrai si l'incrémentation à eu lieu, pour limiter le panier à 10 films
     * @return vrai si le panier a pu être incrémenté
     */
    public Boolean incrementerPanier(){
        if(nombreDelementsDansLePanier == 10){
            return false;
        } else {
            nombreDelementsDansLePanier++;
            return true;
        }
    }

    /**
     * Décremente le panier pour connaitre le nombre d'éléments actuellement
     */
    public void decrementerPanier(){
        if(nombreDelementsDansLePanier != 0){
            nombreDelementsDansLePanier--;
        }
    }

    public void setFilmActuel(FilmEtFormat f){
        this.filmActuel = f;
    }

    public FilmEtFormat getFilmActuel(){
        return filmActuel;
    }

    public void errorDialog(String text){
        JOptionPane.showMessageDialog(ecran,
                text,
                "ERREUR",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        InterfaceUtilisateur UI = new InterfaceUtilisateur();
    }
}
