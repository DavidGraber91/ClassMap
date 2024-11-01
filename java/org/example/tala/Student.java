/**
 * This department represents a student with one diagnostic score
 */
package org.example.tala;

import java.io.Serializable;
import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Student  implements Serializable {

// the value of the grate
    public static final int DEF = 0;
    public static final int IMPROPER = 1;
    public static final int VERY_LOW = 2;
    public static final int LOW = 3;
    public static final int BELOW_AVERAGE = 4;
    public static final int AVERAGE = 5;
    public static final int ABOVE_AVERAGE = 6;
    public static final int HIGH = 7;
    public static final int VERY_HIGH = 8;
    public static final int HIGH_LEVEL = 8;


 // Teh Strings of the grate
    public static final String[] LEVELS = {"/","בלתי תקין", "נמוך מאוד", "נמוך מהממוצע", "תקין מתחת לממוצע","ממוצע"
            ,"תקין מעל הממוצע","גבוה", "גבוה מאוד"};

    // The Strings og classes
    public static final String[] CLASSES = {"ב", "ג", "ד","ה", "ו"};

    // The value of classes
    static final int DEF_CLASS = 0;
    static final int CLASS_2 = 0;
    static final int CLASS_3 = 1;
    static final int CLASS_4 = 2;
    static final int CLASS_5 = 3;
    static final int CLASS_6 = 4;
    static final int ALL_CLASSES = 5;
    static final int HIGH_CLASS = 5;

    //The diagnoses
    static final int SINGLE_WORD_TIME = 0;
    static final int SINGLE_WORD_NUM = 1;
    static final int READ_STORY_TIME = 2;
    static final int READ_STORY_NUM = 3;
    static final int MOVEMENT_TIME = 4;
    static final int MOVEMENT_NUM = 5;
    static final int PHONEMIC = 6;
    static final int WRITE_TIME = 7;
    static final int WRITE_NUM = 8;
    static final int SOME_DIAGNOSES = 9;

    //The Strings of the diagnoses
    static final String[] DIAGNOSES = {"הכתבה", "ערנות פונמית", "קריאת עיצורים", "קריאת טקסט", "מילים בודדות"};

    private static final long serialVersionUID = -3762240635619668047L;
    private String name;
    private int _class;
    private LocalDate date;
    private String remarks;
    private int[] diagnosis;

    /**
     * Creates a default student.
     */
    public Student()
    {
        name = "";
        _class = -1;
        remarks = "";
        date =  LocalDate.now(Clock.systemDefaultZone());
        diagnosis = new int[SOME_DIAGNOSES];
    }

    /**
     * Student creator
     * @param name The name of student
     * @param _class The class of student
     * @param diagnosis The value of grate of diagnosis
     */
    public Student(String name, int _class, int[] diagnosis)
    {
        this.name = name;
        this._class = _class;
        date = LocalDate.now(Clock.systemDefaultZone());
        this.diagnosis = new int[SOME_DIAGNOSES];
        remarks = "";
        setDiagnosis(diagnosis);
    }


    /**
     * Changes the scores of the diagnosis. If it is not in the middle does not change the same diagnosis
     * @param diagnosis array of the diagnosis. the grates Should be arranged according to the final
     */
    public void setDiagnosis(int[] diagnosis) {
        if(diagnosis.length == SOME_DIAGNOSES)
            for(int i = 0; i < SOME_DIAGNOSES; i++)
            {
                if(diagnosis[i] >= 0 && diagnosis[i] <= HIGH_LEVEL)
                    this.diagnosis[i] = diagnosis[i];
            }

    }

    /**
     * Set the class
     * @param _class The class
     */
    public void set_class(int _class) {
        this._class = _class;
    }

    /**
     * Set the remarks
     * @param remarks new remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * Set the name
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the date
     * @param date The date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Get the value of class
     * @return Value of class
     */
    public int get_class() {
        return _class;
    }

    /**
     * Return remarks
     * @return Remarks
     */
    public String getRemarks() {
        return remarks;
    }

    public Student getStudent(){return this;}

    /**
     * Get the string of class
     * @return The string of class
     */
    public String getClassName() {
        try {
            return CLASSES[_class];
        } catch (ArrayIndexOutOfBoundsException e){
        System.out.println("Index out of bounds for length Student.CLASSES");
        }
        return "";
    }

    /**
     * get the name
     * @return The name
     */
    public String getName(){
        return name;
    }

    /**
     * Get the date
     * @return The date
     */
    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Get the value of diagnosis of single word num
     * @return The value of diagnosis of single word num
     */
    public int getSingleWortNum() {
        return diagnosis[SINGLE_WORD_NUM];
    }

    /**
     * Get the value of diagnosis of single word time
     * @return The value of diagnosis of single word time
     */
    public int getSingleWortTime() {
        return diagnosis[SINGLE_WORD_TIME];
    }

    /**
     * Get the value of diagnosis of read story num
     * @return The value of diagnosis of read story num
     */
    public int getReadStoryNum() {
        return diagnosis[READ_STORY_NUM];
    }

    /**
     * Get the value of diagnosis of read story time
     * @return The value of diagnosis of read story time
     */
    public int getReadStoryTime() {
        return diagnosis[READ_STORY_TIME];
    }

    /**
     * Get the value of diagnosis of movement num
     * @return The value of diagnosis of movement num
     */
    public int getMovementNum() {
        return diagnosis[MOVEMENT_NUM];
    }

    /**
     * Get the value of diagnosis of movement time
     * @return The value of diagnosis of movement time
     */
    public int getMovementTime() {
        return diagnosis[MOVEMENT_TIME];
    }

    /**
     * Get the value of diagnosis of phonemic
     * @return The value of diagnosis of phonemic
     */
    public int getPhonemic() {
        return diagnosis[PHONEMIC];
    }

    /**
     * Get the value of diagnosis of write num
     * @return The value of diagnosis of write num
     */
    public int getWriteNum() {
        return diagnosis[WRITE_NUM];
    }

    /**
     * Get the value of diagnosis of write time
     * @return The value of diagnosis of write time
     */
    public int getWriteTime() {
        return diagnosis[WRITE_TIME];
    }

    /**
     * Get the string of diagnosis of single word num
     * @return The string of diagnosis of single word num
     */
    public String getLevelSingleWortNum() {
        return LEVELS[diagnosis[SINGLE_WORD_NUM]];
    }

    /**
     * Get the string of diagnosis of single word time
     * @return The string of diagnosis of single word time
     */
    public String getLevelSingleWortTime() {
        return LEVELS[diagnosis[SINGLE_WORD_TIME]];
    }

    /**
     * Get the string of diagnosis of read story num
     * @return The string of diagnosis of read story num
     */
    public String getLevelReadStoryNum() {
        return LEVELS[diagnosis[READ_STORY_NUM]];
    }

    /**
     * Get the string of diagnosis of read story time
     * @return The string of diagnosis of read story time
     */
    public String getLevelReadStoryTime() {
        return LEVELS[diagnosis[READ_STORY_TIME]];
    }

    /**
     * Get the string of diagnosis of movement num
     * @return The string of diagnosis of movement num
     */
    public String getLevelMovementNum() {
        return LEVELS[diagnosis[MOVEMENT_NUM]];
    }

    /**
     * Get the string of diagnosis of movement time
     * @return The string of diagnosis of movement time
     */
    public String getLevelMovementTime() {
        return LEVELS[diagnosis[MOVEMENT_TIME]];
    }

    /**
     * Get the string of diagnosis of phonemic
     * @return The string of diagnosis of phonemic
     */
    public String getLevelPhonemic() {
        return LEVELS[diagnosis[PHONEMIC]];
    }

    /**
     * Get the string of diagnosis of write num
     * @return The string of diagnosis of write num
     */
    public String getLevelWriteNum() {
        return LEVELS[diagnosis[WRITE_NUM]];
    }

    /**
     * Get the string of diagnosis of write num
     * @return The string of diagnosis of write num
     */
    public String getLevelWriteTime() {
        return LEVELS[diagnosis[WRITE_TIME]];
    }

    /**
     * return the value of diagnosis of the index
     * @param index
     * @return The value of diagnosis
     */
    public int getDiagnosisForIndex(int index)
    {
        if(index >= 0 && index < SOME_DIAGNOSES)
            return diagnosis[index];
        else return -1;
    }

    /**
     * return the string of diagnosis of the index
     * @param index
     * @return The string of diagnosis
     */
    public String getLevelDiagnosisForIndex(int index)
    {
        if(index >= 0 && index < SOME_DIAGNOSES)
            return LEVELS[diagnosis[index]];
        else return "";
    }

    /**
     * Compares two students by name and then by class and then by date
     * @param otherStudent The comparative student
     * @return 1 if this student is ahead of him 0 if they are equal and -1 if after
     */
    public int compareTo(Student otherStudent)
    {
        int temp;
        temp = name.compareTo(otherStudent.name);
        if(temp == 0)
            temp = _class - otherStudent._class;
        if(temp == 0)
            temp = date.compareTo(otherStudent.date);
        return temp;
    }

    public boolean equals(Student other)
    {
        return name.equals(other.name) && _class == other._class;
    }
}
