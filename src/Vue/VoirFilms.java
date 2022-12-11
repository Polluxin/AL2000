package Vue;

import Controle.DonneesEvenement;
import Controle.Handler;
import Metier.Exception.FormulaireInvalide;
import Metier.GestionLocation.Film;
import Metier.GestionLocation.FilmEtFormat;
import Metier.GestionLocation.FiltreTri;
import Metier.GestionLocation.Tri;
import Metier.GestionMachine.FormulaireInscription;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

    Runnable backgroundThread;
    Thread backgroundThreadRun;

    List<FilmEtFormat> listeFilms;
    Boolean grilleDesFilms_initialise;

    public VoirFilms(InterfaceUtilisateur iu){
        this.iu = iu;
        grilleDesFilms_initialise = Boolean.FALSE;

        backgroundThread = new Runnable() {
            @Override
            public void run() {
                iu.getMediateur().abonner("recupererListeFilms", new Handler() {
                    @Override
                    public void handle(DonneesEvenement e) {
                        creerListefilms(iu.getLogiciel().donnerCatalogue((FiltreTri) e.getDonnees()));
                        iu.getMediateur().desabonner("recupererListeFilms");
                        threadInterrupt();
                        grilleDesFilms_initialise = true;
                        System.out.println("Recuperer liste films handled ..");
                    }
                });
            }
        };

        backgroundThreadRun = new Thread(backgroundThread);
        backgroundThreadRun.start();

        int nombreFilms = 14;
        tousLesFilmsBoutons = new JButton[nombreFilms];
        tousLesFilms = new Film[nombreFilms];
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
                int height = 5*200;
                return new Dimension(scrollPaneWidth, height);
            }
        };

        iu.getMediateur().publier("recupererListeFilms", new DonneesEvenement() {
            @Override
            public Object getDonnees() {
                // TODO Gestion des Filtres
                return new FiltreTri(Tri.TITRE, null);
            }
        });
        while(!grilleDesFilms_initialise){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        grilleDesFilms.setOpaque(false);

        tousLesFilmsBoutons = new JButton[nombreFilms];
        tousLesFilms = new Film[nombreFilms];
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

    private void threadInterrupt(){
        backgroundThreadRun.interrupt();
    }

    private void creerListefilms(List<FilmEtFormat> listeFilms){
        this.listeFilms = listeFilms;
        int i = 0;
        for (FilmEtFormat fef : listeFilms) {
            JButton b1 = OurTools.transparentButtonWithIcon("src/ressources/ajouterPanier.png");
            b1.setMinimumSize(new Dimension(300,60));
            b1.setPreferredSize(new Dimension(300,60));
            //b1.setOpaque(false);
            tousLesFilmsBoutons[i] = b1;
            tousLesFilms[i] = new Film();

            b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    iu.setFilmActuel(fef.getFilm());
                    iu.changerEtat(ETAT_IU.AJOUTER_AU_PANIER);

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
            i++;
        }

    }

}
