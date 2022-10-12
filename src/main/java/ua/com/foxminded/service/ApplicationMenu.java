package ua.com.foxminded.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.com.foxminded.config.SpringConfig;
import ua.com.foxminded.dao.CoursesStudentsDao;
import ua.com.foxminded.exceptions.DAOException;
import ua.com.foxminded.dao.impl.CourseDaoImpl;
import ua.com.foxminded.dao.impl.CoursesStudentsDaoImpl;
import ua.com.foxminded.dao.impl.StudentsDaoImpl;
import ua.com.foxminded.formatter.Formatter;
import ua.com.foxminded.model.Course;
import ua.com.foxminded.model.Student;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ApplicationMenu {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    private Formatter formatter = new Formatter();
    private BufferedReader userInputLine = new BufferedReader(new InputStreamReader(System.in));
    private final int studentLimit = 200;
    StudentsDaoImpl studentsDao = context.getBean(StudentsDaoImpl.class);
    CourseDaoImpl courseDao = context.getBean(CourseDaoImpl.class);
    CoursesStudentsDao coursesStudentsDao = context.getBean(CoursesStudentsDaoImpl.class);

    public void callConsoleMenu() throws DAOException {
        try {
            formatter.showInitialMenu();
            getUserRequest();
        } catch (IOException e) {
            throw new DAOException(e);
        }
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
                formatter.showMessageStudentInput();
                addNewStudent(userInputLine.readLine());
            } else if (userInput.equals("d")) {
                formatter.showStudentsList(getStudentList());
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
        ArrayList<String> groupList = studentsDao.getGroupsWithEqualOrLessStudentsNumber(studentsDao.getGroupsWithEqualOrLessStudentsNumber(studentsNumber));
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
        formatter.getCourseNameList(courseDao.getCourseList());
    }

    public void getAllStudentsOfChosenCourse(String courseName) {
        ArrayList<String> studentsList = coursesStudentsDao.getStudentsNamesAndSurnamesList(coursesStudentsDao.getStudentsListRelatedToCourseByName(courseName));
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

            studentsDao.deleteStudentById(studentId);
            formatter.showMessageStudentDeleted();
           } catch (NumberFormatException e) {
            formatter.showMessageStudentIdIsInvalid();
        }
        formatter.showBackToMenuMessage();
    }

    private List<Student> getStudentList(){

        return studentsDao.getStudentsList();
    }

    private Integer studentIdValidation(String userInput) {
        int studentId = 0;
        try {
            studentId = Integer.parseInt(userInput);

            if (studentId > studentLimit) {
                throw new NumberFormatException();
            }

        } catch (NumberFormatException e) {
            formatter.showMessageStudentIdIsInvalid();
        }
        return studentId;
    }

    private ArrayList<Course> getCourseList() {

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
        coursesStudentsDao.insertStudentAndCourse(studentId,courseId);
    }

    private ArrayList<Course> getCourseListByStudentId(int studentsId) {
        return coursesStudentsDao.getCourseListByStudentId(studentsId);
    }

    private void removeStudentFromCourseByStudentId(int courseId,int studentId) {
              coursesStudentsDao.deleteStudentFromCourseById(courseId,studentId);
    }
}
