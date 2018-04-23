package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.*;
import modelPackage.OrdrePreparation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class PanneauModification extends JPanel
{
    private JPanel panneauModifications, panneauBoutons;
    private ArrayList<OrdrePreparation> ordres;
    private JComboBox ordresJCombo, recetteCombo, codeBarreCombo, matriculeCuiCombo, matriculeResCombo;
    private JLabel ordresLabel, recetteLabel, quantitePrevueLabel, quantiteProduiteLabel, dateVenteLabel, datePreparationLabel, remarqueLabel, codeBarreLabel, matriculeCuiLabel, matriculeResLabel;
    private JTextField quantitePrevueText, quantiteProduiteText, remarqueText;
    private JButton validation, retour, reinitialiser;
    private PanneauBienvenue panneauBienvenue;
    private JRadioButton urgent, pasUrgent, ouiDateVente, nonDateVente, ouiDatePrep, nonDatePrep;
    private ButtonGroup buttonGroup, buttonGroupDateVente, buttonGroupDatePrep;
    private PanneauSpinnerDate spinnerDateVente, spinnerDatePrep;
    private ApplicationController controller;
    private DateFormat dateFormat;
    private ArrayList<String> recettes, codeBarres, matriculesCui, matriculesRes;
    private OrdrePreparation ordrePreparation;
    private Integer iOrdre = null;
    private FonctionEcouteurs fonctionEcouteurs;

    public PanneauModification()
    {
        //On créé les différents panneaux
        this.setLayout(new BorderLayout());
        panneauModifications = new JPanel();
        panneauModifications.setLayout(new GridLayout(13, 2, 3, 3));
        this.add(panneauModifications, BorderLayout.CENTER);
        panneauBoutons = new JPanel();
        panneauBoutons.setLayout(new FlowLayout());
        this.add(panneauBoutons, BorderLayout.SOUTH);

        //On créé les labels + les champs
        ordresLabel = new JLabel("Listes des ordres :");
        ordresLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        ordresLabel.setToolTipText("Choississez l'ordre que vous voulez modifier ");
        panneauModifications.add(ordresLabel);
        setController(new ApplicationController());

        ordres = new ArrayList<>();
        try
        {
            ordres = controller.getAllOrdres();
        }
        catch (AllOrdresException e)
        {
            e.printStackTrace();
        }
        catch (ModelException e)
        {
            e.printStackTrace();
        }
        ordresJCombo = new JComboBox();
        String dateString = "";
        for (OrdrePreparation ordresCombox : ordres)
        {
            dateString = conversionDateVersString(ordresCombox.getDate());
            ordresJCombo.addItem(ordresCombox.getNumeroSequentiel() + " -> " + dateString);
        }
        ordresJCombo.setMaximumRowCount(5);
        ordresJCombo.setSelectedIndex(0);
        panneauModifications.add(ordresJCombo);

        recetteLabel = new JLabel("Recette :");
        recetteLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        recetteLabel.setToolTipText("Nom de la recette à changer");
        panneauModifications.add(recetteLabel);

        recettes = new ArrayList<>();
        try
        {
            recettes = controller.getAllRecetteNom();
        } catch (GeneralException e)
        {
            System.out.println("Erreur Recupération des noms de recette");  // Changer en autre que println (Afficher une erreur dans la JCOMBOBOX par ex
        }
        recetteCombo = new JComboBox();
        for(String recetteNom : recettes){
            recetteCombo.addItem(recetteNom);
        }
        panneauModifications.add(recetteCombo);

        quantitePrevueLabel = new JLabel("Quantité prévue :");
        quantitePrevueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        quantitePrevueLabel.setToolTipText("Quantité prévue à la création de l'ordre");
        panneauModifications.add(quantitePrevueLabel);
        quantitePrevueText = new JTextField();
        panneauModifications.add(quantitePrevueText);

        quantiteProduiteLabel = new JLabel("Quantité produite : ");
        quantiteProduiteLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        quantiteProduiteLabel.setToolTipText("Quantité effectivement produite");
        panneauModifications.add(quantiteProduiteLabel);
        quantiteProduiteText = new JTextField();
        panneauModifications.add(quantiteProduiteText);

        dateVenteLabel = new JLabel("Date vente : ");
        dateVenteLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        dateVenteLabel.setToolTipText("Date de mise en vente");
        panneauModifications.add(dateVenteLabel);
        spinnerDateVente = new PanneauSpinnerDate();
        panneauModifications.add(spinnerDateVente);

        ouiDateVente = new JRadioButton("Modifier la date de vente", false);
        ouiDateVente.setHorizontalAlignment(SwingConstants.LEFT);
        panneauModifications.add(ouiDateVente);
        nonDateVente = new JRadioButton("Ne pas modifier la date de vente", true);
        nonDateVente.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauModifications.add(nonDateVente);

        buttonGroupDateVente = new ButtonGroup();
        buttonGroupDateVente.add(ouiDateVente);
        buttonGroupDateVente.add(nonDateVente);

        datePreparationLabel = new JLabel("Date de préparation :");
        datePreparationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauModifications.add(datePreparationLabel);
        spinnerDatePrep = new PanneauSpinnerDate();
        panneauModifications.add(spinnerDatePrep);

        ouiDatePrep = new JRadioButton("Modifier la date de préparation", false);
        ouiDatePrep.setHorizontalAlignment(SwingConstants.LEFT);
        panneauModifications.add(ouiDatePrep);
        nonDatePrep = new JRadioButton("Ne pas modifier la date de préparation", true);
        nonDatePrep.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauModifications.add(nonDatePrep);

        buttonGroupDatePrep = new ButtonGroup();
        buttonGroupDatePrep.add(ouiDatePrep);
        buttonGroupDatePrep.add(nonDatePrep);

        remarqueLabel = new JLabel("Remarque : ");
        remarqueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauModifications.add(remarqueLabel);
        remarqueText = new JTextField();
        panneauModifications.add(remarqueText);

        urgent = new JRadioButton("Est urgent", false);
        urgent.setHorizontalAlignment(SwingConstants.LEFT);
        /*urgent.setBackground(Color.RED);*/
        panneauModifications.add(urgent);
        pasUrgent = new JRadioButton("N'est pas urgent", true);
        pasUrgent.setHorizontalAlignment(SwingConstants.RIGHT);
        /*pasUrgent.setBackground(Color.RED);*/
        panneauModifications.add(pasUrgent);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(urgent);
        buttonGroup.add(pasUrgent);

        codeBarreLabel = new JLabel("Code barre :");
        codeBarreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        codeBarreLabel.setToolTipText("Référence vers le type d'article quand il sera mis en vente");
        panneauModifications.add(codeBarreLabel);

        codeBarres = new ArrayList<>();
        try {
            codeBarres = controller.getCodeBarres();
        } catch (GeneralException e) {
            e.printStackTrace();
        }
        codeBarreCombo = new JComboBox();
        for(String codeBarre : codeBarres){
            codeBarreCombo.addItem(codeBarre);
        }
        panneauModifications.add(codeBarreCombo);

        matriculeCuiLabel = new JLabel("Matricule cuisinier :");
        matriculeCuiLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        matriculeCuiLabel.setToolTipText("Référence vers le cuisinier à qui la préparation a été attribué");
        panneauModifications.add(matriculeCuiLabel);

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
        panneauModifications.add(matriculeCuiCombo);


        matriculeResLabel = new JLabel("Matricule responsable :");
        matriculeResLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        matriculeResLabel.setToolTipText("Référence vers la responsable des ventes qui a créé l'ordre");
        panneauModifications.add(matriculeResLabel);

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
        panneauModifications.add(matriculeResCombo);

        //Ajout des boutons au panneauBoutons
        retour = new JButton("Retour");
        panneauBoutons.add(retour);
        validation = new JButton("Validation");
        panneauBoutons.add(validation);
        reinitialiser = new JButton("Réinitialiser");
        panneauBoutons.add(reinitialiser);

        //Ajout des écouteurs aux boutons
        ButtonsAndTextsListener buttonsAndTextsListener = new ButtonsAndTextsListener();
        JComboxListener jComboxListener = new JComboxListener();
        retour.addActionListener(buttonsAndTextsListener);
        validation.addActionListener(buttonsAndTextsListener);
        reinitialiser.addActionListener(buttonsAndTextsListener);
        ordresJCombo.addItemListener(jComboxListener);
    }

    private class ButtonsAndTextsListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == retour)
            {
                PanneauModification.this.removeAll();
                PanneauModification.this.panneauBienvenue = new PanneauBienvenue();
                PanneauModification.this.add(panneauBienvenue);
                PanneauModification.this.repaint();
                PanneauModification.this.validate();
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
                       reinitialiser();
                    }
                }
            }
        }
    }

    private class JComboxListener implements ItemListener
    {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED)
            {
                iOrdre = ordresJCombo.getSelectedIndex();
                String ordre = "<html>" +
                        "<h1> Date de création :" + conversionDateVersString(ordres.get(iOrdre).getDate()) + " Numéro séquentiel :" + ordres.get(iOrdre).getNumeroSequentiel() + "</h1>" +
                        "<h2>" + ordres.get(iOrdre).getNom() + "</h2>" +
                        "<h3> "+ ordres.get(iOrdre).getQuantitePrevue() + "</h3>" +
                        "</html>";
                JOptionPane.showMessageDialog(null, ordre, "Ordre de préparation", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void setController(ApplicationController controller) {
        this.controller = controller;
    }

    public void reinitialiser ()
    {
        spinnerDateVente.reinitialiserChamps();
        spinnerDatePrep.reinitialiserChamps();
        quantitePrevueText.setText("");
        quantiteProduiteText.setText("");
        remarqueText.setText("");
    }

    public void validation ()
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
        //Modification de l'ordre dans la DB
        ordrePreparation = new OrdrePreparation();
        try
        {
            //Récupération de la date et du numéro séquentiel via le JCombobox
            ordrePreparation.setDate(ordres.get(iOrdre).getDate());
            ordrePreparation.setNumeroSequentiel(ordres.get(iOrdre).getNumeroSequentiel());
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
            controller.updateOrdre(ordrePreparation);

            //Affichage d'un message de confirmation  de la modification + réinitialisation des champs
            JOptionPane.showMessageDialog(null, "Confirmation de la modification de l'ordre !", "Information", JOptionPane.INFORMATION_MESSAGE);
            PanneauModification.this.removeAll();
            PanneauModification.this.add(new PanneauModification());
            PanneauModification.this.validate();
        }
        catch (ModelException eME)
        {
            eME.getMessage();
        }
        catch (UpdateOrdreException eUO)
        {
            eUO.getMessage();
        }
    }

    public String conversionDateVersString (GregorianCalendar calendar)
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = calendar.getTime();
        return dateFormat.format(date);
    }
}
