package dataAccessPackage;

import modelPackage.OrdrePreparation;

import java.sql.*;
import java.util.ArrayList;


public class DBAccess implements DataAccess
{
    public void addOrdre (OrdrePreparation ordrePreparation)
    {

    }

    public ArrayList<OrdrePreparation> getAllOrdres ()
    {
        return new ArrayList<>();
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
