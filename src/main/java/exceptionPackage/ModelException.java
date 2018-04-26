package exceptionPackage;

public class ModelException extends Exception
{
    private String variable;
    private String model;

    public ModelException(String variable, String model)
    {
        this.variable = variable;
        this.model = model;
        afficheExceptionConsole();
    }

    @Override
    public String getMessage() {
        return "Erreur survenue en voulant attribuer la variable : " + this.variable + " à l'objet : " + model;
    }
    public void afficheExceptionConsole(){
        System.out.println(getMessage());
    }

}
