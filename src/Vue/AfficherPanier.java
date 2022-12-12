package Vue;

import Controle.DonneesEvenement;
import Controle.Handler;
import Metier.GestionClient.CarteAbo;
import Metier.GestionLocation.Location;
import com.sun.jdi.BooleanType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

public class AfficherPanier extends JPanel {
    JPanel panneauBas;
    JPanel affichage;
    JPanel panneauBasGauche;
    JPanel grilleDesFilms;
    JScrollPane listeDeFilms;
    JTextField montantValeur;

    JButton validerPanier;

    InterfaceUtilisateur interfaceUtilisateur;
    float prix;
    float prixGeneral;

    public AfficherPanier(InterfaceUtilisateur interfaceUtilisateur){
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        this.interfaceUtilisateur = interfaceUtilisateur;
        validerPanier = OurTools.transparentButtonWithIcon("src/ressources/valider.png");
        affichage = new JPanel(new BorderLayout());
        affichage.setOpaque(false);

        JTextField montant = new JTextField("Montant à regler :");
        montantValeur = new JTextField("35€");
        panneauBasGauche = new JPanel(new FlowLayout());
        panneauBasGauche.setOpaque(false);
        panneauBasGauche.add(montant);
        panneauBasGauche.add(montantValeur);
        panneauBas = new JPanel(new GridLayout(0,2));
        panneauBas.add(panneauBasGauche);
        panneauBas.add(validerPanier);
        panneauBas.setBackground(OurColors.fond2());
        this.add(panneauBas, BorderLayout.SOUTH);

        grilleDesFilms = new JPanel(new GridLayout(10, 0)){
            public Dimension getPreferredSize() {
                int scrollPaneWidth = listeDeFilms.getViewport().getWidth();
                int height = 10*200;
                return new Dimension(scrollPaneWidth, height);
            }
        };
        grilleDesFilms.setOpaque(false);

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

        montantValeur.setEditable(false);
        montantValeur.setOpaque(false);
        montant.setOpaque(false);
        montant.setEditable(false);
    }

    public void activate(){
        /*
        Runnable r = new Runnable() {
            @Override
            public void run() {
                interfaceUtilisateur.getMediateur().abonner("Recuperer panier", new Handler() {
                    @Override
                    public void handle(DonneesEvenement e) {
                        build(interfaceUtilisateur.getLogiciel().consulterPanier());
                    }
                });
            }
        };
         Thread t = new Thread(r);
         t.start();
         interfaceUtilisateur.getMediateur().publier("Recuperer panier", new DonneesEvenement() {
             @Override
             public Object getDonnees() {
                 return "null";
             }
         });
         interfaceUtilisateur.getMediateur().desabonner("Recuperer panier");
         t.interrupt();*/
        build(interfaceUtilisateur.getLogiciel().consulterPanier());
    }

    private void build(List<Location> locations){
        supprimerGrille();
        prix = 0;
        if(interfaceUtilisateur.getNavBar().estConnecte){
            prixGeneral = 4;
        } else {
            prixGeneral = 5;
        }
        for (Location location : locations) {
            System.out.println(location);
            JPanel current = new JPanel(new GridLayout(3,0));
            current.setBackground(new Color(100 + (int)(Math.random() * 151), 100 + (int)(Math.random() * 151), 100 + (int)(Math.random() * 151)));
            current.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
            current.add(new JLabel(location.getSupport().getFilm().getTitre()));
            current.add(new JLabel(location.getSupport().getFilm().getRealisateur()));
            JButton suppr = OurTools.transparentButtonWithIcon("src/ressources/annuler.png");
            suppr.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    grilleDesFilms.remove(current);
                    interfaceUtilisateur.getLogiciel().supprimerPanier(location.getSupport());
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

    private void supprimerGrille(){
        while(grilleDesFilms.getComponentCount() != 0) {
            grilleDesFilms.remove(0);
        }
    }
}
