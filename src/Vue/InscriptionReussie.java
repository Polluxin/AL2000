package Vue;

import javax.swing.*;
import java.awt.*;

public class InscriptionReussie extends JPanel {
    public InscriptionReussie(){
        JTextArea texte = new JTextArea("Votre inscription a bien été prise en compte. Vous recevrez un mail de confirmation et votre carte par la poste dès que possible. Merci beaucoup !");
        texte.setOpaque(false);
        texte.setLineWrap(true);
        texte.setWrapStyleWord(true);
        texte.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        texte.setEditable(false);
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.add(texte);
    }
}
