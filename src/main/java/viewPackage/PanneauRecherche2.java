package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.GeneralException;
import exceptionPackage.ModelException;
import modelPackage.Recherche2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class PanneauRecherche2 extends JPanel
{
    private JPanel panneauRecherche2, panneauBoutons;
    private ApplicationController controller;
    private JLabel dateDebut, dateFin;
    private JButton retour, validation, nouvRecherche;
    private PanneauBienvenue panneauBienvenue;
    private PanneauFiller panneauFiller;
    private PanneauSpinnerDate spinnerDateDeb, spinnerDateFin;
    private ArrayList<Recherche2> recherches2;
    private GetRecherche2Model getRecherche2Model;
    private JTable jTable;

    public PanneauRecherche2() {
        setController(new ApplicationController());
        //On ajoute les différents panneaux et les layouts
        this.setLayout(new BorderLayout());
        panneauRecherche2 = new JPanel();
        panneauRecherche2.setLayout(new FlowLayout());
        this.add(panneauRecherche2, BorderLayout.CENTER);
        panneauBoutons = new JPanel();
        panneauBoutons.setLayout(new FlowLayout());
        this.add(panneauBoutons, BorderLayout.SOUTH);

        panneauFiller = new PanneauFiller("<html><h3>Permettre de lister les ordres de préparations vendu dans une intervalle de temps</h3></html>");
        this.add(panneauFiller, BorderLayout.NORTH);

        dateDebut = new JLabel("Date du début : ");
        dateDebut.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauRecherche2.add(dateDebut);

        spinnerDateDeb = new PanneauSpinnerDate();
        panneauRecherche2.add(spinnerDateDeb);

        dateFin = new JLabel("Date de fin : ");
        dateFin.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauRecherche2.add(dateFin);

        spinnerDateFin = new PanneauSpinnerDate();
        panneauRecherche2.add(spinnerDateFin);

        //Les boutons
        retour = new JButton("Retour");
        panneauBoutons.add(retour);
        validation = new JButton("Validation");
        panneauBoutons.add(validation);
        nouvRecherche = new JButton("Nouvelle recherche");
        panneauBoutons.add(nouvRecherche);

        //Ecouteurs
        ButtonsListener buttonsListener = new ButtonsListener();
        retour.addActionListener(buttonsListener);
        validation.addActionListener(buttonsListener);
        nouvRecherche.addActionListener(buttonsListener);
    }

    public ApplicationController getController() {
        return controller;
    }

    public void setController(ApplicationController controller) {
        this.controller = controller;
    }

    private class ButtonsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == retour)
            {
                PanneauRecherche2.this.removeAll();
                PanneauRecherche2.this.panneauBienvenue = new PanneauBienvenue();
                PanneauRecherche2.this.add(panneauBienvenue);
                PanneauRecherche2.this.repaint();
                PanneauRecherche2.this.validate();
            }
            else
            {
                if (e.getSource() == nouvRecherche) {
                    PanneauRecherche2.this.removeAll();
                    PanneauRecherche2.this.add(new PanneauRecherche2());
                    PanneauRecherche2.this.validate();
                }
                else
                {
                    if (e.getSource() == validation) {
                        recherches2 = new ArrayList<>();

                        try
                        {
                            recherches2 = controller.getRecherche2(spinnerDateDeb.getDate(), spinnerDateFin.getDate());

                        }
                        catch (ModelException eM)
                        {
                            System.out.println(eM.getMessage());
                        }
                        catch (GeneralException eG)
                        {
                            System.out.println(eG.getMessage());
                        }

                        getRecherche2Model = new GetRecherche2Model(recherches2);
                        jTable = new JTable(getRecherche2Model);
                        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                        JScrollPane jScrollPane = new JScrollPane(jTable);

                        //On remplace le panneau de la recherche1 par la jtable
                        panneauRecherche2.removeAll();
                        panneauRecherche2.setLayout(new BorderLayout());
                        panneauRecherche2.add(jScrollPane);
                        panneauRecherche2.repaint();
                        panneauRecherche2.validate();
                    }
                }
            }
        }
    }
}
