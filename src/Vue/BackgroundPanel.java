package Vue;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class BackgroundPanel extends Panneau {
    File f;
    StretchIcon background;
    public BackgroundPanel(){
        f = new File("res/ressources/Background.png");
        background = new StretchIcon(f.getAbsolutePath());
        this.setLayout(new BorderLayout());
    }
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(background.getImage(), 0, 0, super.getWidth(), super.getHeight(), null);
    }
}
