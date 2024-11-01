package org.example.tala;


import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args)
    {
        HelloApplication.main(new String[0]);
    }
}


/*
    public static void main(String[] args) {

        List<StudentOld> list = new LinkedList<>();
        List<Student> newList = new LinkedList<>();
        //HelloApplication.main(new String[0]);
        inPut(list);
        for (StudentOld student : list)

            newList.add(new Student(student));

        //outPut(newList);
        list.clear();
        newList.clear();


    }

    public static void inPut(List<StudentOld> list) {
        String path = "date\\stu";

        FileInputStream file = null;
        ObjectInputStream in = null;
        try {
            file = new FileInputStream(path);
            in = new ObjectInputStream(file);

            while (true)
                list.add((StudentOld) in.readObject());


        } catch (EOFException e) {
            System.out.println("EOF: " + e);
        } catch (FileNotFoundException e) {
            System.out.println("file: " + e);
        } catch (ClassNotFoundException e) {
            System.out.println(" class: " + e);
        } catch (IOException e) {
            System.out.println("IO: "  + e);
        }


    }


    public static void outPut(List<Student> list) {

        String path = "date\\stu";
        File file = new File(path);
        if (list.isEmpty() && !file.exists()) {
            return;
        }
        try {
            file.createNewFile();

            FileOutputStream fileWrite = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileWrite);
            for (int i = 0; i < list.size(); i++)
                out.writeObject(list.get(i));

            out.close();
            fileWrite.close();
        } catch (EOFException e) {
            return;
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
*/

