package viewPackage;

import javax.swing.*;
import java.awt.*;

public class FenetrePrincipale extends JFrame
{
    private Container frameContainer;
    private PanneauBienvenue panneauBienvenue;
    private JMenuBar menuBar;
    private JMenu application, insertionOrdre, modifierOrdre, supprimerOrdre, listerOrdres, recherche, tacheMetier;
    private JMenuItem jMenuItem;

    public FenetrePrincipale()
    {
        super("Welcome");
        setBounds(400, 150, 300, 500);
        //On ajoute le panneauBienvenu
        frameContainer = this.getContentPane();
        frameContainer.setLayout(new BorderLayout());
        panneauBienvenue = new PanneauBienvenue();
        frameContainer.add(panneauBienvenue);

        //On rends visible la fenetre
        this.setVisible(true);
    }
}
