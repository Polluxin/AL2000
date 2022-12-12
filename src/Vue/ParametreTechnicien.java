package Vue;

import javax.swing.*;
import java.awt.*;

public class ParametreTechnicien extends Panneau {
    JButton afficherStat;
    JButton ouvrirMachine;
    InterfaceUtilisateur interfaceUtilisateur;
    public ParametreTechnicien(InterfaceUtilisateur interfaceUtilisateur){
        this.interfaceUtilisateur = interfaceUtilisateur;
        this.setOpaque(false);
        this.setLayout(new GridLayout(2, 0));
        afficherStat = OurTools.transparentButtonWithIcon("res/ressources/afficherStatistiques.png");
        ouvrirMachine = OurTools.transparentButtonWithIcon("res/ressources/afficherStatistiques.png");
        this.add(afficherStat);
        this.add(ouvrirMachine);

    }
}
