package com.example.patientportal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;



public class PatientPortal extends Application {
    
    // *** Create applicaiton properties ***  
    private static Stage stg;
    public static String firstName = "N/A";
    public static String lastName = "N/A";
    public static String phonenumber;
    public static String immunization_history;
    public static String medical_history;
    public static int patientID = 0;
    public static int doctorID = 0;
    public static int nurseID = 0;
    public static String insurance;

    public String phoneNum;

    // *** Set-up the application's scene properties ***
    @Override
    public void start(Stage stage) throws IOException {

        stg = stage;
        stage.setResizable(false);

        FXMLLoader fxmlLoader = new FXMLLoader(PatientPortal.class.getResource("login-selection.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400); 
        stage.setTitle("Patient Portal");
        stage.setScene(scene);
        stage.show();




    }
    
    // *** Load and change to a different scene *** 
    public static void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(PatientPortal.class.getResource(fxml));

        stg.getScene().setRoot(pane);

    }
    
    // *** Function utilized to change values related to patient information *** 
    public static void setValues(String first, String last, String pnum, String medh, String imm)
    {

        firstName = first;
        lastName = last;
        phonenumber = pnum;
        medical_history = medh;
        immunization_history = imm;

    }

    // *** Run the application :) ***
    public static void main(String[] args) {
        launch();
    }
}
