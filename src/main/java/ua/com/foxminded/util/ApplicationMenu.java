package ua.com.foxminded.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.exceptions.DAOException;
import ua.com.foxminded.formatter.Formatter;
import ua.com.foxminded.model.Course;
import ua.com.foxminded.model.Student;
import ua.com.foxminded.service.impl.CourseStudentsServiceImpl;
import ua.com.foxminded.service.impl.CoursesServiceImpl;
import ua.com.foxminded.service.impl.StudentServiceImpl;
import ua.com.foxminded.service.validator.Validator;
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
    private StudentServiceImpl studentServiceImpl;

    @Autowired
    private CoursesServiceImpl courseService;

    @Autowired
    private CourseStudentsServiceImpl courseStudentsServiceImpl;

    @Autowired
    private Validator applicationMenuValidator;

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
                formatter.showCoursesList(courseService.getAllCoursesNameList());
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
                boolean studentExist = applicationMenuValidator.validateStudentId(studentId, studentServiceImpl.getStudentIdList());
                if (studentExist == true) {
                    formatter.showCoursesList(courseService.getAllCoursesNameList());
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

    public void findAllGroupWithEqualOrLessStudentsNumber(String userInput) {
        if (applicationMenuValidator.validateIntegerInput(userInput) == true) {
            formatter.formatGroupListOutput(Integer.valueOf(userInput), studentServiceImpl.getAllGroupsWithEqualOrLessStudents(Integer.valueOf(userInput)));
        } else {
            formatter.showMessageEnteredDataIsInvalid();
        }
    }

    public void findAllStudentsRelatedToCourseWithGivenName(String courseName) {
        try {
            getAllStudentsOfChosenCourse(courseName);
        } catch (Exception e) {
            formatter.showMessageEnteredDataIsInvalid();
        }
        formatter.showBackToMenuMessage();
    }

    private void getAllStudentsOfChosenCourse(String courseName) {
           ArrayList<String> studentsList = courseStudentsServiceImpl.getStudentsListRelatedToCourse(courseName);
          if (applicationMenuValidator.validateCourseName(studentsList) == true) {
            getFormattedStudentList(studentsList, courseName);
        } else {
            formatter.showMessageEnteredDataIsInvalid();
        }
    }

    private void getFormattedStudentList(ArrayList<String> studentsList, String courseName) {
        formatter.formatStudentsListOfChosenCourse(courseName, studentsList);
    }

    public void addNewStudent(String studentNameAndSurname) {
        String[] nameAndSurname = studentNameAndSurname.split(" ");
        try {
            Student student = new Student(nameAndSurname[0], nameAndSurname[1]);
            applicationMenuValidator.validateStudentInsert(studentServiceImpl.addStudent(student));
            formatter.showMessageStudentAdded();
        } catch (ArrayIndexOutOfBoundsException e) {
            formatter.showMessageStudentNameAndSurnameInvalid();
        }
        formatter.showBackToMenuMessage();
    }

    public void deleteStudentById(String userInput) {
        try {
            int studentId = Integer.parseInt(userInput);
            deleteValidatedStudent(studentServiceImpl.deleteStudentById(studentId));
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
        return studentServiceImpl.getStudentList();
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

    public void addStudentToCourse(int studentId, int courseId) {
        if (courseStudentsServiceImpl.getCoursesIdListByStudent(studentId).contains(courseId)) {
            formatter.showMessageStudentAlreadyAssignedToCourse();
        } else {
            courseStudentsServiceImpl.insertStudentAndCourse(studentId, courseId);
            formatter.showMessageStudentAdded();
        }
    }

    private ArrayList<Course> getCourseListByStudentId(int studentsId) {
        return courseStudentsServiceImpl.getCourseListByStudentId(studentsId);
    }

    public void removeStudentFromCourseByStudentId(int courseId, int studentId) {
        if (applicationMenuValidator.validateDeleteStudentFromCourse(courseStudentsServiceImpl.deleteStudentFromCourseById(courseId,studentId))) {
            formatter.showMessageStudentWasRemovedFromCourse();
        } else {
            formatter.showMessageNoCourseFoundAssignedToCurrentStudent();
        }
    }
}
