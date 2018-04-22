package businessPackage;

import dataAccessPackage.DBAccess;
import dataAccessPackage.DataAccess;
import exceptionPackage.*;
import modelPackage.OrdrePreparation;

import java.util.ArrayList;

public class OrdreManager
{
    private DataAccess dao;

    public OrdreManager() {
        setDao(new DBAccess());
    }

    public void setDao(DBAccess dao) {
        this.dao = dao;
    }

    public void addOrdre (OrdrePreparation ordrePreparation) throws AddOrdreException
    {
        dao.addOrdre(ordrePreparation);
    }

    public ArrayList<OrdrePreparation> getAllOrdres () throws AllOrdresException, ModelException {
        return dao.getAllOrdres();
    }

    public ArrayList<String> getAllRecetteNom () throws GeneralException {
        return dao.getAllRecetteNom();
    }

    public ArrayList<String> getCodeBarres () throws GeneralException {
        return dao.getCodeBarres();
    }

    public ArrayList<String> getMatriculesCui () throws GeneralException {
        return dao.getMatriculesCui();
    }

    public ArrayList<String> getMatriculesRes () throws GeneralException {
        return dao.getMatriculesRes();
    }

    public Integer getNumeroSequentiel () throws GeneralException
    {
        return dao.getNumeroSequentiel();
    }

    public void updateOrdre (OrdrePreparation ordrePreparation) throws UpdateOrdreException
    {
        dao.updateOrdre(ordrePreparation);
    }
}
