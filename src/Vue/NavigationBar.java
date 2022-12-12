package Vue;

import Metier.GestionMachine.FormulaireSignalement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * Barre de navigation présente dans tous les panneaux,
 * contenant les accès aux differentes fonctionnalités ainsi que
 * les informations concernant la connexion de l'utilisateur
 * @author Matvei Pavlov
 */
public class NavigationBar extends Panneau {

    private final LinkedList<JDialog> awaitingProcess = new LinkedList<>();
    private final JPanel droiteNav;
    private final JPanel droiteNavOut;
    private final JPanel gaucheNav;
    private final JButton compte;
    private final JButton retour;
    private final JButton aide;
    private final JButton connexion;
    private final JButton parametres;
    private final JButton parametreAdmin;
    private final JButton deconnexion;
    private final JButton panier;
    private final int borderSize;
    private final LinkedList<ETAT_IU> fifo = new LinkedList<ETAT_IU>();
    private Boolean estConnecte;

    /**
     * Constructeur, initialise tous les parametres de la barre de navigation
     * @param iu l'interface utilisateur
     */
    public NavigationBar(InterfaceUtilisateur iu){
        this.interfaceUtilisateur = iu;
        this.setLayout(new BorderLayout());
        this.setOpaque(false);

        Color navColor = OurColors.fond();

        droiteNav = new JPanel();
        droiteNav.setBackground(navColor);
        gaucheNav = new JPanel();
        gaucheNav.setBackground(navColor);

        droiteNav.setLayout(new FlowLayout());
        gaucheNav.setLayout(new BorderLayout());


        // Initialisation des boutons de la barre de navigation
        retour = bouton("res/ressources/retour.png");
        this.setRetour();
        aide = bouton("res/ressources/signalerProbleme.png");
        connexion = bouton("res/ressources/connexion.png");
        compte = bouton("res/ressources/compte.png");
        parametres = bouton("res/ressources/parametres.png");
        parametreAdmin = bouton("res/ressources/admin.png");
        deconnexion = bouton("res/ressources/disconnect.png");
        panier = bouton("res/ressources/panier.png");

        // Initialisation des actions des boutons
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
        this.setConnecte(false);
        this.cacher();
    }

    /**
     * Création d'un bouton à placer dans la barre de navigation
     * @param imageRef le path vers l'image
     * @return un JButton initialisé
     */
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

        //necessary to properly resize
        jb.setBorder(new EmptyBorder(1,1,1,1));

        // Makes the button disappear
        jb.setBorderPainted(false);
        jb.setContentAreaFilled(false);
        jb.setFocusPainted(false);
        jb.setOpaque(false);

        return jb;
    }

    /**
     * Initialisation des effets des boutons
     */
    void boutonsInit(){
        connexion.addActionListener(e -> interfaceUtilisateur.changerEtat(ETAT_IU.PRE_CONNEXION));
        deconnexion.addActionListener(e -> interfaceUtilisateur.deconnexion());
        parametres.addActionListener(e -> interfaceUtilisateur.changerEtat(ETAT_IU.PARAMETRES_ABONNE));
        panier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                interfaceUtilisateur.changerEtat(ETAT_IU.AFFICHER_PANIER);
            }
        });
        aide.addActionListener(e -> {
            JTextField nom = new JTextField();
            JTextField prenom = new JTextField();
            JTextField mail = new JTextField();
            JTextField signal = new JTextField();
            Object[] message = {
                    "Nom:", nom,
                    "Prenom:", prenom,
                    "Adresse Mail:", mail,
                    "Message:",signal
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Signaler Problème", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                interfaceUtilisateur.getLogiciel().signalerProbleme(new FormulaireSignalement(nom.getText(), prenom.getText(), mail.getText(), signal.getText()));
            }
        });
    }


    /**
     * Active/Desactive la vue de la barre de navigation de l'administrateur
     * @param admin True pour activer
     */
    public void setAdmin(boolean admin){
        parametreAdmin.setVisible(admin);
        parametreAdmin.setEnabled(admin);
    }

    /**
     * Modifie la barre de navigation en fonction de la connexion de l'utilisateur
     * @param estConnecte true pour être connecté, false sinon
     */
    public void setConnecte(boolean estConnecte){
        this.estConnecte = estConnecte;
        connexion.setEnabled(!estConnecte);
        connexion.setVisible(!estConnecte);
        compte.setEnabled(estConnecte);
        compte.setVisible(estConnecte);
        parametres.setEnabled(estConnecte);
        parametres.setVisible(estConnecte);
        deconnexion.setEnabled(estConnecte);
        deconnexion.setVisible(estConnecte);
    }

    /**
     * Fait completement disparaitre la barre de navigation
     */
    public void cacher(){
        this.setVisible(false);
    }

    /**
     * Active/Desactive la vue ne contenant que le bouton de retour
     * @param retourSeulement true pour activer, false pour desactiver
     */
    public void retourSeulement(boolean retourSeulement){
        this.setVisible(true);
        this.droiteNav.setVisible(!retourSeulement);
        this.droiteNavOut.setVisible(!retourSeulement);
        this.gaucheNav.setOpaque(!retourSeulement);
        this.setOpaque(!retourSeulement);
        this.aide.setVisible(!retourSeulement);
        this.setThisBorder(!retourSeulement);
    }

    /**
     * Modifie l'apparence de la bordure
     * @param visible true pour la rendre visible, false pour la faire disparaitre
     */
    public void setThisBorder(boolean visible){
        if(visible) {
            this.setBorder(new MatteBorder(borderSize, borderSize, borderSize, borderSize, OurColors.border()));
        } else {
            this.setBorder(new EmptyBorder(1,1,1,1));
        }
    }

    /**
     * Ajoute un état dans l'historique des états
     * @param etat le nouvel etat
     */
    public void ajouterEtat(ETAT_IU etat){
        fifo.push(etat);
    }

    /**
     * Action à effectuer lors du retour
     */
    private void setRetour(){
        this.retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fifo.pop(); //supprimer l'etat courant
                ETAT_IU nouveau = fifo.pop(); // recuperer l'etat precedent
                interfaceUtilisateur.changerEtat(nouveau);
                releaseProcess();
            }
        });
    }

    /**
     * reinitialise la barre de navigation
     */
    public void reset(){
        this.setVisible(true);
        this.droiteNav.setVisible(true);
        this.droiteNavOut.setVisible(true);
        this.gaucheNav.setOpaque(true);
        this.setOpaque(true);
        this.aide.setVisible(true);
        this.setThisBorder(true);
    }


    /**
     * getter d'estConnecte
     * @return true si un abonné est connecté
     */
    public Boolean estConnecte(){
        return this.estConnecte;
    }

    /**
     * Ajoute un dialog multithreadé à surveiller
     * @param j le JDialog
     */
    public void addAwaitingProcess(JDialog j){
        awaitingProcess.add(j);
    }

    /**
     * Ferme et detruit les dialog multithreadés contenus dans la liste
     */
    public void releaseProcess(){
        for(int i=0; i<awaitingProcess.size(); i++){
            JDialog j = awaitingProcess.pop();
            j.setVisible(false);
            j.dispose();
        }
    }

}
