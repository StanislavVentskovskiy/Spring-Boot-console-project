package ua.com.foxminded.service;

import ua.com.foxminded.formatter.dao.CoursesStudentsDao;
import ua.com.foxminded.formatter.dao.DAOException;
import ua.com.foxminded.formatter.dao.impl.CourseDaoImpl;
import ua.com.foxminded.formatter.dao.impl.CoursesStudentsDaoImpl;
import ua.com.foxminded.formatter.dao.impl.StudentsDaoImpl;
import ua.com.foxminded.formatter.Formatter;
import ua.com.foxminded.model.Course;
import ua.com.foxminded.model.Student;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ApplicationMenu {
    private Formatter formatter = new Formatter();
    private BufferedReader userInputLine = new BufferedReader(new InputStreamReader(System.in));

    public void callConsoleMenu() throws DAOException, IOException {
        formatter.showInitialMenu();
        getUserRequest();
    }

    private void getUserRequest() throws DAOException, IOException {
        String userInput;
        while (!(userInput = userInputLine.readLine()).equals("exit")) {
            if (userInput.equals("a")) {
                formatter.showMessageEnterStudentNumber();
                findAllGroupWithEqualOrLessStudentsNumber(userInputLine.readLine());
            } else if (userInput.equals("b")) {
                getAllCoursesNameList();
                formatter.showMessageEnterCourseName();
                findAllStudentsRelatedToCourseWithGivenName(userInputLine.readLine());
            } else if (userInput.equals("c")) {
                formatter.showMassageStudentInput();
                addNewStudent(userInputLine.readLine());
            } else if (userInput.equals("d")) {
                formatter.showInsertStudentIdText();
                deleteStudentById(userInputLine.readLine());
            } else if (userInput.equals("e")) {
                formatter.showStudentsList(getStudentList());
                formatter.showMessageChooseStudentById();
                int studentId = studentIdValidation(userInputLine.readLine());
                formatter.showCoursesList(getCourseList());
                formatter.showMessageChooseCourseById();
                int courseId = checkViableCourseId(userInputLine.readLine());
                addStudentToCourse(studentId, courseId);
                formatter.showMessageStudentAddedToCourse();
            } else if (userInput.equals("f")) {
                formatter.showStudentsList(getStudentList());
                formatter.showMessageChooseStudentById();
                int studentId = studentIdValidation(userInputLine.readLine());
                formatter.showCoursesList(getCourseListByStudentId(studentId));
                formatter.showMessageToChooseCourseById();
                int courseId = studentIdValidation(userInputLine.readLine());
                removeStudentFromCourseByStudentId(courseId, studentId);
                formatter.showMessageStudentWasRemovedFromCourse();
                formatter.showBackToMenuMessage();
            } else {
                formatter.showMessageEnteredDataIsInvalid();
            }
        }
    }

    public void getAllGroupsWithEqualOrLessStudents(int studentsNumber) {
        StudentsDaoImpl studentsDao = new StudentsDaoImpl();
        ArrayList<String> groupList = studentsDao.getGroupsWithEqualOrLessStudentsNumber(studentsNumber);
        formatter.formatGroupListOutput(studentsNumber, groupList);
    }

    public void findAllGroupWithEqualOrLessStudentsNumber(String userInput) {
        try {
            int studentsNumber = Integer.parseInt(userInput);
            getAllGroupsWithEqualOrLessStudents(Integer.valueOf(studentsNumber));
        } catch (NumberFormatException e) {
            formatter.showMessageEnteredDataIsInvalid();
        }
        formatter.showBackToMenuMessage();
    }

    private void findAllStudentsRelatedToCourseWithGivenName(String courseName) {


        try {
            getAllStudentsOfChosenCourse(courseName);
        } catch (Exception e) {
            formatter.showMessageEnteredDataIsInvalid();
        }
        formatter.showBackToMenuMessage();
    }

    private void getAllCoursesNameList(){
        CourseDaoImpl courseDao = new CourseDaoImpl();
        formatter.getCourseNameList(courseDao.getCourseList());
    }

    private void getAllStudentsOfChosenCourse(String courseName) {
        CoursesStudentsDaoImpl coursesStudentsDao = new CoursesStudentsDaoImpl();
        ArrayList<String> studentsList = coursesStudentsDao.getStudentsListRelatedToCourseByName(courseName);
        getFormattedStudentList(studentsList,courseName);
    }

    private void getFormattedStudentList(ArrayList<String> studentsList, String courseName)  {
        if (!studentsList.isEmpty()) {
            formatter.formatStudentsListOfChosenCourse(courseName, studentsList);
        } else {
            formatter.showMessageEnteredDataIsInvalid();
        }
    }

    private void addNewStudent(String studentNameAndSurname) {
        String[] nameAndSurname = studentNameAndSurname.split(" ");
        try {
            Student student = new Student(nameAndSurname[0], nameAndSurname[1]);
            StudentsDaoImpl studentsDao = new StudentsDaoImpl();
            studentsDao.insertStudent(student);
            formatter.showMessageStudentAdded();
        } catch (ArrayIndexOutOfBoundsException e) {
            formatter.showMessageStudentNameAndSurnameInvalid();
        }
        formatter.showBackToMenuMessage();
    }

    private void deleteStudentById(String userInput) {
        try {
            int studentId = Integer.parseInt(userInput);
            StudentsDaoImpl studentsDao = new StudentsDaoImpl();
            studentsDao.deleteStudentById(studentId);
            formatter.showMessageStudentDeleted();
           } catch (NumberFormatException e) {
            formatter.showMessageStudentIdIsInvalid();
        }
        formatter.showBackToMenuMessage();
    }

    private ArrayList<Student> getStudentList(){
        StudentsDaoImpl studentsDao = new StudentsDaoImpl();
        return studentsDao.getStudentsList();
    }

    private Integer studentIdValidation(String userInput) {
        int studentId = 0;
        try {
            studentId = Integer.parseInt(userInput);

            if (studentId > 200) {
                throw new NumberFormatException();
            }

        } catch (NumberFormatException e) {
            formatter.showMessageStudentIdIsInvalid();
        }
        return studentId;
    }

    private ArrayList<Course> getCourseList() {
        CourseDaoImpl courseDao = new CourseDaoImpl();
        return courseDao.getCourseList();
    }

    private Integer checkViableCourseId(String userInput) {
        int studentId = 0;
        try {
            studentId = Integer.parseInt(userInput);

            if (studentId > 10) {
                throw new NumberFormatException();
            }

        } catch (NumberFormatException e) {
            formatter.showMessageStudentIdIsInvalid();
        }
        return studentId;
    }

    private void addStudentToCourse(int studentId, int courseId) {
        CoursesStudentsDao coursesStudentsDao = new CoursesStudentsDaoImpl();
        coursesStudentsDao.insertStudentAndCourse(studentId,courseId);
    }

    private ArrayList<Course> getCourseListByStudentId(int studentsId) {
        CoursesStudentsDaoImpl courseDao = new CoursesStudentsDaoImpl();
        return courseDao.getCourseListByStudentId(studentsId);
    }

    private void removeStudentFromCourseByStudentId(int courseId,int studentId) {
        CoursesStudentsDaoImpl coursesStudentsDao = new CoursesStudentsDaoImpl();
        coursesStudentsDao.deleteStudentFromCourseById(courseId,studentId);


    }
}
