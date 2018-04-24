package exceptionPackage;

public class GetOrdresRecettesCuisiniersException extends Exception
{
    private String messageErreur;

    public GetOrdresRecettesCuisiniersException (String messageErreur)
    {
        this.messageErreur = messageErreur;
    }

    public String getMessage()
    {
        return "L'erreur suivante s'est produite lors de la récupération des ordres lié aux recettes et aux cuisiniers : " + messageErreur;
    }
}
