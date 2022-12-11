package Vue;

import Metier.GestionLocation.Film;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VoirFilms extends JPanel {
    JPanel panneauGauche;
    JPanel rechercher;
    JPanel panneauDroite;

    JScrollPane listeDeFilms;
    JPanel grilleDesFilms;

    JTextField barreDeRecherche;
    JButton btnRecherche;
    JButton[] tousLesFilmsBoutons;
    Film[] tousLesFilms;
    InterfaceUtilisateur iu;


    public VoirFilms(InterfaceUtilisateur iu){
        this.iu = iu;
        int nombreFilms = 30;
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        panneauGauche = new JPanel();
        panneauGauche.setBackground(new Color(0,0,0, 0));
        panneauGauche.setLayout(new BorderLayout());

        rechercher = new JPanel(new BorderLayout());
        rechercher.setBackground(OurColors.fond2());
        rechercher.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.BLACK));
        barreDeRecherche = new JTextField();
        barreDeRecherche.setOpaque(false);
        barreDeRecherche.setEditable(true);

        btnRecherche = OurTools.transparentButtonWithIcon("src/ressources/search.png");
        //System.out.println("Dimensions of textfield : "+barreDeRecherche.getPreferredSize());
        btnRecherche.setPreferredSize(new Dimension(75,75));

        rechercher.add(barreDeRecherche, BorderLayout.CENTER);
        rechercher.add(btnRecherche, BorderLayout.EAST);

        grilleDesFilms = new JPanel(new GridLayout(0, 3)){
            @Override
            public Dimension getPreferredSize() {
                int scrollPaneWidth = listeDeFilms.getViewport().getWidth();
                int height = 10*200;
                return new Dimension(scrollPaneWidth, height);
            }
        };

        grilleDesFilms.setOpaque(false);

        tousLesFilmsBoutons = new JButton[nombreFilms];
        tousLesFilms = new Film[nombreFilms];
        initGrid();
        listeDeFilms = new JScrollPane(grilleDesFilms, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        listeDeFilms.getVerticalScrollBar().setUnitIncrement(14); // increase scroll speed
        listeDeFilms.setOpaque(false);
        listeDeFilms.getViewport().setBackground(OurColors.fond2());


        panneauGauche.add(rechercher, BorderLayout.NORTH);
        panneauGauche.add(listeDeFilms);

        panneauDroite = new JPanel();
        panneauDroite.setBackground(OurColors.fond());
        panneauDroite.setPreferredSize(new Dimension(300, 600));

        this.add(panneauGauche, BorderLayout.CENTER);
        this.add(panneauDroite, BorderLayout.EAST);
    }

    private void initGrid(){
        for(int i=0; i<18; i++){
            JButton b1 = OurTools.transparentButtonWithIcon("src/ressources/ajouterPanier.png");
            b1.setMinimumSize(new Dimension(300,60));
            b1.setPreferredSize(new Dimension(300,60));
            //b1.setOpaque(false);
            tousLesFilmsBoutons[i] = b1;
            tousLesFilms[i] = new Film();

            b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(iu.getNavBar().estConnecte()){
                        iu.changerEtat(ETAT_IU.AJOUTER_AU_PANIER);
                    } else {
                        System.out.println("Aucun abonné connecté !");
                    }
                }
            });

            b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });

            StretchIcon imageFilm = OurPictures.getPicture("src/ressources/couverture.png");
            JLabel jl1 = new JLabel(imageFilm);
            jl1.setPreferredSize(new Dimension(300,300));
            jl1.setMinimumSize(new Dimension(300,300));
            JPanel p1 = new JPanel(new BorderLayout());

            p1.setMinimumSize(new Dimension(300, 350));
            p1.add(b1, BorderLayout.SOUTH);
            p1.add(jl1, BorderLayout.CENTER);

            grilleDesFilms.add(p1);
        }
    }

}
