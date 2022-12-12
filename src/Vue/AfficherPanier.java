package Vue;

import Controle.DonneesEvenement;
import Controle.Handler;
import Metier.Exception.FondsInsuffisants;
import Metier.GestionLocation.Location;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * JPanel contenant le contenu du panier de l'utilisateur. Permet à l'utilisateur de supprimer des éléments, et de payer le contenu de son panier
 * @author Matvei Pavlov
 */
public class AfficherPanier extends Panneau {
    float prix;
    float prixGeneral;
    InterfaceUtilisateur interfaceUtilisateur;
    JButton validerPanier;
    JPanel affichage;
    JPanel grilleDesFilms;
    JPanel panneauBas;
    JPanel panneauBasGauche;
    JScrollPane listeDeFilms;
    JTextField montantValeur;
    Runnable backgroundThread;
    Thread backgroundThreadRun;

    /**
     * Constructeur de l'affichage de panier
     * @param interfaceUtilisateur
     */
    public AfficherPanier(InterfaceUtilisateur interfaceUtilisateur){
        // Parametrage de this
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        this.interfaceUtilisateur = interfaceUtilisateur;

        // bouton de validation du panier
        validerPanier = OurTools.transparentButtonWithIcon("res/ressources/valider.png");
        initValider();

        // JPanel principal
        affichage = new JPanel(new BorderLayout());
        affichage.setOpaque(false);

        // Informations sur le solde
        JTextField montant = new JTextField("Montant à regler :");
        montantValeur = new JTextField("35€");
        montantValeur.setEditable(false);
        montantValeur.setOpaque(false);
        montant.setOpaque(false);
        montant.setEditable(false);
        panneauBasGauche = new JPanel(new FlowLayout());
        panneauBasGauche.setOpaque(false);
        panneauBasGauche.add(montant);
        panneauBasGauche.add(montantValeur);
        panneauBas = new JPanel(new GridLayout(0,2));
        panneauBas.add(panneauBasGauche);
        panneauBas.add(validerPanier);
        panneauBas.setBackground(OurColors.fond2());
        this.add(panneauBas, BorderLayout.SOUTH);

        // Grille des films contenus dans le panier, a inserer dans un scrollpane
        grilleDesFilms = new JPanel(new GridLayout(10, 0)){
            public Dimension getPreferredSize() {
                int scrollPaneWidth = listeDeFilms.getViewport().getWidth();
                int height = 10*200;
                return new Dimension(scrollPaneWidth, height);
            }
        };
        grilleDesFilms.setOpaque(false);

        // ScrollPane des films dans le panier
        listeDeFilms = new JScrollPane(grilleDesFilms, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        listeDeFilms.getVerticalScrollBar().setUnitIncrement(14); // increase scroll speed
        listeDeFilms.setOpaque(false);
        listeDeFilms.getViewport().setOpaque(false);
        JTextField votrePanierText = new JTextField("Votre Panier : ");
        votrePanierText.setEditable(false);
        votrePanierText.setOpaque(false);
        affichage.add(listeDeFilms, BorderLayout.CENTER);
        affichage.add(votrePanierText, BorderLayout.NORTH);
        this.add(affichage);
    }

    /**
     * Met à jour la page avec le contenu actuel du panier de l'utilisateur
     */
    public void activer(){
        build(interfaceUtilisateur.getLogiciel().consulterPanier());
        backgroundThread = new Runnable() {
            @Override
            public void run() {
                interfaceUtilisateur.getMediateur().abonner("Paiement", new Handler() {
                    @Override
                    public void handle(DonneesEvenement e) {
                        // Cas Carte abonné
                        if(interfaceUtilisateur.estConnecte()){
                            try {
                                interfaceUtilisateur.getLogiciel().louerFilms();
                            } catch (FondsInsuffisants ex) {
                                interfaceUtilisateur.errorDialog("Fonds insuffisant : "+prix+" > "+interfaceUtilisateur.getCarteAbonne().getSolde());
                                interfaceUtilisateur.changerEtat(ETAT_IU.RECHARGER);
                            }
                        } else {
                            interfaceUtilisateur.changerEtat(ETAT_IU.REGLER_PANIER_CB);
                        }
                    }
                });
            }
        };
        backgroundThreadRun = new Thread(backgroundThread);
        backgroundThreadRun.start();
    }

    /**
     * desactive le panneau courant, interromps les threads et se désabonne
     */
    public void desactiver(){
        backgroundThreadRun.interrupt();
        interfaceUtilisateur.getMediateur().desabonner("Paiement");

    }

    /**
     * Construit la page avec les nouvelles données de l'utilisateur
     * @param locations
     */
    private void build(List<Location> locations){
        supprimerGrille();
        prix = 0;
        if(interfaceUtilisateur.getNavBar().estConnecte){
            prixGeneral = 4;
        } else {
            prixGeneral = 5;
        }
        // Pour chaque locations dans le panier
        for (Location location : locations) {
            JPanel current = new JPanel(new GridLayout(3,0));
            current.setBackground(new Color(100 + (int)(Math.random() * 151), 100 + (int)(Math.random() * 151), 100 + (int)(Math.random() * 151))); //couleur aleatoire
            current.setBorder(BorderFactory.createEmptyBorder(40,10,40,10));
            current.add(new JLabel(location.getSupport().getFilm().getTitre()));
            current.add(new JLabel(location.getSupport().getFilm().getRealisateur()));
            JButton suppr = OurTools.transparentButtonWithIcon("res/ressources/annuler.png");
            // Suppression d'un élément de la liste et du panier si appuie sur Annuler
            suppr.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    grilleDesFilms.remove(current);
                    interfaceUtilisateur.getLogiciel().supprimerPanier(location.getSupport());
                    interfaceUtilisateur.decrementerPanier();
                    prix -= prixGeneral;
                    montantValeur.setText("" + prix);
                    repaint();
                }
            });
            prix += prixGeneral;
            current.add(suppr, BorderLayout.SOUTH);
            grilleDesFilms.add(current);
        }
        montantValeur.setText(""+prix);

    }

    /**
     * Supprimer le contenu de la grille des films
     */
    private void supprimerGrille(){
        while(grilleDesFilms.getComponentCount() != 0) {
            grilleDesFilms.remove(0);
        }
    }

    /**
     * Initialisation du vouton de validation
     */
    private void initValider(){
        validerPanier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                interfaceUtilisateur.getMediateur().publier("Paiement", new DonneesEvenement() {
                    @Override
                    public Object getDonnees() {
                        return null;
                    }
                });
            }
        });
    }
}
