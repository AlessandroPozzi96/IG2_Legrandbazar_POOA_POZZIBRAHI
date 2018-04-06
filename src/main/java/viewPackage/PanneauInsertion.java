package viewPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class PanneauInsertion extends JPanel {
    private JPanel panneauFormulaire, panneauBoutons;
    private JLabel nomLabel, dateLabel, numeroSequencielLabel, quantitePrevueLabel, quantiteProduiteLabel, dateVenteLabel, datePreparationLabel, remarqueLabel, estUrgentLabel, codeBarreLabel, matriculeCuiLabel, matriculeResLabel;
    private JTextField nomText, numeroSequentielText, quantitePrevueText, quantiteProduiteText, remarqueText, codeBarreText, matriculeCuiText, matriculeResText;
    private JButton validation, retour, reinitialiser;
    private PanneauBienvenue panneauBienvenue;
    private JRadioButton urgent, pasUrgent;
    private ButtonGroup buttonGroup;
    private FonctionEcouteurs fonctionEcouteurs;
    private PanneauSpinnerDate spinnerDate, spinnerDateVente, spinnerDatePrep;

    public PanneauInsertion()
    {
        //Création des panneaux et de leurs layouts
        this.setLayout(new BorderLayout());
        panneauFormulaire = new JPanel();
        panneauFormulaire.setLayout(new GridLayout(12, 2, 3, 3));
        /*panneauFormulaire.setBackground(Color.RED);*/
        panneauBoutons = new JPanel();
        /*panneauBoutons.setBackground(Color.RED);*/
        panneauBoutons.setLayout(new FlowLayout());
        this.add(panneauFormulaire, BorderLayout.CENTER);
        this.add(panneauBoutons, BorderLayout.SOUTH);

        //Création des labels dans la grille (12 lignes)
        nomLabel = new JLabel("Nom :");
        nomLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        nomLabel.setToolTipText("Nom de la recette");
        panneauFormulaire.add(nomLabel);
        nomText = new JTextField();
        panneauFormulaire.add(nomText);

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

        dateVenteLabel = new JLabel("Date vente : ");
        dateVenteLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        dateVenteLabel.setToolTipText("[FACULTATIF] Date de mise en vente");
        panneauFormulaire.add(dateVenteLabel);
        spinnerDateVente = new PanneauSpinnerDate();
        panneauFormulaire.add(spinnerDateVente);

        datePreparationLabel = new JLabel("Date de préparation :");
        datePreparationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(datePreparationLabel);
        spinnerDatePrep = new PanneauSpinnerDate();
        panneauFormulaire.add(spinnerDatePrep);

        remarqueLabel = new JLabel("Remarque : ");
        remarqueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        remarqueLabel.setToolTipText("[FACULTATIF] Indiquez une remarque");
        panneauFormulaire.add(remarqueLabel);
        remarqueText = new JTextField();
        panneauFormulaire.add(remarqueText);

        urgent = new JRadioButton("Est urgent", false);
        urgent.setHorizontalAlignment(SwingConstants.LEFT);
        /*urgent.setBackground(Color.RED);*/
        panneauFormulaire.add(urgent);
        pasUrgent = new JRadioButton("N'est pas urgent", true);
        pasUrgent.setHorizontalAlignment(SwingConstants.RIGHT);
        /*pasUrgent.setBackground(Color.RED);*/
        panneauFormulaire.add(pasUrgent);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(urgent);
        buttonGroup.add(pasUrgent);

        codeBarreLabel = new JLabel("Code barre :");
        codeBarreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        codeBarreLabel.setToolTipText("Référence vers le type d'article quand il sera mis en vente");
        panneauFormulaire.add(codeBarreLabel);
        codeBarreText = new JTextField();
        panneauFormulaire.add(codeBarreText);

        matriculeCuiLabel = new JLabel("Matricule cuisinier :");
        matriculeCuiLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        matriculeCuiLabel.setToolTipText("Référence vers le cuisinier à qui la préparation a été attribué");
        panneauFormulaire.add(matriculeCuiLabel);
        matriculeCuiText = new JTextField();
        panneauFormulaire.add(matriculeCuiText);

        matriculeResLabel = new JLabel("Matricule responsable :");
        matriculeResLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        matriculeResLabel.setToolTipText("Référence vers la responsable des ventes qui a créé l'ordre");
        panneauFormulaire.add(matriculeResLabel);
        matriculeResText = new JTextField();
        panneauFormulaire.add(matriculeResText);

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

    public JTextField getNomText() {
        return nomText;
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
                    if (nomText.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Nom invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
                        nomText.setBackground(Color.RED);
                    }
                    else
                        {
                            nomText.setBackground(Color.WHITE);
                        }
/*                    Integer quantitePrevue = null;
                    try
                    {
                        Integer.valueOf(quantitePrevueText.getText());
                    }
                    catch (Exception error)
                    {
                    }*/
                    if (quantitePrevueText.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Quantité prévue invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
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
                        buttonGroup.clearSelection();
                        nomText.setText("");
                        codeBarreText.setText("");
                        matriculeCuiText.setText("");
                        matriculeResText.setText("");
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
