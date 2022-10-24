package ua.com.foxminded.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.exceptions.DAOException;
import ua.com.foxminded.dao.impl.CourseDaoImpl;
import ua.com.foxminded.dao.impl.CoursesStudentsDaoImpl;
import ua.com.foxminded.dao.impl.StudentsDaoImpl;
import ua.com.foxminded.formatter.Formatter;
import ua.com.foxminded.model.Course;
import ua.com.foxminded.model.Student;
import ua.com.foxminded.service.validaton.ApplicationMenuValidator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationMenu {
    private BufferedReader userInputLine = new BufferedReader(new InputStreamReader(System.in));

    @Autowired
    private Formatter formatter;

    @Autowired
    private StudentsDaoImpl studentsDao;

    @Autowired
    private CourseDaoImpl courseDao;

    @Autowired
    private CoursesStudentsDaoImpl coursesStudentsDao;

    @Autowired
    private ApplicationMenuValidator applicationMenuValidator;

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
                boolean studentExist = applicationMenuValidator.validateStudentId(studentId, studentsDao.getStudentsIdList());
                if (studentExist == true) {
                    formatter.showCoursesList(getCourseList());
                    formatter.showMessageChooseCourseById();
                    int courseId = checkViableCourseId(userInputLine.readLine());
                    addStudentToCourse(studentId, courseId);
                } else {
                    formatter.showMessageNoSuchStudentFound();
                }
            } else if (userInput.equals("f")) {
                formatter.showStudentsList(getStudentList());
                formatter.showMessageChooseStudentById();
                int studentId = studentIdValidation(userInputLine.readLine());
                formatter.showCoursesList(getCourseListByStudentId(studentId));
                formatter.showMessageToChooseCourseById();
                int courseId = studentIdValidation(userInputLine.readLine());
                removeStudentFromCourseByStudentId(courseId, studentId);
                formatter.showBackToMenuMessage();
            } else {
                formatter.showMessageEnteredDataIsInvalid();
            }
            formatter.showInitialMenu();
        }
    }

    private void getAllGroupsWithEqualOrLessStudents(int studentsNumber) {
        ArrayList<String> groupList = studentsDao.getGroupsWithEqualOrLessStudentsNumber(studentsDao.getGroupsWithEqualOrLessStudentsNumber(studentsNumber));
        formatter.formatGroupListOutput(studentsNumber, groupList);
    }

    private void findAllGroupWithEqualOrLessStudentsNumber(String userInput) {
        if (applicationMenuValidator.validateIntegerInput(userInput) == true) {
            getAllGroupsWithEqualOrLessStudents(Integer.valueOf(userInput));
        } else {
            formatter.showMessageEnteredDataIsInvalid();
        }
    }

    private void findAllStudentsRelatedToCourseWithGivenName(String courseName) {
        try {
            getAllStudentsOfChosenCourse(courseName);
        } catch (Exception e) {
            formatter.showMessageEnteredDataIsInvalid();
        }
        formatter.showBackToMenuMessage();
    }

    private void getAllCoursesNameList() {
        formatter.getCourseNameList(courseDao.getCourseList());
    }

    private void getAllStudentsOfChosenCourse(String courseName) {
        ArrayList<String> studentsList = coursesStudentsDao.getStudentsNamesAndSurnamesList(coursesStudentsDao.getStudentsListRelatedToCourseByName(courseName));
          if (applicationMenuValidator.validateCourseName(studentsList) == true) {
            getFormattedStudentList(studentsList, courseName);
        } else {
            formatter.showMessageEnteredDataIsInvalid();
        }
    }

    private void getFormattedStudentList(ArrayList<String> studentsList, String courseName) {
        formatter.formatStudentsListOfChosenCourse(courseName, studentsList);
    }

    private void addNewStudent(String studentNameAndSurname) {
        String[] nameAndSurname = studentNameAndSurname.split(" ");
        try {
            Student student = new Student(nameAndSurname[0], nameAndSurname[1]);
            applicationMenuValidator.validateStudentInsert(studentsDao.insertStudent(student));
            formatter.showMessageStudentAdded();
        } catch (ArrayIndexOutOfBoundsException e) {
            formatter.showMessageStudentNameAndSurnameInvalid();
        }
        formatter.showBackToMenuMessage();
    }

    private void deleteStudentById(String userInput) {
        try {
            int studentId = Integer.parseInt(userInput);
            deleteValidatedStudent(studentsDao.deleteStudentById(studentId));
        } catch (NumberFormatException e) {
            formatter.showMessageStudentIdIsInvalid();
        }
        formatter.showBackToMenuMessage();
    }

    private void deleteValidatedStudent(int deleteResult) {
        if (applicationMenuValidator.validateStudentDelete(deleteResult) == true) {
            formatter.showMessageStudentDeleted();
        } else {
            formatter.showMessageNoSuchStudentFound();
        }
    }


    private List<Student> getStudentList() {

        return studentsDao.getStudentsList();
    }

    private Integer studentIdValidation(String userInput) {
        int studentId = 0;
        try {
            studentId = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            formatter.showMessageStudentIdIsInvalid();
        }
        return studentId;
    }

    private ArrayList<Course> getCourseList() {

        return courseDao.getCourseList();
    }

    private Integer checkViableCourseId(String userInput) {
        int courseId = 0;
        try {
            courseId = Integer.parseInt(userInput);
            if (courseId > 10) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            formatter.showMessageStudentIdIsInvalid();
        }
        return courseId;
    }

    private void addStudentToCourse(int studentId, int courseId) {
        if (coursesStudentsDao.getCoursesIdListByStudent(studentId).contains(courseId)) {
            formatter.showMessageStudentAlreadyAssignedToCourse();
        } else {
            coursesStudentsDao.insertStudentAndCourse(studentId, courseId);
            formatter.showMessageStudentAdded();
        }
    }

    private ArrayList<Course> getCourseListByStudentId(int studentsId) {
        return coursesStudentsDao.getCourseListByStudentId(studentsId);
    }

    private void removeStudentFromCourseByStudentId(int courseId, int studentId) {
        if (applicationMenuValidator.validateDeleteStudentFromCourse(coursesStudentsDao.deleteStudentFromCourseById(courseId, studentId))) {
            formatter.showMessageStudentWasRemovedFromCourse();
        } else {
            formatter.showMessageNoCourseFoundAssignedToCurrentStudent();
        }
    }
}
