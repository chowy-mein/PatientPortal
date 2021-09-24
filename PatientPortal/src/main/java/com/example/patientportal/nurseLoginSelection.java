package com.example.patientportal;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

public class nurseLoginSelection {

    @FXML
    private MenuItem nurseExitButton;

    @FXML
    private Button nurseLoginButton;

    public void exitProgram(ActionEvent actionEvent) {

        Platform.exit();

    }

    public void nurseLogin(ActionEvent actionEvent) {

        //method to check nurse login

    }
}
