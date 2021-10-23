package com.example.patientportal;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

import java.io.IOException;

public class LoginSelection {


    @FXML
    private Button doctorButton, nurseButton, patientButton;

    @FXML
    private MenuItem mainExit;

    public void doctorLoginPageSelect(ActionEvent actionEvent) throws IOException {

        PatientPortal m = new PatientPortal(); //create new portal object
        PatientPortal.changeScene("doctorLoginPage.fxml"); //open doctor login page

    }

    public void nurseLoginPageSelect(ActionEvent actionEvent) throws IOException {

        PatientPortal m = new PatientPortal(); //create new portal object
        PatientPortal.changeScene("nurseLoginPage.fxml"); //open nurse login page
    }

    public void patientLoginPageSelect(ActionEvent actionEvent) throws IOException {

        PatientPortal m = new PatientPortal(); //create new portal object
        PatientPortal.changeScene("patientLoginPage.fxml"); //open patient login/create accnt page
    }

    public void exitProgram(ActionEvent actionEvent) throws IOException {

        Platform.exit(); //exit the program

    }
}


