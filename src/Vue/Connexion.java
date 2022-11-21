package Vue;

import javax.swing.*;
import java.awt.*;

public class Connexion extends JPanel {
    JPanel centrePanneau;
    JPanel motDePassePanneau;
    JButton connexion;
    JPasswordField motDePasse;
    JTextField motDePasseTxt;
    JTextArea instructions;
    public Connexion(){
        this.setLayout(new BorderLayout());
        instructions = new JTextArea("Veuillez insérer votre carte d'abonné");
        instructions.setOpaque(false);
        instructions.setLineWrap(true);
        instructions.setWrapStyleWord(true);
        instructions.setEditable(false);
        instructions.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        centrePanneau = new JPanel(new BorderLayout());
        centrePanneau.setOpaque(false);

        motDePassePanneau = new JPanel(new BorderLayout());

        motDePasseTxt = new JTextField("Veuillez entrer votre mot de passe :");
        motDePasseTxt.setEditable(false);
        motDePasseTxt.setOpaque(false);

        motDePasse = new JPasswordField("");
        motDePasse.setOpaque(true);

        connexion = ourTools.transparentButtonWithIcon("src/ressources/connexion.png");
        connexion.setOpaque(false);


        motDePassePanneau.add(motDePasseTxt, BorderLayout.NORTH);
        motDePassePanneau.add(motDePasse);

        centrePanneau.add(motDePassePanneau, BorderLayout.NORTH);
        centrePanneau.add(connexion);

        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        this.add(instructions, BorderLayout.NORTH);
        this.add(centrePanneau, BorderLayout.CENTER);
        this.setOpaque(false);
    }
}
