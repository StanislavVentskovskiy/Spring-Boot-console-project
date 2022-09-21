package ua.com.foxminded.menu;

import ua.com.foxminded.dao.impl.CoursesStudentsDaoImpl;
import ua.com.foxminded.dao.impl.StudentsDaoImpl;
import ua.com.foxminded.formatter.Formatter;
import ua.com.foxminded.model.Student;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ApplicationMenu {

    Formatter formatter = new Formatter();

    public void callMenu() throws IOException{
        formatter.showInitialMenu();
        userInputValidation();
    }

    private void userInputValidation() throws IOException {
        BufferedReader userInputLine = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        while (!(userInput = userInputLine.readLine()).equals("exit")) {
            if (userInput.equals("a")) {
                formatter.sendEnterStudentsNumberText();
                getAPointOutput(userInputLine.readLine());
                formatter.showBackToMenuMessage();
            } else if (userInput.equals("b")) {
                formatter.sendEnterCourseNameText();
                getBPointOutput(userInputLine.readLine());
                formatter.showBackToMenuMessage();
            } else if (userInput.equals("c")) {
                formatter.showMassageStudentInput();
                getCPointOutput(userInputLine.readLine());
                formatter.showBackToMenuMessage();
            } else if (userInput.equals("d")) {
                formatter.showInsertStudentIdText();
                deleteStudentById(userInputLine.readLine());
                formatter.showBackToMenuMessage();
            } else if (userInput.equals("e")) {
            //    System.out.println("in progress");
            //    formatter.showBackToMenuMessage();
            } else if (userInput.equals("f")) {
            //    System.out.println("in progress");
            //    formatter.showBackToMenuMessage();
            } else {
                formatter.showMessageMenuOptionIsIncorrect();
            }
        }
    }

    private void getAllGroupsWithEqualAndLessStudents(int studentsNumber) {
        StudentsDaoImpl studentsDao = new StudentsDaoImpl();
        ArrayList<String> groupList = studentsDao.getGroupsWithEqualOrLessStudentsNumber(studentsNumber);
        formatter.getAllGroupsWithEqualAndLessStudents(studentsNumber, groupList);
    }

    private void getAPointOutput(String userInput) {
        try {
            int studentsNumber = Integer.parseInt(userInput);
            getAllGroupsWithEqualAndLessStudents(Integer.valueOf(studentsNumber));
        } catch (NumberFormatException e) {
            formatter.showMessageStudentNameIsInvalid();
        }
    }

    private void getBPointOutput(String courseName) {
        try {
            getAllStudentsOfChosenCourse(courseName);
        } catch (Exception e) {
            formatter.enteredCourseNameIsNotValid();
        }
    }

    private void getCPointOutput(String studentNameAndSurname) {
        String[] nameAndSurname = studentNameAndSurname.split(" ");
        try {
            Student student = new Student(nameAndSurname[0], nameAndSurname[1]);
            StudentsDaoImpl studentsDao = new StudentsDaoImpl();
            studentsDao.insertStudent(student);
        } catch (ArrayIndexOutOfBoundsException e) {
            formatter.showMessageStudentNameAndSurnameInvalid();
        }
    }

    private void getAllStudentsOfChosenCourse(String courseName) {
        CoursesStudentsDaoImpl coursesStudentsDao = new CoursesStudentsDaoImpl();
        ArrayList<String> studentsList = coursesStudentsDao.getStudentsListRelatedToCourseByName(courseName);
        checkIfCourseNameExist(studentsList,courseName);
    }

    private void checkIfCourseNameExist(ArrayList<String> studentsList, String courseName) {
        if (!studentsList.isEmpty()) {
            formatter.getStudentsListOfChosenCourse(courseName, studentsList);
        } else {
            formatter.showMassageCourseNameIsInvalid();
        }
    }

    private void deleteStudentById(String userInput) {
        try {
            int studentId = Integer.parseInt(userInput);
            StudentsDaoImpl studentsDao = new StudentsDaoImpl();
            studentsDao.deleteStudentById(studentId);
           } catch (NumberFormatException e) {
            formatter.showMessageStudentIdIsInvalid();
        }
    }
}
