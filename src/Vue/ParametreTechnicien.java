package Vue;

import javax.swing.*;
import java.awt.*;

public class ParametreTechnicien extends JPanel {
    JButton afficherStat;
    JButton ouvrirMachine;
    InterfaceUtilisateur interfaceUtilisateur;
    public ParametreTechnicien(InterfaceUtilisateur interfaceUtilisateur){
        this.interfaceUtilisateur = interfaceUtilisateur;
        this.setOpaque(false);
        this.setLayout(new GridLayout(2, 0));
        afficherStat = OurTools.transparentButtonWithIcon("src/ressources/afficherStatistiques.png");
        ouvrirMachine = OurTools.transparentButtonWithIcon("src/ressources/afficherStatistiques.png");
        this.add(afficherStat);
        this.add(ouvrirMachine);

    }
}
