package Vue;

import BaseDeDonnees.Session;
import Controle.AL2000;
import Controle.Mediateur;
import Metier.GestionClient.CarteAbo;

import javax.swing.*;
import java.awt.*;

public class InterfaceUtilisateur {

    JFrame ecran;
    boolean utilisateurConnecte;
    NavigationBar navBar;
    BackgroundPanel fondDEcran;
    Dimension tailleEcran;
    Bienvenue ecranDeBienvenue;
    Inscription inscription;
    InscriptionReussie inscriptionReussie;
    Connexion connexion;
    PreConnexion preConnexion;
    RendreBluray rendrebluray;
    VoirFilms voir_films;
    AjouterAuPanier ajouterAuPanier;
    AfficherPanier afficherPanier;
    ConfirmerAjouterPanier confirmerAjouterPanier;
    AttenteDVD attenteDVD;
    ParametreAbonne parametreAbonne;
    ParametreTechnicien parametreTechnicien;
    ETAT_IU etatCourant;
    JPanel panneauCourant;

    Mediateur mediateur;
    AL2000 logiciel;
    CarteAbo carteAbonne;

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
        fondDEcran = new BackgroundPanel();
        tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
        etatCourant = ETAT_IU.AUCUN;

        // Initialisation des panneaux
        navBar = new NavigationBar(this);
        ecranDeBienvenue = new Bienvenue(this);
        inscription = new Inscription(this);
        inscriptionReussie = new InscriptionReussie();
        connexion = new Connexion(this);
        preConnexion = new PreConnexion(this);
        rendrebluray = new RendreBluray(this);
        voir_films = new VoirFilms(this);
        attenteDVD = new AttenteDVD();
        ajouterAuPanier = new AjouterAuPanier(this);
        afficherPanier = new AfficherPanier(this);
        confirmerAjouterPanier = new ConfirmerAjouterPanier(this);
        parametreAbonne = new ParametreAbonne(this);
        parametreTechnicien = new ParametreTechnicien(this);


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
     * @param nouvelEtat
     */
    public void changerEtat(ETAT_IU nouvelEtat) {
        // enlever l'ecran precedent
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
                } case PARAMETRES_ABONNE -> {
                    panneauCourant = parametreAbonne;
                } case PARAMETRES_TECHNICIEN -> {
                    panneauCourant = parametreTechnicien;
                }
                case PRE_CONNEXION -> {
                    navBar.retourSeulement(true);
                    panneauCourant = preConnexion;
                }
                case RENDRE_DVD -> {
                    navBar.retourSeulement(true);
                    panneauCourant = rendrebluray;
                }
                case VOIR_FILMS -> panneauCourant = voir_films;
                case ATTENTE_DVD -> panneauCourant = attenteDVD;
                case AJOUTER_AU_PANIER -> panneauCourant = ajouterAuPanier;
                case AFFICHER_PANIER -> panneauCourant = afficherPanier;
                case CONFIRMER_AJOUTER_AU_PANIER -> {
                    navBar.retourSeulement(true);
                    panneauCourant = confirmerAjouterPanier;
                }
                default -> {
                    System.out.println("ERROR -- Unknown new state !");
                }
            }
            // parametrage de l'ecran courant
            ecran.add(panneauCourant);
            etatCourant = nouvelEtat;
            navBar.ajouterEtat(nouvelEtat);
            panneauCourant.setVisible(true);
            ecran.pack();
            ecran.repaint();
        }
    }

    public void connexion(){
        this.utilisateurConnecte = true;
    }


    /**
     * Reinitialise tout en cas de deconnexion pour eviter d'avoir des données de connexion qui se propagent entre les sessions
     */
    public void deconnexion(){
        resetIU();
        changerEtat(ETAT_IU.BIENVENUE);
        inscription = new Inscription(this);
        connexion = new Connexion(this);
        preConnexion = new PreConnexion(this);
        rendrebluray = new RendreBluray(this);
        voir_films = new VoirFilms(this);
        navBar.setConnecte(false);
        navBar.setAdmin(false);
        System.out.println("Déconnexion réussie !");
    }

    private void resetIU(){
        carteAbonne=null;
        utilisateurConnecte = false;
    }

    public boolean estConnecte(){
        return this.utilisateurConnecte;
    }

    public NavigationBar getNavBar(){
        return this.navBar;
    }

    public Mediateur getMediateur(){
        return this.mediateur;
    }

    public AL2000 getLogiciel(){
        return logiciel;
    }

    public CarteAbo getCarteAbonne() {
        return carteAbonne;
    }

    public void setCarteAbonne(CarteAbo ca){
        this.carteAbonne = ca;
    }

    public static void main(String[] args) {
        InterfaceUtilisateur UI = new InterfaceUtilisateur();
    }
}
