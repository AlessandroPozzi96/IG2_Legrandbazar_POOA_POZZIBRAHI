package dataAccessPackage;

import modelPackage.OrdrePreparation;

import java.sql.*;


public class DBAccess implements DataAccess
{
    public void addOrdre (OrdrePreparation ordrePreparation)
    {

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
