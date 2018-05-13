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

    public ArrayList<TacheMetier> getDatesPreparationDuJour(int jour, int horaire1, int horaire2, int trancheHoraire) throws GeneralException{
        if (horaire2 < horaire1 || horaire1 == horaire2) {
            throw new GeneralException("Erreur ! Horaire invalide", "Horaire tâche métier");
        }
        int tranche = 0;
        try {
            tranche = (horaire2 - horaire1) / trancheHoraire;
        } catch (Exception e) {
            throw new GeneralException("Entier non null", "TrancheHoraire tâche métier");
        }
        int cptTranches [] = new int[tranche];
        double [] nbOrdre = {0,0,0,0,0,0,0};

        double huitADix, dixUneADouze, douzeAQuatorze, quatorzeASeize;
        huitADix = dixUneADouze = douzeAQuatorze = quatorzeASeize = 0.0;
        ArrayList<GregorianCalendar> datesPreparationDuJour;
        datesPreparationDuJour = dao.getDatesPreparationDuJour(jour);
        ArrayList<TacheMetier> tacheMetiers;


/*        for(GregorianCalendar cal : datesPreparationDuJour){
            if(cal.getTime().getHours() > 7 && cal.getTime().getHours()< 10){
                huitADix ++;
            }else{
                if(cal.getTime().getHours()<12){
                    dixUneADouze++;
                }else{
                    if(cal.getTime().getHours()<14){
                        douzeAQuatorze++;
                    }
                    else{
                        quatorzeASeize++;
                    }
                }
            }*/
        int heure;
        int horaire1Addition;
        int horaire2Addition;
        for(GregorianCalendar cal : datesPreparationDuJour){
            heure = cal.getTime().getHours();
            horaire1Addition = horaire1;
            horaire2Addition = horaire1;
            for (int i = 0; i < tranche; i++) {
                horaire1Addition += trancheHoraire;
                if ( heure> (horaire2Addition-1) && heure < horaire1Addition) {
                    cptTranches[i]++;
                }
                horaire2Addition = horaire1Addition;
            }
        }
        tacheMetiers = new ArrayList<>();
       /* tacheMetiers.add(new TacheMetier("8h à 10h",huitADix/3.0));
        tacheMetiers.add(new TacheMetier("10h à 12h",dixUneADouze/3.0));
        tacheMetiers.add(new TacheMetier("12h à 14h",douzeAQuatorze/3.0));
        tacheMetiers.add(new TacheMetier("14h à 16h",quatorzeASeize/3.0));*/

       int cumulHoraire1 = horaire1;
       int cumulHoraire2 = horaire1;
        for (int i = 0; i < tranche; i++) {
            cumulHoraire2 += trancheHoraire;
            tacheMetiers.add(new TacheMetier(cumulHoraire1 + "H à " + cumulHoraire2 + "H", new Double (cptTranches[i] / (trancheHoraire + 1))));
            cumulHoraire1 = cumulHoraire2;
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
