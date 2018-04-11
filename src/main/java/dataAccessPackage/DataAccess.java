package dataAccessPackage;

import modelPackage.OrdrePreparation;

import java.util.ArrayList;

public interface DataAccess
{
    void addOrdre (OrdrePreparation ordrePreparation);
    ArrayList<OrdrePreparation> getAllOrdres();
}
