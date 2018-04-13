package controllerPackage;

import businessPackage.OrdreManager;
import exceptionPackage.AddOrdreException;
import exceptionPackage.AllOrdresException;
import exceptionPackage.GeneralException;
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

    public ArrayList<String> getAllRecetteNom () throws GeneralException {
        return manager.getAllRecetteNom();
    }

    public ArrayList<String> getCodeBarres () throws GeneralException {
        return manager.getCodeBarres();
    }

    public ArrayList<String> getMatriculesCui () throws GeneralException {
        return manager.getMatriculesCui();
    }

    public ArrayList<String> getMatriculesRes () throws GeneralException {
        return manager.getMatriculesRes();
    }


}
