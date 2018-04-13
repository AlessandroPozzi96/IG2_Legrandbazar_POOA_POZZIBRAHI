package controllerPackage;

import businessPackage.OrdreManager;
import exceptionPackage.AddOrdreException;
import exceptionPackage.AllOrdresException;
import exceptionPackage.AllRecetteNomException;
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

    public void addOrdre (OrdrePreparation ordrePreparation) throws AddOrdreException
    {
        manager.addOrdre(ordrePreparation);
    }

    public ArrayList<OrdrePreparation> getAllOrdres () throws AllOrdresException {
        return manager.getAllOrdres();
    }

    public ArrayList<String> getAllRecetteNom () throws AllRecetteNomException {
        return manager.getAllRecetteNom();
    }
}
