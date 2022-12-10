package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PreConnexion extends JPanel {
    JTextArea instructions;
    JLabel icon;
    JButton simulation;
    NavigationBar navbar;
    InterfaceUtilisateur iu;
    public PreConnexion(InterfaceUtilisateur iu){
        this.iu = iu;
        navbar = iu.getNavBar();
        icon = new JLabel();
        icon.setIcon(OurPictures.getPicture("src/ressources/rendredvd.png"));
        icon.setOpaque(false);

        instructions = new JTextArea("Veuillez insérer votre Carte d'abonné dans la fente prévue à cet effet.");
        instructions.setOpaque(false);
        instructions.setLineWrap(true);
        instructions.setWrapStyleWord(true);

        simulation = new JButton("Simuler entrée Carte");
        simulation.setPreferredSize(new Dimension(100, 100));
        simulation.setBackground(Color.CYAN);

        simulation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane simulateur = OurTools.testerPane(navbar, PreConnexion.this, "Veuillez entrer le numero de la carte :");
                JDialog dialog = simulateur.createDialog(null, "Veuillez entrer le numero");
                dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                dialog.setModal(false);
                dialog.setVisible(true);
            }
        });

        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.add(instructions, BorderLayout.NORTH);
        this.add(icon);
        this.add(simulation, BorderLayout.SOUTH);
    }

    public void testerPaneGetter(String numero){
        iu.numeroDeCarte = Integer.parseInt(numero);
        System.out.println("Carte numero "+iu.numeroDeCarte+" à été entré.");
        iu.changerEtat(ETAT_IU.CONNEXION);
    }
}
