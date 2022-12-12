package Vue;

import Controle.DonneesEvenement;
import Controle.Handler;
import Metier.Exception.MauvaisMotDePasse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Connexion extends Panneau {
    JPanel centrePanneau;
    JPanel motDePassePanneau;
    JButton connexion;
    JPasswordField motDePasse;
    JTextField motDePasseTxt;
    Runnable backgroundThread;
    Thread backgroundThreadRun;
    public Connexion(InterfaceUtilisateur iu){
        this.interfaceUtilisateur = iu;

        this.setLayout(new BorderLayout());
        centrePanneau = new JPanel(new BorderLayout());
        centrePanneau.setOpaque(false);

        motDePassePanneau = new JPanel(new BorderLayout());

        motDePasseTxt = new JTextField("Veuillez entrer votre mot de passe :");
        motDePasseTxt.setEditable(false);
        motDePasseTxt.setOpaque(false);

        motDePasse = new JPasswordField("CHARLESbogoss2002");
        motDePasse.setOpaque(true);

        connexion = OurTools.transparentButtonWithIcon("src/ressources/connexion.png");
        connexion.setOpaque(false);
        connexion.setMaximumSize(new Dimension(500,500));
        connexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(interfaceUtilisateur.getNavBar().estConnecte()){
                    System.out.println("Utilisateur déja connecté ! ");
                } else {
                    interfaceUtilisateur.getMediateur().publier("Connexion", new DonneesEvenement() {
                        @Override
                        public Object getDonnees() {
                            return String.valueOf(motDePasse.getPassword());
                        }
                    });
                }
            }
        });

        motDePassePanneau.add(motDePasseTxt, BorderLayout.NORTH);
        motDePassePanneau.add(motDePasse);

        centrePanneau.add(motDePassePanneau, BorderLayout.NORTH);
        centrePanneau.add(connexion);

        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        this.add(centrePanneau, BorderLayout.CENTER);
        this.setOpaque(false);

    }

    private void threadInterrupt(){
        backgroundThreadRun.interrupt();
    }

    @Override
    public void desactiver() {
        interfaceUtilisateur.getMediateur().desabonner("Connexion");
        threadInterrupt();
    }

    @Override
    public void activer() {
        backgroundThread = new Runnable() {
            @Override
            public void run() {
                interfaceUtilisateur.getMediateur().abonner("Connexion", new Handler() {
                    @Override
                    public void handle(DonneesEvenement e) {
                        String donnes = (String) e.getDonnees();
                        try {
                            interfaceUtilisateur.getLogiciel().connexion(interfaceUtilisateur.getCarteAbonne(), donnes);
                            interfaceUtilisateur.getNavBar().setConnecte(true);
                            interfaceUtilisateur.changerEtat(ETAT_IU.VOIR_FILMS);
                        } catch (MauvaisMotDePasse ex) {
                            interfaceUtilisateur.errorDialog("ERREUR : Mauvais Mot de Passe");
                        }
                    }
                });
            }
        };
        backgroundThreadRun = new Thread(backgroundThread);
        backgroundThreadRun.start();
    }
}
