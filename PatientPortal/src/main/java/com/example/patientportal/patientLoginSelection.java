package com.example.patientportal;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URI;
import java.sql.*;


public class patientLoginSelection {

    @FXML
    private Button patientLoginButton, createAccntBttn; //objects for our clickable buttons

    @FXML
    private TextField patientUsernameInput;

    @FXML
    private PasswordField patientPasswordInput;

    @FXML
    private Label patientSuccessLabel;

    @FXML
    private MenuItem backMenu;

    public void endProgram(ActionEvent actionEvent) {

        Platform.exit();

    }

    public void patientCreateAccount(ActionEvent actionEvent) throws IOException {

        PatientPortal m = new PatientPortal();
        PatientPortal.changeScene("createAccountPage.fxml");

    }

    public void patientLogin(ActionEvent actionEvent) throws IOException, SQLException {

        patientAuth();

    }


    private void patientAuth() throws IOException, SQLException {

        PatientPortal m = new PatientPortal();

        //create SQL database connection
        DatabaseConnect connectNow = new DatabaseConnect();

        //create connection
        Connection connectDb = connectNow.getConnection();

        ResultSet resultSet = null;
        Statement statement = connectDb.createStatement();


        //create string to verify the doctor logon information
        String verifyLogin = "SELECT count(1) FROM patientlogins WHERE username = '" + patientUsernameInput.getText() + "' AND password ='" + patientPasswordInput.getText() + "'";

        String name_query = "SELECT firstname, lastname, phonenumber, medh, imm, patientID FROM patientlogins WHERE username = '" + patientUsernameInput.getText() +"'";


        try{

            ResultSet queryResult = statement.executeQuery(verifyLogin);
            //name query statement
            PreparedStatement pst = connectDb.prepareStatement(name_query);
            //set the query
            ResultSet names = pst.executeQuery();

            while (names.next()){

                PatientPortal.firstName = names.getString("firstname");
                PatientPortal.lastName = names.getString("lastname");
                PatientPortal.phonenumber = names.getString("phonenumber");
                PatientPortal.medical_history = names.getString("medh");
                PatientPortal.immunization_history = names.getString("imm");
                PatientPortal.patientID = names.getInt("patientID");

            }


            //1 is within database and 0 is not included in the database
            while(queryResult.next())
            {

                if(queryResult.getInt(1) == 1)
                {

                    patientSuccessLabel.setTextFill(Color.GREEN);
                    patientSuccessLabel.setText("Success!");

                    PatientPortal.changeScene("PatientMainPage.fxml");

                }
                else
                {

                    patientSuccessLabel.setTextFill(Color.RED);
                    patientSuccessLabel.setText("Incorrect Username/Password");

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
