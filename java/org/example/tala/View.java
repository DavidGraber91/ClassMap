/**
 * This department is responsible for the display
 */

package org.example.tala;

import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

import java.util.List;

public class View {

    static final int COLOR_ERROR = 9;


    //
    private static String name = null;
    private static int _class = -1;
    private static int prevClass = -1;




    /**
     * Returns a function to change the values of the table. Changes the caption to the appropriate grade and the color according to the appropriate grade
     * @return The function
     */
    public static Callback<TableColumn<Student, Object>, TableCell<Student, Object>> newCell(Data data)
    {
        return  new Callback<TableColumn<Student, Object>, TableCell<Student, Object>>() {
            // Updates the cell's item and applies custom formatting
            @Override
            public TableCell<Student, Object> call(TableColumn<Student, Object> diagnosisIntegerTableColumn) {
                return new TableCell<Student, Object>() {

                    protected void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);

                        // Check if item is an Integer and apply specific style and text
                        if(item instanceof Integer) {
                            if (!empty && item != null) {
                                setText(Student.LEVELS[(Integer)item]); // Set the text based on student level
                                setStyle(data.getColorIndex((Integer)item));// Set the background color

                            }else {
                                setText("");
                                setStyle("");
                            }

                        // If item is a String, display it as is
                        }else if(item instanceof String){
                            if(!empty && item != null) {
                                setStyle("");
                                setText((String) item);
                            }
                            else{
                                    setText("");
                                    setStyle("");
                                }
                        }else {
                            setText("");
                            setStyle("");
                        }

                        // Center align the content in the cell
                        setAlignment(Pos.CENTER);
                    }
                };
            }
        };
    }


    /**
     * Returns a function to change the values of the table.
     * Changes the caption only if the name is different from the name of the previous line or the class is different,
     * uses the private parameters name and prevClass and _class. Designed so that there is no duplication of names
     * @return The function
     */
    public static Callback<TableColumn<Student, Student>, TableCell<Student, Student>> cellName(List<Student> list)
    {
        return  new Callback<TableColumn<Student, Student>, TableCell<Student, Student>>() {

            // Updates the cell's item and applies custom formatting
            @Override
            public TableCell<Student, Student> call(TableColumn<Student, Student> diagnosisIntegerTableColumn) {

                return new TableCell<Student, Student>() {

                    protected void updateItem(Student item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty && item != null) {
                            if (name == null) {
                                int prev = list.indexOf(item) - 1;
                                if(prev >= 0) {
                                    Student studentPrev = list.get(prev);
                                    if (studentPrev.equals(item))
                                        setText("");
                                    else setText(item.getName());
                                }else setText(item.getName());
                            }
                            else
                                setText("");// if is as Student as don't set the text
                        } else
                            setText("");

                        setAlignment(Pos.CENTER);
                    }
                };
            }
        };
    }


    /**
     *Returns a function to change the values of the table rows.
     * Changes the line to 2 pixels if the student's name is different from the previous student's name.
     * Designed to differentiate between students.
     * And also adds a menu by clicking the right mouse button
     * @param tableView The table
     * @param controller The controller of the table
     * @return The function
     */

    public static TableRow<Student> row(TableView<Student> tableView, Controller controller){

        TableRow<Student> row =  new TableRow<Student>() {
            @Override
            protected void updateItem(Student student, boolean empty) {
                super.updateItem(student, empty);
                if (student == null || empty) {
                    setStyle("");
                } else {
                    int next = tableView.getItems().indexOf(student) + 1;
                    if(next < tableView.getItems().size()) {
                        Student studentNext = tableView.getItems().get(next);
                        if (studentNext != null && !student.equals(studentNext))
                            setStyle("-fx-border-width: 0 0 2px 0; -fx-border-color: black");
                        else setStyle("");
                    }
                    setAlignment(Pos.CENTER);
                    setWrapText(true);
                    setTextAlignment(TextAlignment.CENTER);
                    setPrefHeight(USE_COMPUTED_SIZE);
                    setMinHeight(USE_COMPUTED_SIZE);
                    setMaxHeight(USE_COMPUTED_SIZE);

                }
            }

        };
            ContextMenu menu = Function.rowClick(tableView, row, controller); // get menu from function

            // Bind this menu in row
            row.contextMenuProperty().bind(
                    javafx.beans.binding.Bindings.when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(menu)
            );
        return row;
    }


}
