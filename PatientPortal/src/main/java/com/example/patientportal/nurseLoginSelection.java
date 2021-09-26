package com.example.patientportal;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;


import java.io.IOException;

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

        if(nurseUsernameInput.getText().toString().equals("nurse") && nursePasswordInput.getText().toString().equals("123"))
        {

            successLabel.setTextFill(Color.GREEN);
            successLabel.setText("Success!");

            PatientPortal.changeScene("nurseMainPage.fxml");


        } else if (nurseUsernameInput.getText().isEmpty() && nursePasswordInput.getText().isEmpty()) {

            successLabel.setTextFill(Color.RED);
            successLabel.setText("Enter Information");

        }
        else
        {

            successLabel.setTextFill(Color.RED);
            successLabel.setText("Incorrect Username/Password");

        }


    }

}
