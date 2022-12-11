package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class OurTools {

    /**
     * > transparentButtonWithIcon Créé un bouton avec une icone, et ne montre que l'icone
     *
     * @param rss le chemin vers l'image dans les fichiers
     * @return Un JButton avec une icone
     */
    public static JButton transparentButtonWithIcon(String rss){
        String pressedName = rss+"_pressed.png";
        // Create the button and initialise it
        JButton jb = new JButton();
        StretchIcon icon = OurPictures.getPicture(rss);
        StretchIcon pressed = OurPictures.getPicture(pressedName);

        //System.out.println(icon.getDescription());
        jb.setIcon(icon);
        jb.setPressedIcon(pressed);

        // Makes the button disappear
        jb.setBorderPainted(false);
        jb.setContentAreaFilled(false);
        jb.setFocusPainted(false);
        //jb.setOpaque(false); // <-- https://stackoverflow.com/a/8367524
        jb.setVisible(true);

        return jb;
    }

    /**
     * setFont charge une police de caractère personnalisée et l'assigne aux objets utilisés
     */
    public static void setFont(){
        Font font;
        try {
            File f = new File("src/ressources/Cocogoose.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, f);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        font = font.deriveFont(48f);
        UIManager.put("TextField.font", font);
        UIManager.put("CheckBox.font", font);
        UIManager.put("PasswordField.font", font);
        UIManager.put("TextArea.font", font);
        UIManager.put("Button.font", font);
        UIManager.put("Label.font", font);
    }

    /**
     * Créé un panneau de test pour entrer des numeros, comme des Blu-ray et un numero de carte
     * @return le JOptionPane de test
     */
    public static JOptionPane testerPane(JPanel jb, String customText){
        JOptionPane tester;
        //navbar.addAwaitingProcess(tester);
        EntreeDuTesteur form = new EntreeDuTesteur(customText);
        JButton annuler = new JButton("ANNULER");
        JButton valider = new JButton("VALIDER");

        annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = getOptionPane((JComponent)e.getSource());
                System.out.println("Annuler a été appuyé !");
                pane.setValue(JOptionPane.NO_OPTION);
                //navbar.removeAwaitingProcess(tester);
            }
        });

        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = getOptionPane((JComponent)e.getSource());
                pane.setValue(JOptionPane.YES_OPTION);
                if(jb.getClass().getName() == "Vue.RendreBluray"){
                    RendreBluray rb = (RendreBluray) jb;
                    rb.testerPaneGetter(form.getText());
                } else if (jb.getClass().getName() == "Vue.PreConnexion"){
                    PreConnexion pcnx = (PreConnexion) jb;
                    pcnx.testerPaneGetter(form.getText());
                }
            }
        });


        Object[] options = {annuler,valider};
        tester = new JOptionPane(
                form,
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.YES_NO_OPTION);
        tester.setOptions(options);
        return tester;
    }

    protected static JOptionPane getOptionPane(JComponent parent) {
        JOptionPane pane;
        if (!(parent instanceof JOptionPane)) {
            pane = getOptionPane((JComponent)parent.getParent());
        } else {
            pane = (JOptionPane) parent;
        }
        return pane;
    }
}
