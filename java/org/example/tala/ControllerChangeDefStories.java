package org.example.tala;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for managing the selection of stories for different classes.
 * Implements Initialize to set up the ComboBoxes with story options.
 */
public class ControllerChangeDefStories implements Initializable {

    @FXML
    private ComboBox<String> classB, classC, classD, classE, classF;

    private Data data;

    /**
     * Initializes the controller after its root element has been processed.
     * This method populates the ComboBoxes with story names based on the legal stories for each class.
     * @param url the location used to resolve relative paths for the root object, or null if the location is not known
     * @param resourceBundle the resource bundle used to localize the root object, or null if the root object was not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Populate ComboBox for Classes with legal stories

        for(int i = 0; i < Data.LEGAL_STORIES[Student.CLASS_2].length; i++)
            classB.getItems().add(Data.NAME_STORIES[Data.LEGAL_STORIES[Student.CLASS_2][i]]);

        for(int i = 0; i < Data.LEGAL_STORIES[Student.CLASS_3].length; i++)
            classC.getItems().add(Data.NAME_STORIES[Data.LEGAL_STORIES[Student.CLASS_3][i]]);

        for(int i = 0; i < Data.LEGAL_STORIES[Student.CLASS_4].length; i++)
            classD.getItems().add(Data.NAME_STORIES[Data.LEGAL_STORIES[Student.CLASS_4][i]]);

        for(int i = 0; i < Data.LEGAL_STORIES[Student.CLASS_5].length; i++)
            classE.getItems().add(Data.NAME_STORIES[Data.LEGAL_STORIES[Student.CLASS_5][i]]);

        for(int i = 0; i < Data.LEGAL_STORIES[Student.CLASS_6].length; i++)
            classF.getItems().add(Data.NAME_STORIES[Data.LEGAL_STORIES[Student.CLASS_6][i]]);
    }

    /**
     * Sets the data for the controller, updating the ComboBoxes with the currently selected stories.
     * @param data the Data object containing the stories for each class
     */
    public void setData(Data data) {
        this.data = data;

        // Select the stored story for Classes

        classB.getSelectionModel().select(Data.NAME_STORIES[data.getStories()[Student.CLASS_2]]);
        classC.getSelectionModel().select(Data.NAME_STORIES[data.getStories()[Student.CLASS_3]]);
        classD.getSelectionModel().select(Data.NAME_STORIES[data.getStories()[Student.CLASS_4]]);
        classE.getSelectionModel().select(Data.NAME_STORIES[data.getStories()[Student.CLASS_5]]);
        classF.getSelectionModel().select(Data.NAME_STORIES[data.getStories()[Student.CLASS_6]]);
    }

    /**
     * Saves the currently selected stories from the ComboBoxes to the Data object and closes the window.
     * @param event the ActionEvent triggered by the save action
     */
    public void save(ActionEvent event)
    {
        // Create an array to store the selected stories
        String temp [] = {classB.getSelectionModel().getSelectedItem()
                        ,classC.getSelectionModel().getSelectedItem()
                        ,classD.getSelectionModel().getSelectedItem()
                        ,classE.getSelectionModel().getSelectedItem()
                        ,classF.getSelectionModel().getSelectedItem()};

        // Save the selected stories to the Data object
        data.setStories(temp);
        // Close the window after saving
        exit(null);
    }

    /**
     * Closes the current window.
     * @param event the ActionEvent triggered by the exit action
     */
    public void exit(ActionEvent event)
    {
        ((Stage)classB.getParent().getScene().getWindow()).close();
    }
}
