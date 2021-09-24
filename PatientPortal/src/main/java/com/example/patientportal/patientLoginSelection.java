package com.example.patientportal;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class patientLoginSelection {

    @FXML
    private Button patientLoginButton;

    public void endProgram(ActionEvent actionEvent) {

        Platform.exit();

    }

    public void patientCreateAccount(MouseEvent mouseEvent) {
    }

    public void patientLogin(MouseEvent mouseEvent) {
    }
}
