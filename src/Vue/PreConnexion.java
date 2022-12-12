package Vue;

import Controle.DonneesEvenement;
import Controle.Handler;
import Metier.Exception.CarteIllisible;
import Metier.Exception.ConnexionImpossible;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreConnexion extends Panneau {
    JTextArea instructions;
    JLabel icon;
    JButton simulation;
    NavigationBar navbar;
    InterfaceUtilisateur iu;
    public PreConnexion(InterfaceUtilisateur iu){
        this.iu = iu;
        navbar = iu.getNavBar();
        icon = new JLabel();
        icon.setIcon(OurPictures.getPicture("src/ressources/insertion.png"));
        icon.setOpaque(false);

        instructions = new JTextArea("Veuillez insérer votre Carte d'abonné dans la fente prévue à cet effet.");
        instructions.setOpaque(false);
        instructions.setLineWrap(true);
        instructions.setWrapStyleWord(true);

        simulation = new JButton("Simuler entrée Carte");
        simulation.setPreferredSize(new Dimension(100, 100));
        simulation.setBackground(Color.CYAN);

        simulation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane simulateur = OurTools.testerPane(PreConnexion.this, "Veuillez entrer le numero de la carte :");
                JDialog dialog = simulateur.createDialog(null, "Veuillez entrer le numero");
                dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                dialog.setModal(false);
                dialog.setVisible(true);
                iu.getMediateur().abonner("InsertionCA", new Handler() {
                    @Override
                    public void handle(DonneesEvenement e) {
                        String id = (String) e.getDonnees();
                        try {
                            iu.setCarteAbonne(iu.getLogiciel().simulerInsertionCA(id));
                            System.out.println("Connexion réussie avec la carte "+id);
                            iu.getMediateur().desabonner("InsertionCA");
                            iu.changerEtat(ETAT_IU.CONNEXION);
                        } catch (CarteIllisible ex) {
                            iu.errorDialog("ERREUR : Carte Illisible");
                        } catch (ConnexionImpossible ex) {
                            iu.errorDialog("ERREUR : Carte Invalide");
                        }

                    }
                });
            }
        });

        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.add(instructions, BorderLayout.NORTH);
        this.add(icon);
        this.add(simulation, BorderLayout.SOUTH);
    }

    public void testerPaneGetter(String numero){
        iu.getMediateur().publier("InsertionCA", new DonneesEvenement() {
            @Override
            public String getDonnees() {
                return numero;
            }
        });


//        System.out.println("Carte numero "+iu.numeroDeCarte+" à été entré.");

    }
}
