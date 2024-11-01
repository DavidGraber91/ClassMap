package org.example.tala;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class ControllerChangePassword {

    @FXML
    private TextField oldPassword, newPassword;

    Data data;

    /**
     * Sets the data instance for the controller.
     * @param data The Data instance used to verify and set passwords
     */
    public void setData(Data data) {
        this.data = data;
    }

    /**
     * Validates the old password and, if correct, updates it to the new password.
     * If the old password is incorrect, displays a warning alert.
     * @param event The ActionEvent triggered by the save button
     */
    public void save(ActionEvent event)
    {
        // Check if the entered old password matches the current password
        if(data.equalsPassword(oldPassword.getText())){
            // If the password is correct, update to the new password and close the window
            data.setPassword(newPassword.getText());
            ((Stage)oldPassword.getParent().getScene().getWindow()).close();
        }
        else {
            // If the password is incorrect, display a warning message
            Alert alert = new Alert(Alert.AlertType.WARNING, "", ButtonType.OK);
            alert.setTitle("שגיאה");
            alert.setHeaderText("הסיסמה לא נכונה");
            alert.show();
        }
    }
}
