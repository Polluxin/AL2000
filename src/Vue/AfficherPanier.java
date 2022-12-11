package Vue;

import com.sun.jdi.BooleanType;

import javax.swing.*;
import java.awt.*;

public class AfficherPanier extends JPanel {
    JPanel panneauBas;
    JPanel affichage;
    JPanel panneauBasGauche;
    JPanel grilleDesFilms;
    JScrollPane listeDeFilms;

    JButton validerPanier;

    public AfficherPanier(InterfaceUtilisateur interfaceUtilisateur){
        this.setLayout(new BorderLayout());
        this.setOpaque(false);

        validerPanier = OurTools.transparentButtonWithIcon("src/ressources/valider.png");
        affichage = new JPanel(new BorderLayout());
        affichage.setOpaque(false);

        JTextField montant = new JTextField("Montant à regler :");
        JTextField montantValeur = new JTextField("35€");
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
            @Override
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
}
