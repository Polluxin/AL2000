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

    int awaitingProcess;

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
    JButton panier;
    Color navColor;
    int borderSize;
    LinkedList<ETAT_IU> fifo = new LinkedList<ETAT_IU>();

    public NavigationBar(InterfaceUtilisateur iu){
        this.iu = iu;
        this.setLayout(new BorderLayout());
        //this.setBackground(Color.GREEN);
        this.setOpaque(false);

        awaitingProcess = 0;

        navColor = OurColors.fond();

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
        panier = bouton("src/ressources/panier.png");

        boutonsInit();

        gaucheNav.add(retour, BorderLayout.WEST);
        gaucheNav.add(aide);

        droiteNav.add(connexion);
        droiteNav.add(parametres);
        droiteNav.add(parametreAdmin);
        droiteNav.add(deconnexion);
        droiteNav.add(panier);

        droiteNavOut = new JPanel();
        droiteNavOut.setLayout(new BorderLayout());
        droiteNavOut.add(droiteNav, BorderLayout.EAST);
        droiteNavOut.setBackground(navColor);

        this.add(gaucheNav, BorderLayout.WEST);
        this.add(droiteNavOut);

        borderSize = 10;

        this.setThisBorder(true);
        this.setMinimumSize(new Dimension(856, 100));

        this.setAdmin(false);
    }

    JButton bouton(String imageRef){
        // Create the Icon
        StretchIcon ii = OurPictures.getPicture(imageRef);// load image
        StretchIcon pressed = OurPictures.getPicture(imageRef+"_pressed.png");// load image
        //testDialog(ii);

        // Create the button and initialise it
        JButton jb = new JButton();
        jb.setIcon(ii);
        jb.setPressedIcon(pressed);

        Dimension defaultImageDimension = OurPictures.getDimensions(ii);
        jb.setPreferredSize(OurPictures.scaleDimensionByHeight(defaultImageDimension, 100));

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
        dialog.setPreferredSize(OurPictures.getDimensions(image));
        System.out.println(OurPictures.getDimensions(image));

        dialog.add(new JLabel(image));

        dialog.pack();
        dialog.setLocationByPlatform(true);
        dialog.setVisible(true);
    }

    void boutonsInit(){
        connexion.addActionListener(e -> iu.changerEtat(ETAT_IU.CONNEXION));
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
            this.setBorder(new MatteBorder(borderSize, borderSize, borderSize, borderSize, OurColors.border()));
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
                if(awaitingProcess == 0) {
                    fifo.pop(); //supprimer l'etat courant
                    ETAT_IU nouveau = fifo.pop(); // recuperer l'etat precedent
                    System.out.println("Retour --> Nouvel etat = " + nouveau + " .. reste des etats = " + fifo.toString());
                    iu.changerEtat(nouveau);
                }
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

    public void setCompte(boolean active) {
        this.compte.setVisible(active);
        this.compte.setEnabled(active);
    }

    public void setRetour(boolean active) {
        this.retour.setVisible(active);
        this.retour.setEnabled(active);
    }

    public void setAide(boolean active) {
        this.aide.setVisible(active);
        this.aide.setEnabled(active);
    }

    public void setConnexion(boolean active) {
        this.connexion.setVisible(active);
        this.connexion.setEnabled(active);
    }
    public void setParametres(boolean active) {
        this.parametres.setVisible(active);
        this.parametres.setEnabled(active);
    }

    public void setParametresAdmin(boolean active) {
        this.parametreAdmin.setVisible(active);
        this.parametreAdmin.setEnabled(active);
    }
    public void setDeconnexion(boolean active) {
        this.deconnexion.setVisible(active);
        this.deconnexion.setEnabled(active);
    }
    public void setPanier(boolean active) {
        this.panier.setVisible(active);
        this.panier.setEnabled(active);
    }

    public void addAwaitingProcess(){
        awaitingProcess += 1;
    }

    public void removeAwaitingProcess(){
        awaitingProcess -= 1;
    }

}
