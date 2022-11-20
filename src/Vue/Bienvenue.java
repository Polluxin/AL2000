package Vue;

import javax.swing.*;
import java.awt.*;
public class Bienvenue extends JPanel{
    JPanel panneauPrincipal;
    JPanel boutons;
    JButton[] listeBoutons;
    NavigationBar navigationBar;

    public Bienvenue(){
        // Content Pane
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

        // Ajouter tous les boutons dans leur panneau
        for(int i=0; i<4;i++){
            listeBoutons[i] = this.transparentButtonWithIcon(i);
            listeBoutons[i].addActionListener(e -> panneauPrincipal.setVisible(false));
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

        this.add(panneauPrincipal, BorderLayout.CENTER);

    }

    JButton transparentButtonWithIcon(int i){
        /**
         * Creates a JButton that does not appear but has an icon set.
         */

        String[] refs = {"src/ressources/connexion.png", "src/ressources/rendrefilms.png", "src/ressources/inscription.png", "src/ressources/voirfilms.png"};

        // Create the button and initialise it
        JButton jb = new JButton();
        StretchIcon icon = ourPictures.getPicture(refs[i]);
        System.out.println(icon.getDescription());
        jb.setIcon(icon);

        // Debug parameters
        jb.addActionListener(e -> System.out.println("Hello"));

        // Makes the button disappear
        jb.setBorderPainted(false);
        jb.setContentAreaFilled(false);
        jb.setFocusPainted(false);
        jb.setOpaque(false); // <-- https://stackoverflow.com/a/8367524

        return jb;
    }

    public JButton getBouton(String name){
        if(name == "connexion"){
            return this.listeBoutons[0];
        } else if (name == "rendre films"){
            return this.listeBoutons[1];
        } else if (name == "inscription"){
            return this.listeBoutons[2];
        } else if (name == "voir films"){
            return this.listeBoutons[3];
        }
        else return null;
    }


}
