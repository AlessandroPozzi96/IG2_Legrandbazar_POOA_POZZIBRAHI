package modelPackage;

import exceptionPackage.ModelException;

import java.util.Calendar;
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

    public void setCodeBarre(Integer codeBarre) throws ModelException
    {
        if (this.codeBarre == null || this.codeBarre <= 0)
            throw  new ModelException("CodeBarre");
        else
            this.codeBarre = codeBarre;
    }

    public void setDate(GregorianCalendar date) throws ModelException
    {
        if (this.date == null)
            throw new ModelException("date");
        else
            this.date = date;
    }

    public void setDatePreparation(GregorianCalendar datePreparation) {
        this.datePreparation = datePreparation;
    }

    public void setDateVente(GregorianCalendar dateVente) {
        this.dateVente = dateVente;
    }

    public void setNumeroSequentiel(Integer numeroSequentiel) throws ModelException
    {
        if (this.numeroSequentiel == null || this.numeroSequentiel <= 0)
            throw new ModelException("numeroSequentiel");
        else
            this.numeroSequentiel = numeroSequentiel;
    }

    public void setEstUrgent(Boolean estUrgent) throws ModelException
    {
        if (this.estUrgent == null)
            throw new ModelException("estUrgent");
        else
            this.estUrgent = estUrgent;
    }

    public void setNom(String nom) throws ModelException
    {
        if (this.nom == null || !this.nom.matches(".*[a-zA-Z].*"))
            throw new ModelException("nom");
        else
            this.nom = nom;
    }

    public void setMatricule_Cui(Integer matricule_Cui) throws ModelException
    {
        if (this.matricule_Cui != null && this.matricule_Cui <= 0)
            throw new ModelException("matricule_Cui");
        else
            this.matricule_Cui = matricule_Cui;
    }

    public void setMatricule_Res(Integer matricule_Res) throws ModelException
    {
        if (this.matricule_Res == null || this.matricule_Res <= 0)
            throw new ModelException("matricule_Res");
        else
            this.matricule_Res = matricule_Res;
    }

    public void setQuantitePrevue(Integer quantitePrevue) throws ModelException
    {
        if (this.quantitePrevue == null || this.quantitePrevue <= 0)
            throw new ModelException("quantitePrevue");
        else
            this.quantitePrevue = quantitePrevue;
    }

    public void setQuantiteProduite(Integer quantiteProduite) throws ModelException
    {
        if (this.quantiteProduite != null && quantiteProduite <= 0)
            throw new ModelException("quantiteProduite");
        else
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

    public Integer getMatricule_Res()
    {
        return matricule_Res;
    }



}