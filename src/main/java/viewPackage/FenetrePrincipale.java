package viewPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetrePrincipale extends JFrame
{
    private Container frameContainer;
    private PanneauBienvenue panneauBienvenue;
    private PanneauInsertion panneauInsertion;
    private PanneauListing panneauListing;
    private PanneauModification panneauModification;
    private JMenuBar menuBar;
    private JMenu application, ordre, recherches, tacheMetier;
    private JMenuItem quitter, insertionOrdre, modifierOrdre, supprimerOrdre, listerOrdres, recherche1, recherche2, recherche3;

    public FenetrePrincipale()
    {
        super("Le grand bazar");
        setSize(600,650);
        setLocationRelativeTo(null);

        //On ajoute le panneauBienvenu
        frameContainer = this.getContentPane();
        frameContainer.setLayout(new BorderLayout());
        panneauBienvenue = new PanneauBienvenue();
        frameContainer.add(panneauBienvenue, BorderLayout.CENTER);

        //On créé les différents menu
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        application = new JMenu("Application");
        application.setMnemonic('A');
        menuBar.add(application);

        ordre = new JMenu("Ordre");
        ordre.setMnemonic('O');
        menuBar.add(ordre);

        recherches = new JMenu("Recherches");
        recherches.setMnemonic('R');
        menuBar.add(recherches);

        tacheMetier = new JMenu("Tâche métier ");
        tacheMetier.setMnemonic('M');
        menuBar.add(tacheMetier);

        //On ajoute les différents items lié aux menus
        quitter = new JMenuItem("Quitter");
        quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        application.add(quitter);

        insertionOrdre = new JMenuItem("Insertion d'un ordre");
        modifierOrdre = new JMenuItem("Modification d'un ordre");
        supprimerOrdre = new JMenuItem("Suppression d'un ordre");
        listerOrdres = new JMenuItem("Listing des ordres");
        ordre.add(insertionOrdre);
        ordre.addSeparator();
        ordre.add(modifierOrdre);
        ordre.addSeparator();
        ordre.add(supprimerOrdre);
        ordre.addSeparator();
        ordre.add(listerOrdres);

        //On ajoute les différents écouteurs
        this.addWindowListener(new ClosingListener());
        ButtonsListener buttonsListener = new ButtonsListener();
        quitter.addActionListener(buttonsListener);
        insertionOrdre.addActionListener(buttonsListener);
        modifierOrdre.addActionListener(buttonsListener);
        supprimerOrdre.addActionListener(buttonsListener);
        listerOrdres.addActionListener(buttonsListener);

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
                    frameContainer.validate();
                }
                else
                {
                    if (e.getSource() == modifierOrdre)
                    {
                        frameContainer.removeAll();
                        panneauModification = new PanneauModification();
                        frameContainer.add(panneauModification);
                        frameContainer.repaint();
                        frameContainer.validate();
                    }
                    else
                    {
                        if (e.getSource() == listerOrdres){
                            frameContainer.removeAll();
                            panneauListing = new PanneauListing();
                            frameContainer.add(panneauListing);
                            frameContainer.repaint();
                            frameContainer.validate();
                        }
                    }
                }
            }
        }
    }
}
