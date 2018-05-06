package viewPackage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class PanneauGIF extends JPanel {
    private Icon icon;
    private JLabel gif;

    public PanneauGIF() {
        this.setLayout(new FlowLayout());
        icon = new ImageIcon("./caddy.gif");
        gif = new JLabel(icon);
        setSize(new Dimension(10, 10));
        setBackground(Color.RED);
        this.add(gif);
    }
}
