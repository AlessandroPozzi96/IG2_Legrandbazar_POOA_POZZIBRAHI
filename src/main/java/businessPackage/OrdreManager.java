package businessPackage;

import dataAccessPackage.DBAccess;
import dataAccessPackage.DataAccess;
import exceptionPackage.*;
import modelPackage.OrdrePreparation;
import modelPackage.Recherche2;
import modelPackage.Recherche3;
import modelPackage.Reservation;
import modelPackage.TacheMetier;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

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
    public void supprimerOrdre(int numeroSequentiel)throws GeneralException {
        dao.supprimerOrdre(numeroSequentiel);
    }

    public ArrayList<Reservation> getForeignKeyReservation(int numeroSequentie) throws GeneralException {
        return dao.getForeignKeyReservation(numeroSequentie);
    }
    public void supprimerForeignKeyReservation(int numeroSequentiel) throws GeneralException {
        dao.supprimerForeignKeyReservation(numeroSequentiel);
    }

    public ArrayList<OrdrePreparation> getOrdresRecettesCuisiniers(Integer matri_Cui, String recette) throws GetOrdresRecettesCuisiniersException, ModelException
    {
        return dao.getOrdresRecettesCuisiniers(matri_Cui, recette);
    }

    public ArrayList<Recherche2> getRecherche2(GregorianCalendar dateDeb, GregorianCalendar dateFin) throws GeneralException, ModelException
    {
        return dao.getRecherche2(dateDeb, dateFin);
    }

    public ArrayList<Recherche3> getRecherche3 (Integer numClient) throws GeneralException, ModelException
    {
        return dao.getRecherche3(numClient);
    }

    public ArrayList<String> getClients () throws GeneralException
    {
        return dao.getClients();
    }

    public ArrayList<TacheMetier> getDatesPreparationDuJour(int jour)throws GeneralException{
        Double huitADix, dixUneADouze, douzeUneAQuatorze, quatorzeUneASeize;
        huitADix = dixUneADouze = douzeUneAQuatorze = quatorzeUneASeize = 0.0;
        ArrayList<GregorianCalendar> datesPreparationDuJour;
        datesPreparationDuJour = dao.getDatesPreparationDuJour(jour);
        ArrayList<TacheMetier> tacheMetiers;


        for(GregorianCalendar cal : datesPreparationDuJour){
            if(cal.getTime().getHours()<= 10){
                huitADix ++;
            }else{
                if(cal.getTime().getHours()<=12){
                    dixUneADouze++;
                }else{
                    if(cal.getTime().getHours()<=14){
                        douzeUneAQuatorze++;
                    }
                    else{
                        quatorzeUneASeize++;
                    }
                }
            }
        }
        tacheMetiers = new ArrayList<>();
        tacheMetiers.add(new TacheMetier("8h à 10h",huitADix/3.0));
        tacheMetiers.add(new TacheMetier("10h à 12h",dixUneADouze/3.0));
        tacheMetiers.add(new TacheMetier("12h à 14h",douzeUneAQuatorze/3.0));
        tacheMetiers.add(new TacheMetier("14h à 16h",quatorzeUneASeize/3.0));

        return tacheMetiers;
    }

}
