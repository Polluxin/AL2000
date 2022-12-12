package Vue;

import Metier.GestionLocation.Film;
import Metier.GestionLocation.FilmEtFormat;
import Metier.GestionLocation.Genre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * JPanel montrant les informations sur un film avant de l'ajouter au panier
 * @author Matvei Pavlov
 */
public class AjouterAuPanier extends Panneau {
    Film ceFilm;
    JButton boutonAjouter;
    JLabel solde;
    JLabel titreFilm;
    JPanel panneauDroite;
    JPanel panneauGauche;
    JPanel panneauPrincipal;
    JPanel soldePayer;
    JPanel titrePanneau;
    JTextArea date;
    JTextArea duree;
    JTextArea genre;
    JTextArea realisateur;

    /**
     * Constructeur
     * @param film un film, qui sera mis à jour à chaque ouverture
     * @param interfaceUtilisateur L'interface appelante
     */
    public AjouterAuPanier(Film film, InterfaceUtilisateur interfaceUtilisateur){
        // Parametres de this
        this.setLayout(new BorderLayout());
        this.interfaceUtilisateur = interfaceUtilisateur;
        this.ceFilm = film;
        this.titreFilm = new JLabel(ceFilm.getTitre(),SwingConstants.CENTER);
        this.titreFilm.setMinimumSize(new Dimension(1000, 50));
        titrePanneau = new JPanel(new BorderLayout());
        titrePanneau.add(titreFilm);


        panneauPrincipal = new JPanel(new GridLayout(0,2));
        // Initialisation du panneau de droite
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

        //Initialisation du panneau de gauche
        panneauGauche = new JPanel(new BorderLayout());
        panneauGauche.add(new JLabel(OurPictures.getPicture("res/ressources/couverture.png")));
        // Solde
        soldePayer = new JPanel(new GridLayout(2,0));
        solde = new JLabel("Solde : XXX");
        soldePayer.add(solde);

        // Ajout au panier
        boutonAjouter = OurTools.transparentButtonWithIcon("res/ressources/ajouterPanier.png");
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

    /**
     * Deuxieme version du constructeur avec film prédéfini
     * @param iu Interface Utilisateur appelante
     */
    public AjouterAuPanier(InterfaceUtilisateur iu){
        this(new Film("Genial Eric le Generique", "Eric Lanvin", new java.sql.Date(System.currentTimeMillis()), "2h00", Genre.ACTION), iu);
    }


    /**
     * Modifie l'interface avec le film contenu dans setFilm
     * @param f
     */
    public void setFilm(FilmEtFormat f){
        ceFilm = f.getFilm();
        titreFilm.setText(ceFilm.getTitre());
        if(interfaceUtilisateur.getCarteAbonne() == null){
            solde.setText("Solde : XXX");
        } else {
            solde.setText("Solde : "+String.valueOf(interfaceUtilisateur.getCarteAbonne().getSolde())+" €");
        }
        duree.setText(ceFilm.getDuree());
        date.setText(ceFilm.getDate().toString());
        genre.setText(ceFilm.getGenre().toString());
        realisateur.setText(ceFilm.getRealisateur());
    }

    /**
     * Créé un JTextArea qui supporte le line wrapping, pour les elements de plusieurs lignes
     * @param text le texte à mettre dans le JTextArea
     * @return un nouveau JTextArea avec du line-wrap.
     */
    private JTextArea textWrapped(String text){
        JTextArea jt = new JTextArea(text);
        jt.setWrapStyleWord(true);
        jt.setLineWrap(true);
        jt.setOpaque(false);
        return jt;
    }

    @Override
    public void activer() {
        setFilm(interfaceUtilisateur.getFilmActuel());
    }
}
