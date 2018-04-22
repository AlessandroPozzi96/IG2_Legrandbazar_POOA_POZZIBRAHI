package exceptionPackage;

public class UpdateOrdreException extends Exception
{
    private String messageErreur;

    public UpdateOrdreException (String messageErreur)
    {
        this.messageErreur = messageErreur;
    }

    public String getMessage()
    {
        return "L'erreur suivante s'est produite en voulant modifier un ordre : " + messageErreur;
    }
}
