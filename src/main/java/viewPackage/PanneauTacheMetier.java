package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.GeneralException;
import exceptionPackage.ModelException;
import modelPackage.TacheMetier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PanneauTacheMetier extends JPanel {
    private JPanel panneauTacheMetier, panneauBoutons, choixHoraire, choixJour, nbTranche;
    private JLabel joursLabel, choix1HoraireLabel, choix2HoraireLabel, nbTrancheLabel;
    private ApplicationController controller;
    private JButton recherche, retour, nouvRecherche;
    private JComboBox<String> joursCombo;
    private JComboBox horaire1, horaire2;
    private ButtonGroup bg;
    private JRadioButton chaqueHeure, chaque2Heure, chaque3Heure;
    private int intervalHeure;
    private Map<String, Integer> jours = Map.of("Lundi", 2, "Mardi", 3, "Mercredi", 4, "Jeudi", 5, "Vendredi", 6, "Samedi", 7, "Dimanche", 1);
    private ArrayList<TacheMetier> tacheMetiers;
    private PanneauFiller panneauFiller;

    public PanneauTacheMetier() {


        this.setLayout(new BorderLayout());
        panneauTacheMetier = new JPanel();
        panneauTacheMetier.setLayout(new GridLayout(11, 1, 3, 3));
        //panneauSuppression.setLayout();

        //Description de la tache métier
        panneauFiller = new PanneauFiller("Calcule la moyenne des ordres de préparations (Basé sur les 3 dernières semaines du jour sélectionné) :");
        panneauTacheMetier.add(panneauFiller);

        this.add(panneauTacheMetier, BorderLayout.CENTER);
        panneauBoutons = new JPanel();
        panneauBoutons.setLayout(new FlowLayout());
        this.add(panneauBoutons, BorderLayout.SOUTH);

        choixJour = new JPanel();

        joursLabel = new JLabel("Choix de du Jour :");
        joursLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        joursLabel.setToolTipText("Choississez le jour de la recherche ");
        choixJour.add(joursLabel);

        joursCombo = new JComboBox<>();


        joursCombo.addItem("Lundi");
        joursCombo.addItem("Mardi");
        joursCombo.addItem("Mercredi");
        joursCombo.addItem("Jeudi");
        joursCombo.addItem("Vendredi");
        joursCombo.addItem("Samedi");
        joursCombo.addItem("Dimanche");

        choixJour.add(joursCombo);
        panneauTacheMetier.add(choixJour);

        choix1HoraireLabel = new JLabel("Choix horaire :  de ");
        choix1HoraireLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        choixHoraire = new JPanel(new FlowLayout());
        choixHoraire.add(choix1HoraireLabel);
        horaire1 = new JComboBox();
        horaire2 = new JComboBox();
        for (int i = 0; i <= 24; i++) {
            horaire1.addItem(i);
            horaire2.addItem(i);
        }
        choixHoraire.add(horaire1);
        choix2HoraireLabel = new JLabel(" a ");
        choixHoraire.add(choix2HoraireLabel);
        choixHoraire.add(horaire2);
        panneauTacheMetier.add(choixHoraire);

        horaire1.addItemListener((new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    choixDesTranches();
                }
            }
        }));
        horaire2.addItemListener((new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    choixDesTranches();
                }
            }
        }));
        nbTranche = new JPanel();
        nbTrancheLabel = new JLabel("Nombre de tranche désirée : ");
        nbTranche.add(nbTrancheLabel);
        bg = new ButtonGroup();
        chaqueHeure = new JRadioButton("Chaque heure");
        chaque2Heure = new JRadioButton("Toute les 2 heures");
        chaque3Heure = new JRadioButton("Toute les 3 heures");
        chaqueHeure.setSelected(true);
        chaque2Heure.setEnabled(false);
        chaque3Heure.setEnabled(false);
        bg.add(chaque2Heure);
        bg.add(chaqueHeure);
        bg.add(chaque3Heure);
        nbTranche.add(chaqueHeure);
        nbTranche.add(chaque2Heure);
        nbTranche.add(chaque3Heure);

        panneauTacheMetier.add(nbTranche);


        setController(new ApplicationController());
        //Ajout des boutons au panneauBoutons
        retour = new JButton("Retour");
        panneauBoutons.add(retour);
        recherche = new JButton("Recherche");
        panneauBoutons.add(recherche);
        nouvRecherche = new JButton("Nouvelle recherche");
        panneauBoutons.add(nouvRecherche);
        // action des bouttons
        retour.addActionListener(new ButtonsAndTextsListener());
        recherche.addActionListener(new ButtonsAndTextsListener());
        nouvRecherche.addActionListener(new ButtonsAndTextsListener());

    }

    public void setController(ApplicationController controller) {
        this.controller = controller;
    }

    private class ButtonsAndTextsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == retour) {
                PanneauTacheMetier.this.removeAll();
                PanneauTacheMetier.this.add(new PanneauBienvenue());
                PanneauTacheMetier.this.validate();
            } else {
                if (e.getSource() == recherche) {
                    recherche();
                }
                else {
                    if (e.getSource() == nouvRecherche) {
                        PanneauTacheMetier.this.removeAll();
                        PanneauTacheMetier.this.add(new PanneauTacheMetier());
                        PanneauTacheMetier.this.validate();
                    }
                }
            }
        }
    }

    public void recherche() {
        if ((int)horaire1.getSelectedItem() == (int)horaire2.getSelectedItem() || (int)horaire1.getSelectedItem() > (int)horaire2.getSelectedItem()) {
            JOptionPane.showMessageDialog(null, "Tranche horraire incorect", "Erreur", JOptionPane.ERROR_MESSAGE);
        }else{
            tacheMetiers = new ArrayList<>();
            try {
                if(chaqueHeure.isSelected()){
                    intervalHeure = 1;
                }else{
                    if(chaque2Heure.isSelected()){
                        intervalHeure = 2;
                    }else{
                        intervalHeure = 3;
                    }
                }

                try {
                    tacheMetiers = controller.getDatesPreparationDuJour(jours.get(joursCombo.getSelectedItem()),(int)horaire1.getSelectedItem(),(int)horaire2.getSelectedItem(),intervalHeure);
                } catch (ModelException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }

                TrancheHoraireJourModel trancheHoraireJourModel = new TrancheHoraireJourModel(tacheMetiers);
                JTable table = new JTable(trancheHoraireJourModel);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                JScrollPane scrollPane = new JScrollPane(table);

                PanneauTacheMetier.this.panneauTacheMetier.removeAll();
                PanneauTacheMetier.this.panneauTacheMetier.setLayout(new BorderLayout());
                joursLabel.setText(joursCombo.getSelectedItem() + " de "+horaire1.getSelectedItem()+" a "+horaire2.getSelectedItem()+" heure toute les "+intervalHeure+" heures");
                joursLabel.setHorizontalAlignment(SwingConstants.CENTER);
                PanneauTacheMetier.this.panneauTacheMetier.add(joursLabel,BorderLayout.NORTH);
                PanneauTacheMetier.this.panneauTacheMetier.add(scrollPane,BorderLayout.CENTER);
                PanneauTacheMetier.this.panneauTacheMetier.repaint();
                PanneauTacheMetier.this.panneauTacheMetier.validate();
            } catch (GeneralException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
    }
    public void choixDesTranches(){
        if ((int)horaire1.getSelectedItem() == (int)horaire2.getSelectedItem() || (int)horaire1.getSelectedItem() > (int)horaire2.getSelectedItem()) {

        }else{
            int nombreHeure = (int) horaire1.getSelectedItem() - (int) horaire2.getSelectedItem();
            if (nombreHeure % 2 == 0) {
                chaque2Heure.setEnabled(true);
            } else {
                chaque2Heure.setEnabled(false);
                if(chaque2Heure.isSelected()){
                    chaque2Heure.setSelected(true);
                }
            }
            if (nombreHeure % 3 == 0) {
                chaque3Heure.setEnabled(true);
            } else {
                chaque3Heure.setEnabled(false);
                if(chaque3Heure.isSelected()){
                    chaqueHeure.setSelected(true);
                }
            }
        }
    }
}
