package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AddOrdreException;
import exceptionPackage.GeneralException;
import exceptionPackage.ModelException;
import modelPackage.OrdrePreparation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class PanneauInsertion extends JPanel
{
    private JPanel panneauFormulaire, panneauBoutons,panneauDateVente,panneauDatePreparation;
    private JLabel recetteLabel, dateLabel, numeroSequencielLabel, quantitePrevueLabel, quantiteProduiteLabel, dateVenteLabel, datePreparationLabel, remarqueLabel, codeBarreLabel, matriculeCuiLabel, matriculeResLabel;
    private JTextField numeroSequentielText, quantitePrevueText, quantiteProduiteText, remarqueText;
    private JComboBox codeBarreCombo, matriculeCuiCombo, matriculeResCombo, recetteCombo;
    private ArrayList<String> recettes, codeBarres, matriculesCui, matriculesRes;
    private JButton validation, retour, reinitialiser;
    private PanneauBienvenue panneauBienvenue;
    private JRadioButton urgent, pasUrgent;
    private ButtonGroup buttonGroup;
    private PanneauSpinnerDate spinnerDate, spinnerDateVente, spinnerDatePrep;
    private ApplicationController controller;
    private Integer dernierNumeroSequentiel;
    private OrdrePreparation ordrePreparation;
    private JCheckBox bouttonDateVente, bouttonDatePreparation;
    private  String [] motSepare;

    public PanneauInsertion()
    {
        controller = new ApplicationController();
        //Création des panneaux et de leurs layouts
        this.setLayout(new BorderLayout());
        panneauFormulaire = new JPanel();
        panneauFormulaire.setLayout(new GridLayout(12, 2, 3, 3));
        panneauBoutons = new JPanel();
        /*panneauBoutons.setBackground(Color.RED);*/
        panneauBoutons.setLayout(new FlowLayout());
        this.add(panneauFormulaire, BorderLayout.CENTER);
        this.add(panneauBoutons, BorderLayout.SOUTH);

        //Création des labels dans la grille (12 lignes)
        recetteLabel = new JLabel("Recette :");
        recetteLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        recetteLabel.setToolTipText("Nom de la recette");
        panneauFormulaire.add(recetteLabel);

        recettes = new ArrayList<>();
        try {
            recettes = controller.getAllRecetteNom();
        } catch (GeneralException e) {
            System.out.println("Erreur Recupération des noms de recette");  // Changer en autre que println (Afficher une erreur dans la JCOMBOBOX par ex
            JOptionPane.showMessageDialog(null, "Erreur ! \n Impossible de se connecter à la base de donnée \n Veuillez réessayer plus tard", "Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        recetteCombo = new JComboBox();
        for(String recetteNom : recettes){
            recetteCombo.addItem(recetteNom);
        }
        panneauFormulaire.add(recetteCombo);

        dateLabel = new JLabel("Date :");
        dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        dateLabel.setToolTipText("Entrez la date de la création de l'ordre");
        panneauFormulaire.add(dateLabel);
        spinnerDate = new PanneauSpinnerDate();
        panneauFormulaire.add(spinnerDate);


        //Récupérer le numéro du dernier ordre introduit puis l'incrémenter
        numeroSequencielLabel = new JLabel("Numéro séquentiel :");
        numeroSequencielLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        numeroSequencielLabel.setToolTipText("Numéro séquentiel auto incrémenté");
        panneauFormulaire.add(numeroSequencielLabel);
        try
        {
            dernierNumeroSequentiel = controller.getNumeroSquentiel() + 1; //On ajoute 1 pour le nouveau ordre qui va être créer
        }
        catch (GeneralException e)
        {
            System.out.println("Erreur le dernier numéro séquentiel ne peut pas être obtenu");
        }
        numeroSequentielText = new JTextField();
        numeroSequentielText.setText(String.valueOf(dernierNumeroSequentiel));
        numeroSequentielText.setEnabled(false);
        panneauFormulaire.add(numeroSequentielText);

        quantitePrevueLabel = new JLabel("Quantité prévue :");
        quantitePrevueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        quantitePrevueLabel.setToolTipText("Quantité prévue à la création de l'ordre");
        panneauFormulaire.add(quantitePrevueLabel);
        quantitePrevueText = new JTextField();
        panneauFormulaire.add(quantitePrevueText);

        quantiteProduiteLabel = new JLabel("Quantité produite : ");
        quantiteProduiteLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        quantiteProduiteLabel.setToolTipText("[FACULTATIF] Quantité effectivement produite");
        panneauFormulaire.add(quantiteProduiteLabel);
        quantiteProduiteText = new JTextField();
        panneauFormulaire.add(quantiteProduiteText);

        bouttonDateVente = new JCheckBox("Encoder une date vente");
        bouttonDateVente.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(bouttonDateVente);

        bouttonDateVente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spinnerDateVente.getSpinnerDate().setEnabled(bouttonDateVente.isSelected());
            }
        });

        panneauDateVente = new JPanel();
        dateVenteLabel = new JLabel("Date vente : ");
        dateVenteLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        dateVenteLabel.setToolTipText("[FACULTATIF] Date de mise en vente");
        panneauDateVente.add(dateVenteLabel);
        spinnerDateVente = new PanneauSpinnerDate();
        spinnerDateVente.getSpinnerDate().setEnabled(false);
        panneauDateVente.add(spinnerDateVente);
        panneauFormulaire.add(panneauDateVente);

        bouttonDatePreparation = new JCheckBox("Encoder une date préparation");
        bouttonDatePreparation.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(bouttonDatePreparation);

        bouttonDatePreparation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spinnerDatePrep.getSpinnerDate().setEnabled(bouttonDatePreparation.isSelected());
            }
        });

        panneauDatePreparation = new JPanel();
        datePreparationLabel = new JLabel("Date de préparation :");
        datePreparationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        datePreparationLabel.setToolTipText("[FACULTATIF] Date lors de la préparation");
        panneauDatePreparation.add(datePreparationLabel);
        spinnerDatePrep = new PanneauSpinnerDate();
        spinnerDatePrep.getSpinnerDate().setEnabled(false);
        panneauDatePreparation.add(spinnerDatePrep);
        panneauFormulaire.add(panneauDatePreparation);

        remarqueLabel = new JLabel("Remarque : ");
        remarqueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        remarqueLabel.setToolTipText("[FACULTATIF] Indiquez une remarque");
        panneauFormulaire.add(remarqueLabel);
        remarqueText = new JTextField();
        panneauFormulaire.add(remarqueText);

        urgent = new JRadioButton("Est urgent", false);
        urgent.setHorizontalAlignment(SwingConstants.RIGHT);
        /*urgent.setBackground(Color.RED);*/
        panneauFormulaire.add(urgent);
        pasUrgent = new JRadioButton("N'est pas urgent", true);
        pasUrgent.setHorizontalAlignment(SwingConstants.LEFT);
        /*pasUrgent.setBackground(Color.RED);*/
        panneauFormulaire.add(pasUrgent);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(urgent);
        buttonGroup.add(pasUrgent);

        codeBarreLabel = new JLabel("Code barre :");
        codeBarreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        codeBarreLabel.setToolTipText("Référence vers le type d'article quand il sera mis en vente");
        panneauFormulaire.add(codeBarreLabel);

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
        panneauFormulaire.add(codeBarreCombo);

        matriculeCuiLabel = new JLabel("Matricule cuisinier :");
        matriculeCuiLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        matriculeCuiLabel.setToolTipText("Référence vers le cuisinier à qui la préparation a été attribué");
        panneauFormulaire.add(matriculeCuiLabel);

        matriculesCui = new ArrayList<>();
        try {
            matriculesCui = controller.getMatriculesCui();
        } catch (GeneralException e) {
            e.printStackTrace();
        }
        matriculeCuiCombo = new JComboBox();
        matriculeCuiCombo.addItem("Pas de cuisinier");
        for(String matriculeCui : matriculesCui){
            matriculeCuiCombo.addItem(matriculeCui);
        }
        panneauFormulaire.add(matriculeCuiCombo);

        matriculeResLabel = new JLabel("Matricule responsable :");
        matriculeResLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        matriculeResLabel.setToolTipText("Référence vers la responsable des ventes qui a créé l'ordre");
        panneauFormulaire.add(matriculeResLabel);

        matriculesRes = new ArrayList<>();
        try {
            matriculesRes = controller.getMatriculesRes();
        } catch (GeneralException e) {
            e.printStackTrace();
        }
        matriculeResCombo = new JComboBox();
        for(String matriculeRes : matriculesRes){
            matriculeResCombo.addItem(matriculeRes);
        }
        panneauFormulaire.add(matriculeResCombo);

        //Ajout des boutons au panneauBoutons
        retour = new JButton("Retour");
        panneauBoutons.add(retour);
        validation = new JButton("Validation");
        panneauBoutons.add(validation);
        reinitialiser = new JButton("Réinitialiser");
        panneauBoutons.add(reinitialiser);

        //On ajoute les action aux listeners
        ButtonsAndTextsListener buttonsAndTextsListener = new ButtonsAndTextsListener();
        retour.addActionListener(buttonsAndTextsListener);
        validation.addActionListener(buttonsAndTextsListener);
        reinitialiser.addActionListener(buttonsAndTextsListener);
    }

    private class ButtonsAndTextsListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == retour)
            {
                changementPanneau(new PanneauBienvenue());
            }
            else
            {
                if (e.getSource() == validation)
                {
                    validation();
                }
                else
                {
                    if (e.getSource() == reinitialiser)
                    {
                        changementPanneau(new PanneauInsertion());
                    }
                }
            }
        }
    }
    public void changementPanneau (JPanel panneau)
    {
        PanneauInsertion.this.removeAll();
        PanneauInsertion.this.add(panneau);
        PanneauInsertion.this.validate();
    }

    public void validation ()
    {
        Integer quantitePrevue = controller.conversionStringVersInteger(quantitePrevueText.getText());

        if (quantitePrevueText.getText().isEmpty() || quantitePrevue == null || quantitePrevue <= 0) // le isEmpty est inutile (me semble) car si il est vide quantitéprévue = -1 donc erreur
        {
            JOptionPane.showMessageDialog(null, "Quantité prévue incorrecte !\nVeuillez entrez un nombre positif.", "Erreur", JOptionPane.ERROR_MESSAGE);
            quantitePrevueText.setBackground(Color.RED);
        }
        /*else
        {
            quantitePrevueText.setBackground(Color.WHITE);
        }*/// --> inutile car on réinitialise le panneau d'office

        Integer quantiteProduite = null;
        if (! quantiteProduiteText.getText().isEmpty())
        {
            try
            {
                quantiteProduite = Integer.valueOf(quantiteProduiteText.getText());
            }
            catch (Exception error)
            {
                quantiteProduite = null;
            }
            finally
            {
                if (quantiteProduite == null || quantiteProduite <= 0)
                {
                    JOptionPane.showMessageDialog(null, "Quantité produite incorrecte !\nVeuillez entrez un nombre positif, ou aucune valeur.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    quantiteProduiteText.setBackground(Color.RED);
                }
                /*else
                {
                    quantiteProduiteText.setBackground(Color.WHITE);
                }*/  // --> inutile car on réinitialise le panneau d'office
            }
        }
        //Ajout de l'ordre dans la DB
        ordrePreparation = new OrdrePreparation();
        try
        {
            ordrePreparation.setDate(spinnerDate.getDate());
            /*ordrePreparation.setNumeroSequentiel(dernierNumeroSequentiel);*/
            ordrePreparation.setNumeroSequentiel(0);
            ordrePreparation.setQuantitePrevue(quantitePrevue);
            ordrePreparation.setQuantiteProduite(quantiteProduite);
            if (bouttonDateVente.isSelected())
            {
                ordrePreparation.setDateVente(spinnerDateVente.getDate());
            }
            else
            {
                ordrePreparation.setDateVente(null);
            }
            if (bouttonDatePreparation.isSelected())
            {
                ordrePreparation.setDatePreparation(spinnerDatePrep.getDate());
            }
            else
            {
                ordrePreparation.setDatePreparation(null);
            }
            if(remarqueText.getText().equals("")){
                ordrePreparation.setRemarque(null);  // Met a null car facultatif
            }else{
                ordrePreparation.setRemarque(remarqueText.getText());
            }
            ordrePreparation.setEstUrgent(urgent.isSelected());

            ordrePreparation.setNom(recettes.get(recetteCombo.getSelectedIndex()));

            if(codeBarreCombo.getSelectedIndex()==0 || codeBarreCombo.getSelectedItem().equals("Pas d'arcticle")){
                ordrePreparation.setCodeBarre(null);
            }
            else{
                motSepare = codeBarreCombo.getSelectedItem().toString().split(" ");
                Integer cBN = Integer.parseInt(motSepare[0]);
                ordrePreparation.setCodeBarre(cBN);
            }
            if(matriculeCuiCombo.getSelectedIndex()==0 || matriculeCuiCombo.getSelectedItem().equals("Pas de cuisinier")){
                ordrePreparation.setMatricule_Cui(null);
            }
            else{
                motSepare = matriculeCuiCombo.getSelectedItem().toString().split(" ");
                Integer matriC = Integer.parseInt(motSepare[0]);
                ordrePreparation.setMatricule_Cui(matriC);
            }
            motSepare = matriculeResCombo.getSelectedItem().toString().split(" ");
            //char matriRes = matriculesRes.get(matriculeResCombo.getSelectedIndex()).charAt(0);
            Integer matriR = Integer.parseInt(motSepare[0]);
            ordrePreparation.setMatricule_Res(matriR);
            controller.addOrdre(ordrePreparation);

            //Affichage d'un message de confirmation  de l'insertion + réinitialisation des champs
            JOptionPane.showMessageDialog(null, "Confirmation de l'insertion de l'ordre !", "Information", JOptionPane.INFORMATION_MESSAGE);
            changementPanneau(new PanneauInsertion());
        }
        catch (ModelException eME)
        {
            eME.getMessage();
        }
        catch (AddOrdreException eAO)
        {
            eAO.getMessage();
        }
    }
}