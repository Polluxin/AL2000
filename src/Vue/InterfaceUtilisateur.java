package Vue;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static java.awt.Font.createFont;


public class InterfaceUtilisateur {

    JFrame ecran;
    NavigationBar navBar;
    backgroundPanel fondDEcran;
    Dimension tailleEcran;
    Bienvenue ecranDeBienvenue;
    Inscription inscription;
    InscriptionReussie inscriptionReussie;
    ETAT_IU etatCourant;
    JPanel panneauCourant;

    JPanel panneau_inscription = new JPanel();

    public InterfaceUtilisateur(){
        ourTools.setFont();

        // Initialisations
        ecran = new JFrame();
        navBar = new NavigationBar(this);
        fondDEcran = new backgroundPanel();
        tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
        etatCourant = ETAT_IU.AUCUN;

        ecranDeBienvenue = new Bienvenue();
        inscription = new Inscription();
        inscriptionReussie = new InscriptionReussie();

        inscription.setVisible(true);
        inscriptionReussie.setVisible(true);

        panneauCourant = ecranDeBienvenue;
        navBar.ajouterEtat(ETAT_IU.BIENVENUE);

        // Taille de l'ecran de l'utilisateur :
        double LARGEUR = tailleEcran.getWidth()/3;
        double HAUTEUR = tailleEcran.getHeight()/3;

        // Parametrage
        ecran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ecran.setMinimumSize(new Dimension(900, 500));
        ecran.setPreferredSize(new Dimension((int)LARGEUR,(int)HAUTEUR));
        ecran.setContentPane(fondDEcran);
        ecran.setResizable(true);

        ecran.setLayout(new BorderLayout());
        ecran.add(navBar, BorderLayout.NORTH);

        ecran.add(panneauCourant);

        changerEtat(ETAT_IU.INSCRIPTION_VALIDE);

        ecran.pack();
        ecran.setVisible(true);
        ecran.setLocationRelativeTo(null);
    }

    public void changerEtat(ETAT_IU nouvelEtat) {
        ecran.remove(panneauCourant);
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
            }
            ecran.add(panneauCourant);
            etatCourant = nouvelEtat;
            navBar.ajouterEtat(nouvelEtat);
            ecran.pack();
        }
    }


    public static void main(String[] args) {
        InterfaceUtilisateur UI = new InterfaceUtilisateur();
    }
}
