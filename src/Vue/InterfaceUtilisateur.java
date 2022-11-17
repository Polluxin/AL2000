package Vue;

import Test.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class InterfaceUtilisateur extends JFrame{
    public InterfaceUtilisateur(){
        this.setLocationRelativeTo(null);
        this.setResizable(true);
    }

    Dimension scaleDimRatio(Dimension in, double maxWidth){
        /**
         *  Get in a dimension and an expected width and outputs a dimension with the expected width and the new height.
         */
        double width = in.getWidth();
        System.out.println("Current Dimensions = W:"+in.getWidth()+", H:"+in.getHeight()+"\nNext Dimensions = W:"+maxWidth+", H:"+(maxWidth*in.getHeight())/in.getWidth());
        return new Dimension((int)maxWidth, (int)((maxWidth*in.getHeight())/in.getWidth()));
    }

    JButton transparentButtonWithIcon(String imageRef){
        /**
         * Creates a JButton that does not appear but has an icon set.
         */

        // Create the Icon
        ImageIcon ii = new StretchIcon(imageRef); // load image

        // Create the button and initialise it
        JButton jb = new JButton();
        jb.setIcon(ii);

        // Debug parameters
        jb.addActionListener(e -> System.out.println("Hello"));

        // Makes the button disappear
        jb.setBorderPainted(false);
        jb.setContentAreaFilled(false);
        jb.setFocusPainted(false);
        jb.setOpaque(false); // <-- https://stackoverflow.com/a/8367524

        return jb;
    }

    void resizeButtons(JButton[] lis, Dimension newDim){
        for(int i=0; i<lis.length; i++){
            System.out.println("kek");
        }

    }

    public static void main(String[] args) {
        InterfaceUtilisateur iu = new InterfaceUtilisateur();
        // Starting code from :  https://www.guru99.com/java-swing-gui.html
        // Taille de l'ecran de l'utilisateur :
        Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
        double LARGEUR = tailleEcran.getWidth()/5;
        double HAUTEUR = tailleEcran.getHeight()/5;

        // Princi
        iu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        iu.setSize((int)LARGEUR,(int)HAUTEUR);

        // Content Pane
        JPanel panneauPrincipal = new JPanel();
        panneauPrincipal.setBackground(Color.GREEN);
        panneauPrincipal.setLayout(new GridBagLayout());
        iu.setContentPane(panneauPrincipal);

        // GridBagConstraint
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = gbc.REMAINDER;
        gbc.weightx = 1;
        gbc.weighty = 1;

        // Panneau contenant tous les boutons
        JPanel boutons = new JPanel(); // Panel containing all the buttons
        boutons.setLayout(new GridLayout(4,3));
        boutons.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));

        boutons.setOpaque(false); // make it transparent to see the background

        // Liste de tous les boutons et creation des boutons
        JButton listeBoutons[] = new JButton[4];
        for(int i=0; i<4; i++){
            int tailleBorder = 10;
            listeBoutons[i] = iu.transparentButtonWithIcon("/home/matvei/Documents/GitHub/AL2000/src/ressources/button.png");
        }

        // Ajouter tous les boutons dans leur panneau
        boutons.add(new JLabel(""));
        for(int i=0; i<3;i++){
            boutons.add(listeBoutons[i]);
            boutons.add(new JLabel(""));
            boutons.add(new JLabel(""));
        }
        boutons.add(listeBoutons[3]);
        boutons.add(new JLabel(""));

        // Ajouter le panneau des boutons au panneau principal
        //panneauPrincipal.add(boutons);

        iu.add(boutons, gbc);

        iu.setVisible(true);
    }

}
