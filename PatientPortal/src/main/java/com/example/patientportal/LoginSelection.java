package com.example.patientportal;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

import java.io.IOException;
/*
    The login Selection page is the first page that shows up when the user first runs the program. 
    This page has three buttons that would lead to the login/signup pages for Doctor, Nurse or Patient. 
    There is also an exit button to shut down the program. This class’s main purpose is for the user to
    select who they are and that determines what types of information they will be provided after authentication.
*/
public class LoginSelection {

    // Instantiate the private Button and menuItem
    @FXML
    private Button doctorButton, nurseButton, patientButton;

    @FXML
    private MenuItem mainExit;
    
    // Method for Docotor button for the doctors 
    public void doctorLoginPageSelect(ActionEvent actionEvent) throws IOException {

        PatientPortal m = new PatientPortal(); //create new portal object
        PatientPortal.changeScene("doctorLoginPage.fxml"); //open doctor login page

    }
    
    // Method for Nurse button for the nurse 
    public void nurseLoginPageSelect(ActionEvent actionEvent) throws IOException {

        PatientPortal m = new PatientPortal(); //create new portal object
        PatientPortal.changeScene("nurseLoginPage.fxml"); //open nurse login page
    }
    
    // Method for Patient button for the patient 
    public void patientLoginPageSelect(ActionEvent actionEvent) throws IOException {

        PatientPortal m = new PatientPortal(); //create new portal object
        PatientPortal.changeScene("patientLoginPage.fxml"); //open patient login/create accnt page
    }

    public void exitProgram(ActionEvent actionEvent) throws IOException {

        Platform.exit(); //exit the program

    }
}


