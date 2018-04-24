package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.GeneralException;
import modelPackage.OrdrePreparation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanneauRecherche1 extends JPanel
{
    private JPanel panneauRecherche1, panneauBoutons;
    private ApplicationController controller;
    private JLabel recettesLabel, cuisiniersLabel;
    private JComboBox recettesJCombox, cuisiniersJCombox;
    private ArrayList<String> recettes, cuisiniers;
    private ArrayList<OrdrePreparation> ordres;
    private JButton retour, validation, reinitialiser;

    public PanneauRecherche1()
    {
        //Eventuellement créer un panneau au milieu qui affichera une area de texte !
        setController(new ApplicationController());
        //Création des panneaux et de leurs layouts
        this.setLayout(new BorderLayout());
        panneauRecherche1 = new JPanel();
        panneauRecherche1.setLayout(new GridLayout(2, 2, 3, 3));
        panneauBoutons = new JPanel();
        panneauBoutons.setLayout(new FlowLayout());
        this.add(panneauRecherche1, BorderLayout.NORTH);
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
        for(String recetteNom : recettes)
        {
            recettesJCombox.addItem(recetteNom);
        }
        panneauRecherche1.add(recettesJCombox);

        cuisiniersLabel = new JLabel("Matricule cuisinier :");
        cuisiniersLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        cuisiniersLabel.setToolTipText("Référence vers le cuisinier à qui la préparation a été attribué");
        panneauRecherche1.add(cuisiniersLabel);

        cuisiniers = new ArrayList<>();
        try
        {
            cuisiniers = controller.getMatriculesCui();
        }
        catch (GeneralException e) {
            e.printStackTrace();
        }
        cuisiniersJCombox = new JComboBox();
        for(String matriculeCui : cuisiniers){
            cuisiniersJCombox.addItem(matriculeCui);
        }
        panneauRecherche1.add(cuisiniersJCombox);

        //On ajoute les boutons

        retour = new JButton("Retour");
        panneauBoutons.add(retour);
        validation = new JButton("Valider");
        reinitialiser = new JButton("Reinitialiser");
        panneauBoutons.add(reinitialiser);

        //On ajoute les listeners aux boutons

        ButtonsAndTextsListener buttonsAndTextsListener = new ButtonsAndTextsListener();
        retour.addActionListener(buttonsAndTextsListener);
        validation.addActionListener(buttonsAndTextsListener);
        reinitialiser.addActionListener(buttonsAndTextsListener);
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
                PanneauRecherche1.this.add(new PanneauRecherche1());
                PanneauRecherche1.this.validate();
            }

        }
    }
}
