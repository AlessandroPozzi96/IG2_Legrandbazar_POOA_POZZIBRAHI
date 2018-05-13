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
    private JPanel panneauTacheMetier, panneauBoutons, choixHoraire,choixJour,nbTranche;
    private JLabel joursLabel,choix1HoraireLabel,choix2HoraireLabel,nbTrancheLabel,chaqueHeureLabel;
    private ApplicationController controller;
    private JButton recherche,retour, nouvRecherche;
    private JComboBox<String> joursCombo;
    private JComboBox horaire1, horaire2;
    private ButtonGroup bg;
    private JRadioButton chaqueHeure, chaque2Heure, chaque3Heure;
    private PanneauFiller panneauFiller;
    private int choixH = 1;

    private Map<String, Integer> jours = Map.of("Lundi",2,"Mardi",3,"Mercredi",4,"Jeudi",5,"Vendredi",6,"Samedi",7,"Dimanche",1);
    private ArrayList<TacheMetier> tacheMetiers;
    public PanneauTacheMetier(){
        this.setLayout(new BorderLayout());
        panneauFiller = new PanneauFiller("Calcule la moyenne des ordres de préparation </br> (Basé sur les 3 dernières semaines du jour sélectionné) :");
        panneauTacheMetier = new JPanel();
        panneauTacheMetier.setLayout(new GridLayout(11,1,3,3));
        panneauTacheMetier.add(panneauFiller);
        //panneauSuppression.setLayout();

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

        /*for(String jour : jours.keySet()){   --> ne parcourt pas la hashmap dans l'ordre ?
            System.out.println(jour);
            joursCombo.addItem(jour);
        }*/

        choixJour.add(joursCombo);
        panneauTacheMetier.add(choixJour);

        choix1HoraireLabel = new JLabel("Choix horaire :  de ");
        choix1HoraireLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        choixHoraire = new JPanel(new FlowLayout());
        choixHoraire.add(choix1HoraireLabel);
        horaire1 = new JComboBox();
        horaire2 = new JComboBox();
        for(int i = 0; i <24 ; i++){
            horaire1.addItem(i);
            horaire2.addItem(i);
        }
        choixHoraire.add(horaire1);
        choix2HoraireLabel = new JLabel(" a ");
        choixHoraire.add(choix2HoraireLabel);
        choixHoraire.add(horaire2);
        panneauTacheMetier.add(choixHoraire);


        nbTranche = new JPanel();
        nbTrancheLabel = new JLabel("Nombre de tranche désirée : ");
        nbTranche.add(nbTrancheLabel);
        bg = new ButtonGroup();
        chaqueHeure = new JRadioButton("Chaque heure", true);
        chaque2Heure = new JRadioButton("Toute les 2 heures");
        chaque3Heure = new JRadioButton("Toute les 3 heures");
        bg.add(chaqueHeure);
        bg.add(chaque2Heure);
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
        ItemsListener itemsListener = new ItemsListener();
        chaqueHeure.addItemListener(itemsListener);
        chaque2Heure.addItemListener(itemsListener);
        chaque3Heure.addItemListener(itemsListener);
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
            }
            else{
                if(e.getSource() == recherche){
                    recherche();
                }
                else
                {
                    if (e.getSource() == nouvRecherche) {
                        PanneauTacheMetier.this.removeAll();
                        PanneauTacheMetier.this.add(new PanneauTacheMetier());
                        PanneauTacheMetier.this.validate();
                    }
                }
            }
        }
    }

    private class ItemsListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getItem() == chaqueHeure && e.getStateChange() == ItemEvent.SELECTED) {
                choixH = 1;
            }
            else
            {
                if (e.getItem() == chaque2Heure && e.getStateChange() == ItemEvent.SELECTED) {
                        choixH = 2;
                    }
                    else
                {
                    if (e.getItem() == chaque3Heure && e.getStateChange() == ItemEvent.SELECTED) {
                            choixH = 3;
                        }
                    }
                }
            }
        }

    public void recherche(){
        tacheMetiers = new ArrayList<>();
        int h1 = 0, h2 = 0;
        try {
            h1 = horaire1.getSelectedIndex();
            h2 = horaire2.getSelectedIndex();
            tacheMetiers = controller.getDatesPreparationDuJour(jours.get(joursCombo.getSelectedItem()), h1, h2, choixH);

            for(TacheMetier tacheMetier : tacheMetiers){
                System.out.println(tacheMetier.getTrancheHoraire());
                System.out.println(tacheMetier.getMoyenne());
            }
            TrancheHoraireJourModel trancheHoraireJourModel = new TrancheHoraireJourModel(tacheMetiers);
            JTable table = new JTable(trancheHoraireJourModel);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            JScrollPane scrollPane = new JScrollPane(table);

      /*      PanneauTacheMetier.this.removeAll();
            joursLabel.setText(joursCombo.getSelectedItem()+"");
            joursLabel.setHorizontalAlignment(SwingConstants.CENTER);
            PanneauTacheMetier.this.add(joursLabel,BorderLayout.NORTH);
            PanneauTacheMetier.this.add(scrollPane,BorderLayout.CENTER);
            PanneauTacheMetier.this.validate();*/

            PanneauTacheMetier.this.panneauTacheMetier.removeAll();
            PanneauTacheMetier.this.panneauTacheMetier.setLayout(new BorderLayout());
            PanneauTacheMetier.this.panneauTacheMetier.add(joursLabel,BorderLayout.NORTH);
            PanneauTacheMetier.this.panneauTacheMetier.add(scrollPane,BorderLayout.CENTER);
            PanneauTacheMetier.this.panneauTacheMetier.repaint();
            PanneauTacheMetier.this.panneauTacheMetier.validate();
        } catch (GeneralException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur ! \n Impossible de se connecter à la base de donnée \n Veuillez réessayer plus tard", "Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
}
