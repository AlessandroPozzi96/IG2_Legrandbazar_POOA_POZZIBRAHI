package viewPackage;

import javax.swing.*;
import java.awt.*;

public class PanneauGIF extends JPanel {
    private Icon icon;
    private JLabel gif;

    public PanneauGIF() {
        this.setLayout(new FlowLayout());
        icon = new ImageIcon("./caddy.gif");
        gif = new JLabel(icon);
        setBackground(Color.BLUE);
        setSize(icon.getIconWidth(), icon.getIconHeight());
        setMaximumSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
        this.add(gif);
    }
}
