package businessPackage;

import dataAccessPackage.DBAccess;
import dataAccessPackage.DataAccess;
import exceptionPackage.AddOrdreException;
import exceptionPackage.AllOrdresException;
import exceptionPackage.AllRecetteNomException;
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

    public ArrayList<OrdrePreparation> getAllOrdres () throws AllOrdresException {
        return dao.getAllOrdres();
    }

    public ArrayList<String> getAllRecetteNom () throws AllRecetteNomException {
        return dao.getAllRecetteNom();
    }
}
