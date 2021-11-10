package com.example.patientportal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


import java.io.IOException;
import java.net.URI;
import java.sql.*;

import javafx.scene.input.MouseDragEvent;

import javax.swing.*;

public class doctorMain {

    @FXML
    private MenuItem helpButton;

    @FXML
    private TextArea infoField;

    @FXML
    private RadioButton vitalsButton, messageButton;

    @FXML
    public static Label welcomeLabel;

    @FXML
    private ComboBox patientList;

    private String info;

    public String doctorMessage;

    public doctorMain() throws SQLException {
    }


    public void backToLogin(ActionEvent actionEvent) throws IOException {

        PatientPortal m = new PatientPortal();
        PatientPortal.changeScene("login-selection.fxml");

    }

    public void helpDocs(ActionEvent actionEvent) throws IOException {

        java.awt.Desktop.getDesktop().browse(URI.create("https://github.com/TopLevelDevils/Phase1_DoctorsOffice#readme"));


    }



    public void updateList(ActionEvent actionEvent){

        //create SQL database connection
        DatabaseConnect connectNow = new DatabaseConnect();

        //create connection
        Connection connectDb = connectNow.getConnection();

        String getPatients = "SELECT firstname, lastname FROM patientvitals";

        try
        {

            Statement statement = connectDb.createStatement();
            ResultSet populate = statement.executeQuery(getPatients);

            while(populate.next())
            {

                patientList.getItems().addAll(

                        populate.getString("firstname") + " "
                                + populate.getString("lastname")

                );

            }

        }
        catch (Exception e)
        {

            e.printStackTrace();
            e.getCause();

        }


    }

    public void displayInformation(ActionEvent actionEvent)
    {

        //check patient ID's
        //create SQL database connection
        DatabaseConnect connectNow = new DatabaseConnect();

        //create connection
        Connection connectDb = connectNow.getConnection();

        String checkID = "SELECT firstname, lastname, patientID FROM patientvitals";

        String firstCheck = "";
        String lastCheck = "";
        int id = 0;

        int chosenID = 0;

        try
        {

            Statement stmt = connectDb.createStatement();
            ResultSet checkName = stmt.executeQuery(checkID);

            while (checkName.next())
            {

                firstCheck = checkName.getString("firstname");
                lastCheck = checkName.getString("lastname");
                id = checkName.getInt("patientID");

                String fullname = (firstCheck + " " + lastCheck);

                if (patientList.getValue().equals(fullname.toString())){

                    //found the correct id of the patient we want
                    chosenID = id;

                }

                System.out.println(chosenID);


            }

            System.out.print(chosenID);


            //create string to verify the doctor logon information
            //using the id that was found above == chosenID
            String gatherMessages = "SELECT title, body FROM messages WHERE patientID= " + chosenID + " AND type=" + 1;
            String gatherPatientVitals = "SELECT firstname, lastname, weight, heightf, heighti, temp, age, bloodp FROM patientvitals WHERE patientID=" + chosenID;
            infoField.setStyle("-fx-text-fill: #000000");

            System.out.print(chosenID);

            String addTitle = "";
            String addBody = "";
            String fname = "";
            String lname = "";
            int weight = 0;
            int feet = 0;
            int inches = 0;
            int age = 0;
            int temp = 0;
            int bloodp = 0;



            PreparedStatement pst = connectDb.prepareStatement(gatherMessages);
            ResultSet addText = pst.executeQuery();

            while (addText.next()) {
                addTitle = addText.getString("title");
                addBody = addText.getString("body");

            }

            PreparedStatement pst2 = connectDb.prepareStatement(gatherPatientVitals);
            ResultSet patientVitals = pst2.executeQuery();

            while (patientVitals.next())
            {

                fname = patientVitals.getString("firstname");
                lname = patientVitals.getString("lastname");
                weight = patientVitals.getInt("weight");
                feet = patientVitals.getInt("heightf");
                inches = patientVitals.getInt("heighti");
                temp = patientVitals.getInt("temp");
                age = patientVitals.getInt("age");
                bloodp = patientVitals.getInt("bloodp");

            }


            if (vitalsButton.isSelected() && !messageButton.isSelected()){



                infoField.setText("");
                infoField.setText("Name: " + fname + " " + lname + "\nWeight: " + weight + "lbs.\nHeight: "
                        + feet + "ft." + inches + "in. \nTemperature: " + temp + "⁰F\nAge: " + age + "\nBlood Pressure: " + bloodp + "mmHg");


            }
            else if (vitalsButton.isSelected() && messageButton.isSelected())
            {

                infoField.setText("");
                infoField.setText("Name: " + fname + " " + lname + "\nWeight: " + weight + "lbs.\nHeight: "
                        + feet + "ft." + inches + "in. \nTemperature: " + temp + "⁰F\nAge: " + age + "\nBlood Pressure: " + bloodp + "mmHg\n\nMessage Title: "
                        + addTitle + "\nBody: "  + addBody);

            }
            else if (!vitalsButton.isSelected() && !messageButton.isSelected())
            {

                infoField.setStyle("-fx-text-fill: #ff0000");
                infoField.setText("No Option Selected");

            }
            else
            {
                infoField.setText("");
                infoField.setText("Message Title: " + addTitle + "\nBody: " + addBody);
            }


        }
        catch (Exception e)
        {

            e.printStackTrace();
            e.getCause();

        }

    }

    public void sendMessage(ActionEvent actionEvent) {

        infoField.setStyle("-fx-text-fill: #00ff00");
        infoField.setText("");
        infoField.setText("Message Sent");
        infoField.setStyle("-fx-text-fill: #000000");

    }
}
