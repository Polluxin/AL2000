package Vue;

import BaseDeDonnees.Session;
import Controle.AL2000;
import Controle.Mediateur;
import Metier.GestionClient.CarteAbo;
import Metier.GestionLocation.Film;
import Metier.GestionLocation.FilmEtFormat;
import Metier.GestionLocation.FiltreTri;
import Metier.GestionLocation.Tri;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InterfaceUtilisateur {


    AL2000 logiciel;
    AfficherPanier afficherPanier;
    AjouterAuPanier ajouterAuPanier;
    BackgroundPanel fondDEcran;
    Bienvenue ecranDeBienvenue;
    CarteAbo carteAbonne;
    ConfirmerAjouterPanier confirmerAjouterPanier;
    Connexion connexion;
    Dimension tailleEcran;
    ETAT_IU etatCourant;
    Film filmActuel;
    Inscription inscription;
    InscriptionReussie inscriptionReussie;
    JFrame ecran;
    Panneau panneauCourant;
    Mediateur mediateur;
    NavigationBar navBar;
    ParametreAbonne parametreAbonne;
    ParametreTechnicien parametreTechnicien;
    PreConnexion preConnexion;
    Recharger recharger;
    RendreBluray rendrebluray;
    VoirFilms voir_films;
    boolean utilisateurConnecte;
    int nombreDelementsDansLePanier;

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
     * @param nouvelEtat
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
                case RECHARGER -> {
                    panneauCourant = recharger;
                }
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
            panneauCourant.activer();
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
        getLogiciel().deconnexion();
    }

    private void resetIU(){
        carteAbonne=null;
        utilisateurConnecte = false;
    }

    public boolean estConnecte(){
        return this.navBar.estConnecte();
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
    public AfficherPanier getAfficherPanier(){
        return this.afficherPanier;
    }

    public Boolean incrementerPanier(){
        if(nombreDelementsDansLePanier == 10){
            return false;
        } else {
            nombreDelementsDansLePanier++;
            return true;
        }
    }

    public Boolean décrementerPanier(){
        if(nombreDelementsDansLePanier == 0){
            return false;
        } else {
            nombreDelementsDansLePanier--;
            return true;
        }
    }

    public void setFilmActuel(Film f){
        this.filmActuel = f;
    }

    public Film getFilmActuel(){
        return filmActuel;
    }

    public static void main(String[] args) {
        InterfaceUtilisateur UI = new InterfaceUtilisateur();
    }
}
