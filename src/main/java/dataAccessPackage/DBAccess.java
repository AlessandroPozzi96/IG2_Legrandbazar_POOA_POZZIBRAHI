package dataAccessPackage;


import java.util.*;
import java.sql.*;


public class DBAccess {

private Connection connection;

    {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgrandbazar?useSSL=false","root","159357");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
