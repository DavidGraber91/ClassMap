/**
 * This class controls the main window
 */

package org.example.tala;

import java.io.*;
import java.util.LinkedList;
import java.util.List;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.PrinterJob;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import javax .swing.*;


public class Controller{


    private ObservableList<Student> studentList = FXCollections.observableArrayList();
    private List<Student> list = new LinkedList<>();

    @FXML
    private VBox vBox;

    @FXML
    private TableView<Student> table;

    @FXML
    private TableColumn<Student, Object>   tabClass,  tabData, tabSingleWordNum,  tabSingleWordTime,  tabReadStoryNum,  tabReadStoryTime
            , tabMovementNum,  tabMovementTime,  tabPhonemic,  tabWriteNum,  tabWriteTime, tabRemarks;

    @FXML
    private TableColumn<Student, Student> tabName;

    @FXML
    private Button butSave,  butAdd,  butPrint;

    @FXML
    private Label userName;

    public Data data;

    //Initializes all the columns of the table
    private void makeTable()
    {
        table.setItems(studentList);
        table.setRowFactory(tableView -> View.row(tableView, this));

        tabName.setCellValueFactory(new PropertyValueFactory<>("Student")); // Set the item to get value to cell
        tabName.setCellFactory(View.cellName(studentList));
        tabClass.setCellValueFactory(new PropertyValueFactory<>("className"));
        tabClass.setCellFactory(View.newCell(data)); // Set the function of this cell
        tabData.setCellValueFactory(new PropertyValueFactory<>("date"));
        tabData.setCellFactory(View.newCell(data));
        tabSingleWordNum.setCellValueFactory(new PropertyValueFactory<>("singleWortNum"));
        tabSingleWordNum.setCellFactory(View.newCell(data));
        tabSingleWordTime.setCellValueFactory(new PropertyValueFactory<>("singleWortTime"));
        tabSingleWordTime.setCellFactory(View.newCell(data));
        tabReadStoryNum.setCellValueFactory(new PropertyValueFactory<>("readStoryNum"));
        tabReadStoryNum.setCellFactory(View.newCell(data));
        tabReadStoryTime.setCellValueFactory(new PropertyValueFactory<>("readStoryTime"));
        tabReadStoryTime.setCellFactory(View.newCell(data));
        tabMovementNum.setCellValueFactory(new PropertyValueFactory<>("movementNum"));
        tabMovementNum.setCellFactory(View.newCell(data));
        tabMovementTime.setCellValueFactory(new PropertyValueFactory<>("movementTime"));
        tabMovementTime.setCellFactory(View.newCell(data));
        tabPhonemic.setCellValueFactory(new PropertyValueFactory<>("phonemic"));
        tabPhonemic.setCellFactory(View.newCell(data));
        tabWriteNum.setCellValueFactory(new PropertyValueFactory<>("writeNum"));
        tabWriteNum.setCellFactory(View.newCell(data));
        tabWriteTime.setCellValueFactory(new PropertyValueFactory<>("writeTime"));
        tabWriteTime.setCellFactory(View.newCell(data));
        tabRemarks.setCellValueFactory(new PropertyValueFactory<>("remarks"));
    }

    /**
     * Prints the entire table of students
     * @param event
     */
    @FXML
    public void print(ActionEvent event)
    {
        Pane pane = new Pane(table);
        double height = table.getHeight();

        // Store the original size of the pane
        double originalHeight = pane.getHeight();
        double originalWidth = pane.getWidth();

        // Set the pane to a larger size for a higher-resolution snapshot
        pane.setPrefSize(originalWidth * 2, originalHeight * 2);

        // Get the current stage from the event source (the button click)
        Stage stage = ((Stage) vBox.getScene().getWindow());

        // Create a PrinterJob instance for printing
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            // Create a writable image with double the size of the pane for better resolution
            WritableImage snapshotImage = new WritableImage(3500, 3500);

            // Set up SnapshotParameters with a scaling factor to increase the resolution
            SnapshotParameters snapshotParameters = new SnapshotParameters();

            // Scaling the snapshot by 3x for better quality
            snapshotParameters.setTransform(new Scale(3, 3));

            // Take a snapshot of the pane at the higher resolution
            pane.snapshot(snapshotParameters, snapshotImage);

            // Create an ImageView to hold the snapshot image for printing
            ImageView imageView = new ImageView(snapshotImage);
            imageView.setFitWidth(job.getJobSettings().getPageLayout().getPrintableWidth());
            imageView.setPreserveRatio(true);

            // Show the print dialog to the user
            job.showPrintDialog(stage);

            // Print the page containing the ImageView (which holds the snapshot)
            if (job.printPage(imageView))
                job.endJob();

            // Restore the original size of the pane after the snapshot
            pane.setPrefSize(originalWidth, originalHeight);
            table.setPrefHeight(height);
            vBox.getChildren().add(1, table);
            System.out.println("finish");
        }
    }


    /**
     * Starts the window operation, adds a save function when closing the window.
     * Change the username.
     * Remove the list of students from the file
     * @param _data data of user
     */
    public void start(Data _data)
    {
        data = _data;
        userName.setText(data.getName()); //Change the username
        Function.inPut(list); // Remove the list of students from the file
        studentList.addAll(list); //Adds the list of the table
        makeTable();
    }

    /**
     * Adds a new student to the list
     * @param event
     */

    @FXML
    private void addStudent(ActionEvent event) {
        Student student = new Student();
        boolean success = openStageDiagnosis(student); // Open the window of 'adding_diagnosis

        // If it's saved adds this on the list
        if (success) {
            list.add(student);
            list.sort(Student::compareTo);
            studentList.clear();
            studentList.addAll(list);
            save(null);
        }

    }


    //saving the list of students
    @FXML
    private void save(ActionEvent event) {
            Function.outPut(list);
    }

    @FXML
    private void exit(ActionEvent event) {
        ((Stage)vBox.getScene().getWindow()).close();
    }

    /**
     *Deletes a student from the list
     * @param event
     * @param row The student of delete
     */
    public void delete(ActionEvent event, TableRow<Student> row) {
        Student student = row.getItem();
        studentList.remove(student);
        list.remove(student);
        save(null);
    }

    /**
     *Edit a student from the list
     * @param event
     * @param row The student of delete
     */
    public void edit(ActionEvent event, TableRow<Student> row) {
        Student student = row.getItem();
        // Open the window of 'adding_diagnosis
        boolean success = openStageDiagnosis(student);

        if (success) {
            list.sort(Student::compareTo);
            studentList.clear();
            studentList.addAll(list);
            save(null);
        }
    }


    // Open The window of the 'adding_student'
    // parameter is the student thw went to edit
    private boolean openStageDiagnosis(Student student) {
        try {
            // Loading the file fxml
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adding_diagnosis.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load()));
            // Get Controller of the fxml
            ControllerDiagnosis controller = fxmlLoader.getController();
            // sending the student
            controller.display(student, data);
            // Change the function that cannot close the window without confirming
            stage.setOnCloseRequest(event1 -> {
                event1.consume();
                controller.exit(stage);
            });

            //set icon
            Image image = new Image(getClass().getResourceAsStream("icons/Designer_icn.png"));
            stage.getIcons().add(image);

            //set title
            stage.setTitle("הוספת מיפוי");

            //Determines that the window size will not change
            stage.setResizable(false);
            stage.showAndWait();

            //return if the user is saving
            return controller.isSaving();

            //In the case of exception print to the user and return false
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "01c" + e, "error", JOptionPane.ERROR_MESSAGE);
            return false;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "02c" + e, "error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    public void changeColors(ActionEvent event)
    {
        try {
            // Loading the file fxml
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("change-colors.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load()));

            // sending the student
            ((ControllerChangeColors)fxmlLoader.getController()).setData(data);

            //Determines that the window size will not change
            stage.setResizable(false);

            //set icon and title
            Image image = new Image(getClass().getResourceAsStream("icons/colores.png"));
            stage.getIcons().add(image);
            stage.setTitle("שינוי צבעים");

            stage.showAndWait();

            //In the case of exception print to the user and return false
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "03c" + e, "error", JOptionPane.ERROR_MESSAGE);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "04c" + e, "error", JOptionPane.ERROR_MESSAGE);
        }

        data.save();

        //Refills the list
        studentList.clear();
        studentList.addAll(list);

    }

    public void changeDefStores(ActionEvent event)
    {
        try {
            // Loading the file fxml
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("change_def_stores.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load()));

            // sending the student
            ((ControllerChangeDefStories)fxmlLoader.getController()).setData(data);

            //set title and icon
            Image image = new Image(getClass().getResourceAsStream("icons/Designer_icn.png"));
            stage.getIcons().add(image);
            stage.setTitle("שינוי סיפורים");

            //Determines that the window size will not change
            stage.setResizable(false);
            stage.showAndWait();

            //In the case of exception print to the user and return false
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "03c" + e, "error", JOptionPane.ERROR_MESSAGE);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "04c" + e, "error", JOptionPane.ERROR_MESSAGE);
        }

        data.save();

        //Refills the list
        studentList.clear();
        studentList.addAll(list);

    }

    public void changePassword(ActionEvent event)
    {
        try {
            // Loading the file fxml
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("change-password.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load()));

            // sending the student
            ((ControllerChangePassword)fxmlLoader.getController()).setData(data);

            //set title and icon
            Image image = new Image(getClass().getResourceAsStream("icons/sc.png"));
            stage.getIcons().add(image);
            stage.setTitle("שינוי סיסמה");

            //Determines that the window size will not change
            stage.setResizable(false);
            stage.showAndWait();

            //In the case of exception print to the user and return false
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "03c" + e, "error", JOptionPane.ERROR_MESSAGE);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "04c" + e, "error", JOptionPane.ERROR_MESSAGE);
        }

        data.save();
    }

}
