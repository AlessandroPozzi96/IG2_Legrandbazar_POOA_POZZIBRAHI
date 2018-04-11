package dataAccessPackage;

import exceptionPackage.AddOrdreException;
import exceptionPackage.AllOrdresException;
import modelPackage.OrdrePreparation;

import java.util.ArrayList;

public interface DataAccess
{
    void addOrdre (OrdrePreparation ordrePreparation) throws AddOrdreException;
    ArrayList<OrdrePreparation> getAllOrdres() throws AllOrdresException;
}
