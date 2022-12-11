package Vue;

import Controle.DonneesEvenement;
import Controle.Handler;
import Metier.Exception.FormulaireInvalide;
import Metier.Exception.MauvaisMotDePasse;
import Metier.GestionLocation.Genre;
import Metier.GestionMachine.FormulaireInscription;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inscription extends JPanel {
    JPanel nom;
    JPanel prenom;
    JPanel adresseMail;
    JPanel adressePostale;
    JPanel motDePasse;
    JPanel confirmation;
    JPanel interdiction;
    InterfaceUtilisateur interfaceUtilisateur;


    JCheckBox western;
    JCheckBox action;
    JCheckBox fantaisie;
    JCheckBox anime;
    JCheckBox horreur;
    JCheckBox sf;
    JCheckBox suspense;
    JCheckBox romance;

    JButton inscriptionPayer;

    JScrollPane affichage;
    JPanel dansScrollPane;

    Runnable backgroundThread;
    Thread backgroundThreadRun;


    public Inscription(InterfaceUtilisateur iu){
        this.interfaceUtilisateur = iu;
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        dansScrollPane = new JPanel(new GridLayout(8,0));
        nom = nouvelEnsemble("nom", "Veuillez entrer votre nom");
        prenom = nouvelEnsemble("prénom", "Veuillez entrer votre prénom");
        adresseMail = nouvelEnsemble("adresse mail", "Veuillez entrer votre adresse mail");
        adressePostale = nouvelEnsemble("adresse", "Veuillez entrer votre adresse");
        motDePasse = nouvelEnsemble("mot de passe", "Veuillez entrer votre mot de passe");
        confirmation = nouvelEnsemble("mot de passe", "Veuillez confirmer votre mot de passe");
        dansScrollPane.add(prenom);
        dansScrollPane.add(nom);
        dansScrollPane.add(adresseMail);
        dansScrollPane.add(adressePostale);
        dansScrollPane.add(motDePasse);
        dansScrollPane.add(confirmation);

        interdiction = new JPanel(new FlowLayout());
        interdiction.setOpaque(false);
        western = nouvelleCheckBox("WESTERN");
        action = nouvelleCheckBox("ACTION");
        fantaisie = nouvelleCheckBox("FANTAISIE");
        anime = nouvelleCheckBox("ANIME");
        horreur = nouvelleCheckBox("HORREUR");
        sf = nouvelleCheckBox("SF");
        suspense = nouvelleCheckBox("SUSPENSE");
        romance = nouvelleCheckBox("ROMANCE");
        interdiction.add(western);
        interdiction.add(action);
        interdiction.add(fantaisie);
        interdiction.add(anime);
        interdiction.add(horreur);
        interdiction.add(sf);
        interdiction.add(suspense);
        interdiction.add(romance);

        dansScrollPane.add(interdiction);

        inscriptionPayer = OurTools.transparentButtonWithIcon("src/ressources/inscriptionpayer.png");
        inscriptionPayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //iu.changerEtat(ETAT_IU.INSCRIPTION_REUSSIE);
                effectuerInscription();
            }
        });
        dansScrollPane.add(inscriptionPayer);

        affichage = new JScrollPane(dansScrollPane);
        affichage.getVerticalScrollBar().setUnitIncrement(14);
        this.add(affichage);

        backgroundThread = new Runnable() {
            @Override
            public void run() {
                iu.getMediateur().abonner("Inscription", new Handler() {
                    @Override
                    public void handle(DonneesEvenement e) {
                        FormulaireInscription fi = (FormulaireInscription) e.getDonnees();
                        try {
                            interfaceUtilisateur.getLogiciel().inscription(fi);
                            interfaceUtilisateur.getMediateur().desabonner("Inscription");
                            threadInterrupt();
                            interfaceUtilisateur.changerEtat(ETAT_IU.INSCRIPTION_REUSSIE);
                        } catch (FormulaireInvalide ex) {
                            System.out.println("Inscription Impossible");
                        }
                        System.out.println("Inscription handle ..");
                        System.out.println(fi.getNom());
                    }
                });
            }
        };

        backgroundThreadRun = new Thread(backgroundThread);
        backgroundThreadRun.start();


    }

    public JPanel nouvelEnsemble(String text, String title){
        JPanel out = new JPanel(new BorderLayout());
        JTextField jtxt;
        out.setOpaque(false);
        if(text == "mot de passe"){
            jtxt = new JPasswordField("mot de passe");
        } else {
            jtxt = new JTextField(text);
        }
        JTextField jtitle = new JTextField(title);
        jtitle.setEditable(false);
        jtitle.setOpaque(false);

        out.add(jtitle, BorderLayout.NORTH);
        out.add(jtxt);
        return out;
    }

    public JCheckBox nouvelleCheckBox(String name){
        JCheckBox out = new JCheckBox(name);
        out.setOpaque(false);
        out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentText = out.getText();
                String newText;
                if(currentText.startsWith("<html")){
                    newText = currentText.split("color='red'>")[1].split("</font>")[0];
                } else {
                    newText = "<html><font color='red'>"+currentText+"</font></html>";
                }
                out.setText(newText);
            }
        });
        return out;
    }

    private void effectuerInscription(){
        Genre[] genres = new Genre[8];
        int i =0;
        for (Component c : interdiction.getComponents()) {
            JCheckBox current = (JCheckBox) c;
            if(current.isSelected()){
                String text = current.getText().split("color='red'>")[1].split("</font>")[0];
                System.out.println(text+" is selected");
                genres[i] = Genre.valueOf(text);
                System.out.println(genres[i]);
            }
        }


            FormulaireInscription fi = new FormulaireInscription(
                    getNouvelEnsembleValeur(nom),
                    getNouvelEnsembleValeur(prenom),
                    getNouvelEnsembleValeur(adressePostale),
                    getNouvelEnsembleValeur(adresseMail),
                    genres,
                    getNouvelEnsembleValeur(motDePasse)

            );
        interfaceUtilisateur.getMediateur().publier("Inscription", new DonneesEvenement() {
            @Override
            public Object getDonnees() {
                return fi;
            }
        });

    }

    private String getNouvelEnsembleValeur(JPanel j ){
        BorderLayout l = (BorderLayout)j.getLayout();
        JTextField txt = (JTextField) l.getLayoutComponent(BorderLayout.CENTER);
        return txt.getText();
    }

    private void threadInterrupt(){
        backgroundThreadRun.interrupt();
    }


}
