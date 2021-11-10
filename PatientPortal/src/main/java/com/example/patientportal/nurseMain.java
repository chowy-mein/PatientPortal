package com.example.patientportal;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.IOException;

public class nurseMain {

    //add labels
    @FXML
    private Label ageLabel, bpLabel, bpUnitsLabel;

    //add TextFields
    @FXML
    private TextField weightInput, heightFeetInput, heightInchesInput, tempInput, ageInput, bpInput, firstNameInput, lastNameInput;
    
    // add Button Properties
    @FXML
    private MenuButton ageCheck;

    @FXML
    private Button submitButton;

    @FXML
    private RadioButton lbsRadio, kgRadio;

    @FXML
    private TextArea infoCheck;

    boolean older = false;

    // *** Logout feature for nurse side ***
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


    // *** Handling if Blood Pressure is a necessary field ***
    // ** If the patient is > 12 yrs, get their blood pressure **
    public void openBP(ActionEvent actionEvent) {

        older = true;
        ageLabel.setVisible(true);
        bpLabel.setVisible(true);
        bpUnitsLabel.setVisible(true);
        bpInput.setVisible(true);
        ageInput.setVisible(true);


    }
    
    // ** Deactivate blood pressure fields if patient is < 12 yrs **
    public void noBP(ActionEvent actionEvent) {

        bpLabel.setVisible(false);
        bpUnitsLabel.setVisible(false);
        bpInput.setVisible(false);

        ageLabel.setVisible(true);
        ageInput.setVisible(true);

    }
    // *******************************************************

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

        } else if (bpInput.getText().isEmpty() && older) {

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

            if (tempInt > 1000)
            {


                temp += "Damn you're hot (☞ﾟヮﾟ)☞  ☜(ﾟヮﾟ☜)\n";



            }
            else
            {

                temp = "Temperature: " + tempInput.getText().toString() + "⁰F\n";

            }

            String bp, age = "";

            if (older) {

                bp = "Blood Pressure: " + bpInput.getText().toString() + "\n";
                age = "Age: " + ageInput.getText().toString() + "\n";

            } else {

                age = "Blood Pressure: " + ageInput.getText().toString() + "\n";
                bp = "Age: " + "Patient is not old enough for Blood Pressure Readings\n";

            }

            infoCheck.setText(name + weight + height + temp + bp + age);


        }
    }


    public void patientSearch(ActionEvent actionEvent) throws IOException {

        PatientPortal m = new PatientPortal();
        PatientPortal.changeScene("login-selection.fxml"); //back to login for now, next is patient lookup

    }
}
