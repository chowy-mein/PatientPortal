package com.example.patientportal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;

public class createAccount {

    @FXML
    private TextField firstName, lastName, month, date, year, phoneNum, usernameField, passwordField;

    @FXML
    private TextArea verifyArea;

    private String information;

    public void exitPage(ActionEvent actionEvent) throws IOException {

        PatientPortal m = new PatientPortal();
        PatientPortal.changeScene("login-selection.fxml");

    }


    public void submitButton(ActionEvent actionEvent) throws IOException {

        information = "First: " + firstName.getText().toString() + "\nLast: " + lastName.getText().toString() +
                "\nDate of Birth: " + month.getText().toString() + "/" + date.getText().toString() + "/"
                + year.getText().toString() + "\nPhone Number: " + phoneNum.getText().toString() + "\n\nUsername: " + usernameField.getText() +
                "\nPassword: " + passwordField.getText();

        verifyArea.setText(information);



    }

    public void createAccount(ActionEvent actionEvent) throws SQLException, IOException {


        try {
            //create SQL database connection
            DatabaseConnect connectNow = new DatabaseConnect();

            //create connection
            Connection connectDb = connectNow.getConnection();

            PreparedStatement statement = null;
            ResultSet resultSet = null;

            String query = "INSERT INTO patientlogins (patientID, username, password, firstname, lastname, phonenumber, medh, imm) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connectDb.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            //rand number
            int randAdd = (int) (Math.random() * (1000 - 100 + 1) + 100);

            int count = 1;
            statement.setString(count++, null);
            statement.setString(count++, usernameField.getText());
            statement.setString(count++, passwordField.getText());
            statement.setString(count++, firstName.getText());
            statement.setString(count++, lastName.getText());
            statement.setString(count++, phoneNum.getText());
            statement.setString(count++, "N/A");
            statement.setString(count++, "N/A");

            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();

            PatientPortal.firstName = "SELECT firstname FROM patientlogins WHERE username = '" + usernameField.getText() + "' AND password ='" + passwordField.getText() + "'";
            PatientPortal.lastName = "SELECT lastname FROM patientlogins WHERE username = '" + usernameField.getText() + "' AND password ='" + passwordField.getText() + "'";



        }
        catch (Exception e)
        {

            e.printStackTrace();
            e.getCause();

        }

        PatientPortal m = new PatientPortal();

        PatientPortal.changeScene("patientMainPage.fxml");
    }
}
