package Vue;

import Metier.GestionLocation.Film;
import Metier.GestionLocation.Genre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AjouterAuPanier extends JPanel {
    Film ceFilm;
    JTextArea realisateur;
    JTextArea duree;
    JTextArea date;
    JTextArea genre;
    JLabel titreFilm;
    JLabel solde;
    JPanel titrePanneau;
    JPanel panneauPrincipal;
    JPanel panneauGauche;
    JPanel panneauDroite;
    JPanel panneauDroiteNord;
    JPanel soldePayer;
    JButton boutonAjouter;
    InterfaceUtilisateur interfaceUtilisateur;
    public AjouterAuPanier(Film film, InterfaceUtilisateur interfaceUtilisateur){
        this.setLayout(new BorderLayout());
        this.interfaceUtilisateur = interfaceUtilisateur;
        this.ceFilm = film;
        this.titreFilm = new JLabel(ceFilm.getTitre(),SwingConstants.CENTER);
        this.titreFilm.setMinimumSize(new Dimension(1000, 50));
        titrePanneau = new JPanel(new BorderLayout());
        titrePanneau.add(titreFilm);

        panneauPrincipal = new JPanel(new GridLayout(0,2));
        panneauGauche = new JPanel(new BorderLayout());
        panneauDroite = new JPanel(new GridLayout(8, 0));
        panneauDroite.add(new JLabel("Realisateur : "));
        realisateur = textWrapped("");
        panneauDroite.add(realisateur);
        panneauDroite.add(new JLabel("Date : "));
        date = textWrapped("");
        panneauDroite.add(date);
        panneauDroite.add(new JLabel("Duree : "));
        duree = textWrapped("");
        panneauDroite.add(duree);
        panneauDroite.add(new JLabel("Genre : "));
        genre = textWrapped("");
        panneauDroite.add(genre);
        //panneauDroiteNord.add()

        panneauGauche.add(new JLabel(OurPictures.getPicture("src/ressources/couverture.png")));

        soldePayer = new JPanel(new GridLayout(2,0));
        solde = new JLabel("Solde : XXX");
        soldePayer.add(solde);

        boutonAjouter = OurTools.transparentButtonWithIcon("src/ressources/ajouterPanier.png");
        boutonAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                interfaceUtilisateur.changerEtat(ETAT_IU.CONFIRMER_AJOUTER_AU_PANIER);
            }
        });
        soldePayer.add(boutonAjouter);
        panneauGauche.add(soldePayer, BorderLayout.SOUTH);



        panneauPrincipal.add(panneauGauche);
        panneauPrincipal.add(panneauDroite);

        this.add(titrePanneau, BorderLayout.NORTH);
        this.add(panneauPrincipal);

    }
    public AjouterAuPanier(InterfaceUtilisateur iu){
        this(new Film("Genial Eric le Generique", "Eric Lanvin", new java.sql.Date(System.currentTimeMillis()), "2h00", Genre.ACTION), iu);
    }

    public void setFilm(Film f){
        ceFilm = f;
        titreFilm.setText(f.getTitre());
        if(interfaceUtilisateur.getCarteAbonne() == null){
            solde.setText("Solde : XXX");
        } else {
            solde.setText("Solde : "+String.valueOf(interfaceUtilisateur.getCarteAbonne().getSolde())+" €");
        }
        duree.setText(f.getDuree());
        date.setText(f.getDate().toString());
        genre.setText(f.getGenre().toString());
        realisateur.setText(f.getRealisateur());
    }

    private JTextArea textWrapped(String text){
        JTextArea jt = new JTextArea(text);
        jt.setWrapStyleWord(true);
        jt.setLineWrap(true);
        jt.setOpaque(false);
        return jt;
    }


}
