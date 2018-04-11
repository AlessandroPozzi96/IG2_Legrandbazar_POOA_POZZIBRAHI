package exceptionPackage;

public class AllOrdresException extends Exception {
    private String messageErreur;

    public AllOrdresException (String messageErreur)
    {
        this.messageErreur = messageErreur;
    }

    public String getMessage()
    {
        return "L'erreur suivante s'est produite en voulant ajouter un ordre : " + messageErreur;
    }
}
