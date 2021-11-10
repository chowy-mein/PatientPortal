package com.example.patientportal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;


import java.io.IOException;
import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseDragEvent;

public class doctorMain {

    @FXML
    private MenuItem helpButton;

    @FXML
    private TextArea infoField;

    @FXML
    private RadioButton vitalsButton, messageButton;

    @FXML
    public static Label welcomeLabel;

    private String info;

    public String doctorMessage;

    int patientID = 0;

    public doctorMain() throws SQLException {
    }


    public void backToLogin(ActionEvent actionEvent) throws IOException {

        PatientPortal m = new PatientPortal();
        PatientPortal.changeScene("login-selection.fxml");

    }

    public void helpDocs(ActionEvent actionEvent) throws IOException {

        java.awt.Desktop.getDesktop().browse(URI.create("https://github.com/TopLevelDevils/Phase1_DoctorsOffice#readme"));


    }





    public void openMark(ActionEvent actionEvent) throws SQLException {

        infoField.setStyle("-fx-text-fill: #000000");

        //create SQL database connection
        DatabaseConnect connectNow = new DatabaseConnect();

        //create connection
        Connection connectDb = connectNow.getConnection();

        patientID = 1;

        //create string to verify the doctor logon information
        String gatherMessages = "SELECT title, body FROM messages WHERE senderID = " + patientID;

        String addTitle = "";
        String addBody = "";



        PreparedStatement pst = connectDb.prepareStatement(gatherMessages);
        ResultSet addText = pst.executeQuery();

        while (addText.next()) {
            addTitle = addText.getString("title");
            addBody = addText.getString("body");

        }


        if (vitalsButton.isSelected() && !messageButton.isSelected()){



            infoField.setText("");
            infoField.setText("Name: Mark A\n" +
                    "Weight: 155lbs.\n" +
                    "Height: 5ft. 9in.\n" +
                    "Temperature: 96.8⁰F\n" +
                    "Blood Pressure: 76\n" +
                    "Age: 20\n\n");


        }
        else if (vitalsButton.isSelected() && messageButton.isSelected())
        {

            infoField.setText("");
            infoField.setText("Name: Mark A\n" +
                    "Weight: 155lbs.\n" +
                    "Height: 5ft. 9in.\n" +
                    "Temperature: 96.8⁰F\n" +
                    "Blood Pressure: 76\n" +
                    "Age: 20\n\nMessage Title: " + addTitle + "\n\nBody: " + addBody);

        }
        else if (!vitalsButton.isSelected() && !messageButton.isSelected())
        {

            infoField.setStyle("-fx-text-fill: #ff0000");
            infoField.setText("No Option Selected");

        }
        else
        {
           infoField.setText("");
           infoField.setText("Message Title: " + addTitle + "\n\nBody: " + addBody);
        }

    }

    public void openTanishq(ActionEvent actionEvent) throws SQLException {

        //create SQL database connection
        DatabaseConnect connectNow = new DatabaseConnect();

        //create connection
        Connection connectDb = connectNow.getConnection();

        patientID = 2;

        //create string to verify the doctor logon information
        String gatherMessages = "SELECT title, body FROM messages WHERE senderID = " + patientID;

        String addTitle = "";
        String addBody = "";



        PreparedStatement pst = connectDb.prepareStatement(gatherMessages);
        ResultSet addText = pst.executeQuery();

        while (addText.next()) {
            addTitle = addText.getString("title");
            addBody = addText.getString("body");

        }

        infoField.setText("");
        infoField.setText("Name: Tanishq M\n" +
                "Weight: 140lbs.\n" +
                "Height: 5ft. 11in.\n" +
                "Temperature: 96.8⁰F\n" +
                "Blood Pressure: 76\n" +
                "Age: 20\n\nMessages:\n" + addTitle + "\n\nBody:\n" + addBody);
    }

    public void openSam(ActionEvent actionEvent) {
        infoField.setText("");
        infoField.setText("Name: Sammy A\n" +
                "Weight: 160lbs.\n" +
                "Height: 6ft. 0in.\n" +
                "Temperature: 96.8⁰F\n" +
                "Blood Pressure: 76\n" +
                "Age: 20");
    }

    public void openSara(ActionEvent actionEvent) {
        infoField.setText("");
        infoField.setText("Name: Sara K\n" +
                "Weight: 120lbs.\n" +
                "Height: 5ft. 6in.\n" +
                "Temperature: 96.8⁰F\n" +
                "Blood Pressure: 76\n" +
                "Age: 20");
    }

    public void openJuls(ActionEvent actionEvent) {
        infoField.setText("");
        infoField.setText("Name: Juls H\n" +
                "Weight: 145lbs.\n" +
                "Height: 5ft. 9in.\n" +
                "Temperature: 96.8⁰F\n" +
                "Blood Pressure: 76\n" +
                "Age: 20");
    }

    public void sendMessage(ActionEvent actionEvent) {

        infoField.setStyle("-fx-text-fill: #00ff00");
        infoField.setText("");
        infoField.setText("Message Sent");
        infoField.setStyle("-fx-text-fill: #000000");

    }
}
