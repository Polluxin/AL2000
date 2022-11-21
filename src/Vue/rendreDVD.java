package Vue;

import javax.swing.*;
import java.awt.*;

public class rendreDVD extends JPanel {
    JTextArea instructions;
    JLabel icon;
    public rendreDVD(){
        icon = new JLabel();
        icon.setIcon(ourPictures.getPicture("src/ressources/rendredvd.png"));
        icon.setOpaque(false);

        instructions = new JTextArea("Veuillez insérer les DVD dans la fente prévue à cet effet.");
        instructions.setOpaque(false);
        instructions.setLineWrap(true);
        instructions.setWrapStyleWord(true);

        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.add(instructions, BorderLayout.NORTH);
        this.add(icon);
    }
}
