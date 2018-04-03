package viewPackage;

import javax.swing.*;
import java.awt.*;

public class PanneauBienvenue extends JPanel
{
    private JLabel bienvenue;

    public PanneauBienvenue ()
    {
        bienvenue = new JLabel("<html><h1>Bienvenue ! </h1></html>");
        this.setLayout(new BorderLayout());
        bienvenue.setHorizontalAlignment(0);
        this.setBackground(Color.RED);
        this.add(bienvenue);
    }
}
