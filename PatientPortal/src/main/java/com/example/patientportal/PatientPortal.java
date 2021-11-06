package com.example.patientportal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;



public class PatientPortal extends Application {

    private static Stage stg;
    public static String firstName;
    public static String lastName;
    public static String phonenumber;
    public static String immunization_history;
    public static String medical_history;

    public String phoneNum;

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

    public static void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(PatientPortal.class.getResource(fxml));

        stg.getScene().setRoot(pane);

    }

    public static void setValues(String first, String last, String pnum, String medh, String imm)
    {

        firstName = first;
        lastName = last;
        phonenumber = pnum;
        medical_history = medh;
        immunization_history = imm;

    }


    public static void main(String[] args) {
        launch();
    }
}