package modelPackage;

import java.util.GregorianCalendar;

public class OrdrePreparation {
    private GregorianCalendar date;
    private Integer numeroSequentiel;
    private Integer quantitePrevue;
    private Integer quantiteProduite;
    private GregorianCalendar dateVente;
    private GregorianCalendar datePreparation;
    private String remarque;
    private Boolean estUrgent;
    private String nom;
    private Integer codeBarre;
    private Integer matricule_Cui;
    private Integer matricule_Res;


    public OrdrePreparation() {
    }

    public void setCodeBarre(Integer codeBarre) {
        this.codeBarre = codeBarre;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public void setDatePreparation(GregorianCalendar datePreparation) {
        this.datePreparation = datePreparation;
    }

    public void setDateVente(GregorianCalendar dateVente) {
        this.dateVente = dateVente;
    }

    public void setNumeroSequentiel(Integer numeroSequentiel) {
        this.numeroSequentiel = numeroSequentiel;
    }

    public void setEstUrgent(Boolean estUrgent) {
        this.estUrgent = estUrgent;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setMatricule_Cui(Integer matricule_Cui) {
        this.matricule_Cui = matricule_Cui;
    }

    public void setMatricule_Res(Integer matricule_Res) {
        this.matricule_Res = matricule_Res;
    }

    public void setQuantitePrevue(Integer quantitePrevue) {
        this.quantitePrevue = quantitePrevue;
    }

    public void setQuantiteProduite(Integer quantiteProduite) {
        this.quantiteProduite = quantiteProduite;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public Integer getNumeroSequentiel() {
        return numeroSequentiel;
    }

    public Integer getQuantitePrevue() {
        return quantitePrevue;
    }

    public Integer getQuantiteProduite() {
        return quantiteProduite;
    }

    public GregorianCalendar getDateVente() {
        return dateVente;
    }

    public GregorianCalendar getDatePreparation() {
        return datePreparation;
    }

    public String getRemarque() {
        return remarque;
    }

    public Boolean getEstUrgent() {
        return estUrgent;
    }

    public String getNom() {
        return nom;
    }

    public Integer getCodeBarre() {
        return codeBarre;
    }

    public Integer getMatricule_Cui() {
        return matricule_Cui;
    }

    public Integer getMatricule_Res() {
        return matricule_Res;
    }



}