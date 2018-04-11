package controllerPackage;

import businessPackage.OrdreManager;
import modelPackage.OrdrePreparation;

import java.util.ArrayList;

public class ApplicationController
{
    private OrdreManager manager;

    public ApplicationController()
    {
        setManager(new OrdreManager());
    }

    public void setManager(OrdreManager manager) {
        this.manager = manager;
    }

    public void addOrdre (OrdrePreparation ordrePreparation)
    {
        manager.addOrdre(ordrePreparation);
    }

    public ArrayList<OrdrePreparation> getAllOrdres ()
    {
        return manager.getAllOrdres();
    }
}
