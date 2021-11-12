package com.example.patientportal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.*;

public class nurseMain {

    //add labels
    @FXML
    private Label ageLabel, bpLabel;

    //add TextFields
    @FXML
    private TextField nurseTitleArea, weightInput, heightFeetInput, heightInchesInput, tempInput, ageInput, bpInput, firstNameInput, lastNameInput;
    
    // add Button Properties
    @FXML
    private MenuButton ageCheck;

    @FXML
    private Button submitButton;

    @FXML
    private RadioButton lbsRadio, kgRadio, newPatientRadio, returnPatientRadio, yesButton, noButton, vitalsButton;

    @FXML
    private TextArea infoCheck, nurseBodyArea;

    @FXML
    private ComboBox patientList;

    boolean lessThan = false;

    int chosenPatientID = 0;
    int lbskg = 0;

    String thisFirstName, thisLastName;

    public void logout(ActionEvent actionEvent) throws IOException {

        PatientPortal m = new PatientPortal();
        PatientPortal.changeScene("login-selection.fxml");

    }


    public void checkLbs(ActionEvent actionEvent) {

        if(kgRadio.isSelected())
        {

            lbsRadio.setSelected(false);

        }

    }

    public void checkKG(ActionEvent actionEvent) {

        if(lbsRadio.isSelected())
        {

            kgRadio.setSelected(false);

        }

    }

    public void turnoffKg(ActionEvent actionEvent)
    {

        kgRadio.setSelected(false);

    }

    public void turnoffLbs(ActionEvent actionEvent)
    {

        lbsRadio.setSelected(false);

    }

    public void showSubmit(ActionEvent actionEvent)
    {

        submitButton.setVisible(true);
        returnPatientRadio.setSelected(false);
        patientList.getItems().clear();
        clearItems();


    }

    // *** Submit information input by Nurse ***
    public void submit(ActionEvent actionEvent) {

        float tempInt;
        // ** Exception handling should program find empty fields **
        if (firstNameInput.getText().isEmpty()) {

            infoCheck.setText("Missing First Name");

        } else if (lastNameInput.getText().isEmpty()) {
            infoCheck.setText("Missing Last Name");
        } else if (weightInput.getText().isEmpty() || (!lbsRadio.isSelected() && !kgRadio.isSelected())) {

            infoCheck.setText("Missing weight Input");

        } else if (heightFeetInput.getText().isEmpty() || heightInchesInput.getText().isEmpty()) {

            infoCheck.setText("Missing height info");

        } else if (tempInput.getText().isEmpty() ) {


            infoCheck.setText("Missing Temperature");

        }

        else if (ageInput.getText().isEmpty()) {

            infoCheck.setText("Missing Age");

        } else if (bpInput.getText().isEmpty() && !lessThan) {

            infoCheck.setText("Missing Blood Pressure");

        }
        else if (lbsRadio.isSelected() && kgRadio.isSelected())
        {

            infoCheck.setText("Both KG and Lbs has been Chosen");

        }
        // ****************************************************
        else {

            String tempS = tempInput.getText().toString();
            tempInt = Float.parseFloat(tempS);


            //gather string of all information
            String name = "Name: " + firstNameInput.getText().toString() + " " + lastNameInput.getText().toString() + "\n";
            String weight = "Weight: ";
            
            // Convert weight metric depending on selection
            if (lbsRadio.isSelected()) {

                weight += weightInput.getText().toString() + "lbs.\n";

            } else {

                weight += weightInput.getText().toString() + "kg.\n";

            }

            // Display height information
            String height = "Height: " + heightFeetInput.getText().toString() + "ft. " + heightInchesInput.getText().toString() + "in.\n";

            String temp = "";

            if (tempInt > 1000) {


                temp += "Damn you're hot (☞ﾟヮﾟ)☞  ☜(ﾟヮﾟ☜)\n";


            } else {

                temp = "Temperature: " + tempInput.getText().toString() + "⁰F\n";

            }

            String bp, age = "";

            if (!lessThan) {

                bp = "Blood Pressure: " + bpInput.getText().toString() + "\n";
                age = "Age: " + ageInput.getText().toString() + "\n";

            } else {

                age = "Blood Pressure: " + ageInput.getText().toString() + "\n";
                bp = "Age: " + "Patient is not old enough for Blood Pressure Readings\n";

            }

            infoCheck.setText(name + weight + height + temp + bp + age);

            //add all information to the database
            //create SQL database connection
            DatabaseConnect connectNow = new DatabaseConnect();

            //create connection
            Connection connectDb = connectNow.getConnection();


            if (kgRadio.isSelected())
            {

                lbskg = 1;

            }




            try {

                String addInfo = "INSERT INTO patientvitals (patientID, firstname, lastname, weight, heightf, heighti, temp," +
                        "age, bloodp, lbskg) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";



                //chosenID now equals the patientID
                PreparedStatement statement = connectDb.prepareStatement(addInfo, Statement.RETURN_GENERATED_KEYS);

                int count = 1;
                statement.setNull(count++, Types.NULL);
                statement.setString(count++, firstNameInput.getText());
                statement.setString(count++, lastNameInput.getText());
                statement.setInt(count++, Integer.parseInt(weightInput.getText()));
                statement.setInt(count++, Integer.parseInt(heightFeetInput.getText()));
                statement.setInt(count++, Integer.parseInt(heightInchesInput.getText()));
                statement.setDouble(count++, Double.parseDouble(tempInput.getText()));
                statement.setInt(count++, Integer.parseInt(ageInput.getText()));
                statement.setInt(count++, Integer.parseInt(bpInput.getText()));
                statement.setInt(count++, lbskg);

                statement.executeUpdate();

                infoCheck.setText("");
                infoCheck.setText("Information has been updated.");


            } catch (Exception e) {

                e.printStackTrace();
                e.getCause();

            }
        }


    }

    public void older(ActionEvent actionEvent) throws IOException{

        //uncheck the no button
        noButton.setSelected(false);

        ageLabel.setVisible(false);
        ageInput.setVisible(false);
        bpLabel.setVisible(false);
        bpInput.setVisible(false);

        ageLabel.setVisible(true);
        ageInput.setVisible(true);
        bpLabel.setVisible(true);
        bpInput.setVisible(true);
        lessThan = false;

    }
    public void younger(ActionEvent actionEvent) throws IOException{

        //uncheck the yes button
        yesButton.setSelected(false);

        ageLabel.setVisible(false);
        ageInput.setVisible(false);
        bpLabel.setVisible(false);
        bpInput.setVisible(false);

        ageLabel.setVisible(true);
        ageInput.setVisible(true);
        lessThan = true;

    }

    public void update(ActionEvent actionEvent) throws IOException
    {
        //add all information to the database
        //create SQL database connection
        DatabaseConnect connectNow = new DatabaseConnect();

        //create connection
        Connection connectDb = connectNow.getConnection();

        int id = 0;

        int chosenID = 0;
        String firstCheck = "";
        String lastCheck = "";

        try
        {

            String getID = "SELECT firstname, lastname, patientID FROM patientlogins";

            Statement stmt = connectDb.createStatement();

                // for a returning patient
                ResultSet checkName = stmt.executeQuery(getID);



                while (checkName.next()) {

                    firstCheck = checkName.getString("firstname");
                    lastCheck = checkName.getString("lastname");


                    if (patientList.getValue().equals(firstCheck + " " + lastCheck)) {

                        //found the correct id of the patient we want
                        chosenID = checkName.getInt("patientID");
                        //stack trace
                        System.out.println("true");

                    }


                }

                chosenPatientID = chosenID;

                String updateQuery = "";

                //default 0 is lbs and 1 is kg
                int lbskg = 0;

                if (kgRadio.isSelected())
                {

                    lbskg = 1;

                }

                if (lessThan == false)
                {
                    updateQuery = "UPDATE patientvitals " +
                            "SET firstname='" + firstNameInput.getText() +
                            "', lastname='" + lastNameInput.getText() + "', weight=" +
                            Integer.parseInt(weightInput.getText()) + ", heightf=" +
                            Integer.parseInt(heightFeetInput.getText()) + ", heighti=" +
                            Integer.parseInt(heightInchesInput.getText()) + ", temp=" +
                            Double.parseDouble(tempInput.getText()) + ", age=" + Integer.parseInt(ageInput.getText()) +
                            ", bloodp=" + Integer.parseInt(bpInput.getText()) + ", lbskg=" + lbskg +
                            " WHERE patientID=" + chosenID;


                }
                else
                {

                    updateQuery = "UPDATE patientvitals " +
                            "SET firstname='" + firstNameInput.getText() +
                            "', lastname='" + lastNameInput.getText() + "', weight=" +
                            Integer.parseInt(weightInput.getText()) + ", heightf=" +
                            Integer.parseInt(heightFeetInput.getText()) + ", heighti=" +
                            Integer.parseInt(heightInchesInput.getText()) + ", temp=" +
                            Double.parseDouble(tempInput.getText()) + ", age=" + Integer.parseInt(ageInput.getText()) +
                            ", bloodp=" + null + ", lbskg=" + lbskg + " WHERE patientID=" + chosenID;


                }




                PreparedStatement statement = connectDb.prepareStatement(updateQuery);
                statement.executeUpdate();

                String updateNames = "UPDATE patientlogins SET firstname='" +firstNameInput.getText() + "', lastname='"
                        + lastNameInput.getText() + "' WHERE patientID=" + chosenID;

                PreparedStatement statement1 = connectDb.prepareStatement(updateNames);
                statement1.executeUpdate();

                //clear tabs and show update message
                clearItems();
                newPatientRadio.setSelected(false);



        }
        catch (Exception e)
        {

            e.printStackTrace();
            e.getCause();

        }

    }

    public void showVitals(ActionEvent actionEvent) throws SQLException {

        if (vitalsButton.isSelected())
        {

            //add all information to the database
            //create SQL database connection
            DatabaseConnect connectNow = new DatabaseConnect();

            //create connection
            Connection connectDb = connectNow.getConnection();

            int id = 0;

            int chosenID = 0;
            String firstCheck = "";
            String lastCheck = "";

            String getID = "SELECT firstname, lastname, patientID FROM patientlogins";

            Statement stmt = connectDb.createStatement();

            // for a returning patient
            ResultSet checkName = stmt.executeQuery(getID);

            try {



                while (checkName.next()) {

                    firstCheck = checkName.getString("firstname");
                    lastCheck = checkName.getString("lastname");


                    if (patientList.getValue().equals(firstCheck + " " + lastCheck)) {

                        //found the correct id of the patient we want
                        chosenID = checkName.getInt("patientID");
                        //stack trace
                        System.out.println("true");

                    }


                }

                String gatherPatientVitals = "";

                if (lessThan)
                {
                    gatherPatientVitals = "SELECT firstname, lastname, weight, heightf, heighti, temp, age, lbskg FROM patientvitals WHERE patientID=" + chosenID;

                }
                else {
                    //fill area with information
                    gatherPatientVitals = "SELECT firstname, lastname, weight, heightf, heighti, temp, age, bloodp, lbskg FROM patientvitals WHERE patientID=" + chosenID;
                }


                String fname = "";
                String lname = "";
                int weight = 0;
                int feet = 0;
                int inches = 0;
                int age = 0;
                int temp = 0;
                int bloodp = 0;
                lbskg = 0;

                PreparedStatement pst2 = connectDb.prepareStatement(gatherPatientVitals);
                ResultSet patientVitals = pst2.executeQuery();

                if (lessThan) {

                    while (patientVitals.next()) {

                        fname = patientVitals.getString("firstname");
                        lname = patientVitals.getString("lastname");
                        weight = patientVitals.getInt("weight");
                        feet = patientVitals.getInt("heightf");
                        inches = patientVitals.getInt("heighti");
                        temp = patientVitals.getInt("temp");
                        age = patientVitals.getInt("age");

                        lbskg = patientVitals.getInt("lbskg");

                    }
                }
                else
                {

                    while (patientVitals.next()) {
                        fname = patientVitals.getString("firstname");
                        lname = patientVitals.getString("lastname");
                        weight = patientVitals.getInt("weight");
                        feet = patientVitals.getInt("heightf");
                        inches = patientVitals.getInt("heighti");
                        temp = patientVitals.getInt("temp");
                        age = patientVitals.getInt("age");
                        bloodp = patientVitals.getInt("bloodp");
                        lbskg = patientVitals.getInt("lbskg");
                    }

                }

                String lbsOkg = "lbs.";

                if (lbskg == 1)
                {

                    lbsOkg = "kg.";

                }


                if (!lessThan){
                    infoCheck.setText("");
                    infoCheck.setText("Name: " + fname + " " + lname + "\nWeight: " + weight + " " + lbsOkg + "\nHeight: "
                            + feet + "ft." + inches + "in. \nTemperature: " + temp + "⁰F\nAge: " + age + "\nBlood Pressure: " + bloodp + "mmHg");
                }
                else
                {
                    infoCheck.setText("");
                    infoCheck.setText("Name: " + fname + " " + lname + "\nWeight: " + weight + " " + lbsOkg + "\nHeight: "
                            + feet + "ft." + inches + "in. \nTemperature: " + temp + "⁰F\nAge: " + age + "\nBlood Pressure: not available");

                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
                e.getCause();

            }

        }
        else
        {

            infoCheck.setText("");
            vitalsButton.setSelected(false);

        }



    }

    public void clearItems()
    {

        //clear tabs and show update message
        firstNameInput.setText("");
        lastNameInput.setText("");
        heightInchesInput.setText("");
        heightFeetInput.setText("");
        bpInput.setText("");
        bpInput.setVisible(false);
        ageInput.setText("");
        ageInput.setVisible(false);
        bpLabel.setVisible(false);
        ageLabel.setVisible(false);
        tempInput.setText("");
        yesButton.setSelected(false);
        noButton.setSelected(false);
        weightInput.setText("");
        lbsRadio.setSelected(false);
        kgRadio.setSelected(false);
        returnPatientRadio.setSelected(false);


    }


    public void patientSearch(ActionEvent actionEvent) throws IOException{

        //use database to search for patient
        //create SQL database connection
        DatabaseConnect connectNow = new DatabaseConnect();

        //create connection
        Connection connectDb = connectNow.getConnection();

        //use patient accounts rather than vitals for all patients
        String getPatients = "SELECT firstname, lastname FROM patientlogins";

        patientList.getItems().clear();

        try
        {

            Statement stmt = connectDb.createStatement();
            ResultSet populate = stmt.executeQuery(getPatients);

            while (populate.next())
            {

                patientList.getItems().addAll(

                        populate.getString("firstname") + " "
                                + populate.getString("lastname")

                );

            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    public void submitNurseMessage(ActionEvent actionEvent) throws  IOException{

        //create SQL database connection
        DatabaseConnect connectNow = new DatabaseConnect();

        //create connection
        Connection connectDb = connectNow.getConnection();

        try
        {

            if (chosenPatientID != 0) {

                //type 2 = nurse message
                String message = "INSERT INTO messages (patientID, type, title, body, recipientID, pharm) VALUES(?,?,?,?,?,?)";

                PreparedStatement statement = connectDb.prepareStatement(message, Statement.RETURN_GENERATED_KEYS);

                int count = 1;

                statement.setInt(count++, chosenPatientID);
                statement.setInt(count++, 3); //3 for nurse message
                statement.setString(count++, nurseTitleArea.getText());
                statement.setString(count++, nurseBodyArea.getText());
                statement.setInt(count++, chosenPatientID);
                statement.setInt(count++, 0);

                statement.executeUpdate();

                //clear text areas
                nurseBodyArea.setText("");
                nurseTitleArea.setText("");
            }

            else
            {

                nurseTitleArea.setText("");
                nurseBodyArea.setText("Message Not Sent | Select Patient on Previous Tab");

            }


        }
        catch (Exception e)
        {

            e.printStackTrace();
            e.getCause();

        }


    }

    public void selectPatient(ActionEvent actionEvent)
    {

        //add all information to the database
        //create SQL database connection
        DatabaseConnect connectNow = new DatabaseConnect();

        //create connection
        Connection connectDb = connectNow.getConnection();

        int id = 0;

        int chosenID = 0;
        String firstCheck = "";
        String lastCheck = "";

        try {

            String getID = "SELECT firstname, lastname, patientID FROM patientlogins";

            Statement stmt = connectDb.createStatement();

            // for a returning patient
            ResultSet checkName = stmt.executeQuery(getID);


            while (checkName.next()) {

                firstCheck = checkName.getString("firstname");
                lastCheck = checkName.getString("lastname");


                if (patientList.getValue().equals(firstCheck + " " + lastCheck)) {

                    //found the correct id of the patient we want
                    chosenID = checkName.getInt("patientID");
                    //stack trace
                    System.out.println("true");

                }


            }

            chosenPatientID = chosenID;

            nurseBodyArea.setText("");

        }
        catch (Exception e)
        {

            e.printStackTrace();

        }

    }
}
