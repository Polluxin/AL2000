package Vue;

import javax.swing.*;
import java.awt.*;

import static java.awt.Font.createFont;


public class InterfaceUtilisateur {

    JFrame ecran;
    boolean utilisateurConnecte;
    NavigationBar navBar;
    backgroundPanel fondDEcran;
    Dimension tailleEcran;
    Bienvenue ecranDeBienvenue;
    Inscription inscription;
    InscriptionReussie inscriptionReussie;
    Connexion connexion;
    rendreBluray rendrebluray;
    VoirFilms voir_films;
    AttenteDVD attenteDVD;
    ETAT_IU etatCourant;
    JPanel panneauCourant;


    public InterfaceUtilisateur(){
        ourTools.setFont();

        // Initialisations
        ecran = new JFrame();
        navBar = new NavigationBar(this);
        fondDEcran = new backgroundPanel();
        tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
        etatCourant = ETAT_IU.AUCUN;

        ecranDeBienvenue = new Bienvenue(this);
        inscription = new Inscription();
        inscriptionReussie = new InscriptionReussie();
        connexion = new Connexion();
        rendrebluray = new rendreBluray(navBar);
        voir_films = new VoirFilms();
        attenteDVD = new AttenteDVD();

        inscription.setVisible(true);
        inscriptionReussie.setVisible(true);

        panneauCourant = ecranDeBienvenue;
        navBar.ajouterEtat(ETAT_IU.BIENVENUE);
        navBar.cacher();

        // Taille de l'ecran de l'utilisateur :
        double LARGEUR = tailleEcran.getWidth()/3;
        double HAUTEUR = tailleEcran.getHeight()/3;

        // Parametrage
        ecran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ecran.setMinimumSize(new Dimension(1300, 1000));
        ecran.setPreferredSize(new Dimension((int)LARGEUR,(int)HAUTEUR));
        ecran.setContentPane(fondDEcran);
        ecran.setResizable(true);

        ecran.setLayout(new BorderLayout());
        ecran.add(navBar, BorderLayout.NORTH);

        ecran.add(panneauCourant);

        //changerEtat(ETAT_IU.ATTENTE_DVD);

        ecran.pack();
        ecran.setVisible(true);
        ecran.setLocationRelativeTo(null);
    }

    public void changerEtat(ETAT_IU nouvelEtat) {
        ecran.remove(panneauCourant);
        navBar.reset();
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
                case INSCRIPTION_VALIDE -> {
                    navBar.retourSeulement(true);
                    panneauCourant = inscriptionReussie;
                }
                case CONNEXION -> {
                    navBar.retourSeulement(true);
                    panneauCourant = connexion;
                }
                case RENDRE_DVD -> {
                    navBar.retourSeulement(true);
                    panneauCourant = rendrebluray;
                }
                case VOIR_FILMS -> {
                    panneauCourant = voir_films;
                }
                case ATTENTE_DVD -> {
                    panneauCourant = attenteDVD;
                }
                default -> {
                    System.out.println("ERROR -- Unknown new state !");
                }
            }
            ecran.add(panneauCourant);
            etatCourant = nouvelEtat;
            navBar.ajouterEtat(nouvelEtat);
            ecran.pack();
            ecran.repaint();
        }
    }

    public void connexion(){
        this.utilisateurConnecte = true;
    }

    public void deconnexion(){
        this.utilisateurConnecte = false;
    }

    public boolean estConnecte(){
        return this.utilisateurConnecte;
    }


    public static void main(String[] args) {
        InterfaceUtilisateur UI = new InterfaceUtilisateur();
    }
}
