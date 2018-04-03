package viewPackage;

import javax.swing.*;
import java.awt.*;

public class FenetrePrincipale extends JFrame
{
    private Container frameContainer;
    private PanneauBienvenue panneauBienvenue;
    private JMenuBar menuBar;
    private JMenu application, quitter, crud, recherches, tacheMetier;
    private JMenuItem insertionOrdre, modifierOrdre, supprimerOrdre, listerOrdres;

    public FenetrePrincipale()
    {
        super("Welcome");
        setBounds(400, 30, 700, 750);
        //On ajoute le panneauBienvenu
        frameContainer = this.getContentPane();
        frameContainer.setLayout(new BorderLayout());
        panneauBienvenue = new PanneauBienvenue();
        frameContainer.add(panneauBienvenue, BorderLayout.CENTER);

        //On créé les différents menu
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        application = new JMenu("Application");
        menuBar.add(application);

        quitter = new JMenu("Quitter");
        menuBar.add(quitter);

        crud = new JMenu("CRUD");
        menuBar.add(crud);

        recherches = new JMenu("Recherches");
        menuBar.add(recherches);

        tacheMetier = new JMenu("Tâche métier ");
        menuBar.add(tacheMetier);

        //On rends visible la fenetre
        this.setVisible(true);
    }
}
