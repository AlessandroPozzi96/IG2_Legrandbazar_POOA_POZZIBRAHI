package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AllOrdresException;
import modelPackage.OrdrePreparation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanneauListing extends JPanel {

    private ApplicationController controller;
    private ArrayList <OrdrePreparation> ordrePreparations;

    PanneauListing(){

        setController(new ApplicationController());
        //this.setLayout(new BorderLayout());

        try{
            ordrePreparations = controller.getAllOrdres();
        }catch (AllOrdresException e){

        }

        /* TEST
        System.out.println(ordrePreparations.get(0).getDate());
        System.out.println(ordrePreparations.get(0).getNumeroSequentiel());
        System.out.println(ordrePreparations.get(0).getQuantitePrevue());
        System.out.println(ordrePreparations.get(0).getQuantiteProduite());
        System.out.println(ordrePreparations.get(0).getDateVente());
        System.out.println(ordrePreparations.get(0).getDatePreparation());
        System.out.println(ordrePreparations.get(0).getRemarque());
        System.out.println(ordrePreparations.get(0).getEstUrgent());
        System.out.println(ordrePreparations.get(0).getNom());
        System.out.println(ordrePreparations.get(0).getCodeBarre());
        System.out.println(ordrePreparations.get(0).getMatricule_Cui());
        System.out.println(ordrePreparations.get(0).getMatricule_Res());*/

        AllOrdresPreparationModel allOrdresPreparationModel = new AllOrdresPreparationModel(ordrePreparations);
        JTable table = new JTable(allOrdresPreparationModel);
        JScrollPane scrollPane = new JScrollPane(table);
       this.add(scrollPane);


    }

    public void setController(ApplicationController controller){
        this.controller = controller;
    }

}
