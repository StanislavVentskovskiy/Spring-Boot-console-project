package ua.com.foxminded.formatter;

import java.util.ArrayList;

public class Formatter {
    public void showInitialMenu(){
        System.out.printf("Data initiated please choose request from list below: \n");
        System.out.println("type \"a\" - find all groups with less or equal students number");
        System.out.println("type \"b\" - find all students related to the course with given name");
        System.out.println("type \"c\" - add new student");
        System.out.println("type \"d\" - delete student by STUDENT_ID");
        System.out.println("type \"e\" - add a student to the course(from a list)");
        System.out.println("type \"f\" - remove the student from one of their course");
        System.out.println("type \"exit\" - to stop the application.");
    }

    public void showMessageStudentNameIsInvalid(){
        System.out.println("Entered data is not valid");
    }

    public void getAllGroupsWithEqualAndLessStudents(int studentsNumber, ArrayList<String> groupsList) {
        System.out.println("Next groups got " + studentsNumber + " students or less:");
        System.out.println(groupsList.toString());
    }

    public void sendEnterStudentsNumberText() {
        System.out.println("Enter students number:");
    }

    public void sendEnterCourseNameText() {
        System.out.println("Enter course name:");
    }

    public void enteredCourseNameIsNotValid() {
        System.out.println("Entered course name is invalid");
    }

    public void getStudentsListOfChosenCourse(String courseName, ArrayList<String> studentsList){
        System.out.printf("Next students related to the course " + courseName + ":" + "\n");
        System.out.println(studentsList.toString());
    }

    public void showMassageStudentInput() {
        System.out.println("Please enter student name and surname divided by space:");
    }

    public void showMassageCourseNameIsInvalid() {
        System.out.println("Course name is invalid");
    }

    public void showBackToMenuMessage(){
        System.out.println("Choose an option from menu or type \"exit\" to quit:");
    }

    public void showMessageMenuOptionIsIncorrect(){
        System.out.println("Please enter correct option from menu.");
    }

    public void showMessageStudentNameAndSurnameInvalid() {
        System.out.println("Entered student name and surname is invalid");
    }

    public void showMessageStudentIdIsInvalid() {
        System.out.println("Student id is invalid.");
    }

    public  void showInsertStudentIdText() {
        System.out.println("Enter student id:");
    }
}
