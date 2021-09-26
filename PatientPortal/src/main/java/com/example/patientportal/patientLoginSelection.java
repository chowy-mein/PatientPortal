package com.example.patientportal;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URI;


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

    public void patientLogin(ActionEvent actionEvent) throws IOException {

        patientAuth();

    }







    private void patientAuth() throws IOException {

        PatientPortal m = new PatientPortal();

        if(patientUsernameInput.getText().toString().equals("patient") && patientPasswordInput.getText().toString().equals("123"))
        {

            patientSuccessLabel.setTextFill(Color.GREEN);
            patientSuccessLabel.setText("Success!");

            PatientPortal.changeScene("patientMainPage.fxml");


        } else if (patientUsernameInput.getText().isEmpty() && patientPasswordInput.getText().isEmpty()) {

            patientSuccessLabel.setTextFill(Color.RED);
            patientSuccessLabel.setText("Enter Information");

        }
        else
        {

            patientSuccessLabel.setTextFill(Color.RED);
            patientSuccessLabel.setText("Incorrect Username/Password");

        }

    }

    public void backPage(ActionEvent actionEvent) throws IOException {

        PatientPortal m = new PatientPortal();
        PatientPortal.changeScene("login-selection.fxml");

    }


}
