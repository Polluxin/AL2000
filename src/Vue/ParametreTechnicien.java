package Vue;

import javax.swing.*;
import java.awt.*;

public class ParametreTechnicien extends Panneau {
    public ParametreTechnicien(InterfaceUtilisateur interfaceUtilisateur){
        this.interfaceUtilisateur = interfaceUtilisateur;
        this.setOpaque(false);
        this.setLayout(new GridLayout(2, 0));
        JButton afficherStat = OurTools.transparentButtonWithIcon("res/ressources/afficherStatistiques.png");
        JButton ouvrirMachine = OurTools.transparentButtonWithIcon("res/ressources/afficherStatistiques.png");
        this.add(afficherStat);
        this.add(ouvrirMachine);

    }
}
