package Vue;

import Controle.DonneesEvenement;
import Controle.Handler;
import Metier.Exception.CarteIllisible;
import Metier.Exception.ConnexionImpossible;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panneau d'insertion de la carte abonné afin de se connecter
 */
public class PreConnexion extends Panneau {

    /**
     * Constructeur
     * @param iu l'interface utilisateur courante
     */
    public PreConnexion(InterfaceUtilisateur iu){
        this.interfaceUtilisateur = iu;
        JLabel icon = new JLabel();
        icon.setIcon(OurPictures.getPicture("res/ressources/insertion.png"));
        icon.setOpaque(false);

        JTextArea instructions = new JTextArea("Veuillez insérer votre Carte d'abonné dans la fente prévue à cet effet.");
        instructions.setOpaque(false);
        instructions.setLineWrap(true);
        instructions.setWrapStyleWord(true);

        JButton simulation = new JButton("Simuler entrée Carte");
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
                interfaceUtilisateur.getMediateur().abonner("InsertionCA", new Handler() {
                    @Override
                    public void handle(DonneesEvenement e) {
                        String id = (String) e.getDonnees();
                        try {
                            interfaceUtilisateur.setCarteAbonne(interfaceUtilisateur.getLogiciel().simulerInsertionCA(id));
                            System.out.println("Connexion réussie avec la carte "+id);
                            interfaceUtilisateur.getMediateur().desabonner("InsertionCA");
                            interfaceUtilisateur.changerEtat(ETAT_IU.CONNEXION);
                        } catch (CarteIllisible ex) {
                            interfaceUtilisateur.errorDialog("ERREUR : Carte Illisible");
                        } catch (ConnexionImpossible ex) {
                            interfaceUtilisateur.errorDialog("ERREUR : Carte Invalide");
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

    /**
     * Action effecutée lors de l'insertion de la carte
     * @param numero le numero de la carte
     */
    public void testerPaneGetter(String numero){
        interfaceUtilisateur.getMediateur().publier("InsertionCA", new DonneesEvenement() {
            @Override
            public String getDonnees() {
                return numero;
            }
        });

    }
}
