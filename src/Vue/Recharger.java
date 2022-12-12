package Vue;

import Controle.DonneesEvenement;
import Controle.Handler;
import Metier.Exception.*;
import Metier.GestionClient.CB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Recharger le solde de la carte abonné à l'aide d'une carte bancaire
 * @author Matvei Pavlov
 */
public class Recharger extends Panneau {
    private final JTextArea instructions;
    private final JTextField montantAAjouter;
    public Recharger(InterfaceUtilisateur iu){
        this.interfaceUtilisateur = iu;
        this.setLayout(new GridLayout(3, 0));
        JLabel icon = new JLabel();
        icon.setIcon(OurPictures.getPicture("res/ressources/insertion.png"));
        icon.setOpaque(false);

        instructions = new JTextArea("");
        instructions.setOpaque(false);
        instructions.setLineWrap(true);
        instructions.setWrapStyleWord(true);
        instructions.setEditable(false);

        montantAAjouter = new JTextField("");
        montantAAjouter.setFont(montantAAjouter.getFont().deriveFont(100f));
        montantAAjouter.setBorder(BorderFactory.createMatteBorder(10,10,10,10,OurColors.border()));

        JButton simulation = new JButton("Simuler insertion Carte Banquaire");
        simulation.setPreferredSize(new Dimension(100, 100));
        simulation.setBackground(Color.CYAN);

        simulation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane simulateur = OurTools.testerPane(Recharger.this, "Veuillez entrer le numero de la Carte Bancaire:");
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
        this.add(montantAAjouter);
        this.add(simulation);
    }

    /**
     * Action à effectuer sur insertion d'une carte bancaire
     * @param numero numero de carte
     */
    public void testerPaneGetter(String numero){
        interfaceUtilisateur.getMediateur().publier("InsertionCB", new DonneesEvenement() {
            @Override
            public String getDonnees() {
                return numero;
            }
        });
    }

    /**
     * Action effectuée chaque fois que ce panneau devient le panneau courant
     */
    public void activer(){
        interfaceUtilisateur.getMediateur().abonner("InsertionCB", new Handler() {
            @Override
            public void handle(DonneesEvenement e) {
                String id = (String) e.getDonnees();
                try {
                    System.out.println("test : id -> "+id);
                    CB cb = interfaceUtilisateur.getLogiciel().simulerInsertionCB(id);
                    System.out.println("Carte "+cb.getInformationsBancaires()+" inséré.");
                    if(montantAAjouter.getText() != ""){
                        interfaceUtilisateur.getLogiciel().recharger(Float.valueOf(montantAAjouter.getText()), cb.getInformationsBancaires());
                    }
                    interfaceUtilisateur.changerEtat(ETAT_IU.VOIR_FILMS);
                } catch (CarteIllisible ex) {
                    interfaceUtilisateur.errorDialog("ERREUR : Carte Illisible");
                } catch (PaiementRefuse ex) {
                    interfaceUtilisateur.errorDialog("ERREUR : Paiement refusé");
                }

            }
        });
        instructions.setText("Votre solde est de : "+interfaceUtilisateur.getCarteAbonne().getSolde()+"\nVeuillez entrer le montant de la recharge et insérer votre Carte Bancaire.\nCarte : 5341 2154 2225 4448-04 25-Paul Fort-888-");

    }

    /**
     * Action effectuée lorsque ce panneau n'est plus panneau courant.
     */
    public void desactiver(){
        interfaceUtilisateur.getMediateur().desabonner("InsertionCB");
    }
}


