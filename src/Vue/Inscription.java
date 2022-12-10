package Vue;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inscription extends JPanel {
    JPanel adresseMail;
    JPanel adressePostale;
    JPanel motDePasse;
    JPanel confirmation;
    JPanel interdiction;
    JPanel inscription;


    JCheckBox action;
    JCheckBox aventure;
    JCheckBox thriller;
    JCheckBox romantique;
    JCheckBox horreur;

    JButton inscriptionPayer;


    public Inscription(InterfaceUtilisateur iu){
        this.setOpaque(false);
        this.setLayout(new GridLayout(6,1));

        adresseMail = nouvelEnsemble("adresse mail", "Veuillez entrer votre adresse mail");
        adressePostale = nouvelEnsemble("adresse", "Veuillez entrer votre adresse");
        motDePasse = nouvelEnsemble("mot de passe", "Veuillez entrer votre mot de passe");
        confirmation = nouvelEnsemble("mot de passe", "Veuillez confirmer votre mot de passe");
        this.add(adresseMail);
        this.add(adressePostale);
        this.add(motDePasse);
        this.add(confirmation);

        interdiction = new JPanel(new FlowLayout());
        interdiction.setOpaque(false);
        action = nouvelleCheckBox("Action");
        aventure = nouvelleCheckBox("Aventure");
        thriller = nouvelleCheckBox("Thriller");
        romantique = nouvelleCheckBox("Romantique");
        horreur = nouvelleCheckBox("Horreur");
        interdiction.add(action);
        interdiction.add(aventure);
        interdiction.add(thriller);
        interdiction.add(romantique);
        interdiction.add(horreur);

        this.add(interdiction);

        inscriptionPayer = OurTools.transparentButtonWithIcon("src/ressources/inscriptionpayer.png");
        inscriptionPayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iu.changerEtat(ETAT_IU.INSCRIPTION_REUSSIE);
                effectuerInscription();
            }
        });
        this.add(inscriptionPayer);
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
                String newText = "";
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
        for (Component c : interdiction.getComponents()) {
            JCheckBox current = (JCheckBox) c;
            if(current.isSelected()){
                String text = current.getText().split("color='red'>")[1].split("</font>")[0];
                System.out.println(text+" is selected");
            }
        }
        System.out.println("Mail : "+getNouvelEnsembleValeur(adresseMail));
        System.out.println("Adresse : "+getNouvelEnsembleValeur(adressePostale));
        System.out.println("Mot de passe : "+getNouvelEnsembleValeur(motDePasse));
        System.out.println("Confirmation : "+getNouvelEnsembleValeur(confirmation));
    }

    private String getNouvelEnsembleValeur(JPanel j ){
        BorderLayout l = (BorderLayout)j.getLayout();
        JTextField txt = (JTextField) l.getLayoutComponent(BorderLayout.CENTER);
        return txt.getText();
    }


}
