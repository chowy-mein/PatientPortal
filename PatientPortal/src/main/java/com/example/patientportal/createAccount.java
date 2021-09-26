package com.example.patientportal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class createAccount {

    @FXML
    private TextField firstName, lastName, month, date, year, phoneNum;

    @FXML
    private TextArea verifyArea;

    private String information;

    public void exitPage(ActionEvent actionEvent) throws IOException {

        PatientPortal m = new PatientPortal();
        PatientPortal.changeScene("login-selection.fxml");

    }


    public void submitButton(ActionEvent actionEvent) {

        information = "First: " + firstName.getText().toString() + "\nLast: " + lastName.getText().toString() +
                "\nDate of Birth: " + month.getText().toString() + "/" + date.getText().toString() + "/"
                + year.getText().toString() + "\nPhone Number: " + phoneNum.getText().toString();

        verifyArea.setText(information);

    }
}
