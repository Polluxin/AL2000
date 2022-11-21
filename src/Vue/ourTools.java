package Vue;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ourTools {

    /**
     * > transparentButtonWithIcon creates a button with an icon, and makes it transparent to only show the icon
     *
     * @param rss the name of the image file in the resources folder
     * @return A JButton with an icon.
     */
    public static JButton transparentButtonWithIcon(String rss){
        String pressedName = rss+"_pressed.png";
        // Create the button and initialise it
        JButton jb = new JButton();
        StretchIcon icon = ourPictures.getPicture(rss);
        StretchIcon pressed = ourPictures.getPicture(pressedName);

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
     * setFont loads a font from a file and sets it as the default font for all text fields, check boxes, password fields and
     * text areas
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
    }
}
