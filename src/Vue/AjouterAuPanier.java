package Vue;

import Metier.GestionLocation.Film;
import Metier.GestionLocation.Genre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AjouterAuPanier extends JPanel {
    Film ceFilm;
    JLabel titreFilm;
    JPanel titrePanneau;
    JPanel panneauPrincipal;
    JPanel panneauGauche;
    JPanel panneauDroite;
    JPanel panneauDroiteNord;
    JPanel soldePayer;
    JButton boutonAjouter;
    public AjouterAuPanier(Film film, InterfaceUtilisateur interfaceUtilisateur){
        this.setLayout(new BorderLayout());
        this.ceFilm = film;
        this.titreFilm = new JLabel(ceFilm.getTitre(),SwingConstants.CENTER);
        this.titreFilm.setMinimumSize(new Dimension(1000, 50));
        titrePanneau = new JPanel(new BorderLayout());
        titrePanneau.add(titreFilm);

        panneauPrincipal = new JPanel(new GridLayout(0,2));
        panneauGauche = new JPanel(new BorderLayout());
        panneauDroite = new JPanel(new BorderLayout());
        panneauDroiteNord = new JPanel(new GridLayout(0,4));
        //panneauDroiteNord.add()

        panneauGauche.add(new JLabel(OurPictures.getPicture("src/ressources/couverture.png")));

        soldePayer = new JPanel(new GridLayout(2,0));
        soldePayer.add(new JLabel("Solde : 45€"));

        boutonAjouter = OurTools.transparentButtonWithIcon("src/ressources/ajouterPanier.png");
        boutonAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                interfaceUtilisateur.changerEtat(ETAT_IU.CONFIRMER_AJOUTER_AU_PANIER);
                interfaceUtilisateur.setFilmActuel(film);
            }
        });
        soldePayer.add(boutonAjouter);
        panneauGauche.add(soldePayer, BorderLayout.SOUTH);



        panneauPrincipal.add(panneauGauche);

        this.add(titrePanneau, BorderLayout.NORTH);
        this.add(panneauPrincipal);

    }
    public AjouterAuPanier(InterfaceUtilisateur iu){
        this(new Film("Genial Eric le Generique", "Eric Lanvin", new java.sql.Date(System.currentTimeMillis()), "2h00", Genre.ACTION), iu);
    }

    public void setFilm(Film f){
        ceFilm = f;
        titreFilm.setText(f.getTitre());
    }


}
