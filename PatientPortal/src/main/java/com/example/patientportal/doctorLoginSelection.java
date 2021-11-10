package com.example.patientportal;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.fxml.Initializable;

/*
    This is the page for the doctor to input their login credentials. This page will show input textfields 
    for the doctor’s username and password. Once the doctor has inputted their credentials they will be 
    authenticated by looking at the database. Once authenticated they would be redirected to the doctor main page.
*/

public class doctorLoginSelection {
    
    // Instantiate the private variables
    @FXML
    private MenuItem doctorExit, backMenu;

    @FXML
    private TextField doctorUsernameInput;

    @FXML
    private PasswordField doctorPasswordInput;

    @FXML
    private Button doctorLoginButton;

    @FXML
    private Label successLabel;


    // Method for exiting the program
    public void exitProgram(ActionEvent actionEvent) {

        Platform.exit(); //exit the application

    }
    // Method for when the user needs to login
    public void doctorLogin(ActionEvent actionEvent) throws IOException {

        checkLogin(); //method to check login parameters

    }
    // Method for checking if the login information is correct or not using the SQL database
    private void checkLogin() throws IOException { 

        PatientPortal m = new PatientPortal(); //create new portal object

        //create SQL database connection
        DatabaseConnect connectNow = new DatabaseConnect();

        //create connection
        Connection connectDb = connectNow.getConnection();

        //create string to verify the doctor logon information
        String verifyLogin = "SELECT count(1) FROM doctorlogins WHERE username = '" + doctorUsernameInput.getText() + "' AND password ='" + doctorPasswordInput.getText() + "'";



        // Error handling
        try{

            Statement statement = connectDb.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            String name_query = "SELECT firstname, lastname FROM doctorlogins";

            //name query statement
            PreparedStatement pst = connectDb.prepareStatement(name_query);
            //set the query
            ResultSet names = pst.executeQuery();

            while (names.next()){

                PatientPortal.firstName = names.getString("firstname");
                PatientPortal.lastName = names.getString("lastname");

            }

            //1 is within database and 0 is not included in the database
            while(queryResult.next())
            {

                if(queryResult.getInt(1) == 1)
                {

                    successLabel.setTextFill(Color.GREEN);
                    successLabel.setText("Success!");

                    PatientPortal.changeScene("doctorMain.fxml");

                }
                else
                {

                    successLabel.setTextFill(Color.RED);
                    successLabel.setText("Incorrect Username/Password");

                }


            }
        // Catch the errors and print them out 
        } catch (Exception e) {

            e.printStackTrace();
            e.getCause();

        }


    }

    // Method to go back to the main page 
    public void backPage(ActionEvent actionEvent) throws IOException {

        PatientPortal m = new PatientPortal();
        PatientPortal.changeScene("login-selection.fxml");

    }
}
