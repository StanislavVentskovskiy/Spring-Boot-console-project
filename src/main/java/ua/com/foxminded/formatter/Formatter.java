package ua.com.foxminded.formatter;

import ua.com.foxminded.model.Student;
import ua.com.foxminded.model.Course;
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

    public void showMessageEnteredDataIsInvalid(){
        System.out.println("Entered data is not valid");
    }

    public void formatGroupListOutput(int studentsNumber, ArrayList<String> groupsList) {
        if (!groupsList.isEmpty()) {
            System.out.println("Next groups got " + studentsNumber + " students or less:");
            formatListOfDataToColumn(groupsList);
        } else {
            System.out.println("There are no groups with such student number.");
        }
    }

    public void showMessageEnterStudentNumber() {
        System.out.println("Enter students number:");
    }

    public void showMessageEnterCourseName() {
        System.out.println("Enter course name:");
    }

    public void enteredCourseNameIsNotValid() {
        System.out.println("Entered course name is invalid");
    }

    public void formatStudentsListOfChosenCourse(String courseName, ArrayList<String> studentsList){
        System.out.printf("Next students related to the course " + courseName + ":" + "\n");
        formatListOfDataToColumn(studentsList);
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

    public void showMessageStudentNameAndSurnameInvalid() {
        System.out.println("Entered student name and surname is invalid");
    }

    public void showMessageStudentIdIsInvalid() {
        System.out.println("Student id is invalid.");
    }

    public  void showInsertStudentIdText() {
        System.out.println("Enter student id:");
    }

    public void showMessageChooseStudentById() {
        System.out.println("Choose student by Id from list above:");

    }

    public void showStudentsList(ArrayList<Student> students) {
        for(int index = 0; index < students.size(); index++) {
           String studentOutput = students.get(index).getId() + ". " + students.get(index).getName() + " " +students.get(index).getSurname();
            System.out.println(studentOutput);
        }
    }

    public void showMessageChooseCourseById() {
        System.out.println("Choose course by Id from list above:");
    }

    public void showCoursesList(ArrayList<Course> courses) {
        for(int index = 0; index < courses.size(); index++) {
            String studentOutput = courses.get(index).getCourseId() + ". " + courses.get(index).getName() + " " + courses.get(index).getCourseDescription();
            System.out.println(studentOutput);
        }
    }

    public void showMessageStudentAddedToCourse(){
        System.out.println("Student assignet do course successfully.");
    }

    public void showMessageToChooseCourseById() {
        System.out.println("Choose one course to delete by Id above:");
    }
    public void showMessageStudentWasRemovedFromCourse() {
        System.out.println("Student deleted from course.");
    }

    public void getCourseNameList(ArrayList<Course> courseList) {
        ArrayList<String> courseNameList = new ArrayList<>();
        for(int index = 0; index < courseList.size(); index++){
            courseNameList.add(courseList.get(index).getName());
        }
        formatListOfDataToColumn(courseNameList);
    }

    public void showMessageStudentAdded(){
        System.out.println("Student added to list.");
    }

    public void showMessageStudentDeleted(){
        System.out.println("Student removed.");
    }

    private void formatListOfDataToColumn(ArrayList<String> dataList){
        for(int index = 0; index < dataList.size(); index++) {
            System.out.println(dataList.get(index));
        }
    }
}

