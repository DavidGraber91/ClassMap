package org.example.tala;

import javafx.scene.paint.Color;

import javax.swing.*;
import java.io.*;

/**
 * The Data class manages user data, including username, password, color settings,
 * and story configuration. It supports saving and loading data as a serialized file.
 */
class Data implements Serializable {

    // File path for saving user data securely
    protected static final String FILE_SECURITY = "date\\sec";

    // Constants representing different story options
    public static final int WITH_APPETITE = 0;
    public static final int SHAI = 1;
    public static final int CLOCKS_BADE = 2;
    public static final int TWENTY_STUDENTS = 3;

    // Default story configuration and legal options for each story position
    public static final int [] DEF_STORIES = {SHAI, SHAI, TWENTY_STUDENTS, TWENTY_STUDENTS, TWENTY_STUDENTS};
    public static final int[][] LEGAL_STORIES = {{WITH_APPETITE, SHAI}, {SHAI}, {SHAI, CLOCKS_BADE, TWENTY_STUDENTS}, {TWENTY_STUDENTS}, {TWENTY_STUDENTS}};
    public static final String [] NAME_STORIES = {"בתאבון", "שי לא היה פחדן" , "שעוני הגוף" , "עשרים תלמידים"};

    private static final long serialVersionUID = -2320184698916172335L;
    private String userName;
    private String password;
    private String[] colors;
    private int[] stories;


    // Default colors for display elements
    protected static final String[] DEF_COLORS = {"",
            "-fx-background-color: #920000; -fx-text-fill: white;",
            "-fx-background-color: #ff0000; -fx-text-fill: white;"
            , "-fx-background-color: #ff491e;"
            , "-fx-background-color: #f38832;"
            , "-fx-background-color: #ffd600;"
            , "-fx-background-color: #daf14a;"
            , "-fx-background-color: #76f81f;"
            , "-fx-background-color: #207c00;"
            , "-fx-background-color: pink;"};


    /**
     * Constructs a new Data object with a given username and password.
     * Initializes default color and story configurations.
     * @param userName the username for the user
     * @param password the password for the user, which will be hashed
     */
    protected Data(String userName, String password) {
        this.userName = userName;
        this.password = security(password);
        this.colors = DEF_COLORS;
        this.stories = DEF_STORIES;
    }

    /**
     * Gets the username of the user.
     * @return the user's name
     */
    protected String getName(){ return userName;}

    /**
     * Retrieves the index of a given story name.
     * @param story the name of the story to find
     * @return the index of the story in NAME_STORIES or -1 if not found
     */
    public static int getIndexStory(String story)
    {
        int index;
        for(index = 0; index < NAME_STORIES.length; index ++)
            if(story.equals(NAME_STORIES[index]))
                return index;
        return -1;
    }

    /**
     * Retrieves the story configuration as an array of indices.
     * @return an array of integers representing story indices
     */
    protected int [] getStories()
    {
        int [] temp = new int[stories.length];
        System.arraycopy(stories, 0, temp, 0, stories.length);
        return temp;
    }

    /**
     * Gets a specific color by index.
     * @param i the index of the color to retrieve
     * @return a CSS color string
     */
    protected String getColorIndex(int i) {
        return colors[i];
    }

    /**
     * Retrieves a copy of the colors array.
     * @return a copy of the colors array as a new String array
     */
    protected String[] getColors() {
        String[] temp = new String[colors.length];
        for (int i = 0; i < colors.length; i++)
            temp[i] = colors[i];

        return temp;
    }

    /**
     * Sets a new array of stories if it meets specific criteria.
     *
     * @param newStories an array of new stories to set
     */
    public void setStories(int[] newStories) {

        if(newStories[Student.CLASS_2] == WITH_APPETITE || newStories[Student.CLASS_2] == SHAI
                && newStories[Student.CLASS_3] == SHAI
                && newStories[Student.CLASS_4] == SHAI || newStories[Student.CLASS_4] == CLOCKS_BADE || newStories[Student.CLASS_4] == TWENTY_STUDENTS
                && newStories[Student.CLASS_5] == TWENTY_STUDENTS
                && newStories[Student.CLASS_6] == TWENTY_STUDENTS)

            System.arraycopy(newStories, 0, stories, 0, stories.length);
    }

    /**
     * Sets a new array of stories using story names.
     * @param newStories an array of story names
     */
    public void setStories(String [] newStories)
    {
        int [] temp = new int[DEF_STORIES.length];
        for(int i = 0; i < temp.length; i++) {
            temp[i] = getIndexStory(newStories[i]);
            if(temp[i] == -1)
                return;
        }
        setStories(temp);
    }

    /**
     * Sets a new password for the user, with encryption.
     * @param password the new password
     */
    protected void setPassword(String password) {
        this.password = security(password);
    }

    /**
     * Sets new colors if the array meets CSS format requirements.
     * @param newColor an array of new color styles in CSS format
     */
    protected void setColors(String[] newColor) {
        if(isColors(newColor))
            System.arraycopy(newColor, 0, colors, 0, newColor.length);
    }

    /**
     * Compares the given password with the stored password.
     * @param otherPassword the password to compare
     * @return true if the passwords match, false otherwise
     */
    protected boolean equalsPassword(String otherPassword)
    {
        return password.equals(security(otherPassword));
    }

    /**
     * Validates if the colors array meets required CSS format and length.
     * @param colors an array of colors in CSS format
     * @return true if colors are valid, false otherwise
     */
    protected static boolean isColors(String[] colors)
    {
        //check the length array
        if (colors.length != DEF_COLORS.length)
            return false;

        if(!colors[0].isEmpty())
            return false;

        try {
            for (int i = 1; i < colors.length; i++)
                if (!colors[i].substring(0, 22).equals("-fx-background-color: "))
                    return false;

            //check if the color is legal
            for (int i = 1; i < colors.length; i++) {
                int index = colors[i].indexOf(';');
                Color.web(colors[i].substring(22, index));
            }

        }catch (IllegalArgumentException e){return false;}
        catch (StringIndexOutOfBoundsException e){return false;}

        //check if the white color of the font is legal
        for(int i = 1; i < colors.length; i++)
            if(colors[i].length() > 30 && !colors[i].substring(30).equals(" -fx-text-fill: white;"))
                return false;

        return true;

    }

    /**
     * Saves the Data object to a file specified in FILE_SECURITY.
     * Displays error messages if saving fails.
     */
    protected void save() {
        final String FILE_SECURITY = "date\\sec";

        try {
            FileOutputStream fileWrite = new FileOutputStream(FILE_SECURITY);
            ObjectOutputStream out = new ObjectOutputStream(fileWrite);

            out.writeObject(this);

            out.close();
            fileWrite.close();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, "error 02cs: " + e, "error", JOptionPane.ERROR_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "error 03cs: " + e, "error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "error 04cs: " + e, "error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Encrypts the password using modular arithmetic for obfuscation.
     * @param password the password to encrypt
     * @return an obfuscated version of the password
     */
    private static String security (String password){

        final int MOD = 200;
        final int MUL = 16452;
        final int SUM = 150;

        String temp = "";
        for(int i = 0; i < password.length(); i++)
        {
            int num = (password.charAt(i) * MUL) % MOD;
            temp += (char)(num + SUM);
        }
        return temp;
    }
}
