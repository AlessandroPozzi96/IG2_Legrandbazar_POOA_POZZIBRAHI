package viewPackage;

import controllerPackage.ApplicationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauTacheMetier extends JPanel {

    private JPanel panneauTacheMetier, panneauBoutons;
    private JLabel joursLabel;
    private ApplicationController controller;
    private JButton recherche,retour;
    private JComboBox<String> joursCombo;
    private static String [] jours = {"Lundi","Mardi","Mercredi","Jeudi","Vendredi"};

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
        for(String jour : jours){
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
                PanneauTacheMetier.this.add(new PanneauTacheMetier());
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
        System.out.println(joursCombo.getSelectedItem().toString());
    }
}
