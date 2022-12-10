package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Bienvenue extends JPanel{
    JPanel panneauPrincipal;
    JPanel boutons;
    JButton[] listeBoutons;
    InterfaceUtilisateur iu;

    public Bienvenue(InterfaceUtilisateur iu){
        this.iu = iu;

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

        String[] refs = {"src/ressources/connexion.png", "src/ressources/rendrefilms.png", "src/ressources/inscription.png", "src/ressources/voirfilms.png"};
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

    public JButton getBouton(String name){
        if(Objects.equals(name, "connexion")){
            return this.listeBoutons[0];
        } else if (Objects.equals(name, "rendre films")){
            return this.listeBoutons[1];
        } else if (Objects.equals(name, "inscription")){
            return this.listeBoutons[2];
        } else if (Objects.equals(name, "voir films")){
            return this.listeBoutons[3];
        }
        else return null;
    }

    private void setActions(){
        listeBoutons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iu.changerEtat(ETAT_IU.PRE_CONNEXION);
            }
        });

        listeBoutons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iu.changerEtat(ETAT_IU.RENDRE_DVD);
            }
        });

        listeBoutons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iu.changerEtat(ETAT_IU.INSCRIPTION);
            }
        });

        listeBoutons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iu.changerEtat(ETAT_IU.VOIR_FILMS);
            }
        });
    }



}
