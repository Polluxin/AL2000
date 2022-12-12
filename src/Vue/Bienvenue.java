package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Bienvenue extends Panneau{
    JPanel panneauPrincipal;
    JPanel boutons;
    JButton[] listeBoutons;

    public Bienvenue(InterfaceUtilisateur iu){
        this.interfaceUtilisateur= iu;

        this.setLayout(new BorderLayout());
        this.setOpaque(false);

        panneauPrincipal = new JPanel();
        panneauPrincipal.setOpaque(false);
        panneauPrincipal.setLayout(new BorderLayout());

        // Panneau contenant tous les boutons
        boutons = new JPanel(); // Panel containing all the buttons
        boutons.setLayout(new GridLayout(4,3));

        boutons.setOpaque(false); // make it transparent to see the background

        // Liste de tous les boutons
        listeBoutons = new JButton[4];

        String[] refs = {"res/ressources/connexion.png","res/ressources/inscription.png", "res/ressources/rendrefilms.png", "res/ressources/voirfilms.png"};
        // Ajouter tous les boutons dans leur panneau
        for(int i=0; i<4;i++){
            listeBoutons[i] = OurTools.transparentButtonWithIcon(refs[i]);
            //listeBoutons[i].addActionListener(e -> panneauPrincipal.setVisible(false));
            boutons.add(new JLabel(""));
            boutons.add(listeBoutons[i]);
            boutons.add(new JLabel(""));
        }

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setOpaque(false);

        JTextField titleZone = new JTextField();
        titleZone.setEditable(false);
        titleZone.setOpaque(false);
        titleZone.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        titleZone.setForeground(Color.BLACK);
        titleZone.setText("Bienvenue sur AL2000 ");

        titlePanel.add(titleZone, BorderLayout.WEST);

        panneauPrincipal.add(boutons, BorderLayout.CENTER);
        panneauPrincipal.add(titlePanel, BorderLayout.NORTH);

        this.setActions();
        this.add(panneauPrincipal, BorderLayout.CENTER);

    }

    private void setActions(){
        listeBoutons[0].addActionListener(e -> {
            if(!interfaceUtilisateur.estConnecte()) {
                interfaceUtilisateur.changerEtat(ETAT_IU.PRE_CONNEXION);
            }
        });
        listeBoutons[1].addActionListener(e -> {
            if(!interfaceUtilisateur.estConnecte()) {
                interfaceUtilisateur.changerEtat(ETAT_IU.INSCRIPTION);
            }
        });
        listeBoutons[2].addActionListener(e -> interfaceUtilisateur.changerEtat(ETAT_IU.RENDRE_DVD));
        listeBoutons[3].addActionListener(e -> interfaceUtilisateur.changerEtat(ETAT_IU.VOIR_FILMS));
    }
}
