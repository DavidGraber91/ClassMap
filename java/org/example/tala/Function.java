
package org.example.tala;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javax.swing.*;

import java.io.*;
import java.util.List;

public class Function {

    /**
     * Returns a positive integer
     * @param num The number
     * @return if the number is positive return the number. else return -1.
     */
    public static int isPositive(String num)
    {
        int temp = -1;
        try{
            temp = Integer.parseInt(num);
        }catch (NumberFormatException e){
            temp =  -1;
        }
        if(temp < 0)
            return -1;
        else return temp;
    }


    /**
     * Reading from a file a list of students
     * @param list The list into which the students are written
     */
    public static void inPut(List<Student> list)
    {
        FileInputStream file = null;
        ObjectInputStream in = null;
        try {
             file = new FileInputStream("date\\stu");
             in = new ObjectInputStream(file);

            try{
                while (true)
                    list.add((Student) in.readObject()); // adding students into the list of the file
            }catch (EOFException e) {
                }

            in.close();
            file.close();


        }catch (FileNotFoundException e) {
            return;
        }catch (ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, e, "error", JOptionPane.ERROR_MESSAGE);
        }  catch(IOException e){
            JOptionPane.showMessageDialog(null, e, "error", JOptionPane.ERROR_MESSAGE);
        }


    }

    /**
     * Writes to a file from a list of students
     * @param list The list of students
     */
    public synchronized static void outPut(List<Student> list)
    {

        try  {
            File file = new File("date\\stu");
            file.createNewFile();

            FileOutputStream fileWrite = new FileOutputStream("date\\stu");
            ObjectOutputStream out = new ObjectOutputStream(fileWrite);

            for(int i = 0; i < list.size(); i++)
                out.writeObject(list.get(i));

            out.close();
            fileWrite.close();
        }catch (EOFException e){

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e, "error", JOptionPane.ERROR_MESSAGE);
        }  catch(IOException e){
            JOptionPane.showMessageDialog(null, e, "error", JOptionPane.ERROR_MESSAGE);
        }


    }

    /**
     * Creating a menu for the table rows
     * @param table The table
     * @param row The row
     * @param controller The controller fo the main window
     * @return
     */
    public static ContextMenu rowClick(TableView<Student> table,TableRow<Student> row, Controller controller)
    {

        ContextMenu menu = new ContextMenu(); // Creating a menu



        // creating and adding item int ti the menu
        MenuItem item = new MenuItem("עריכה");
        item.setOnAction(event -> controller.edit(event, row));

        menu.getItems().add(item);

        item = new MenuItem("מחיקה");
        item.setOnAction(event -> controller.delete(event, row));
        menu.getItems().add(item);

        return menu;
    }



}
