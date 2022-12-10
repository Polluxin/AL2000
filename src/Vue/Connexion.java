package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Connexion extends JPanel {
    JPanel centrePanneau;
    JPanel motDePassePanneau;
    JButton connexion;
    JButton simulation;
    JPasswordField motDePasse;
    JTextField motDePasseTxt;
    JTextArea instructions;
    String numeroDeCarte;
    public Connexion(NavigationBar navbar){
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

        connexion = OurTools.transparentButtonWithIcon("src/ressources/connexion.png");
        connexion.setOpaque(false);
        connexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Numero : "+numeroDeCarte+"\nMot de passe : "+ String.valueOf(motDePasse.getPassword()));
                navbar.setConnecte(true);
            }
        });

        simulation = new JButton("Simuler entrée Carte Abonné");
        simulation.setPreferredSize(new Dimension(100, 100));
        simulation.setBackground(Color.CYAN);

        simulation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane simulateur = OurTools.testerPane(navbar, Connexion.this, "Veuillez entrer le numero de la carte d'abonné:");
                JDialog dialog = simulateur.createDialog(null, "Veuillez entrer le numero");
                dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                dialog.setModal(false);
                dialog.setVisible(true);
            }
        });

        motDePassePanneau.add(motDePasseTxt, BorderLayout.NORTH);
        motDePassePanneau.add(motDePasse);

        centrePanneau.add(motDePassePanneau, BorderLayout.NORTH);
        centrePanneau.add(connexion);
        centrePanneau.add(simulation, BorderLayout.SOUTH);

        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        this.add(instructions, BorderLayout.NORTH);
        this.add(centrePanneau, BorderLayout.CENTER);
        this.setOpaque(false);
    }

    public void testerPaneGetter(String numero){
        System.out.println("Carte numero "+numero+" à été entré.");
        numeroDeCarte = numero;
    }
}
