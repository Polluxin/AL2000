package Vue;

import Controle.DonneesEvenement;
import Controle.Handler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Connexion extends JPanel {
    JPanel centrePanneau;
    JPanel motDePassePanneau;
    JButton connexion;
    JPasswordField motDePasse;
    JTextField motDePasseTxt;
    InterfaceUtilisateur iu;
    public Connexion(InterfaceUtilisateur iu){
        this.iu = iu;
        this.setLayout(new BorderLayout());
        centrePanneau = new JPanel(new BorderLayout());
        centrePanneau.setOpaque(false);

        motDePassePanneau = new JPanel(new BorderLayout());

        motDePasseTxt = new JTextField("Veuillez entrer votre mot de passe :");
        motDePasseTxt.setEditable(false);
        motDePasseTxt.setOpaque(false);

        motDePasse = new JPasswordField("");
        motDePasse.setOpaque(true);

        connexion = OurTools.transparentButtonWithIcon("src/ressources/connexion.png");
        connexion.setOpaque(false);
        connexion.setMaximumSize(new Dimension(500,1000));
        connexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Numero : "+iu.numeroDeCarte+"\nMot de passe : "+ String.valueOf(motDePasse.getPassword()));
                effectuerConnexion();
                iu.getNavBar().setConnecte(true);
            }
        });

        motDePassePanneau.add(motDePasseTxt, BorderLayout.NORTH);
        motDePassePanneau.add(motDePasse);

        centrePanneau.add(motDePassePanneau, BorderLayout.NORTH);
        centrePanneau.add(connexion);

        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        this.add(centrePanneau, BorderLayout.CENTER);
        this.setOpaque(false);
    }

    private void effectuerConnexion(){
        iu.getMediateur().abonner("insererCarte", new Handler() {
            @Override
            public void handle(DonneesEvenement e) {
                //  iu.getLogiciel().
            }
        });
    }

}
