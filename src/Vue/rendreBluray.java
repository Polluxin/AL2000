package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class rendreBluray extends JPanel {
    JTextArea instructions;
    JLabel icon;
    JButton simulation;
    public rendreBluray(){
        icon = new JLabel();
        icon.setIcon(ourPictures.getPicture("src/ressources/rendredvd.png"));
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
                /*
                entreeDuTesteur form = new entreeDuTesteur();
                Object[] options = {"ANNULER",
                        "VALIDER"};
                int n = JOptionPane.showOptionDialog(null,
                        form,
                        "Simulation",
                        0,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        null
                ); //default button title*/
                JOptionPane simulateur = ourTools.testerPane();
                //JButton[] options = (JButton[]) simulateur.getOptions();
                //options[0].se
                JDialog dialog = simulateur.createDialog(null, " test title ");
                dialog.setModal(false);
                dialog.setVisible(true);

                /*
                if (n == JOptionPane.OK_OPTION){ // case annuler
                    System.out.println("Oof");
                } else { // case valider
                    System.out.println("A BluRay was entered of id : "+form.getText());
                }*/
            }
        });

        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.add(instructions, BorderLayout.NORTH);
        this.add(icon);
        this.add(simulation, BorderLayout.SOUTH);
    }
}
