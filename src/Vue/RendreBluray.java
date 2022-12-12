package Vue;

import Controle.DonneesEvenement;
import Controle.Handler;
import Metier.Exception.BluRayInvalide;
import Metier.Exception.BluRayNonLoue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RendreBluray extends JPanel {
    JTextArea instructions;
    JLabel icon;
    JButton simulation;
    NavigationBar navbar;
    Thread backgroundThreadRun;
    Runnable backgroundThread;
    InterfaceUtilisateur iu;
    public RendreBluray(InterfaceUtilisateur iu){
        navbar = iu.getNavBar();
        this.iu = iu;
        backgroundThread = new Runnable() {
            @Override
            public void run() {
                iu.getMediateur().abonner("Entrer Blu-Ray", new Handler() {
                    @Override
                    public void handle(DonneesEvenement e) {
                        try {
                            iu.getLogiciel().simulerInsertionBluRay((String) e.getDonnees());
                            iu.getMediateur().desabonner("Entrer Blu-Ray");
                            threadInterrupt();
                            System.out.println("Blu-Ray correctement rendu.");
                        } catch (BluRayInvalide ex) {
                            System.out.println("Blu-Ray Invalide !");
                        } catch (BluRayNonLoue eb) {
                            System.out.println("Blu-Ray Actuellement en magasin !");
                        }
                    }
                });
            }
        };
        backgroundThreadRun = new Thread(backgroundThread);
        backgroundThreadRun.start();
        icon = new JLabel();
        icon.setIcon(OurPictures.getPicture("src/ressources/rendredvd.png"));
        icon.setOpaque(false);

        instructions = new JTextArea("Veuillez insérer les Blu-ray dans la fente prévue à cet effet.");
        instructions.setOpaque(false);
        instructions.setLineWrap(true);
        instructions.setWrapStyleWord(true);

        simulation = new JButton("Simuler entrée Blu-ray");
        simulation.setPreferredSize(new Dimension(100, 100));
        simulation.setBackground(Color.CYAN);

        simulation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane simulateur = OurTools.testerPane(RendreBluray.this, "Veuillez entrer le numero du Blu-ray :");
                JDialog dialog = simulateur.createDialog(null, "Veuillez entrer le numero");
                iu.getNavBar().addAwaitingProcess(dialog);
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
        iu.getMediateur().publier("Entrer Blu-Ray", new DonneesEvenement() {
            @Override
            public Object getDonnees() {
                return numero;
            }
        });
    }

    private void threadInterrupt(){
        backgroundThreadRun.interrupt();
    }
}
