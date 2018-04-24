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
    private JLabel recettesLabel, codeBarresLabel, ordresLabel;
    private JComboBox recettesJCombox, codeBarreCombo;
    private ArrayList<String> recettes, codeBarres;
    private ArrayList<OrdrePreparation> ordres;
    private JButton retour, validation, reinitialiser;
    private AllOrdresPreparationModel allOrdresPreparationModel;
    private JTable jTable;
    private PanneauBienvenue panneauBienvenue;
    private Integer codeBarre = null;
    private String recette = null;

    public PanneauRecherche1()
    {
        setController(new ApplicationController());
        //Création des panneaux et de leurs layouts
        this.setLayout(new BorderLayout());
        panneauRecherche1 = new JPanel();
        panneauRecherche1.setLayout(new GridLayout(3, 2, 3, 3));
        panneauBoutons = new JPanel();
        panneauBoutons.setLayout(new FlowLayout());
        this.add(panneauRecherche1, BorderLayout.CENTER);
        this.add(panneauBoutons, BorderLayout.SOUTH);

        //Création des labels et des champs dans la grille
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
        recettesJCombox.addItem("Pas de recettes");
        for(String recetteNom : recettes)
        {
            recettesJCombox.addItem(recetteNom);
        }
        panneauRecherche1.add(recettesJCombox);

        codeBarresLabel = new JLabel("Code barre :");
        codeBarresLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        codeBarresLabel.setToolTipText("Référence vers le code barre à qui la préparation a été attribué");
        panneauRecherche1.add(codeBarresLabel);

        codeBarres = new ArrayList<>();
        try {
            codeBarres = controller.getCodeBarres();
        } catch (GeneralException e) {
            e.printStackTrace();
        }
        codeBarreCombo = new JComboBox();
        codeBarreCombo.addItem("Pas d'article");
        for(String codeBarre : codeBarres){
            codeBarreCombo.addItem(codeBarre);
        }
        panneauRecherche1.add(codeBarreCombo);

        ordresLabel = new JLabel("Résultat de la recherche : ");
        ordresLabel.setToolTipText("Ordres de préparations afficher en fonction de la recette et du cuisinier");
        ordresLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauRecherche1.add(ordresLabel);

        //On ajoute les boutons

        retour = new JButton("Retour");
        panneauBoutons.add(retour);
        validation = new JButton("Valider la recherche");
        panneauBoutons.add(validation);
        reinitialiser = new JButton("Reinitialiser");
        panneauBoutons.add(reinitialiser);

        //On ajoute les listeners aux boutons

        ButtonsAndTextsListener buttonsAndTextsListener = new ButtonsAndTextsListener();
        retour.addActionListener(buttonsAndTextsListener);
        validation.addActionListener(buttonsAndTextsListener);
        reinitialiser.addActionListener(buttonsAndTextsListener);
        ItemsListener itemsListener = new ItemsListener();
        recettesJCombox.addItemListener(itemsListener);
        codeBarreCombo.addItemListener(itemsListener);
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
                if (e.getSource() == reinitialiser)
                {
                    PanneauRecherche1.this.removeAll();
                    PanneauRecherche1.this.add(new PanneauRecherche1());
                    PanneauRecherche1.this.validate();
                }
                else
                {
                    if (e.getSource() == validation)
                    {
                        if (codeBarre != null && recette != null)
                        {
                            ordres = new ArrayList<>();

                            try
                            {
                                ordres = getController().getOrdresRecettesCuisiniers(codeBarre, recette);
                            }
                            catch (GetOrdresRecettesCuisiniersException eG)
                            {
                                System.out.println(eG.getMessage());
                            }
                            catch (ModelException eM)
                            {
                                System.out.println(eM.getMessage());
                            }

                            allOrdresPreparationModel = new AllOrdresPreparationModel(ordres);
                            jTable = new JTable(allOrdresPreparationModel);
                            jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                            JScrollPane jScrollPane = new JScrollPane(jTable);
                            panneauRecherche1.add(jScrollPane, BorderLayout.CENTER);
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
            if (e.getStateChange() == ItemEvent.SELECTED)
            {
                if (recettesJCombox.getSelectedItem().equals("Pas d'article"))
                {
                    recette = (String) recettesJCombox.getSelectedItem();
                }
                if (!codeBarreCombo.getSelectedItem().equals("Pas d'article"))
                {
                    String ordrePreparationSelection;
                    ordrePreparationSelection = codeBarreCombo.getSelectedItem().toString();
                    String [] motSepare = ordrePreparationSelection.split(" ");
                    codeBarre = Integer.parseInt(motSepare[0]);
                }
            }
        }
    }

    public ApplicationController getController() {
        return controller;
    }
}
