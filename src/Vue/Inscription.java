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

    JTextField txt_adresseMail;
    JTextField txt_adressePostale;
    JTextField txt_motDePasse;
    JTextField txt_confirmation;

    JCheckBox action;
    JCheckBox aventure;
    JCheckBox thriller;
    JCheckBox romantique;
    JCheckBox horreur;

    JButton inscriptionPayer;


    public Inscription(){
        this.setOpaque(false);
        this.setLayout(new GridLayout(6,1));

        adresseMail = nouvelEnsemble(txt_adresseMail, "adresse mail", "Veuillez entrer votre adresse mail");
        adressePostale = nouvelEnsemble(txt_adressePostale, "adresse", "Veuillez entrer votre adresse");
        motDePasse = nouvelEnsemble(txt_motDePasse, "mot de passe", "Veuillez entrer votre mot de passe");
        confirmation = nouvelEnsemble(txt_confirmation, "mot de passe", "Veuillez confirmer votre mot de passe");
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

        inscriptionPayer = ourTools.transparentButtonWithIcon("src/ressources/inscriptionpayer.png");
        this.add(inscriptionPayer);
    }

    public JPanel nouvelEnsemble(JTextField jtxt, String text, String title){
        JPanel out = new JPanel(new BorderLayout());
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
}
