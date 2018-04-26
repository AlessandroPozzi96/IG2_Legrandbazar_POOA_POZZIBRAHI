package modelPackage;

import exceptionPackage.ModelException;
import viewPackage.FonctionsUtile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    private String model = "Ordre de preparation";


    public OrdrePreparation() {
    }

    public void setCodeBarre(Integer codeBarre) throws ModelException
    {
        this.codeBarre = codeBarre;

        if(codeBarre != null) {
            if (this.codeBarre <= 0)
                throw new ModelException("CodeBarre", model);
        }
    }

    public void setDate(GregorianCalendar date) throws ModelException
    {
        this.date = date;
        if (this.date == null)
            throw new ModelException("date", model);
    }

    public void setDatePreparation(GregorianCalendar datePreparation) {
        this.datePreparation = datePreparation;
    }

    public void setDateVente(GregorianCalendar dateVenteee) {
        this.dateVente = dateVenteee;
    }

    public void setNumeroSequentiel(Integer numeroSequentiel) throws ModelException
    {
        this.numeroSequentiel = numeroSequentiel;
        if (this.numeroSequentiel == null)
            throw new ModelException("numeroSequentiel", model);
    }

    public void setEstUrgent(Boolean estUrgent) throws ModelException
    {
        this.estUrgent = estUrgent;

        if (this.estUrgent == null)
            throw new ModelException("estUrgent", model);
    }

    public void setNom(String nom) throws ModelException
    {
        this.nom = nom;

        if (this.nom == null || !this.nom.matches(".*[a-zA-Z].*"))
            throw new ModelException("nom", model);
    }

    public void setMatricule_Cui(Integer matricule_Cui) throws ModelException
    {
        this.matricule_Cui = matricule_Cui;

        if (this.matricule_Cui != null && this.matricule_Cui <= 0)
            throw new ModelException("matricule_Cui", model);
    }

    public void setMatricule_Res(Integer matricule_Res) throws ModelException
    {
        this.matricule_Res = matricule_Res;

        if (this.matricule_Res == null || this.matricule_Res <= 0)
            throw new ModelException("matricule_Res", model);
    }

    public void setQuantitePrevue(Integer quantitePrevue) throws ModelException
    {
        this.quantitePrevue = quantitePrevue;

        if (this.quantitePrevue == null || this.quantitePrevue <= 0)
            throw new ModelException("quantitePrevue", model);
    }

    public void setQuantiteProduite(Integer quantiteProduite) throws ModelException
    {
        this.quantiteProduite = quantiteProduite;

        if (this.quantiteProduite != null && quantiteProduite <= 0)
            throw new ModelException("quantiteProduite", model);
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
        return this.dateVente;
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

    @Override
    public String toString() {
        String dateV = (dateVente != null)? this.conversionDateVersString(dateVente) : "Pas de date de vente disponible";
        String dateP = (datePreparation != null)? this.conversionDateVersString(datePreparation) : "Pas de date de préparation disponible";
        return "OrdrePreparation \n{" +
                "\ndate=" + this.conversionDateVersString(date) +
                ", \nnumeroSequentiel=" + numeroSequentiel +
                ", \nquantitePrevue=" + quantitePrevue +
                ", \nquantiteProduite=" + quantiteProduite +
                ", \ndateVente=" + dateV +
                ", \ndatePreparation=" + dateP +
                ", \nremarque='" + remarque + '\'' +
                ", \nestUrgent=" + estUrgent +
                ", \nnom='" + nom + '\'' +
                ", \ncodeBarre=" + codeBarre +
                ", \nmatricule_Cui=" + matricule_Cui +
                ", \nmatricule_Res=" + matricule_Res + "\n" +
                '}';
    }

    public String conversionDateVersString (GregorianCalendar calendar)
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = calendar.getTime();
        return dateFormat.format(date);
    }
}