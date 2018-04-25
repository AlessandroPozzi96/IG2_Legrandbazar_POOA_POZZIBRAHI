package exceptionPackage;

public class AddOrdreException extends Exception
{
    private String messageErreur;

    public AddOrdreException (String messageErreur)
    {
        this.messageErreur = messageErreur;
        getMessage();
    }

    public String getMessage()
    {
        return "L'erreur suivante s'est produite en voulant ajouter un ordre : " + messageErreur;
    }
}
