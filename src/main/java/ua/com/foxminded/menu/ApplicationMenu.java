package ua.com.foxminded.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ApplicationMenu {

    public void callMenu(){
        initialMenu();
        userInputValidation(inputUserData());
    }

    public void initialMenu(){

        System.out.printf("Data initiated please choose request from list below:");
        System.out.println("type \"a\" - find all groups with less or equal students number");
        System.out.println("type \"b\" - find all students related to the course with given name");
        System.out.println("type \"c\" - add new student");
        System.out.println("type \"d\" - delete student by STUDENT_ID");
        System.out.println("type \"e\" - add a student to the course(from a list)");
        System.out.println("type \"f\" - remove the student from one of their course");
        System.out.println("type \"exit\" - to stop the application.");
    }

    private String inputUserData(){
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        String userLine;

        try {
            userLine = userInput.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return (String) userLine;
    }

    private void userInputValidation(String userInput) {
        if (userInput.equals("a")) {
            //methodthat returns groups
            System.out.println("groups");
        } else if (userInput.equals("b")) {
            System.out.println("students");
            //method returns students list
        } else if (userInput.equals("c")) {
            System.out.println("add student");
            // method adds student
        } else if (userInput.equals("d")) {
            System.out.println("deleteStudent");
            //method delete student by id
        } else if (userInput.equals("e")) {
            System.out.println("add stundet to course");
            //addingstudent to course
        } else if (userInput.equals("f")) {
            System.out.println("remove Student from couse");
            //remove student from one of course
        } else if (userInput.equals("exit")) {
            System.exit(0);
        }
    }

}
