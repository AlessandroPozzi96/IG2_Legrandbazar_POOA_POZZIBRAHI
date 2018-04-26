package modelPackage;

public class TacheMetier {
    private String trancheHoraire;
    private Double moyenne;

    public TacheMetier(String trancheHoraire,Double moyenne){
        this.trancheHoraire = trancheHoraire;
        this.moyenne = moyenne;
    }

    public void setTrancheHoraire(String trancheHoraire) {
        this.trancheHoraire = trancheHoraire;
    }

    public void setMoyenne(Double moyenne) {
        this.moyenne = moyenne;
    }

    public Double getMoyenne() {
        return moyenne;
    }

    public String getTrancheHoraire() {
        return trancheHoraire;
    }
}
