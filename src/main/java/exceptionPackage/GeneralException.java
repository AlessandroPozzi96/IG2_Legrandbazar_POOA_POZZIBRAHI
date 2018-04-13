package exceptionPackage;

public class GeneralException extends Exception {
    private String messageErreur;
    private String champ;
    public GeneralException(String messageErreur,String champ)
    {
        this.champ = champ;
        this.messageErreur = messageErreur;
    }

    public String getMessage()
    {
        return "L'erreur suivante s'est produite en voulant récupérer "+champ+" : " + messageErreur;
    }
}
