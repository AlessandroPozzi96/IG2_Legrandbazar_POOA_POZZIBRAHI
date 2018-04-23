package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AllOrdresException;
import exceptionPackage.ModelException;
import modelPackage.OrdrePreparation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PanneauSuppression extends JPanel {

    private JPanel panneauSuppression, panneauBoutons;
    private JLabel ordresLabel;
    private ApplicationController controller;
    private ArrayList<OrdrePreparation> ordres;
    private JComboBox ordresJCombo;
    private DateFormat dateFormat;
    private JButton retour, suppression;

    public PanneauSuppression(){
        //On créé les différents panneaux
        this.setLayout(new BorderLayout());
        panneauSuppression = new JPanel();
        //panneauSuppression.setLayout();
        this.add(panneauSuppression, BorderLayout.CENTER);
        panneauBoutons = new JPanel();
        panneauBoutons.setLayout(new FlowLayout());
        this.add(panneauBoutons, BorderLayout.SOUTH);

        //On créé les labels + les champs
        ordresLabel = new JLabel("Choix de l'ordre à supprimer :");
        ordresLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        ordresLabel.setToolTipText("Choississez l'ordre que vous voulez supprimez ");
        panneauSuppression.add(ordresLabel);

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
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateString;
        for (OrdrePreparation ordresCombox : ordres)
        {
            java.util.Date date = ordresCombox.getDate().getTime();
            dateString = dateFormat.format(date);
            ordresJCombo.addItem(ordresCombox.getNumeroSequentiel() + " -> " + dateString);
        }
        ordresJCombo.setMaximumRowCount(3);
        panneauSuppression.add(ordresJCombo);


        //Ajout des boutons au panneauBoutons
        retour = new JButton("Retour");
        panneauBoutons.add(retour);
        suppression = new JButton("Suppresion");
        panneauBoutons.add(suppression);
        // action des bouttons
        retour.addActionListener(new ButtonsAndTextsListener());
        suppression.addActionListener(new ButtonsAndTextsListener());
    }
    private class ButtonsAndTextsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == retour) {
                PanneauSuppression.this.removeAll();
                PanneauSuppression.this.add(new PanneauBienvenue());
                PanneauSuppression.this.repaint();
                PanneauSuppression.this.validate();
            }
            else{
                if(e.getSource() == suppression){
                    suppression();
                }
            }
        }
    }
    public void suppression(){
        System.out.print(ordresJCombo.getSelectedItem());
        String ordrePreparationSelection = (String)ordresJCombo.getSelectedItem();

    }
    public void setController(ApplicationController controller) {
        this.controller = controller;
    }



}
