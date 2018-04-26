package modelPackage;

import exceptionPackage.ModelException;

import java.util.GregorianCalendar;

public class Recherche3
{
    private String libelle;
    private GregorianCalendar dateTicket;
    private Integer quantite;
    private String model = "Recherche3";

    public Recherche3(String libelle, GregorianCalendar dateTicket, Integer quantite) throws ModelException{
        setLibelle(libelle);
        setDateTicket(dateTicket);
        setQuantite(quantite);
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public GregorianCalendar getDateTicket() {
        return dateTicket;
    }

    public void setDateTicket(GregorianCalendar dateTicket) {
        this.dateTicket = dateTicket;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) throws ModelException{
        this.quantite = quantite;

        if (quantite == null || quantite <= 0) {
            throw new ModelException("Quantite", model);
        }
    }
}
