package Vue;

import Controle.DonneesEvenement;
import Controle.Handler;
import Metier.Exception.BluRayInvalide;
import Metier.Exception.BluRayNonLoue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Rendre un Blu-ray sous forme physique
 * @author matvei pavlov
 */
public class RendreBluray extends Panneau {
    private Thread backgroundThreadRun;
    public RendreBluray(InterfaceUtilisateur iu){
        this.interfaceUtilisateur = iu;

        JLabel icon = new JLabel();
        icon.setIcon(OurPictures.getPicture("res/ressources/rendredvd.png"));
        icon.setOpaque(false);

        JTextArea instructions = new JTextArea("Veuillez insérer les Blu-ray dans la fente prévue à cet effet.");
        instructions.setFont(instructions.getFont().deriveFont(70f));
        instructions.setOpaque(false);
        instructions.setLineWrap(true);
        instructions.setWrapStyleWord(true);

        JButton simulation = new JButton("Simuler entrée Blu-ray");
        simulation.setPreferredSize(new Dimension(100, 100));
        simulation.setBackground(Color.CYAN);

        simulation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane simulateur = OurTools.testerPane(RendreBluray.this, "Veuillez entrer le numero du Blu-ray :");
                JDialog dialog = simulateur.createDialog(null, "Veuillez entrer le numero");
                interfaceUtilisateur.getNavBar().addAwaitingProcess(dialog);
                dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                dialog.setModal(false);
                dialog.setVisible(true);
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
        interfaceUtilisateur.getMediateur().publier("Entrer Blu-Ray", new DonneesEvenement() {
            @Override
            public Object getDonnees() {
                return numero;
            }
        });
    }

    private void threadInterrupt(){
        backgroundThreadRun.interrupt();
    }

    public void activer(){
        Runnable backgroundThread = new Runnable() {
            @Override
            public void run() {
                interfaceUtilisateur.getMediateur().abonner("Entrer Blu-Ray", new Handler() {
                    @Override
                    public void handle(DonneesEvenement e) {
                        try {
                            interfaceUtilisateur.getLogiciel().simulerInsertionBluRay((String) e.getDonnees());
                            System.out.println("Blu-Ray correctement rendu.");
                        } catch (BluRayInvalide ex) {
                            interfaceUtilisateur.errorDialog("ERREUR : Blu-Ray Invalide !");
                        } catch (BluRayNonLoue eb) {
                            interfaceUtilisateur.errorDialog("ERREUR : Blu-Ray Déja en magasin !");
                        }
                    }
                });
            }
        };
        backgroundThreadRun = new Thread(backgroundThread);
        backgroundThreadRun.start();
    }
    public void desactiver(){
        interfaceUtilisateur.getMediateur().desabonner("Entrer Blu-Ray");
        threadInterrupt();
    }
}
