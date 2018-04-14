package exceptionPackage;

public class ModelException extends Exception
{
    private String variable;

    public ModelException(String variable)
    {
        this.variable = variable;
    }

    @Override
    public String getMessage() {
        return "Erreur survenue en voulant attribuer la variable : " + this.variable + " à l'objet OrdrePreparation";
    }
}