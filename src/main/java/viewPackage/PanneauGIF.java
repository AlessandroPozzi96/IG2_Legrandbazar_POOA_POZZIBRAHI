package viewPackage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class PanneauGIF extends JPanel {
    private URL url;
    private Icon icon;
    private JLabel gif;

    public PanneauGIF() {
        this.setLayout(new FlowLayout());
        try {
            url = this.getClass().getResource("./gb.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        icon = new ImageIcon(url);
        gif = new JLabel(icon);
        this.add(gif);
    }
}
