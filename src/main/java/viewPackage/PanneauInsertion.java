package viewPackage;

import javax.swing.*;
import java.awt.*;

public class PanneauInsertion extends JPanel {
    private JPanel panneauFormulaire, panneauBoutons;
    private JLabel nomLabel, dateLabel, numeroSequencielLabel, quantitePrevueLabel, quantiteProduiteLabel, dateVenteLabel, datePreparationLabel, remarqueLabel, estUrgentLabel, codeBarreLabel, matriculeCuiLabel, matriculeResLabel;
    private JTextField nomText, dateText, numeroSequentielText, quantitePrevueText, quantiteProduiteText, dateVenteText, datePreparationText, remarqueText, estUrgentText, codeBarreText, matriculeCuiText, matriculeResText;
    private JButton validation, retour, reinitialiser;
    private PanneauBienvenue panneauBienvenue;

    public PanneauInsertion()
    {
        //Création des panneaux et de leurs layouts
        this.setLayout(new BorderLayout());
        panneauFormulaire = new JPanel();
        panneauFormulaire.setLayout(new GridLayout(12, 2, 3, 3));
        panneauBoutons = new JPanel();
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
        dateText = new JTextField();
        panneauFormulaire.add(dateText);

        numeroSequencielLabel = new JLabel("Numéro séquentiel :");
        numeroSequencielLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        numeroSequencielLabel.setToolTipText("Numéro séquentiel auto incrémenté");
        panneauFormulaire.add(numeroSequencielLabel);
        numeroSequentielText = new JTextField();
        panneauFormulaire.add(numeroSequentielText);

        quantitePrevueLabel = new JLabel("Quantité prévue :");
        quantitePrevueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        quantitePrevueLabel.setToolTipText("Quantité prévue à la création de l'ordre");
        panneauFormulaire.add(quantitePrevueLabel);
        quantitePrevueText = new JTextField();
        panneauFormulaire.add(quantitePrevueText);

        quantiteProduiteLabel = new JLabel("Quantité produite : ");
        quantiteProduiteLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        quantiteProduiteLabel.setToolTipText("Quantité effectivement produite");
        panneauFormulaire.add(quantiteProduiteLabel);
        quantiteProduiteText = new JTextField();
        panneauFormulaire.add(quantiteProduiteText);

        dateVenteLabel = new JLabel("Date vente : ");
        dateVenteLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        dateVenteLabel.setToolTipText("Date de mise en vente");
        panneauFormulaire.add(dateVenteLabel);
        dateVenteText = new JTextField();
        panneauFormulaire.add(dateVenteText);

        datePreparationLabel = new JLabel("Date de préparation :");
        datePreparationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(datePreparationLabel);
        datePreparationText = new JTextField();
        panneauFormulaire.add(datePreparationText);

        remarqueLabel = new JLabel("Remarque : ");
        remarqueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(remarqueLabel);
        remarqueText = new JTextField();
        panneauFormulaire.add(remarqueText);

        estUrgentLabel = new JLabel("Est urgent ?");
        estUrgentLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        estUrgentLabel.setToolTipText("True si préparation urgente");
        panneauFormulaire.add(estUrgentLabel);
        estUrgentText = new JTextField();
        panneauFormulaire.add(estUrgentText);

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
    }
}
