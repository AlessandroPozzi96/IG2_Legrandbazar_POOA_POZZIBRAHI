package controllerPackage;

import businessPackage.OrdreManager;
import exceptionPackage.*;
import modelPackage.OrdrePreparation;
import modelPackage.Recherche2;
import modelPackage.Recherche3;
import modelPackage.Reservation;

import java.util.ArrayList;
import java.util.GregorianCalendar;

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

    public void supprimerOrdre(int numeroSequentiel)throws GeneralException  {
        manager.supprimerOrdre(numeroSequentiel);
    }

    public ArrayList<Reservation> getForeignKeyReservation(int numeroSequentie) throws GeneralException {
        return manager.getForeignKeyReservation(numeroSequentie);
    }
    public void supprimerForeignKeyReservation(int numeroSequentiel) throws GeneralException {
        manager.supprimerForeignKeyReservation(numeroSequentiel);
    }

    public ArrayList<OrdrePreparation> getOrdresRecettesCuisiniers(Integer matri_Cui, String recette) throws GetOrdresRecettesCuisiniersException, ModelException
    {
        return manager.getOrdresRecettesCuisiniers(matri_Cui, recette);
    }

    public ArrayList<Recherche2> getRecherche2(GregorianCalendar dateDeb, GregorianCalendar dateFin) throws GeneralException, ModelException
    {
        return manager.getRecherche2(dateDeb, dateFin);
    }

    public ArrayList<Recherche3> getRecherche3 (Integer numClient) throws GeneralException, ModelException
    {
        return manager.getRecherche3(numClient);
    }

    public ArrayList<String> getClients () throws GeneralException
    {
        return manager.getClients();
    }
}
