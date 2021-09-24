package com.example.patientportal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class LoginSelection {


    @FXML
    private Button doctorButton;

    @FXML
    private Button nurseButton;

    @FXML
    private Button patientButton;

    public void doctorLoginPageSelect(ActionEvent actionEvent) throws IOException {

        PatientPortal m = new PatientPortal();
        PatientPortal.changeScene("doctorLoginPage.fxml");

    }

    public void nurseLoginPageSelect(ActionEvent actionEvent) {
    }

    public void patientLoginPageSelect(ActionEvent actionEvent) {
    }
}


