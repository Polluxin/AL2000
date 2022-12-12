package Vue;

import Controle.DonneesEvenement;
import Controle.Handler;
import Metier.GestionLocation.Support;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panneau de confirmation d'ajout au panier permettant de choisir un format pour le film
 */
public class ConfirmerAjouterPanier extends Panneau {
    private final JButton qrCode;
    private final JButton physique;
    private Thread backgroundThreadRun;

    /**
     * Constructeur du panneau
     * @param interfaceUtilisateur l'interface utilsiateur
     */
    public ConfirmerAjouterPanier(InterfaceUtilisateur interfaceUtilisateur){
        this.interfaceUtilisateur = interfaceUtilisateur;
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        qrCode = OurTools.transparentButtonWithIcon("res/ressources/QRCode.png");
        physique = OurTools.transparentButtonWithIcon("res/ressources/bluray.png");

        JTextArea text = new JTextArea("Sous quel format souhaitez-vous retirer votre film ?");
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setOpaque(false);

        JPanel conteneurBoutons = new JPanel(new GridLayout(0,2));
        conteneurBoutons.setOpaque(false);

        conteneurBoutons.add(qrCode);
        conteneurBoutons.add(physique);

        this.add(conteneurBoutons);
        this.add(text, BorderLayout.NORTH);

        this.setVisible(true);
    }

    private void actionsBoutons(){
        qrCode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Support qrc = interfaceUtilisateur.getLogiciel().getSupport(interfaceUtilisateur.getFilmActuel().getFilm(), false);
                interfaceUtilisateur.getMediateur().publier("ajouterPanier", new DonneesEvenement() {
                    @Override
                    public Object getDonnees() {
                        return qrc;
                    }
                });
            }
        });
        physique.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Support phy = interfaceUtilisateur.getLogiciel().getSupport(interfaceUtilisateur.getFilmActuel().getFilm(), true);
                interfaceUtilisateur.getMediateur().publier("ajouterPanier", new DonneesEvenement() {
                    @Override
                    public Object getDonnees() {
                        return phy;
                    }
                });
            }
        });
    }

    private void threadInterrupt(){
        backgroundThreadRun.interrupt();
    }

    @Override
    public void activer() {
        Runnable backgroundThread = new Runnable() {
            @Override
            public void run() {
                interfaceUtilisateur.getMediateur().abonner("ajouterPanier", new Handler() {
                    @Override
                    public void handle(DonneesEvenement e) {
                        Support sup = (Support) e.getDonnees();
                        if (interfaceUtilisateur.incrementerPanier()) {
                            interfaceUtilisateur.getLogiciel().ajouterPanier(sup);
                            System.out.println("Film ajouté !");
                        } else {
                            System.out.println("Panier Plein !");
                        }
                        interfaceUtilisateur.changerEtat(ETAT_IU.VOIR_FILMS);
                    }
                });
            }
        };
        actionsBoutons();
        backgroundThreadRun = new Thread(backgroundThread);
        backgroundThreadRun.start();
        physique.setEnabled(interfaceUtilisateur.getFilmActuel().estDispoEnPhysique());
        physique.setVisible(interfaceUtilisateur.getFilmActuel().estDispoEnPhysique());
    }

    @Override
    public void desactiver() {
        threadInterrupt();
        interfaceUtilisateur.getMediateur().desabonner("ajouterPanier");

    }
}
