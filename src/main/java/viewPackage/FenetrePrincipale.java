package viewPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FenetrePrincipale extends JFrame
{
    private Container frameContainer;
    private PanneauBienvenue panneauBienvenue;
    private PanneauInsertion panneauInsertion;
    private JMenuBar menuBar;
    private JMenu application, crud, recherches, tacheMetier;
    private JMenuItem quitter, insertionOrdre, modifierOrdre, supprimerOrdre, listerOrdres, recherche1, recherche2, recherche3;

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

        crud = new JMenu("CRUD");
        menuBar.add(crud);

        recherches = new JMenu("Recherches");
        menuBar.add(recherches);

        tacheMetier = new JMenu("Tâche métier ");
        menuBar.add(tacheMetier);

        //On ajoute les différents items lié aux menus
        quitter = new JMenuItem("Quitter");
        application.add(quitter);

        insertionOrdre = new JMenuItem("Insertion d'un ordre");
        modifierOrdre = new JMenuItem("Modification d'un ordre");
        supprimerOrdre = new JMenuItem("Suppression d'un ordre");
        listerOrdres = new JMenuItem("Listing des ordres");
        crud.add(insertionOrdre);
        crud.addSeparator();
        crud.add(modifierOrdre);
        crud.addSeparator();
        crud.add(supprimerOrdre);
        crud.addSeparator();
        crud.add(listerOrdres);

        //On ajoute les différents écouteurs
        this.addWindowListener(new ClosingListener());
        ButtonsListener buttonsListener = new ButtonsListener();
        quitter.addActionListener(buttonsListener);
        insertionOrdre.addActionListener(buttonsListener);

        //On rends visible la fenetre
        this.setVisible(true);
    }

    private class ClosingListener extends WindowAdapter
    {
        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }

    private class ButtonsListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == quitter)
            {
                //A changer dés qu'on aura accès à la DB !
                System.exit(0);
            }
            else
            {
                if (e.getSource() == insertionOrdre)
                {
                    frameContainer.removeAll();
                    panneauInsertion = new PanneauInsertion();
                    frameContainer.add(panneauInsertion);
                    frameContainer.repaint();
                    frameContainer.validate();
                }
            }
        }
    }
}
