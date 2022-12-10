package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RendreBluray extends JPanel {
    JTextArea instructions;
    JLabel icon;
    JButton simulation;
    NavigationBar navbar;
    public RendreBluray(NavigationBar navigation){
        navbar = navigation;
        icon = new JLabel();
        icon.setIcon(OurPictures.getPicture("src/ressources/rendredvd.png"));
        icon.setOpaque(false);

        instructions = new JTextArea("Veuillez insérer les Blu-ray dans la fente prévue à cet effet.");
        instructions.setOpaque(false);
        instructions.setLineWrap(true);
        instructions.setWrapStyleWord(true);

        simulation = new JButton("Simuler entrée Blu-ray");
        simulation.setPreferredSize(new Dimension(100, 100));
        simulation.setBackground(Color.CYAN);

        simulation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane simulateur = OurTools.testerPane(navbar, RendreBluray.this, "Veuillez entrer le numero du Blu-ray :");
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
        System.out.println("Blu-ray numero "+numero+" à été entré.");
    }
}