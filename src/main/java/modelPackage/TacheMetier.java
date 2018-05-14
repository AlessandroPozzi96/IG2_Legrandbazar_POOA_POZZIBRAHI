package modelPackage;

import exceptionPackage.ModelException;

public class TacheMetier {
    private String trancheHoraire;
    private Double moyenne;

    public TacheMetier(String trancheHoraire,Double moyenne)throws ModelException{
        this.trancheHoraire = trancheHoraire;
        setMoyenne(moyenne);
    }

    public void setTrancheHoraire(String trancheHoraire) {
        this.trancheHoraire = trancheHoraire;
    }

    public void setMoyenne(Double moyenne) throws ModelException{
        this.moyenne = moyenne;
        if (this.moyenne < 0) {
            throw new ModelException("Moyenne", "Tache métier");
        }
    }

    public Double getMoyenne() {
        return moyenne;
    }

    public String getTrancheHoraire() {
        return trancheHoraire;
    }
}
