package modelPackage;

import java.util.GregorianCalendar;

public class Recherche2
{
    private Integer numeroSequentiel;
    private GregorianCalendar dateOrdre;
    private Integer quantitePrevue;
    private Boolean estUrgent;
    private Integer matri_Res;
    private GregorianCalendar dateTicket;

    public Recherche2(Integer numeroSequentiel, GregorianCalendar dateOrdre, Integer quantitePrevue, Boolean estUrgent, Integer matri_Res, GregorianCalendar dateTicket) {
        setNumeroSequentiel(numeroSequentiel);
        setDateOrdre(dateOrdre);
        setQuantitePrevue(quantitePrevue);
        setEstUrgent(estUrgent);
        setMatri_Res(matri_Res);
        setDateTicket(dateTicket);
    }

    public Integer getNumeroSequentiel() {
        return numeroSequentiel;
    }

    public void setNumeroSequentiel(Integer numeroSequentiel) {
        this.numeroSequentiel = numeroSequentiel;
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

    public void setQuantitePrevue(Integer quantitePrevue) {
        this.quantitePrevue = quantitePrevue;
    }

    public Boolean getEstUrgent() {
        return estUrgent;
    }

    public void setEstUrgent(Boolean estUrgent) {
        this.estUrgent = estUrgent;
    }

    public Integer getMatri_Res() {
        return matri_Res;
    }

    public void setMatri_Res(Integer matri_Res) {
        this.matri_Res = matri_Res;
    }

    public GregorianCalendar getDateTicket() {
        return dateTicket;
    }

    public void setDateTicket(GregorianCalendar dateTicket) {
        this.dateTicket = dateTicket;
    }
}
