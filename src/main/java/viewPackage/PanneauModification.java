package viewPackage;

import javax.swing.*;
import java.awt.*;

public class PanneauModification extends JPanel {
    private JPanel panneauModifications, panneauBoutons;
    private JList<String> ordresBox;
    private JLabel numeroIdentifiant, nomLabel, dateLabel, quantitePrevueLabel, quantiteProduiteLabel, dateVenteLabel, datePreparationLabel, remarqueLabel, estUrgentLabel, codeBarreLabel, matriculeCuiLabel, matriculeResLabel;
    private JTextField nomText, quantitePrevueText, quantiteProduiteText, dateVenteText, datePreparationText, remarqueText, codeBarreText, matriculeCuiText, matriculeResText;
    private JButton validation, retour, reinitialiser;
    private PanneauBienvenue panneauBienvenue;
    private JRadioButton urgent, pasUrgent;
    private ButtonGroup buttonGroup;
    //Variables qui va simuler un accès à la base de données
    private String[] ordresDB = {"---Veuillez choisir un ordre---", "040420181", "040420182", "050420183", "040420184"};

    public PanneauModification()
    {
        //On créé les différents panneaux
        this.setLayout(new BorderLayout());
        panneauModifications = new JPanel();
        panneauModifications.setLayout(new GridLayout(11, 2, 3, 3));
        this.add(panneauModifications, BorderLayout.CENTER);
        panneauBoutons = new JPanel();
        panneauBoutons.setLayout(new FlowLayout());
        this.add(panneauBoutons, BorderLayout.SOUTH);

        //On créé les labels + les champs
        numeroIdentifiant = new JLabel("Numéro identifiant de l'ordre :");
        numeroIdentifiant.setHorizontalAlignment(SwingConstants.RIGHT);
        numeroIdentifiant.setToolTipText("Choississez le numéro de l'ordre (Combinaison de la date et d'un numéro séquentiel)");
        panneauModifications.add(numeroIdentifiant);
        ordresBox = new JList<>(ordresDB);
        ordresBox.setSelectedIndex(0);
        ordresBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panneauModifications.add(new JScrollPane(ordresBox));

        nomLabel = new JLabel("Nom :");
        nomLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        nomLabel.setToolTipText("Nom de la recette");
        panneauModifications.add(nomLabel);
        nomText = new JTextField();
        panneauModifications.add(nomText);

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
        dateVenteText = new JTextField();
        panneauModifications.add(dateVenteText);

        datePreparationLabel = new JLabel("Date de préparation :");
        datePreparationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauModifications.add(datePreparationLabel);
        datePreparationText = new JTextField();
        panneauModifications.add(datePreparationText);

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
        codeBarreText = new JTextField();
        panneauModifications.add(codeBarreText);

        matriculeCuiLabel = new JLabel("Matricule cuisinier :");
        matriculeCuiLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        matriculeCuiLabel.setToolTipText("Référence vers le cuisinier à qui la préparation a été attribué");
        panneauModifications.add(matriculeCuiLabel);
        matriculeCuiText = new JTextField();
        panneauModifications.add(matriculeCuiText);

        matriculeResLabel = new JLabel("Matricule responsable :");
        matriculeResLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        matriculeResLabel.setToolTipText("Référence vers la responsable des ventes qui a créé l'ordre");
        panneauModifications.add(matriculeResLabel);
        matriculeResText = new JTextField();
        panneauModifications.add(matriculeResText);

        //Ajout des boutons au panneauBoutons
        retour = new JButton("Retour");
        panneauBoutons.add(retour);
        validation = new JButton("Validation");
        panneauBoutons.add(validation);
        reinitialiser = new JButton("Réinitialiser");
        panneauBoutons.add(reinitialiser);
    }
}
