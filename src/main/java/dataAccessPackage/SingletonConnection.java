package dataAccessPackage;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingletonConnection
{
    private static Connection uniqueConnection;

    private SingletonConnection ()
    {
    }

    public static Connection getInstance ()
    {
        if (uniqueConnection == null)
        {
            try
            {
                //Seb → 159357
                //Aless → Pa456lOt
                uniqueConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgrandbazar?useSSL=false", "root", "Pa456lOt");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return  uniqueConnection;
    }
}
