package controllerPackage;

import businessPackage.OrdreManager;
import exceptionPackage.*;
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

    public ArrayList<OrdrePreparation> getAllOrdres () throws AllOrdresException, ModelException {
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

    public Integer getNumeroSquentiel() throws GeneralException
    {
        return manager.getNumeroSequentiel ();
    }

    public void updateOrdre (OrdrePreparation ordrePreparation) throws UpdateOrdreException
    {
        manager.updateOrdre(ordrePreparation);
    }

    public void supprimerOrdre(int numeroSequentiel)throws GeneralException {
        manager.supprimerOrdre(numeroSequentiel);
    }
}
