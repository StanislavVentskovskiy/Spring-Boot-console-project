package ua.com.foxminded.menu;

import ua.com.foxminded.dao.StudentsDao;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ApplicationMenu {

    public void callMenu() throws IOException{
        initialMenu();
        userInputValidation();
    }

    public void initialMenu(){
        System.out.printf("Data initiated please choose request from list below: \n");
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

    private void userInputValidation() throws IOException {
        BufferedReader userInputLine = new BufferedReader(new InputStreamReader(System.in));
        String userInput;

        while (!(userInput = userInputLine.readLine()).equals("exit")) {

            if (userInput.equals("a")) {
                System.out.println("in progress");
                backToMenu();
            } else if (userInput.equals("b")) {
                System.out.println("students");
                backToMenu();
            } else if (userInput.equals("c")) {
                System.out.println("in progress");
                backToMenu();
            } else if (userInput.equals("d")) {
                System.out.println("in progress");
                backToMenu();
            } else if (userInput.equals("e")) {
                System.out.println("in progress");
                backToMenu();
            } else if (userInput.equals("f")) {
                System.out.println("in progress");
                backToMenu();
            } else {
                System.out.println("Please input correct option.");
            }
        }
    }

    private void backToMenu() {

        System.out.println("Choose an option from menu or type \"exit\" to quit:");
    }
}

