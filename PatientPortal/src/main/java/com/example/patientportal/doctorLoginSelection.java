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

public class doctorLoginSelection {

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



    public void exitProgram(ActionEvent actionEvent) {

        Platform.exit(); //exit the application

    }

    public void doctorLogin(ActionEvent actionEvent) throws IOException {

        checkLogin(); //method to check login parameters

    }

    private void checkLogin() throws IOException { //validate login with SQL database

        PatientPortal m = new PatientPortal(); //create new portal object

        //create SQL database connection
        DatabaseConnect connectNow = new DatabaseConnect();

        //create connection
        Connection connectDb = connectNow.getConnection();

        //create string to verify the doctor logon information
        String verifyLogin = "SELECT count(1) FROM doctorlogins WHERE username = '" + doctorUsernameInput.getText() + "' AND password ='" + doctorPasswordInput.getText() + "'";




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

        } catch (Exception e) {

            e.printStackTrace();
            e.getCause();

        }


    }


    public void backPage(ActionEvent actionEvent) throws IOException {

        PatientPortal m = new PatientPortal();
        PatientPortal.changeScene("login-selection.fxml");

    }
}
