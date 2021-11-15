package com.example.patientportal;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
/*
    This is the page for the nurse to input their login credentials. This page will show input 
    textfields for the nurse’s username and password. Once the nurse has inputted their credentials 
    they will be authenticated by looking at the database. Once authenticated they would be redirected 
    to the nurse main page.
*/

public class nurseLoginSelection {

    @FXML
    private MenuItem nurseExitButton, backItem;
    @FXML //text area and password objects
    private TextField nurseUsernameInput;
    @FXML
    private PasswordField nursePasswordInput;
    @FXML
    private Label successLabel; //label for incorrect password or success
    @FXML
    private Button nurseLoginButton;
    
    public void exitProgram(ActionEvent actionEvent) {

        Platform.exit();

    }

    public void nurseLogin(ActionEvent actionEvent) throws IOException {

        //method to check nurse login
        checkLogin();

    }

    public void backPage(ActionEvent actionEvent) throws IOException {

        PatientPortal m = new PatientPortal();
        PatientPortal.changeScene("login-selection.fxml");

    }

    private void checkLogin() throws IOException //method to verify login information for nurses
    {

        PatientPortal m = new PatientPortal();

        DatabaseConnect connnectNow = new DatabaseConnect();

        Connection connectDb = connnectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM nurselogins WHERE username = '" + nurseUsernameInput.getText() + "' AND password ='" + nursePasswordInput.getText() + "'";

        try{

            Statement statement = connectDb.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            //1 is within database and 0 is not included in the database
            while(queryResult.next())
            {

                if(queryResult.getInt(1) == 1)
                {

                    successLabel.setTextFill(Color.GREEN);
                    successLabel.setText("Success!");

                    PatientPortal.changeScene("nurseMainPage.fxml");

                }
                else
                {

                    successLabel.setTextFill(Color.RED);
                    successLabel.setText("Incorrect Username/Password");

                }

            }

        } catch (Exception e) {

            e.printStackTrace();
            e.getCause();

        }

    }

}
