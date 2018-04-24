package viewPackage;

import javax.swing.*;
import java.awt.*;

public class PanneauFiller extends JPanel
{
    private String texte;
    private JLabel champ;

    public PanneauFiller(String texte) {
        setTexte(texte);
        this.setLayout(new BorderLayout());
        champ = new JLabel(texte);
        champ.setFont(new Font("Apple Casual", Font.PLAIN, 20));
        champ.setHorizontalAlignment(0);
        this.add(champ, BorderLayout.CENTER);
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }
}
