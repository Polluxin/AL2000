package Vue;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ourTools {

    public static JButton transparentButtonWithIcon(String rss){
        /**
         * Creates a JButton that does not appear but has an icon set.
         */

        // Create the button and initialise it
        JButton jb = new JButton();
        StretchIcon icon = ourPictures.getPicture(rss);
        System.out.println(icon.getDescription());
        jb.setIcon(icon);

        // Makes the button disappear
        jb.setBorderPainted(false);
        jb.setContentAreaFilled(false);
        jb.setFocusPainted(false);
        //jb.setOpaque(false); // <-- https://stackoverflow.com/a/8367524
        jb.setVisible(true);

        return jb;
    }

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
    }
}
