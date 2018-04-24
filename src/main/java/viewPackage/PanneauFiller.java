package viewPackage;

import javax.swing.*;
import java.awt.*;

public class PanneauFiller extends JPanel
{
    private String texte;
    private JTextField champ;

    public PanneauFiller(String texte) {
        setTexte(texte);
        this.setLayout(new BorderLayout());
        champ = new JTextField(texte);
        this.add(champ, BorderLayout.CENTER);
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }
}
