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
import java.util.ArrayList;

public class PanneauListing extends JPanel {
    private JPanel panelBouton;
    private JButton suppression;
    private ApplicationController controller;
    private ArrayList <OrdrePreparation> ordrePreparations;
    private ArrayList<Reservation> reservations;

    PanneauListing(){
        setController(new ApplicationController());
        this.setLayout(new BorderLayout());
        ordrePreparations = new ArrayList<>();
        try{
            ordrePreparations = controller.getAllOrdres();
        }catch (AllOrdresException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        catch (ModelException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);

        }
        AllOrdresPreparationModel allOrdresPreparationModel = new AllOrdresPreparationModel(ordrePreparations);
        JTable table = new JTable(allOrdresPreparationModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(table);

        panelBouton = new JPanel();
        suppression = new JButton("Supprimer");
        suppression.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int [] selection = table.getSelectedRows();
                int i =0;
                while(i<selection.length){
                    //System.out.println(selection[i]);
                    Object cellule = table.getValueAt(selection[i],1);
                    System.out.println(cellule);

                    try {
                        reservations = controller.getForeignKeyReservation((int) cellule);
                        if (reservations.size() == 0) {
                            controller.supprimerOrdre((int) cellule);
                            changementPanneau(new PanneauListing());

                    }
                    else{
                                if((JOptionPane.showConfirmDialog(null,reservations.size()+" clés étrangéres sur l'ordre num "+cellule+", supprimer quand même ?","Suppression Ordre",JOptionPane.YES_NO_CANCEL_OPTION)==0)) {
                                    controller.supprimerForeignKeyReservation((int) cellule);
                                    controller.supprimerOrdre((int) cellule);
                                    changementPanneau(new PanneauListing());
                                }
                    }}
                    catch(GeneralException e1){
                        JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                    i++;

                }
        }});
        panelBouton.add(suppression);
        this.setLayout(new BorderLayout());
        this.add(scrollPane,BorderLayout.CENTER);
        this.add(suppression,BorderLayout.SOUTH);
    }
    public void setController(ApplicationController controller){
        this.controller = controller;
    }
    public void changementPanneau(JPanel panel){
        PanneauListing.this.removeAll();
        PanneauListing.this.add(panel);
        PanneauListing.this.validate();
    }
}
