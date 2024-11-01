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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;

public class ControllerLog {

    @FXML
    private HBox myHBox, hBox;

    @FXML
    private TextField passLogIn; // Text field for password input during login

    @FXML
    private Label name; // Label to display the user's name

    @FXML
    public ImageView myImage; // Image view for displaying an image

    private Data data;


    /**
     * Initializes the controller after its root element has been processed.
     * This method adjusts the size of the UI elements based on the window size
     * and loads user data from a file. If data is found, the user's name is displayed.
     */
    @FXML
    void initialize() {
        try {
            //Adjusts the size of the elements to the size of the window
            myImage.fitHeightProperty().bind(Bindings.selectDouble(myImage.sceneProperty(),"height"));
            myImage.fitWidthProperty().bind(Bindings.divide(Bindings.selectDouble(myImage.sceneProperty(), "width"), 2));
            myHBox.prefWidthProperty().bind(Bindings.divide(Bindings.selectDouble(myHBox.sceneProperty(), "width"), 2));
            hBox.prefWidthProperty().bind(Bindings.divide(Bindings.selectDouble(myHBox.sceneProperty(), "width"), 2));

            // Load user data from the security file
            FileInputStream fileRead = new FileInputStream(Data.FILE_SECURITY);
            ObjectInputStream in = new ObjectInputStream(fileRead);

            // Retrieve data object and set the user's name in the label
            data = (Data) in.readObject();
            name.setText(data.getName());

            in.close();
            fileRead.close();



            fileRead.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "error 01cl: " + e, "error", JOptionPane.ERROR_MESSAGE);
        } catch (EOFException e) {
            JOptionPane.showMessageDialog(null, "error 02cl: " + e, "error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "error 03cl: " + e, "error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "error 04cl: " + e, "error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error 05cl: " + e, "error", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Handles the login process when the user clicks the login button.
     * If the password matches the saved password, it loads the next view.
     * Otherwise, it shows an alert indicating an incorrect password.
     * @param event The action event triggered by the button click.
     */
    @FXML
    void logIn(ActionEvent event) {
        try {
            // Check if the entered password matches the saved password
            if (!data.equalsPassword(passLogIn.getText())) {
                // Display a warning if the password is incorrect
                Alert alert = new Alert(Alert.AlertType.WARNING, "", ButtonType.OK);
                alert.setTitle("שגיאה");
                alert.setHeaderText("הסיסמה לא נכונה");
                alert.show();
                return;
            }

            // Load the student view if the password is correct
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("student-view.fxml"));
            Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
            s.setScene(new Scene(fxmlLoader.load()));
            s.setTitle("מיפוי");
            s.centerOnScreen();

            // Pass the user data to the next controller
            ((Controller) fxmlLoader.getController()).start(data);

        }catch (RuntimeException e){
            JOptionPane.showMessageDialog(null, "error 06cl: " + e, "error", JOptionPane.ERROR_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "error 07cl: " + e, "error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "error 08cl: " + e, "error", JOptionPane.ERROR_MESSAGE);
        }

    }

}
