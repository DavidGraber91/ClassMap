/**
 *This department calculates the scores of the diagnoses
 */

package org.example.tala;

public class Calculate {



    static final int DEF_CLASS = 0;
    static final int CLASS_2 = 0;
    static final int CLASS_3 = 1;
    static final int CLASS_4 = 2;
    static final int CLASS_5 = 3;
    static final int CLASS_6 = 4;
    static final int HIGH_CLASS = 5;
    static final double MINUTE = 60.0;
    static final double MAX_PERCENTS = 100.0;

    /**
     * sending to calculate according to class
     * @param _class The class
     * @param value the value of the diagnosis
     * @param diagnosis number of dianosis
     * @return level
     */
    public static int calculate(int _class, int value, int story, int diagnosis)
    {

        if(diagnosis == Student.SINGLE_WORD_NUM)
            return calSingleWortNum(_class, value);
        if(diagnosis == Student.SINGLE_WORD_TIME)
            return calSingleWortTime(_class, value);
        if(diagnosis == Student.READ_STORY_NUM)
            return calReadStoryNum(_class, story, value);
        if(diagnosis == Student.READ_STORY_TIME)
            return calReadStoryTime(_class, story, value);
        if(diagnosis == Student.MOVEMENT_NUM)
            return calMovementNum(_class, value);
        if(diagnosis == Student.MOVEMENT_TIME)
            return calMovementTime(_class, value);
        if(diagnosis == Student.PHONEMIC)
            return calPhonemicNum(_class,value);
        if(diagnosis == Student.WRITE_NUM)
            return calWriteNum(_class,value);
        if(diagnosis == Student.WRITE_TIME)
            return calWriteTime(_class,value);

        return -1;
    }


    private static int calSingleWortNum(int _class, int num)
    {
        final int ALL_WORDS = 38; // some of the word of the diagnosis
        int grade = (int)(num * MAX_PERCENTS) / ALL_WORDS; //Calculate the percentage


        int[][] tableGrade = new int[HIGH_CLASS][];
        tableGrade[CLASS_2] = new int[]{101, 55, 39, 34, 24, 13, 8, 3, 0};
        tableGrade[CLASS_3] = new int[]{101, 45, 32, 26, 20, 11, 7, 3, 0};
        tableGrade[CLASS_4] = new int[]{101, 34, 24, 18, 16, 8, 5, 3, 0};
        tableGrade[CLASS_5] = new int[]{101, 29, 21, 16, 13, 7, 4, 3, 0};
        tableGrade[CLASS_6] = new int[]{101, 24, 18, 13, 11, 5, 3, 0, 0};



        for(int level = 0; level <= Student.HIGH_LEVEL; level++)
            if(grade >= tableGrade[_class][level])
                return level;


        return Student.DEF;

    }

    private static int calSingleWortTime(int _class, int time)
    {
        final int ALL_WORDS = 38;
        int grade =  (int)(ALL_WORDS * MINUTE) / time;

        int[][] tableGrade = new int[HIGH_CLASS][];
        tableGrade[CLASS_2] = new int[]{0, 12, 15, 19, 22, 33, 42, 50};
        tableGrade[CLASS_3] = new int[]{0, 17, 23, 27, 32, 45, 53, 60};
        tableGrade[CLASS_4] = new int[]{0, 23, 30, 36, 42, 56, 65, 70};
        tableGrade[CLASS_5] = new int[]{0, 28, 36, 42, 48, 63, 72, 79};
        tableGrade[CLASS_6] = new int[]{0, 33, 42, 48, 54, 70, 79, 88};

        int level;
        for(level = 0; level < Student.HIGH_LEVEL; level++)
            if(grade <= tableGrade[_class][level])
                return level;

        return level;
    }

    private static int calReadStoryNum(int _class, int story, int num)
    {
        final int [] WOEDS = {194, 99, 196, 100};
        int allWords = WOEDS[story];

        int grade = (int)(num * MAX_PERCENTS) / allWords;

        int[][] tableGrade = new int[HIGH_CLASS + + Data.TWENTY_STUDENTS][];
        tableGrade[CLASS_2 + Data.WITH_APPETITE] = new int[]{101, 18, 11, 8, 5, 2, 1, 1, 0};
        tableGrade[CLASS_2 + Data.SHAI] = new int[]{101, 14, 9, 6, 4, 1, 1, 0, 0};
        tableGrade[CLASS_3 + Data.SHAI] = new int[]{101, 11, 7, 5, 4, 1, 1, 0, 0};
        tableGrade[CLASS_4 + Data.SHAI] = new int[]{101, 7, 5, 4, 3, 1, 0, 0, 0};
        tableGrade[CLASS_4 + Data.CLOCKS_BADE] = new int[]{101, 12, 8, 6, 5, 2, 1, 1, 0};
        tableGrade[CLASS_4 + Data.TWENTY_STUDENTS] = new int[]{101, 15, 10, 7, 5, 3, 1, 0, 0 };
        tableGrade[CLASS_5 + Data.TWENTY_STUDENTS] = new int[]{101, 12, 8, 6, 5, 2, 1, 0, 0};
        tableGrade[CLASS_6 + Data.TWENTY_STUDENTS] = new int[]{101, 9, 6, 5, 4, 1, 1, 0 ,0};


        for(int level = 0; level <= Student.HIGH_LEVEL; level++)
            if(grade >= tableGrade[_class + story][level])
                return level;


        return Student.DEF;

    }

    private static int calReadStoryTime(int _class, int story, int time)
    {

        final int [] WOEDS = {194, 99, 196, 100};
        int allWords = WOEDS[story];
        int grade = (int) (allWords * MINUTE) / time;

        int[][] tableGrade = new int[HIGH_CLASS + + Data.TWENTY_STUDENTS][];
        tableGrade[CLASS_2 + Data.WITH_APPETITE] = new int[]{0, 24, 34, 40 ,54, 80, 95, 112};
        tableGrade[CLASS_2 + Data.SHAI] = new int[]{0, 30, 41, 50, 63, 95, 112, 129};
        tableGrade[CLASS_3 + Data.SHAI] = new int[]{0, 47, 62, 74, 85, 113, 128, 143};
        tableGrade[CLASS_4 + Data.SHAI] = new int[]{0, 65, 84, 98, 108, 132, 145, 157};
        tableGrade[CLASS_4 + Data.CLOCKS_BADE] = new int[]{0, 50, 64, 73, 82, 102, 115, 125};
        tableGrade[CLASS_4 + Data.TWENTY_STUDENTS] = new int[]{0, 42, 55, 64, 72, 96, 109, 120};
        tableGrade[CLASS_5 + Data.TWENTY_STUDENTS] = new int[]{0, 49, 66, 75, 84, 107, 119, 131};
        tableGrade[CLASS_6 + Data.TWENTY_STUDENTS] = new int[]{0, 57, 77, 85, 96, 118, 129, 143};

        int level;
        for(level = 0; level < Student.HIGH_LEVEL; level++)
            if(grade <= tableGrade[_class + story][level])
                return level;

        return level;
    }

    private static int calPhonemicNum(int _class, int num)
    {
        final int ALL_WORDS = 16;
        int grade = (int)(num * MAX_PERCENTS) / ALL_WORDS;

        int[][] tableGrade = new int[HIGH_CLASS][];
        tableGrade[CLASS_2] = new int[]{101, 81, 75, 69, 63, 38, 19, 6, 0};
        tableGrade[CLASS_3] = new int[]{101, 78, 66, 56, 47, 25, 13, 3, 0};
        tableGrade[CLASS_4] = new int[]{101, 75, 56, 44, 31, 13, 6, 0, 0};
        tableGrade[CLASS_5] = new int[]{101, 69, 50, 38, 28, 9, 3, 0, 0};
        tableGrade[CLASS_6] = new int[]{101, 63, 44, 31, 25, 6, 6, 0, 0};


        for(int level = 0; level <= Student.HIGH_LEVEL; level++)
            if(grade >= tableGrade[_class][level])
                return level;

        return Student.DEF;
    }

    private static int calMovementNum(int _class, int num)
    {
        final int ALL_WORDS = 63;
        int grade = (int)(num * MAX_PERCENTS) / ALL_WORDS;

        int[][] tableGrade = new int[HIGH_CLASS][];
        tableGrade[CLASS_2] = new int[]{101, 59, 48, 37, 30, 14, 2, 5, 0};
        tableGrade[CLASS_3] = new int[]{101, 59, 48, 37, 30, 14, 2, 5, 0};
        tableGrade[CLASS_4] = new int[]{101, 53, 44, 38, 33, 19, 13, 8, 0};
        tableGrade[CLASS_5] = new int[]{101, 53, 44, 38, 33, 19, 13, 8, 0};
        tableGrade[CLASS_6] = new int[]{101, 54, 41, 35, 29, 16, 10, 5, 0};


        for(int level = 0; level <= Student.HIGH_LEVEL; level++)
            if(grade >= tableGrade[_class][level])
                return level;


        return Student.DEF;

    }

    private static int calMovementTime(int _class, int time)
    {
        final int ALL_WORDS = 63;
        int grade =  (int)(ALL_WORDS * MINUTE) / time;

        int[][] tableGrade = new int[HIGH_CLASS][];
        tableGrade[CLASS_2] = new int[]{0, 22, 29, 33, 37, 49, 56, 63};
        tableGrade[CLASS_3] = new int[]{0, 24 ,30 ,35, 39, 52, 59, 67};
        tableGrade[CLASS_4] = new int[]{0, 23, 32, 37, 42, 56, 62, 70};
        tableGrade[CLASS_5] = new int[]{0, 29, 35, 40, 45, 59, 66, 74};
        tableGrade[CLASS_6] = new int[]{0, 31, 37, 42, 47, 62, 70, 79};

        int level;
        for(level = 0; level < Student.HIGH_LEVEL; level++)
            if(grade <= tableGrade[_class][level])
                return level;

        return level;
    }

    private static int calWriteTime(int _class, int time)
    {
        int ALL_WORDS;
        if(_class == CLASS_2 || _class == CLASS_3)
            ALL_WORDS = 27;
        else ALL_WORDS = 55;

        int grade = (int)(ALL_WORDS * MINUTE) / time;

        int[][] tableGrade = new int[HIGH_CLASS][];
        tableGrade[CLASS_2] = new int[]{0, 6, 7, 8, 9, 13, 15, 17};
        tableGrade[CLASS_3] = new int[]{0, 6, 7, 8, 9, 13, 15, 17};
        tableGrade[CLASS_4] = new int[]{0, 10, 13, 15, 16, 19, 22, 26};
        tableGrade[CLASS_5] = new int[]{0, 11, 14, 17, 18, 23, 25, 29};
        tableGrade[CLASS_6] = new int[]{0, 13, 16, 19, 20, 26, 28, 32};

        int level;
        for(level = 0; level < Student.HIGH_LEVEL; level++)
            if(grade <= tableGrade[_class][level])
                return level;

        return level;
    }

    private static int calWriteNum(int _class, int num)
    {
        int ALL_WORDS;
        if(_class == CLASS_2 || _class == CLASS_3)
            ALL_WORDS = 27;
        else ALL_WORDS = 55;
        int grade =  (int)(num * MAX_PERCENTS) / ALL_WORDS;

        int[][] tableGrade = new int[HIGH_CLASS][];
        tableGrade[CLASS_2] = new int[]{101, 56, 41, 33, 30, 15, 7, 4, 0};
        tableGrade[CLASS_3] = new int[]{101, 56, 41, 33, 30, 15, 7, 4, 0};
        tableGrade[CLASS_4] = new int[]{101, 50, 36, 31, 24, 11, 5, 2, 0};
        tableGrade[CLASS_5] = new int[]{101, 40, 29, 25, 19, 8, 4, 1, 0};
        tableGrade[CLASS_6] = new int[]{101, 31, 22, 18, 15, 5, 2, 0, 0};


        for(int level = 0; level <= Student.HIGH_LEVEL; level++)
            if(grade >= tableGrade[_class][level])
                return level;


        return Student.DEF;

    }

}
