package org.example.tala;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 *  controller of the student editing window
 */
public class ControllerDiagnosis {

    private Student _student= null;
    private boolean _saving = false;
    static final boolean SAVE = true;
    static final boolean CALCULATE = false;

    long hundredth = 0;
    int second, minute;

    Timer timer = new Timer();

    @FXML
    private ChoiceBox<String> classText, choiceStory;

    @FXML
    private Label errorInt;

    @FXML
    private Label dateText, timerLabel;

    private TextField[] diagnoses;

    @FXML
    private TextField nameText,  singleWortNumText,  readStoryNumText,  singleWortTimeText,  movementsNumText
            , readStoryTimeText,  writeNumText,  movementsTimeText,  phonemicText,  writeTimeText;

    @FXML
    private TextArea remarksText;

    @FXML
    private Button butSave,  butCal , butStop, butShale;

    @FXML
    private ListView<String> listTimer ;

    private Data data;


    /**
     * Set saving
     * @param saving If saving
     */
    public void setSaving(boolean saving) {
        _saving = saving;
    }

    /**
     * return if the saving edit
     * @return The saving
     */
    public boolean isSaving() {
        return _saving;
    }

    /**
     * initialize fo the ChoiceBox
     */
    @FXML
    public void initialize()
    {
        classText.getItems().addAll(Student.CLASSES); //adding items
        classText.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //playing the buttons if choice class

                    butSave.setDisable(false);
                    butCal.setDisable(false);

                    //Set stories according to class
                    setStoryChoice();

                    // text warning
                    errorInt.setText("א\"א לעשות חישוב מחדש אחרי שכבר חושב עבור כיתה/סיפור מסוימת.\nצריך להכניס שוב את הערכים");
                    errorInt.setFont(new Font(12));
                    errorInt.setTextAlignment(TextAlignment.CENTER);
            }

        });

        choiceStory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // text warning
                errorInt.setText("א\"א לעשות חישוב מחדש אחרי שכבר חושב עבור כיתה/סיפור מסוימת.\nצריך להכניס שוב את הערכים");
                errorInt.setFont(new Font(12));
                errorInt.setTextAlignment(TextAlignment.CENTER);
            }
        });
        //String s = timerLabel.getStyle() + "; -fx-border-style: double;";
        //System.out.println(s);
        timerLabel.setStyle(timerLabel.getStyle() + "; -fx-border-style: dotted;");
    }

    /**
     * Changes everything textField to the student's values
     * @param student student to edit
     */
    public void display(Student student, Data data)
    {
        if(student == null) {
            System.out.println("Can't open the window because the student is null");
            ((Stage)dateText.getParent().getScene().getWindow()).close();
            JOptionPane.showMessageDialog(null, "01cd" , "error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        this.data = data;
        _student = student;

        //Puts everything textFiled into an array for convenience
         diagnoses  = new TextField[]{singleWortTimeText, singleWortNumText, readStoryTimeText, readStoryNumText
                 , movementsTimeText, movementsNumText, phonemicText, writeTimeText, writeNumText};

        try {
            if (student.getName() != null) {
                nameText.setText(student.getName());
            }

            //Changes everything textField to the student's values
            dateText.setText(student.getDate());  //Set date
            classText.getSelectionModel().select(student.get_class());  //Set class of student
            remarksText.setText(student.getRemarks());  //Set remarks

            for (int i = 0; i < Student.SOME_DIAGNOSES; i++) {
                diagnoses[i].setText(student.getLevelDiagnosisForIndex(i));
                diagnoses[i].setStyle(data.getColorIndex(student.getDiagnosisForIndex(i)));
            }

        }catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, e, "error", JOptionPane.ERROR_MESSAGE);
            System.out.println("The index of the student is out in the 'adding_diagnosis");
        }
        //timerLabel.setStyle(timerLabel.getStyle() + "; -fx-border-style: double;");
    }

    /**
     * Changes the values and colors of all the textFiled according to the array
     * @param newDiagnoses array of the diagnosis
     */
    private void setDiagnoses(int [] newDiagnoses)
    {
        for(int i = 0; i < Student.SOME_DIAGNOSES;i++)
        {
            if (!diagnoses[i].getStyle().equals(data.getColorIndex(View.COLOR_ERROR))) {
                diagnoses[i].setText(Student.LEVELS[newDiagnoses[i]]);
                diagnoses[i].setStyle(data.getColorIndex(newDiagnoses[i]));
            }
        }
    }

    /**
     * Return story according to class
     * @return The story
     */
    public void setStoryChoice()
    {
        choiceStory.getItems().clear();
        int _class = classText.getSelectionModel().getSelectedIndex();
        if(_class != -1){
            for(int i = 0; i < Data.LEGAL_STORIES[_class].length; i++)
                choiceStory.getItems().add(Data.NAME_STORIES[Data.LEGAL_STORIES[_class][i]]);
        }

        choiceStory.getSelectionModel().select(Data.NAME_STORIES[data.getStories()[_class]]);
    }


     // Function to change cell color when typing
    @FXML
    private void kay(KeyEvent event) {
        ((TextField)event.getSource()).setStyle(data.getColorIndex(Student.DEF));
    }


    @FXML
    // When clicking the save button
    private void Save(ActionEvent event){

        // Message if you want to save
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
        alert.setTitle("יציאה");
        alert.setHeaderText("את בטוחה שאת רוצה לבצע שינוים");
        if(alert.showAndWait().get() == ButtonType.YES) {

            // saving another data
            _student.setName(nameText.getText());
            _student.set_class(classText.getSelectionModel().getSelectedIndex());
            _student.setRemarks(remarksText.getText());

            // if is success to calculate closing the window
           if(calculate(SAVE)) {
               ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
               timer.cancel();
               _saving = true;
           }
        }
    }

    // When clicking the save calculate
    @FXML
    private void onlyCalculate(ActionEvent event)
    {
        calculate(CALCULATE);
    }


    // calculate
    // parameter if it saving or only calculate
    private boolean calculate(boolean save)
    {
      int grate, level = -1;
      int _class = classText.getSelectionModel().getSelectedIndex(); // get class
        int story = Data.getIndexStory(choiceStory.getSelectionModel().getSelectedItem());
      boolean success = true;
      int newDiagnosis[] = new int[Student.SOME_DIAGNOSES];


      // for all diagnoses
      for(int diagnosis = 0; diagnosis < Student.SOME_DIAGNOSES; diagnosis++) {
          level = -1;
          // get Integer from textFiled and if is positive calculate
          if ((grate = Function.isPositive(diagnoses[diagnosis].getText())) != -1) {
              level = Calculate.calculate(_class, grate, story, diagnosis);
          } else { // else check if is level
              for (int k = 0; k <= Student.HIGH_LEVEL; k++)
                  if (diagnoses[diagnosis].getText().equals(Student.LEVELS[k]))
                      level = k;
          }
          if (level != -1) {
              newDiagnosis[diagnosis] = level; // if is positive or level entry the level into the array
          } else { // else return error
              diagnoses[diagnosis].setStyle(data.getColorIndex(View.COLOR_ERROR));
              success = false;
          }
      }


        if(success && save) // If success and user went to save set the array of diagnoses
            _student.setDiagnosis(newDiagnosis);
        else { // If not success or the user not went to save set the level and colors the textFiled
            setDiagnoses(newDiagnosis);
            if(!success) { // And if not success print error
                errorInt.setText("הכנסי רק מספרים שלמים");
                errorInt.setFont(new Font(20));
            }
        }

        return success;
    }

    @FXML
    // When clicking the cancel button closing the window
    private void cancel(ActionEvent event) {
        exit((Stage)((Button)event.getSource()).getScene().getWindow());
    }


    /**
     * Displays an exit message and closes the window
     * @param stage The window
     */
    public  void exit(Stage stage)
    {
        //Displays a message if a user wants to exit
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
        alert.setTitle("יציאה");
        alert.setHeaderText("את בטוחה שאת רוצה לצאת בלי לשמור");
        if(alert.showAndWait().get() == ButtonType.YES) {
            stage.close();
            timer.cancel();
        }
    }
    /**
     * Starts the timer, initializing values and scheduling the timer task to update every 10 milliseconds.
     * @param event The action event triggered by the user (e.g., button click).
     */
    public void startTimer(ActionEvent event)
    {
        // Initialize the timer with zero hundredths of a second
        hundredth = 0;

        // Enable buttons for splitting and stopping the timer
        butShale.setDisable(false);
        butStop.setDisable(false);
        // Set button text for Stop and Split actions
        butStop.setText("עצור");
        butShale.setText("פצל");

        timer.cancel();  // Cancel any existing timer task

        // Create a new timer
        newTimer();
    }

    /**
     * // Create a new timer.
     *   At every hundredth of a second, the counter increases by one and changes the text of the seconds and minutes accordingly
     */
    private void newTimer()
    {
        // Create a new timer and schedule a task
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // Calculate seconds and minutes from hundredths of a second
                second = (int)hundredth / 100;
                minute = second / 60;

                Platform.runLater(() -> {
                    // Update the timer label on the UI thread
                    timerLabel.setText(String.format("%02d:%02d:%02d", minute, second % 60, hundredth++ % 100));
                });
            }
        };
        // Schedule the timer task to run every 10 milliseconds
        timer.scheduleAtFixedRate(task, 0, 10);

    }

    /**
     * Stops or resumes the timer based on the current state of the Stop button.
     * If the timer is running, it stops and updates button text to "Resume".
     * If the timer is paused, it resumes and updates button text to "Stop".
     * @param event The action event triggered by the user (e.g., button click).
     */
    public void stopTimer(ActionEvent event){
        // Check if indicating the timer is running
        if(butStop.getText().equals("עצור")) { //If it's running

            // Stop the timer and update button text to "Resume" and "Reset"
            timer.cancel();
            butStop.setText("המשך");
            butShale.setText("הפסק");

        }else{ //If it's paused

            // Resume the timer by creating a new timer and task
            timer.cancel();
            newTimer();

            // Update button text back to "Stop" and "Split"
            butStop.setText("עצור");
            butShale.setText("פצל");
        }
    }

    /**
     * Handles the actions for the Split and Reset buttons.
     * If the Split button is active, it adds the current timer value to the list.
     * If the Reset button is active, it clears the timer display and resets the buttons.
     * @param event The action event triggered by the user (e.g., button click).
     */
    public void whitTimer(ActionEvent event)
    {
        // Convert seconds to a string for display
        String s =    second + "  שניות";//

        // Check if the Split button shows "Split"
        if(butShale.getText().equals("פצל")) {
            // Add a split time entry to the list with the current timer value and seconds
            listTimer.getItems().add(String.format("%2s%30s", timerLabel.getText(), s));

        }else{
            // Reset: Add final time to list, clear label, and disable buttons
            listTimer.getItems().add("");
            listTimer.getItems().add(timerLabel.getText());
            listTimer.getItems().add("__________________________________");
            timerLabel.setText("00:00:00");

            // Disable buttons for Split and Stop and cancel the timer
            butShale.setDisable(true);
            butStop.setDisable(true);
            timer.cancel();
        }
    }
}
