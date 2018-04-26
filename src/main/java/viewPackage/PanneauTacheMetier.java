package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.GeneralException;
import modelPackage.TacheMetier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PanneauTacheMetier extends JPanel {

    private JPanel panneauTacheMetier, panneauBoutons;
    private JLabel joursLabel;
    private ApplicationController controller;
    private JButton recherche,retour;
    private JComboBox<String> joursCombo;
    private Map<String, Integer> jours = Map.of("Lundi",2,"Mardi",3,"Mercredi",4,"Jeudi",5,"Vendredi",6,"Samedi",7,"Dimanche",1);
    private ArrayList<TacheMetier> tacheMetiers;
    public PanneauTacheMetier(){
        this.setLayout(new BorderLayout());
        panneauTacheMetier = new JPanel();
        //panneauSuppression.setLayout();
        this.add(panneauTacheMetier, BorderLayout.CENTER);
        panneauBoutons = new JPanel();
        panneauBoutons.setLayout(new FlowLayout());
        this.add(panneauBoutons, BorderLayout.SOUTH);

        joursLabel = new JLabel("Choix de du Jour :");
        joursLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        joursLabel.setToolTipText("Choississez le jour de la recherche ");
        panneauTacheMetier.add(joursLabel);

        joursCombo = new JComboBox<>();
        for(String jour : jours.keySet()){
            joursCombo.addItem(jour);
        }
        panneauTacheMetier.add(joursCombo);

        setController(new ApplicationController());

        //Ajout des boutons au panneauBoutons
        retour = new JButton("Retour");
        panneauBoutons.add(retour);
        recherche = new JButton("Recherche");
        panneauBoutons.add(recherche);
        // action des bouttons
        retour.addActionListener(new ButtonsAndTextsListener());
        recherche.addActionListener(new ButtonsAndTextsListener());

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
            }
        }
    }
    public void recherche(){
        tacheMetiers = new ArrayList<>();
        try {
            tacheMetiers = controller.getDatesPreparationDuJour(jours.get(joursCombo.getSelectedItem()));
            /*System.out.println(tranchesHoraire.get("huitADix"));
            System.out.println(tranchesHoraire.get("dixUneADouze"));
            System.out.println(tranchesHoraire.get("douzeUneAQuatorze"));
            System.out.println(tranchesHoraire.get("quatorzeUneASeize"));*/

            for(TacheMetier tacheMetier : tacheMetiers){
                System.out.println(tacheMetier.getTrancheHoraire());
                System.out.println(tacheMetier.getMoyenne());
            }

            TrancheHoraireJourModel trancheHoraireJourModel = new TrancheHoraireJourModel(tacheMetiers);
            JTable table = new JTable(trancheHoraireJourModel);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            JScrollPane scrollPane = new JScrollPane(table);

            PanneauTacheMetier.this.removeAll();
            PanneauTacheMetier.this.add(scrollPane);
            PanneauTacheMetier.this.validate();


        } catch (GeneralException e) {
            e.printStackTrace();
        }

    }
}
