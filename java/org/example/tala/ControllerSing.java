package org.example.tala;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;

public class ControllerSing {

    @FXML
    private HBox myHBox, hBox;

    @FXML
    private TextField nameSignUp; // Text field for user name input

    @FXML
    private TextField passSignUp; // Text field for password input

    @FXML
    private Label errorPass;  // Label for displaying password error messages

    @FXML
    private ImageView myImage; // Image view for displaying an image

    // Maximum length for the password
    protected static final int MAX_PASS = 20;

    /**
     * Initializes the controller after its root element has been processed.
     * This method adjusts the size of the UI elements based on the window size
     * and checks if the necessary data directory exists.
     */
    @FXML
    void initialize()
    {
        //Adjusts the size of the elements to the size of the window
        myImage.fitHeightProperty().bind(Bindings.selectDouble(myImage.sceneProperty(),"height"));
        myImage.fitWidthProperty().bind(Bindings.divide(Bindings.selectDouble(myImage.sceneProperty(), "width"), 2));
        myHBox.prefWidthProperty().bind(Bindings.divide(Bindings.selectDouble(myHBox.sceneProperty(), "width"), 2));
        hBox.prefWidthProperty().bind(Bindings.divide(Bindings.selectDouble(myHBox.sceneProperty(), "width"), 2));

        // Check if the "date" directory exists
        File dir = new File("date");
        if(dir.exists()){
            // Alert the user about potential data loss if the system is reset
            Alert alert = new Alert(Alert.AlertType.WARNING, "האם את רוצה לאתחל את המערכת, \nהאתחול יגרום למחיקת כל הנתונים", ButtonType.YES, ButtonType.NO);
            alert.setTitle("שגיאה");
            alert.setHeaderText("קיים בעיה עם הקובץ אבטחה");

            // If user agrees, delete the existing directory and its content
            if(alert.showAndWait().get() == ButtonType.YES) {
                File file = new File("date\\stu");
                file.delete();
                dir.delete();
            }
        }
    }

    /**
     * Handles the sign-up process when the user clicks the sign-up button.
     * It checks the password length and, if valid, creates a new data directory,
     * saves the user data, and navigates to the student view.
     * @param event The action event triggered by the button click.
     */
    @FXML
    void signUp(ActionEvent event)
    {
        if(passSignUp.getText().length() > MAX_PASS){
            errorPass.setText("הסיסמה יכולה להיות עד 20 תווים");
            return; // Exit the method if the password is too long
        }

        try{
            // Create a new directory for data storage if it does not exist
            File dir = new File("date");

            // Show an error message if directory creation fails
            if(!dir.mkdir()) {
                JOptionPane.showMessageDialog(null, "לא הצליח ליצור תקיה", "error", JOptionPane.ERROR_MESSAGE);
                return; // Exit the method if directory creation fails
            }

            // Create a new Data object with the entered name and password
            Data data = new Data(nameSignUp.getText(), passSignUp.getText());

            data.save();

            // Load the student view and update the stage
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("student-view.fxml"));
            Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
            s.setScene(new Scene(fxmlLoader.load()));
            s.setTitle("מיפוי");
            s.centerOnScreen();

            // Pass the user data to the next controller
            ((Controller)fxmlLoader.getController()).start(data);


        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "error 01cs: " + e, "error", JOptionPane.ERROR_MESSAGE);
        }
    }


}
