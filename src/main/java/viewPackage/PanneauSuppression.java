package viewPackage;


import controllerPackage.ApplicationController;
import exceptionPackage.AllOrdresException;
import exceptionPackage.GeneralException;
import exceptionPackage.ModelException;
import modelPackage.OrdrePreparation;
import modelPackage.Reservation;

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
    private ArrayList<Reservation> reservations;
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
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE); // c'est une erreur connexion qui s'affiche
            System.exit(0);
        }
        catch (ModelException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE); // c'est une erreur connexion qui s'affiche
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
                changementPanneau((new PanneauBienvenue()));
            }
            else{
                if(e.getSource() == suppression){
                    if(ordresJCombo.getSelectedItem() == null){
                        JOptionPane.showMessageDialog(null, "Aucun ordre present dans la bd", "Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        suppression();
                    }
                }
            }
        }
    }
    public void suppression(){
        String ordrePreparationSelection;
        ordrePreparationSelection = ordresJCombo.getSelectedItem().toString();
        String [] motSepare = ordrePreparationSelection.split(" ");
        try {
            reservations = controller.getForeignKeyReservation(Integer.parseInt(motSepare[0]));
            if(reservations.size()==0){
                controller.supprimerOrdre(Integer.parseInt(motSepare[0]));
                changementPanneau(new PanneauSuppression());
            }
            else{
                if(JOptionPane.showConfirmDialog(null,reservations.size()+" clés étrangéres, supprimer quand même ?","Suppression Ordre",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.OK_OPTION){
                    controller.supprimerForeignKeyReservation(Integer.parseInt(motSepare[0]));
                    controller.supprimerOrdre(Integer.parseInt(motSepare[0]));
                    changementPanneau(new PanneauSuppression());
                }
            }
        } catch (GeneralException e) {
            e.printStackTrace();
        }

    }
    public void setController(ApplicationController controller) {
        this.controller = controller;
    }

    public void changementPanneau(JPanel panel){
        PanneauSuppression.this.removeAll();
        PanneauSuppression.this.add(panel);
        PanneauSuppression.this.validate();
    }


}
