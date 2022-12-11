package Vue;

import javax.swing.*;
import java.awt.*;

public class ConfirmerAjouterPanier extends JPanel {
    InterfaceUtilisateur interfaceUtilisateur;
    JButton qrCode;
    JButton physique;
    public ConfirmerAjouterPanier(InterfaceUtilisateur interfaceUtilisateur){
        this.interfaceUtilisateur = interfaceUtilisateur;
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        qrCode = OurTools.transparentButtonWithIcon("src/ressources/QRCode.png");
        physique = OurTools.transparentButtonWithIcon("src/ressources/bluray.png");

        JTextArea text = new JTextArea("Sous quel format souhaitez-vous retirer votre film ?");
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setOpaque(false);

        JPanel conteneurBoutons = new JPanel(new GridLayout(0,2));
        conteneurBoutons.setOpaque(false);

        conteneurBoutons.add(qrCode);
        conteneurBoutons.add(physique);

        this.add(conteneurBoutons);
        this.add(text, BorderLayout.NORTH);

        this.setVisible(true);
    }
}
