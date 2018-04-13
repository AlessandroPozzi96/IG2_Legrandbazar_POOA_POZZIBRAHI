package exceptionPackage;

public class AllRecetteNomException extends Exception {
    private String messageErreur;

    public AllRecetteNomException (String messageErreur)
    {
        this.messageErreur = messageErreur;
    }

    public String getMessage()
    {
        return "L'erreur suivante s'est produite en voulant Récupérer les noms de recette : " + messageErreur;
    }
}
