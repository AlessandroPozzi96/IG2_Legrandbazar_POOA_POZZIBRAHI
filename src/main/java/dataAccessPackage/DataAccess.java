package dataAccessPackage;

import exceptionPackage.*;
import modelPackage.OrdrePreparation;
import modelPackage.Recherche2;
import modelPackage.Recherche3;
import modelPackage.Reservation;

import java.util.ArrayList;
import java.util.GregorianCalendar;

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
    void supprimerOrdre(int numeroSequentiel) throws GeneralException;
    ArrayList<OrdrePreparation> getOrdresRecettesCuisiniers(Integer matri_Cui, String recette) throws GetOrdresRecettesCuisiniersException, ModelException;

    ArrayList<Reservation> getForeignKeyReservation(int numeroSequentie) throws GeneralException;
    void supprimerForeignKeyReservation(int numeroSequentiel) throws GeneralException;
    ArrayList<Recherche2> getRecherche2(GregorianCalendar dateDeb, GregorianCalendar dateFin) throws GeneralException, ModelException;
    ArrayList<String> getClients () throws GeneralException;
    ArrayList<Recherche3> getRecherche3 (Integer numClient) throws GeneralException, ModelException;
}
