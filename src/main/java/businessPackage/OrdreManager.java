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
@SuppressWarnings({"deprecation"})
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

    public ArrayList<TacheMetier> getDatesPreparationDuJour(int jour,int trancheHoraire1,int trancheHoraire2,int interval) throws GeneralException, ModelException {
        int nombreDetranche = (trancheHoraire2-trancheHoraire1) / interval;
        ArrayList<TacheMetier> tacheMetiers;
        tacheMetiers = new ArrayList<>();
        int i = 0;
        while(i < nombreDetranche){
            tacheMetiers.add(new TacheMetier(trancheHoraire1+i*interval+" a "+((int)trancheHoraire1+i*interval+interval),0.0));
            i++;
        }
        ArrayList<GregorianCalendar> datesPreparationDuJour;
        datesPreparationDuJour = dao.getDatesPreparationDuJour(jour);
        for(GregorianCalendar cal : datesPreparationDuJour){
            i = 0;
            while(i<nombreDetranche){
                if(cal.getTime().getHours() >= (trancheHoraire1+i*interval) && cal.getTime().getHours() < trancheHoraire1+i*interval+interval){
                    tacheMetiers.get(i).setMoyenne(tacheMetiers.get(i).getMoyenne()+1);
                }
                i++;
            }

        }
        i = 0;
        System.out.println(datesPreparationDuJour.size());
        while(i<nombreDetranche){
            tacheMetiers.get(i).setMoyenne(tacheMetiers.get(i).getMoyenne()/3.0); // 3 car on la recherche ce fais sur 3 mardi
            i++;
        }
        return tacheMetiers;
    }

    public Integer conversionStringVersInteger(String chaine) {
        Integer entier;
        try
        {
            entier = Integer.valueOf(chaine);
        }
        catch (Exception error)
        {
            entier = -1;
        }
        return  entier;
    }

}
