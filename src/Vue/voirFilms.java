package Vue;

import javax.swing.*;
import java.awt.*;

public class voirFilms extends JPanel {
    JPanel panneauGauche;
    JPanel rechercher;
    JPanel panneauDroite;

    JTextField barreDeRecherche;
    JButton btnRecherche;


    public voirFilms(){
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        panneauGauche = new JPanel();
        panneauGauche.setBackground(new Color(0,0,0,120));
        panneauGauche.setLayout(new BorderLayout());

        rechercher = new JPanel(new BorderLayout());
        rechercher.setBackground(ourColors.fond2());
        rechercher.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.BLACK));
        barreDeRecherche = new JTextField();
        barreDeRecherche.setOpaque(false);
        barreDeRecherche.setEditable(true);

        btnRecherche = ourTools.transparentButtonWithIcon("src/ressources/search.png");
        //System.out.println("Dimensions of textfield : "+barreDeRecherche.getPreferredSize());
        btnRecherche.setPreferredSize(new Dimension(75,75));

        rechercher.add(barreDeRecherche, BorderLayout.CENTER);
        rechercher.add(btnRecherche, BorderLayout.EAST);

        panneauGauche.add(rechercher, BorderLayout.NORTH);

        panneauDroite = new JPanel();
        panneauDroite.setBackground(ourColors.fond());
        panneauDroite.setPreferredSize(new Dimension(300, 600));

        this.add(panneauGauche, BorderLayout.CENTER);
        this.add(panneauDroite, BorderLayout.EAST);
    }

}
