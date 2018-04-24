package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AllOrdresException;
import exceptionPackage.ModelException;
import modelPackage.OrdrePreparation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanneauListing extends JPanel {

    private ApplicationController controller;
    private ArrayList <OrdrePreparation> ordrePreparations;

    PanneauListing(){

        setController(new ApplicationController());
        this.setLayout(new BorderLayout());
        ordrePreparations = new ArrayList<>();
        try{
            ordrePreparations = controller.getAllOrdres();
        }catch (AllOrdresException e){
            System.out.println("Erreur AllOrdreException");
        }
        catch (ModelException e)
        {
            System.out.println(e.getMessage());
        }

        AllOrdresPreparationModel allOrdresPreparationModel = new AllOrdresPreparationModel(ordrePreparations);
        JTable table = new JTable(allOrdresPreparationModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);


    }

    public void setController(ApplicationController controller){
        this.controller = controller;
    }

}
