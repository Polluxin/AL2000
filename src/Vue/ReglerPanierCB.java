package Vue;

import Controle.DonneesEvenement;
import Controle.Handler;
import Metier.Exception.CarteIllisible;
import Metier.Exception.FondsInsuffisants;
import Metier.Exception.PaiementRefuse;
import Metier.GestionClient.CB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Matvei Pavlov
 */
public class ReglerPanierCB extends Panneau{
    JTextArea instructions;
    JLabel icon;
    JButton simulation;
    public ReglerPanierCB(InterfaceUtilisateur iu){
        this.interfaceUtilisateur = iu;
        this.setLayout(new GridLayout(4, 0));
        icon = new JLabel();
        icon.setIcon(OurPictures.getPicture("res/ressources/insertion.png"));
        icon.setOpaque(false);

        instructions = new JTextArea("");
        instructions.setOpaque(false);
        instructions.setLineWrap(true);
        instructions.setWrapStyleWord(true);
        instructions.setEditable(false);

        simulation = new JButton("Simuler insertion Carte Banquaire");
        simulation.setPreferredSize(new Dimension(100, 100));
        simulation.setBackground(Color.CYAN);

        simulation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane simulateur = OurTools.testerPane(ReglerPanierCB.this, "Veuillez entrer le numero de la Carte Bancaire:");
                JDialog dialog = simulateur.createDialog(null, "Veuillez entrer le numero");
                interfaceUtilisateur.getNavBar().addAwaitingProcess(dialog);
                dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                dialog.setModal(false);
                dialog.setVisible(true);
            }
        });

        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        this.setOpaque(false);
        this.add(instructions);
        this.add(simulation);
    }

    public void testerPaneGetter(String numero){
        interfaceUtilisateur.getMediateur().publier("InsertionCB", new DonneesEvenement() {
            @Override
            public String getDonnees() {
                return numero;
            }
        });
    }

    public void activer(){
        interfaceUtilisateur.getMediateur().abonner("InsertionCB", new Handler() {
            @Override
            public void handle(DonneesEvenement e) {
                String id = (String) e.getDonnees();
                try {
                    CB cb = interfaceUtilisateur.getLogiciel().simulerInsertionCB(id);
                    interfaceUtilisateur.getLogiciel().louerFilms(cb);
                    interfaceUtilisateur.changerEtat(ETAT_IU.VOIR_FILMS);
                } catch (CarteIllisible ex) {
                    interfaceUtilisateur.errorDialog("ERREUR : Carte Illisible");
                } catch (FondsInsuffisants ex) {
                    interfaceUtilisateur.errorDialog("ERREUR : Fonds Insuffisants");
                }

            }
        });
        instructions.setText("Veuillez insérer votre carte bancaire.\nCarte : 5341 2154 2225 4448-04 25-Paul Fort-888-");
    }

    public void desactiver(){
        interfaceUtilisateur.getMediateur().desabonner("InsertionCB");
    }
}