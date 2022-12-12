package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParametreAbonne extends Panneau {
    JButton recharger;
    JButton retirer;
    JButton historique;
    JTextField solde;
    InterfaceUtilisateur interfaceUtilisateur;
    public ParametreAbonne(InterfaceUtilisateur interfaceUtilisateur){
        this.interfaceUtilisateur = interfaceUtilisateur;
        this.setOpaque(false);
        this.setLayout(new GridLayout(4, 0));
        recharger = OurTools.transparentButtonWithIcon("src/ressources/recharger.png");
        retirer = OurTools.transparentButtonWithIcon("src/ressources/retirer.png");
        historique = OurTools.transparentButtonWithIcon("src/ressources/afficherHistorique.png");
        solde = new JTextField("solde : XX €");
        solde.setOpaque(false);
        this.add(recharger);
        this.add(retirer);
        this.add(solde);
        this.add(historique);
        initButtons();

    }

    private void initButtons(){
        recharger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                interfaceUtilisateur.changerEtat(ETAT_IU.RECHARGER);
            }
        });
    }

    public void activer(){
        solde.setText("solde : "+interfaceUtilisateur.getCarteAbonne().getSolde()+" €");
    }
}
