package dataAccessPackage;

import exceptionPackage.AddOrdreException;
import modelPackage.OrdrePreparation;

import java.sql.*;
import java.util.ArrayList;


public class DBAccess implements DataAccess
{
    public void addOrdre (OrdrePreparation ordrePreparation) throws AddOrdreException
    {
        if (SingletonConnection.getInstance() == null)
            throw  new AddOrdreException("Erreur connexion !");
    }

    public ArrayList<OrdrePreparation> getAllOrdres ()
    {
        ArrayList<OrdrePreparation> ordres = new ArrayList<>();
        return ordres;
    }
}

/*private Connection connection;

    {
        try
        {
            //Seb → 159357
            //Aless → Pa456lOt
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgrandbazar?useSSL=false","root","Pa456lOt");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }*/
