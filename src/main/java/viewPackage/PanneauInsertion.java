package viewPackage;

import controllerPackage.ApplicationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PanneauInsertion extends JPanel
{
    private JPanel panneauFormulaire, panneauBoutons;
    private JLabel recetteLabel, dateLabel, numeroSequencielLabel, quantitePrevueLabel, quantiteProduiteLabel, dateVenteLabel, datePreparationLabel, remarqueLabel, estUrgentLabel, codeBarreLabel, matriculeCuiLabel, matriculeResLabel;
    private JTextField numeroSequentielText, quantitePrevueText, quantiteProduiteText, remarqueText;
    private JComboBox codeBarreCombo, matriculeCuiCombo, matriculeResCombo, recetteCombo;
    private JButton validation, retour, reinitialiser;
    private PanneauBienvenue panneauBienvenue;
    private JRadioButton urgent, pasUrgent, ouiDateVente, nonDateVente, ouiDatePrep, nonDatePrep;
    private ButtonGroup buttonGroup, buttonGroupDateVente, buttonGroupDatePrep;
    private FonctionEcouteurs fonctionEcouteurs;
    private PanneauSpinnerDate spinnerDate, spinnerDateVente, spinnerDatePrep;
    private ApplicationController controller;

    public PanneauInsertion()
    {
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
        recetteCombo = new JComboBox();    // REMPLIR LA COMBOBOX AVEC LA BD
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
        numeroSequentielText = new JTextField();
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
        codeBarreCombo = new JComboBox();  // COMBOBOX A REMPLIR AVEC LA BD --> peu etre mieux de mettre le libellé que le matricule
        panneauFormulaire.add(codeBarreCombo);

        matriculeCuiLabel = new JLabel("Matricule cuisinier :");
        matriculeCuiLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        matriculeCuiLabel.setToolTipText("Référence vers le cuisinier à qui la préparation a été attribué");
        panneauFormulaire.add(matriculeCuiLabel);
        matriculeCuiCombo = new JComboBox();   // COMBOBOX a remplir avec la BD
        panneauFormulaire.add(matriculeCuiCombo);

        matriculeResLabel = new JLabel("Matricule responsable :");
        matriculeResLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        matriculeResLabel.setToolTipText("Référence vers la responsable des ventes qui a créé l'ordre");
        panneauFormulaire.add(matriculeResLabel);
        matriculeResCombo = new JComboBox(); // COMBOBOX a remplir avec la BD
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
                    Integer quantitePrevue = null;
                    try
                    {
                        quantitePrevue = Integer.valueOf(quantitePrevueText.getText());
                    }
                    catch (Exception error)
                    {

                    }
                    if (quantitePrevueText.getText().isEmpty() || quantitePrevue == null)
                    {
                        JOptionPane.showMessageDialog(null, "Quantité prévue incorrecte !", "Erreur", JOptionPane.ERROR_MESSAGE);
                        quantitePrevueText.setBackground(Color.RED);
                    }
                    else
                    {
                        quantitePrevueText.setBackground(Color.WHITE);
                    }
                }
                else
                {
                    if (e.getSource() == reinitialiser)
                    {
                        spinnerDate.reinitialiserChamps();
                        spinnerDateVente.reinitialiserChamps();
                        spinnerDatePrep.reinitialiserChamps();
                        numeroSequentielText.setText("");
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
        public void itemStateChanged(ItemEvent e) {

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