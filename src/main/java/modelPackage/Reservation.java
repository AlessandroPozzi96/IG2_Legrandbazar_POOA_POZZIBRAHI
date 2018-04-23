package modelPackage;

public class Reservation {

    private Integer numeroSequentiel;
    private Integer codeBarre;
    private Integer quantiteReserve;

    public void setNumeroSequentiel(Integer numeroSequentiel) {
        this.numeroSequentiel = numeroSequentiel;
    }

    public void setCodeBarre(Integer codeBarre) {
        this.codeBarre = codeBarre;
    }

    public void setQuantiteReserve(Integer quantiteReserve) {
        this.quantiteReserve = quantiteReserve;
    }

    public Integer getNumeroSequentiel() {
        return numeroSequentiel;
    }

    public Integer getCodeBarre() {
        return codeBarre;
    }

    public Integer getQuantiteReserve() {
        return quantiteReserve;
    }
}
