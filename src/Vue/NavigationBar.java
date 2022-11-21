package Vue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class NavigationBar extends JPanel {
    InterfaceUtilisateur iu;

    JPanel droiteNav;
    JPanel droiteNavOut;
    JPanel gaucheNav;
    JButton compte;
    JButton retour;
    JButton aide;
    JButton connexion;
    JButton parametres;
    JButton parametreAdmin;
    JButton deconnexion;
    Color navColor;
    int borderSize;
    LinkedList<ETAT_IU> fifo = new LinkedList<ETAT_IU>();

    public NavigationBar(InterfaceUtilisateur iu){
        this.iu = iu;
        this.setLayout(new BorderLayout());
        //this.setBackground(Color.GREEN);
        this.setOpaque(false);

        navColor = ourColors.fond2();

        droiteNav = new JPanel();
        droiteNav.setBackground(navColor);
        gaucheNav = new JPanel();
        gaucheNav.setBackground(navColor);

        droiteNav.setLayout(new FlowLayout());
        gaucheNav.setLayout(new BorderLayout());


        retour = bouton("src/ressources/retour.png");
        this.setRetour();
        aide = bouton("src/ressources/signalerProbleme.png");

        connexion = bouton("src/ressources/connexion.png");
        compte = bouton("src/ressources/compte.png");
        parametres = bouton("src/ressources/parametres.png");
        parametreAdmin = bouton("src/ressources/admin.png");
        deconnexion = bouton("src/ressources/disconnect.png");

        gaucheNav.add(retour, BorderLayout.WEST);
        gaucheNav.add(aide);

        droiteNav.add(connexion);
        droiteNav.add(parametres);
        droiteNav.add(parametreAdmin);
        droiteNav.add(deconnexion);

        droiteNavOut = new JPanel();
        droiteNavOut.setLayout(new BorderLayout());
        droiteNavOut.add(droiteNav, BorderLayout.EAST);
        droiteNavOut.setBackground(navColor);

        this.add(gaucheNav, BorderLayout.WEST);
        this.add(droiteNavOut);

        borderSize = 7;

        this.setThisBorder(true);
        this.setMinimumSize(new Dimension(856, 100));
    }

    JButton bouton(String imageRef){
        // Create the Icon
        StretchIcon ii = ourPictures.getPicture(imageRef);// load image
        //testDialog(ii);

        // Create the button and initialise it
        JButton jb = new JButton();
        jb.setIcon(ii);

        Dimension defaultImageDimension = ourPictures.getDimensions(ii);
        jb.setPreferredSize(ourPictures.scaleDimensionByHeight(defaultImageDimension, 100));

        // Debug parameters
        jb.addActionListener(e -> System.out.println(this.getWidth()));

        //necessary to properly resize
        jb.setBorder(new EmptyBorder(1,1,1,1));

        // Makes the button disappear
        jb.setBorderPainted(false);
        jb.setContentAreaFilled(false);
        jb.setFocusPainted(false);
        jb.setOpaque(false); // <-- https://stackoverflow.com/a/8367524

        return jb;
    }

    void testDialog(StretchIcon image){
        JDialog dialog = new JDialog();
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setTitle("Image Loading Demo");
        dialog.setPreferredSize(ourPictures.getDimensions(image));
        System.out.println(ourPictures.getDimensions(image));

        dialog.add(new JLabel(image));

        dialog.pack();
        dialog.setLocationByPlatform(true);
        dialog.setVisible(true);
    }

    public JButton getRetour() {
        return retour;
    }

    public JButton getAide() {
        return aide;
    }

    public JButton getConnexion() {
        return connexion;
    }

    public JButton getParametres() {
        return parametres;
    }

    public JButton getParametreAdmin() {
        return parametreAdmin;
    }

    public JButton getDeconnexion() {
        return deconnexion;
    }

    public void setAdmin(boolean admin){
        parametreAdmin.setVisible(admin);
        parametreAdmin.setEnabled(admin);
    }

    public void setConnecte(boolean estConnecte){
        if(estConnecte){
            connexion.setEnabled(false);
            connexion.setVisible(false);
            compte.setEnabled(true);
            compte.setVisible(true);
        } else {
            connexion.setEnabled(true);
            connexion.setVisible(true);
            compte.setEnabled(false);
            compte.setVisible(false);
        }
    }

    public void cacher(){
        this.setVisible(false);
    }

    public void montrer(){
        this.setVisible(true);
    }

    public void retourSeulement(boolean retourSeulement){
        this.setVisible(true);
        this.droiteNav.setVisible(!retourSeulement);
        this.droiteNavOut.setVisible(!retourSeulement);
        this.gaucheNav.setOpaque(!retourSeulement);
        this.setOpaque(!retourSeulement);
        this.aide.setVisible(!retourSeulement);
        this.setThisBorder(!retourSeulement);
    }

    public void setThisBorder(boolean visible){
        if(visible) {
            this.setBorder(new MatteBorder(borderSize, borderSize, borderSize, borderSize, ourColors.border()));
        } else {
            this.setBorder(new EmptyBorder(1,1,1,1));
        }
    }

    public void ajouterEtat(ETAT_IU etat){
        fifo.push(etat);
        System.out.println(fifo.toString());
    }

    private void setRetour(){
        this.retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Oy !");
                fifo.pop(); //supprimer l'etat courant
                ETAT_IU nouveau = fifo.pop(); // recuperer l'etat precedent
                System.out.println("Retour --> Nouvel etat = "+nouveau+" .. reste des etats = "+fifo.toString());
                iu.changerEtat(nouveau);
            }
        });
    }

    public void reset(){
        this.setVisible(true);
        this.droiteNav.setVisible(true);
        this.droiteNavOut.setVisible(true);
        this.gaucheNav.setOpaque(true);
        this.setOpaque(true);
        this.aide.setVisible(true);
        this.setThisBorder(true);
    }



}
