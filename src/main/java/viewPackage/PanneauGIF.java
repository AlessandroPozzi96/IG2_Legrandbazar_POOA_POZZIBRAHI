package viewPackage;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PanneauGIF extends JPanel {
    private URL gif;
    private Icon icon;
    private JLabel gifLabel;

    public PanneauGIF() {
        //On définit le layout
        this.setLayout(new BorderLayout());
        gif = this.getClass().getResource("./.gif");
        icon = new ImageIcon(gif);
        //Afin de permettre l'affichage du gif et l'ajouter au panneau
        gifLabel = new JLabel(icon);
        this.add(gifLabel, BorderLayout.CENTER);
    }
}
