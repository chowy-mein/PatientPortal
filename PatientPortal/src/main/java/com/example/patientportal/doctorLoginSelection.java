package com.example.patientportal;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.io.IOException;

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

    private void checkLogin() throws IOException {

        PatientPortal m = new PatientPortal(); //create new portal object

        if (doctorUsernameInput.getText().toString().equals("admin") && doctorPasswordInput.getText().toString().equals("123"))
        {

            successLabel.setTextFill(Color.GREEN);
            successLabel.setText("Success!");

        }

        else if (doctorUsernameInput.getText().isEmpty() && doctorPasswordInput.getText().isEmpty())
        {

            successLabel.setTextFill(Color.RED);
            successLabel.setText("Enter Information");
        }

        else
        {
            successLabel.setTextFill(Color.RED);
            successLabel.setText("Wrong Username or Password");

        }


    }


    public void backPage(ActionEvent actionEvent) throws IOException {

        PatientPortal m = new PatientPortal();
        PatientPortal.changeScene("login-selection.fxml");

    }
}
