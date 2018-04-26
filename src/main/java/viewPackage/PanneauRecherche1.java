package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.GeneralException;
import exceptionPackage.GetOrdresRecettesCuisiniersException;
import exceptionPackage.ModelException;
import modelPackage.OrdrePreparation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class PanneauRecherche1 extends JPanel
{
    private JPanel panneauRecherche1, panneauBoutons;
    private ApplicationController controller;
    private JLabel recettesLabel, codeBarresLabel;
    private JComboBox recettesJCombox, matriculeCuiCombo;
    private ArrayList<String> recettes, matriculesCui;
    private ArrayList<OrdrePreparation> ordres;
    private JButton retour, validation, nouvRecherche;
    private AllOrdresPreparationModel allOrdresPreparationModel;
    private JTable jTable;
    private PanneauBienvenue panneauBienvenue;
    private Integer matri_Cui = null;
    private String recette = null;
    private PanneauFiller panneauFiller;

    public PanneauRecherche1()
    {
        setController(new ApplicationController());
        //Création des panneaux et de leurs layouts
        this.setLayout(new BorderLayout());
        panneauRecherche1 = new JPanel();
        panneauRecherche1.setLayout(new FlowLayout());
        panneauBoutons = new JPanel();
        panneauBoutons.setLayout(new FlowLayout());
        this.add(panneauRecherche1, BorderLayout.CENTER);
        this.add(panneauBoutons, BorderLayout.SOUTH);

        //Création des labels et des champs dans la grille
        panneauFiller = new PanneauFiller("<html><h3>Permet d'afficher les ordres de préparations en fonction d'une recette et d'un cuisinier :</h3></html>");
        this.add(panneauFiller, BorderLayout.NORTH);

        recettesLabel = new JLabel("Choisissez une recette : ");
        recettesLabel.setToolTipText("Recette des ordres de préparations");
        recettesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauRecherche1.add(recettesLabel);
        recettes = new ArrayList<>();
        try
        {
            recettes = controller.getAllRecetteNom();
        }
        catch (GeneralException e) {
            System.out.println("Erreur Recupération des noms de recette");  // Changer en autre que println (Afficher une erreur dans la JCOMBOBOX par ex
        }
        recettesJCombox = new JComboBox();
        for(String recetteNom : recettes)
        {
            recettesJCombox.addItem(recetteNom);
        }
        panneauRecherche1.add(recettesJCombox);

        codeBarresLabel = new JLabel("Matricule Cuisinier :");
        codeBarresLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        codeBarresLabel.setToolTipText("Référence vers le cuisinier à qui la préparation a été attribué");
        panneauRecherche1.add(codeBarresLabel);

        matriculesCui = new ArrayList<>();
        try {
            matriculesCui = controller.getMatriculesCui();
        } catch (GeneralException e) {
            e.printStackTrace();
        }
        matriculeCuiCombo = new JComboBox();
        for(String matriculeCui : matriculesCui){
            matriculeCuiCombo.addItem(matriculeCui);
        }
        panneauRecherche1.add(matriculeCuiCombo);

        //On ajoute les boutons

        retour = new JButton("Retour");
        panneauBoutons.add(retour);
        validation = new JButton("Valider la recherche");
        panneauBoutons.add(validation);
        nouvRecherche = new JButton("Nouvelle recherche");
        panneauBoutons.add(nouvRecherche);

        //On ajoute les listeners aux boutons

        ButtonsAndTextsListener buttonsAndTextsListener = new ButtonsAndTextsListener();
        retour.addActionListener(buttonsAndTextsListener);
        validation.addActionListener(buttonsAndTextsListener);
        nouvRecherche.addActionListener(buttonsAndTextsListener);
        ItemsListener itemsListener = new ItemsListener();
        recettesJCombox.addItemListener(itemsListener);
        matriculeCuiCombo.addItemListener(itemsListener);
    }

    public void setController(ApplicationController controller) {
        this.controller = controller;
    }

    private class ButtonsAndTextsListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == retour)
            {
                PanneauRecherche1.this.removeAll();
                PanneauRecherche1.this.panneauBienvenue = new PanneauBienvenue();
                PanneauRecherche1.this.add(panneauBienvenue);
                PanneauRecherche1.this.repaint();
                PanneauRecherche1.this.validate();
            }
            else
            {
                if (e.getSource() == nouvRecherche)
                {
                    PanneauRecherche1.this.removeAll();
                    PanneauRecherche1.this.add(new PanneauRecherche1());
                    PanneauRecherche1.this.validate();
                }
                else
                {
                    if (e.getSource() == validation)
                    {
                        recette = (String) recettesJCombox.getSelectedItem();

                        String ordrePreparationSelection;
                        ordrePreparationSelection = matriculeCuiCombo.getSelectedItem().toString();
                        String [] motSepare = ordrePreparationSelection.split(" ");
                        matri_Cui = Integer.parseInt(motSepare[0]);

                        if (!matriculesCui.isEmpty() && !recettes.isEmpty())
                        {
                            ordres = new ArrayList<>();

                            try
                            {
                                ordres = getController().getOrdresRecettesCuisiniers(matri_Cui, recette);
                            }
                            catch (GetOrdresRecettesCuisiniersException eG)
                            {
                                System.out.println(eG.getMessage());
                            }
                            catch (ModelException eM)
                            {
                                System.out.println(eM.getMessage());
                            }

                            if (!ordres.isEmpty()) {
                                allOrdresPreparationModel = new AllOrdresPreparationModel(ordres);
                                jTable = new JTable(allOrdresPreparationModel);
                                jTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                                JScrollPane jScrollPane = new JScrollPane(jTable);
                                //On remplace le panneau de la recherche1 par la jtable
                                panneauRecherche1.removeAll();
                                panneauRecherche1.setLayout(new BorderLayout());
                                panneauRecherche1.add(jScrollPane);
                                panneauRecherche1.repaint();
                                panneauRecherche1.validate();
                            }
                        }
                    }
                }
            }

        }
    }

    private class ItemsListener implements ItemListener
    {
        @Override
        public void itemStateChanged(ItemEvent e) {
        }
    }

    public ApplicationController getController() {
        return controller;
    }
}
