package viewPackage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class FenetrePrincipale extends JFrame
{
    private Container frameContainer;
    private PanneauBienvenue panneauBienvenue;
    private PanneauInsertion panneauInsertion;
    private PanneauListing panneauListing;
    private PanneauModification panneauModification;
    private PanneauSuppression panneauSuppression;
    private PanneauRecherche1 panneauRecherche1;
    private PanneauRecherche2 panneauRecherche2;
    private PanneauRecherche3 panneauRecherche3;
    private JMenuBar menuBar;
    private JMenu application, ordre, recherches, tacheMetier;
    private JMenuItem quitter, insertionOrdre, modifierOrdre, supprimerOrdre, listerOrdres, recherche1, recherche2, recherche3, rechercheMoyenneJour;
    private GifAnimationThread gifAnimationThread;
    private JPanel panneauCaddy;

    public FenetrePrincipale()
    {
        super("Le grand bazar");
        setSize(600,650);
        setLocationRelativeTo(null);

        //On ajoute le panneauBienvenue
        frameContainer = this.getContentPane();
        frameContainer.setLayout(new BorderLayout());
        frameContainer.setBackground(Color.red);
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

        recherche1 = new JMenuItem("Recherche 1");
        recherche1.setToolTipText("Listing des ordres de préparations");
        recherche2 = new JMenuItem("Recherche 2");
        recherche2.setToolTipText("Listing des ordres de préparations vendus");
        recherche3 = new JMenuItem("Recherche 3");
        recherche3.setToolTipText("Listing des articles achetés par un client");
        recherches.add(recherche1);
        recherches.addSeparator();
        recherches.add(recherche2);
        recherches.addSeparator();
        recherches.add(recherche3);

        rechercheMoyenneJour = new JMenuItem("Recherche moyenne du jour");
        tacheMetier.add(rechercheMoyenneJour);

        //On ajoute une icone
        try{
            setIconImage(ImageIO.read(new File("./gb.png")));
        }
        catch (Exception ex){
            System.out.println("Icon non chargée");
        }

        //Test d'affichage du gif
        panneauCaddy = new JPanel();
        panneauCaddy.setBackground(Color.RED);
        frameContainer.add(panneauCaddy, BorderLayout.SOUTH);
        gifAnimationThread = new GifAnimationThread(panneauCaddy);
        gifAnimationThread.start();

        //On ajoute les différents écouteurs
        this.addWindowListener(new ClosingListener());
        ButtonsListener buttonsListener = new ButtonsListener();
        quitter.addActionListener(buttonsListener);
        insertionOrdre.addActionListener(buttonsListener);
        modifierOrdre.addActionListener(buttonsListener);
        supprimerOrdre.addActionListener(buttonsListener);
        listerOrdres.addActionListener(buttonsListener);
        recherche1.addActionListener(buttonsListener);
        recherche2.addActionListener(buttonsListener);
        recherche3.addActionListener(buttonsListener);
        rechercheMoyenneJour.addActionListener(buttonsListener);
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
                        else{
                            if(e.getSource() == supprimerOrdre){
                                frameContainer.removeAll();
                                panneauSuppression = new PanneauSuppression();
                                frameContainer.add(panneauSuppression);
                                frameContainer.repaint();
                                frameContainer.validate();
                            }
                            else
                            {
                                if (e.getSource() == recherche1)
                                {
                                    frameContainer.removeAll();
                                    panneauRecherche1 = new PanneauRecherche1();
                                    frameContainer.add(panneauRecherche1);
                                    frameContainer.repaint();
                                    frameContainer.validate();
                                }
                                else
                                {
                                    if (e.getSource() == recherche2) {
                                        frameContainer.removeAll();
                                        panneauRecherche2 = new PanneauRecherche2();
                                        frameContainer.add(panneauRecherche2);
                                        frameContainer.repaint();
                                        frameContainer.validate();
                                    }
                                    else{
                                        if(e.getSource() == rechercheMoyenneJour){
                                            frameContainer.removeAll();
                                            frameContainer.add(new PanneauTacheMetier());
                                            frameContainer.repaint();
                                            frameContainer.validate();
                                        }
                                        else
                                        {
                                            if (e.getSource() == recherche3) {
                                                frameContainer.removeAll();
                                                frameContainer.add(new PanneauRecherche3());
                                                frameContainer.repaint();
                                                frameContainer.validate();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
