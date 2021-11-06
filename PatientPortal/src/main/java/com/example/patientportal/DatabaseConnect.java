package com.example.patientportal;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnect {

    public Connection databaseLink;

    public Connection getConnection()
    {

        String dbName = "patientportalinfo";
        String dbUser = "root";
        String dbPass = "mashinhust12";

        String url = "jdbc:mysql://localhost/" + dbName;

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");

            databaseLink = DriverManager.getConnection(url, dbUser, dbPass);

        }
        catch (Exception e)
        {

            e.printStackTrace();
            e.getCause();

        }

        return databaseLink;

    }

}
