package Vue;

import com.sun.jdi.BooleanType;

import javax.swing.*;
import java.awt.*;

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
    public AjouterAuPanier(Film film){
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

        panneauGauche.add(new JLabel(film.getCouverture()));

        soldePayer = new JPanel(new GridLayout(2,0));
        soldePayer.add(new JLabel("Solde : 45€"));

        boutonAjouter = OurTools.transparentButtonWithIcon("src/ressources/ajouterPanier.png");
        soldePayer.add(boutonAjouter);
        panneauGauche.add(soldePayer, BorderLayout.SOUTH);



        panneauPrincipal.add(panneauGauche);

        this.add(titrePanneau, BorderLayout.NORTH);
        this.add(panneauPrincipal);

    }
    public AjouterAuPanier(){
        this(new Film());
    }

    public void setFilm(Film f){

    }
}
