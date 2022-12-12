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
import java.util.Arrays;
import java.util.Objects;

public class Inscription extends Panneau {
    private final JPanel nom;
    private final JPanel prenom;
    private final JPanel adresseMail;
    private final JPanel adressePostale;
    private final JPanel motDePasse;
    private final JPanel confirmation;
    private final JPanel interdiction;

    private final Thread backgroundThreadRun;


    public Inscription(InterfaceUtilisateur iu){
        this.interfaceUtilisateur = iu;
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        JPanel dansScrollPane = new JPanel(new GridLayout(8, 0));
        nom = nouvelEnsemble("Generique", "Veuillez entrer votre nom");
        prenom = nouvelEnsemble("Eric", "Veuillez entrer votre prénom");
        adresseMail = nouvelEnsemble("genial@ge.nial.com", "Veuillez entrer votre adresse mail");
        adressePostale = nouvelEnsemble("34 rue du genie", "Veuillez entrer votre adresse");
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
        JCheckBox western = nouvelleCheckBox("WESTERN");
        JCheckBox action = nouvelleCheckBox("ACTION");
        JCheckBox fantaisie = nouvelleCheckBox("FANTAISIE");
        JCheckBox anime = nouvelleCheckBox("ANIME");
        JCheckBox horreur = nouvelleCheckBox("HORREUR");
        JCheckBox sf = nouvelleCheckBox("SF");
        JCheckBox suspense = nouvelleCheckBox("SUSPENSE");
        JCheckBox romance = nouvelleCheckBox("ROMANCE");
        interdiction.add(western);
        interdiction.add(action);
        interdiction.add(fantaisie);
        interdiction.add(anime);
        interdiction.add(horreur);
        interdiction.add(sf);
        interdiction.add(suspense);
        interdiction.add(romance);

        dansScrollPane.add(interdiction);

        JButton inscriptionPayer = OurTools.transparentButtonWithIcon("res/ressources/inscriptionpayer.png");
        inscriptionPayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //iu.changerEtat(ETAT_IU.INSCRIPTION_REUSSIE);
                effectuerInscription();
            }
        });
        dansScrollPane.add(inscriptionPayer);

        JScrollPane affichage = new JScrollPane(dansScrollPane);
        affichage.getVerticalScrollBar().setUnitIncrement(14);
        this.add(affichage);

        Runnable backgroundThread = new Runnable() {
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
                            interfaceUtilisateur.errorDialog("Inscription Impossible");
                        }
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
        if(Objects.equals(text, "mot de passe")){
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
        if(!Objects.equals(getNouvelEnsembleValeur(motDePasse), getNouvelEnsembleValeur(confirmation))){
            interfaceUtilisateur.errorDialog("Les deux mots de passes sont différents !");
        } else {
            Genre[] tempgenres = new Genre[8];
            int i = 0;
            for (Component c : interdiction.getComponents()) {
                JCheckBox current = (JCheckBox) c;
                if (current.isSelected()) {
                    String text = current.getText().split("color='red'>")[1].split("</font>")[0];
                    System.out.println(text + " is selected");
                    tempgenres[i] = Genre.valueOf(text);
                    System.out.println(tempgenres[i]);
                    i++;
                }
            }
            Genre[] genres = new Genre[i];
            for(int j=0; j<i; j++){
                genres[j] = tempgenres[j];
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
