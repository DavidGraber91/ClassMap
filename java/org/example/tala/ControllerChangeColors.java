package org.example.tala;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class controls a color-changing window for customizing the appearance of RadioButtons.
 * It provides users with an interface to select and apply colors to various RadioButtons
 * representing different levels or properties. Users can use a ColorPicker to choose colors,
 * toggle text color, and save or reset to default settings.
 *
 * Key Features:
 * - Updates RadioButton colors based on user input from a ColorPicker.
 * - Allows toggling of text color for contrast when needed.
 * - Saves user-defined colors or resets them to default values.
 * - Manages the appearance of the control window and its interaction elements.
 *
 * Main Components:
 * - `setData`: Receives the Data object containing color settings and updates display.
 * - `initialize`: Initializes the RadioButtons array with specific level buttons.
 * - `change`: Updates the selected RadioButton's color using the ColorPicker and checkbox.
 * - `clickButton`: Sets the ColorPicker to reflect the color of the clicked RadioButton.
 * - `save`: Saves the current color selections for each RadioButton.
 * - `exit`: Closes the color-changing window.
 * - `defaultBut`: Resets RadioButton colors to default settings.
 *
 * This class integrates with the Data model for persistent color storage and uses JavaFX
 * controls (ColorPicker, CheckBox, RadioButtons) to manage and apply user color selections.
 */

public class ControllerChangeColors implements Initializable {

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private CheckBox checkBox;

    @FXML
    private ToggleGroup but;

    @FXML
    private RadioButton improper,  veryLow,  low, belowAverage,  average, aboveAverage, high,  veryHigh;

    private RadioButton [] RADIO_BUTTONS;
    private Data data;
    private String [] newColor;


    /**
     * Sets the Data object and updates colors.
     * @param data The data object containing color information.
     */
    public void setData(Data data)
    {
        this.data = data;
        
        newColor = data.getColors();
        display();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initializes RADIO_BUTTONS with predefined RadioButtons representing various levels.
         RADIO_BUTTONS = new RadioButton[]{improper,  veryLow,  low, belowAverage,  average, aboveAverage, high,  veryHigh};

    }

    /**
     * Changes the color of the selected RadioButton based on the color picker and checkbox.
     * @param event Action event triggered on color change.
     */
    @FXML
    void change(ActionEvent event) {
        String style = toStyle(colorPicker.getValue().toString());
        if(checkBox.isSelected())
            style += " -fx-text-fill: white;";
        ((RadioButton)but.getSelectedToggle()).setStyle(style);
    }

    /**
     * Updates the color picker value based on the selected RadioButton.
     * @param event Action event triggered on RadioButton click.
     */
    @FXML
    void clickButton(ActionEvent event) {
        RadioButton button = (RadioButton) event.getSource();
        try {
            colorPicker.setValue(toColor(button.getStyle()));
            if (button.getStyle().substring(31).equals("-fx-text-fill: white;"))
                checkBox.setSelected(true);
            else checkBox.setSelected(false);
        }catch (StringIndexOutOfBoundsException e){
            checkBox.setSelected(false);
        }catch (IllegalArgumentException e){}
    }

    /**
     * Saves the current color styles of RadioButtons to the data object.
     * @param event Action event triggered on save.
     */
    @FXML
    void save(ActionEvent event) {
        for(int i = 0; i < RADIO_BUTTONS.length; i++)
            newColor[i + 1] = RADIO_BUTTONS[i].getStyle();
        data.setColors(newColor);
        exit(null);
    }

    /**
     * Closes the application window.
     * @param event Action event triggered on exit.
     */
    @FXML
    void exit(ActionEvent event) {
        ((Stage)checkBox.getParent().getScene().getWindow()).close();
    }

    /**
     * Converts a color string to a CSS-compatible style string.
     * @param color The color string.
     * @return CSS style string for background color.
     */
    private String toStyle(String color)
    {
        try {
            return "-fx-background-color: #" + color.substring(2, 8) + ";";
        }catch (StringIndexOutOfBoundsException e){return "";}
    }

    /**
     * Converts a CSS color string to a Color object.
     * @param CSS CSS string with color information.
     * @return Color object.
     * @throws StringIndexOutOfBoundsException if the string is incorrectly formatted.
     * @throws IllegalArgumentException if the string is invalid.
     */
    private Color toColor(String CSS) throws StringIndexOutOfBoundsException ,IllegalArgumentException{
            return Color.web(CSS.substring(22, 29));
    }

    /**
     * Displays the colors in newColor on RADIO_BUTTONS.
     */
    private void display()
    {
        if(newColor != null)
            for(int i = 0; i < RADIO_BUTTONS.length; i++)
                RADIO_BUTTONS[i].setStyle(newColor[i + 1]);
        try {
            RadioButton button = (RadioButton)but.getSelectedToggle();
            colorPicker.setValue(toColor(button.getStyle()));
            if (button.getStyle().substring(31).equals("-fx-text-fill: white;"))
                checkBox.setSelected(true);
        }catch (StringIndexOutOfBoundsException e){}catch (IllegalArgumentException e){}
    }

    /**
     * Resets all RadioButtons to their default colors.
     * @param event Action event triggered on default button click.
     */
    @FXML
    private void defaultBut(ActionEvent event)
    {
        for(int i = 0; i < RADIO_BUTTONS.length;i++)
            RADIO_BUTTONS[i].setStyle(Data.DEF_COLORS[i + 1]);
    }
}
