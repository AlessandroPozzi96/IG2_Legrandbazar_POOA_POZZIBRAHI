package modelPackage;

import exceptionPackage.ModelException;

import java.util.GregorianCalendar;

public class Recherche2
{
    private Integer numeroSequentiel;
    private GregorianCalendar dateOrdre;
    private Integer quantitePrevue;
    private Boolean estUrgent;
    private Integer matri_Res;
    private GregorianCalendar dateTicket;

    public Recherche2(Integer numeroSequentiel, GregorianCalendar dateOrdre, Integer quantitePrevue, Boolean estUrgent, Integer matri_Res, GregorianCalendar dateTicket) throws ModelException {
        setNumeroSequentiel(numeroSequentiel);
        setDateOrdre(dateOrdre);
        setQuantitePrevue(quantitePrevue);
        setEstUrgent(estUrgent);
        setMatri_Res(matri_Res);
        setDateTicket(dateTicket);
    }

    public Integer getNumeroSequentiel(){
        return numeroSequentiel;
    }

    public void setNumeroSequentiel(Integer numeroSequentiel) throws ModelException{
        this.numeroSequentiel = numeroSequentiel;

        if (numeroSequentiel == null || numeroSequentiel <= 0 )
        {
            throw new ModelException("NumeroSequentiel recherche 2");
        }
    }

    public GregorianCalendar getDateOrdre() {
        return dateOrdre;
    }

    public void setDateOrdre(GregorianCalendar dateOrdre) {
        this.dateOrdre = dateOrdre;
    }

    public Integer getQuantitePrevue() {
        return quantitePrevue;
    }

    public void setQuantitePrevue(Integer quantitePrevue) throws  ModelException{
        this.quantitePrevue = quantitePrevue;

        if (quantitePrevue == null || quantitePrevue <= 0) {
            throw new ModelException("quantitePrevue recherche2");
        }
    }

    public Boolean getEstUrgent() {
        return estUrgent;
    }

    public void setEstUrgent(Boolean estUrgent) throws ModelException{
        this.estUrgent = estUrgent;

        if (estUrgent == null)
        {
            throw new ModelException("EstUrgent recherche2");
        }
    }

    public Integer getMatri_Res() {
        return matri_Res;
    }

    public void setMatri_Res(Integer matri_Res) throws ModelException{
        this.matri_Res = matri_Res;

        if (matri_Res == null || matri_Res <= 0) {
            throw new ModelException("matri_Res recherche2");
        }
    }

    public GregorianCalendar getDateTicket() {
        return dateTicket;
    }

    public void setDateTicket(GregorianCalendar dateTicket) {
        this.dateTicket = dateTicket;
    }
}
