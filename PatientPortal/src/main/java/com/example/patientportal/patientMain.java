package com.example.patientportal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.*;

public class patientMain {

    @FXML
    private Label phoneNumLabel, lastNameLabel, firstNameLabel, medicalHistoryLabel, immunizationHistoryLabel, incomingAmtLabel;

    @FXML
    private TextField phoneNumField;

    @FXML
    private MenuItem firstDate, secondDate;

    @FXML
    private TextArea visitDateInfo, messageArea, incomingArea;

    @FXML
    private Button logoutButton;

    public String docMessage;


    public void logout(ActionEvent actionEvent) throws IOException{

        PatientPortal m = new PatientPortal();
        PatientPortal.changeScene("login-selection.fxml");

    }

    public void showInfo(ActionEvent actionEvent) throws SQLException {

        //create SQL database connection
        DatabaseConnect connectNow = new DatabaseConnect();

        //create connection
        Connection connectDb = connectNow.getConnection();

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        //show the current patient information
        lastNameLabel.setText(PatientPortal.lastName);
        firstNameLabel.setText(PatientPortal.firstName);
        phoneNumLabel.setText(PatientPortal.phonenumber);
        medicalHistoryLabel.setText(PatientPortal.medical_history);
        immunizationHistoryLabel.setText(PatientPortal.immunization_history);



    }

    public void changeNumber(ActionEvent actionEvent) {


        phoneNumLabel.setText(""); //clear phone text
        //change Label
        phoneNumLabel.setText("+1 " + phoneNumField.getText().toString());

        //extra code for GUI Submission
        lastNameLabel.setText("Ashinhust");
        firstNameLabel.setText("Mark");

        medicalHistoryLabel.setTextFill(Color.RED);
        medicalHistoryLabel.setText("No active medical history");
        immunizationHistoryLabel.setTextFill(Color.RED);
        immunizationHistoryLabel.setText("No history of Immunizations");


    }



    public void firstDate(ActionEvent actionEvent) {

        visitDateInfo.setText("Name: Mark Ashinhust\n\nNotes: This visit was pleasant and Mark is showing signs of good health." +
                "He seems to be exercising and eating well. Blood pressure is average and he has no further concerns.\n\nFinal Evaluation: " +
                "Patient is ready to be released.");

    }


    public void secondDate(ActionEvent actionEvent) {

        visitDateInfo.setText("You're not supposed to BE HERE");
    }

    public void send(ActionEvent actionEvent) {

        docMessage = messageArea.getText().toString();
        messageArea.setStyle("-fx-text-fill: #00ff00");
        messageArea.setText("Message Sent");
        messageArea.setStyle("-fx-text-fill: #000000");

        incomingAmtLabel.setText("0");
        incomingArea.setText("[No messages at this time]");


    }
}
