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
    private JPanel panneauFormulaire, panneauBoutons;
    private JLabel recetteLabel, dateLabel, numeroSequencielLabel, quantitePrevueLabel, quantiteProduiteLabel, dateVenteLabel, datePreparationLabel, remarqueLabel, estUrgentLabel, codeBarreLabel, matriculeCuiLabel, matriculeResLabel;
    private JTextField numeroSequentielText, quantitePrevueText, quantiteProduiteText, remarqueText;
    private JComboBox codeBarreCombo, matriculeCuiCombo, matriculeResCombo, recetteCombo;
    private ArrayList<String> recettes, codeBarres, matriculesCui, matriculesRes;
    private JButton validation, retour, reinitialiser;
    private PanneauBienvenue panneauBienvenue;
    private JRadioButton urgent, pasUrgent, ouiDateVente, nonDateVente, ouiDatePrep, nonDatePrep;
    private ButtonGroup buttonGroup, buttonGroupDateVente, buttonGroupDatePrep;
    private FonctionEcouteurs fonctionEcouteurs;
    private PanneauSpinnerDate spinnerDate, spinnerDateVente, spinnerDatePrep;
    private ApplicationController controller;
    private Integer dernierNumeroSequentiel;
    private OrdrePreparation ordrePreparation;

    public PanneauInsertion() {
        controller = new ApplicationController();
        //Création des panneaux et de leurs layouts
        this.setLayout(new BorderLayout());
        panneauFormulaire = new JPanel();
        panneauFormulaire.setLayout(new GridLayout(14, 2, 3, 3));
        /*panneauFormulaire.setBackground(Color.RED);*/
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

        recettes = new ArrayList<String>();
        try {
            recettes = controller.getAllRecetteNom();
        } catch (GeneralException e) {
            System.out.println("Erreur Recupération des noms de recette");  // Changer en autre que println (Afficher une erreur dans la JCOMBOBOX par ex
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

        // Faire un truc avec code Barre et date Vente (grisé quand pas relié a un code barre = null et non grisé quand relié a un code barre)
        dateVenteLabel = new JLabel("Date vente : ");
        dateVenteLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        dateVenteLabel.setToolTipText("[FACULTATIF] Date de mise en vente");
        panneauFormulaire.add(dateVenteLabel);
        spinnerDateVente = new PanneauSpinnerDate();
        panneauFormulaire.add(spinnerDateVente);

        ouiDateVente = new JRadioButton("Enregistrer la date de vente", false);
        ouiDateVente.setHorizontalAlignment(SwingConstants.LEFT);
        panneauFormulaire.add(ouiDateVente);
        nonDateVente = new JRadioButton("Ne pas enregistrer la date de vente", true);
        nonDateVente.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(nonDateVente);

        buttonGroupDateVente = new ButtonGroup();
        buttonGroupDateVente.add(ouiDateVente);
        buttonGroupDateVente.add(nonDateVente);

        datePreparationLabel = new JLabel("Date de préparation :");
        datePreparationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        datePreparationLabel.setToolTipText("[FACULTATIF] Date lors de la préparation");
        panneauFormulaire.add(datePreparationLabel);
        spinnerDatePrep = new PanneauSpinnerDate();
        panneauFormulaire.add(spinnerDatePrep);

        ouiDatePrep = new JRadioButton("Enregistrer la date de préparation", false);
        ouiDatePrep.setHorizontalAlignment(SwingConstants.LEFT);
        panneauFormulaire.add(ouiDatePrep);
        nonDatePrep = new JRadioButton("Ne pas enregistrer la date de préparation", true);
        nonDatePrep.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(nonDatePrep);

        buttonGroupDatePrep = new ButtonGroup();
        buttonGroupDatePrep.add(ouiDatePrep);
        buttonGroupDatePrep.add(nonDatePrep);

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

        codeBarres = new ArrayList<String>();
        try {
            codeBarres = controller.getCodeBarres();
        } catch (GeneralException e) {
            e.printStackTrace();
        }
        codeBarreCombo = new JComboBox();
        for(String codeBarre : codeBarres){
            codeBarreCombo.addItem(codeBarre);
        }
        panneauFormulaire.add(codeBarreCombo);

        matriculeCuiLabel = new JLabel("Matricule cuisinier :");
        matriculeCuiLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        matriculeCuiLabel.setToolTipText("Référence vers le cuisinier à qui la préparation a été attribué");
        panneauFormulaire.add(matriculeCuiLabel);

        matriculesCui = new ArrayList<String>();
        try {
            matriculesCui = controller.getMatriculesCui();
        } catch (GeneralException e) {
            e.printStackTrace();
        }
        matriculeCuiCombo = new JComboBox();
        for(String matriculeCui : matriculesCui){
            matriculeCuiCombo.addItem(matriculeCui);
        }
        panneauFormulaire.add(matriculeCuiCombo);


        matriculeResLabel = new JLabel("Matricule responsable :");
        matriculeResLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        matriculeResLabel.setToolTipText("Référence vers la responsable des ventes qui a créé l'ordre");
        panneauFormulaire.add(matriculeResLabel);

        matriculesRes = new ArrayList<String>();
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
        ItemListener itemListener = new ItemsListener();
        retour.addActionListener(buttonsAndTextsListener);
        validation.addActionListener(buttonsAndTextsListener);
        reinitialiser.addActionListener(buttonsAndTextsListener);
    }

    private class ButtonsAndTextsListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == retour)
            {
                PanneauInsertion.this.removeAll();
                PanneauInsertion.this.panneauBienvenue = new PanneauBienvenue();
                PanneauInsertion.this.add(panneauBienvenue);
                PanneauInsertion.this.repaint();
                PanneauInsertion.this.validate();
            }
            else
            {
                if (e.getSource() == validation)
                {
                    Integer quantitePrevue = -1;
                    try
                    {
                        quantitePrevue = Integer.valueOf(quantitePrevueText.getText());
                    }
                    catch (Exception error)
                    {
                        quantitePrevue = null;
                    }
                    finally
                    {
                        if (quantitePrevueText.getText().isEmpty() || quantitePrevue == null || quantitePrevue <= 0)
                        {
                            JOptionPane.showMessageDialog(null, "Quantité prévue incorrecte !", "Erreur", JOptionPane.ERROR_MESSAGE);
                            quantitePrevueText.setBackground(Color.RED);
                        }
                        else
                        {
                            quantitePrevueText.setBackground(Color.WHITE);
                        }
                    }

                    Integer quantiteProduite = null;
                    if (!quantiteProduiteText.getText().isEmpty())
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
                                JOptionPane.showMessageDialog(null, "Quantité produite incorrecte !", "Erreur", JOptionPane.ERROR_MESSAGE);
                                quantiteProduiteText.setBackground(Color.RED);
                            }
                            else
                            {
                                quantiteProduiteText.setBackground(Color.WHITE);
                            }
                        }
                    }
                    //Ajout de l'ordre dans la DB
                    ordrePreparation = new OrdrePreparation();
                    try
                    {
                        ordrePreparation.setDate(spinnerDate.getDate());
                        ordrePreparation.setNumeroSequentiel(dernierNumeroSequentiel);
                        ordrePreparation.setQuantitePrevue(quantitePrevue);
                        ordrePreparation.setQuantiteProduite(quantiteProduite);
                        if (ouiDateVente.isSelected())
                        {
                            ordrePreparation.setDateVente(spinnerDateVente.getDate());
                        }
                        else
                        {
                            ordrePreparation.setDateVente(null);
                        }
                        if (ouiDatePrep.isSelected())
                        {
                            ordrePreparation.setDatePreparation(spinnerDatePrep.getDate());
                        }
                        else
                        {
                            ordrePreparation.setDatePreparation(null);
                        }

                        ordrePreparation.setRemarque(remarqueText.getText());
                        ordrePreparation.setEstUrgent(urgent.isSelected());
                        ordrePreparation.setNom(recettes.get(recetteCombo.getSelectedIndex()));
                        char cB = codeBarres.get(codeBarreCombo.getSelectedIndex()).charAt(0);
                        Integer cBN = Character.getNumericValue(cB);
                        ordrePreparation.setCodeBarre(cBN);
                        char matriCui = matriculesCui.get(matriculeCuiCombo.getSelectedIndex()).charAt(0);
                        Integer matriC = Character.getNumericValue(matriCui);
                        ordrePreparation.setMatricule_Cui(matriC);
                        char matriRes = matriculesRes.get(matriculeResCombo.getSelectedIndex()).charAt(0);
                        Integer matriR = Character.getNumericValue(matriRes);
                        ordrePreparation.setMatricule_Res(matriR);

                        controller.addOrdre(ordrePreparation);
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
                else
                {
                    if (e.getSource() == reinitialiser)
                    {
                        spinnerDate.reinitialiserChamps();
                        spinnerDateVente.reinitialiserChamps();
                        spinnerDatePrep.reinitialiserChamps();
                        quantitePrevueText.setText("");
                        quantiteProduiteText.setText("");
                        remarqueText.setText("");
                    }
                }
            }
        }
    }

    private class ItemsListener implements ItemListener
    {
        public void itemStateChanged(ItemEvent e)
        {
        }
    }
}

// TEST DB
/*        DBAccess dBAccess = new DBAccess();
        Connection connection = dBAccess.getConnection();
        String sql = "SELECT * FROM dbgrandbazar.ordrepreparation where QuantitePrevue=4;";
        ResultSet data;
        String nomRecetteOrdre="";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            data = statement.executeQuery();
            while (data.next()){
                nomRecetteOrdre = data.getString("Nom");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(nomRecetteOrdre);*/
// FIN TEST DB