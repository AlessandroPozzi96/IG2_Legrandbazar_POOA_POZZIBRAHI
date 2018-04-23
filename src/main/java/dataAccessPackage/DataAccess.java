package dataAccessPackage;

import exceptionPackage.*;
import modelPackage.OrdrePreparation;

import java.util.ArrayList;

public interface DataAccess
{
    void addOrdre (OrdrePreparation ordrePreparation) throws AddOrdreException;
    ArrayList<OrdrePreparation> getAllOrdres() throws AllOrdresException, ModelException;
    ArrayList<String> getAllRecetteNom () throws GeneralException;
    ArrayList<String> getCodeBarres () throws GeneralException;
    ArrayList<String> getMatriculesCui () throws GeneralException;
    ArrayList<String> getMatriculesRes () throws GeneralException;
    Integer getNumeroSequentiel () throws GeneralException;
    void updateOrdre(OrdrePreparation ordrePreparation) throws UpdateOrdreException;
    public void supprimerOrdre(int numeroSequentiel) throws GeneralException;
}
