package ua.com.foxminded.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationMenu.class);

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
        LOG.info("Entered callConsoleMenu()");
        try {
            formatter.showInitialMenu();
            getUserRequest();
        } catch (IOException e) {
            LOG.error("Error connecting to DB");
            throw new DAOException(e);
        }
        LOG.info("Leave callConsoleMenu() method");
    }

    private void getUserRequest() throws DAOException, IOException {
        LOG.info("Enter method getUserRequest()");
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
                LOG.info("Leave getStudentList()");
                formatter.showInsertStudentIdText();
                deleteStudentById(userInputLine.readLine());
            } else if (userInput.equals("e")) {
                formatter.showStudentsList(getStudentList());
                LOG.info("Leave getStudentList()");
                formatter.showMessageChooseStudentById();
                int studentId = studentIdValidation(userInputLine.readLine());
                LOG.info("Leave method studentIdValidation()");
                boolean studentExist = applicationMenuValidator.validateStudentId(studentId, studentServiceImpl.getStudentIdList(studentServiceImpl.getStudentList()));
                if (studentExist == true) {
                    formatter.showCoursesList(courseService.getAllCoursesNameList());
                    LOG.info("Leave getAllCoursesNameList() method");
                    formatter.showMessageChooseCourseById();
                    int courseId = checkViableCourseId(userInputLine.readLine());
                    LOG.info("Leave checkViableCourseId()");
                    addStudentToCourse(studentId, courseId);
                    LOG.info("Leave addStudentToCourse()");
                } else {
                    formatter.showMessageNoSuchStudentFound();
                    LOG.warn("No input student found");
                }
            } else if (userInput.equals("f")) {
                formatter.showStudentsList(getStudentList());
                LOG.info("Leave getStudentList()");
                formatter.showMessageChooseStudentById();
                int studentId = studentIdValidation(userInputLine.readLine());
                LOG.info("Leave method studentIdValidation()");
                formatter.showCoursesList(getCourseListByStudentId(studentId));
                LOG.info("Leave method getCourseListByStudentId()");
                formatter.showMessageToChooseCourseById();
                int courseId = studentIdValidation(userInputLine.readLine());
                LOG.info("Leave method studentIdValidation()");
                removeStudentFromCourseByStudentId(courseId, studentId);
                LOG.info("Leave method removeStudentFromCourseByStudentId()");
                formatter.showBackToMenuMessage();
            } else {
                formatter.showMessageEnteredDataIsInvalid();
                LOG.warn("Entered data invalid");
            }
            formatter.showInitialMenu();
        }
        LOG.info("Leave method getUserRequest()");
    }

    public void findAllGroupWithEqualOrLessStudentsNumber(String userInput) {
        LOG.info("Enter method findAllGroupWithEqualOrLessStudentsNumber()");
        if (applicationMenuValidator.validateIntegerInput(userInput) == true) {
            formatter.formatGroupListOutput(Integer.valueOf(userInput), studentServiceImpl.getAllGroupsWithEqualOrLessStudents(Integer.valueOf(userInput)));
            LOG.info("Leave method findAllGroupWithEqualOrLessStudentsNumber()");
        } else {
            formatter.showMessageEnteredDataIsInvalid();
            LOG.warn("Entered userInput is invalid");
            LOG.info("Leave method findAllGroupWithEqualOrLessStudentsNumber()");
        }
    }

    public void findAllStudentsRelatedToCourseWithGivenName(String courseName) {
        LOG.info("Enter findAllStudentsRelatedToCourseWithGivenName()");
        try {
            getAllStudentsOfChosenCourse(courseName);
        } catch (Exception e) {
            LOG.error("Entered " + courseName + " is invalid");
            formatter.showMessageEnteredDataIsInvalid();
        }
        formatter.showBackToMenuMessage();
        LOG.info("Leave method findAllStudentsRelatedToCourseWithGivenName()");
    }

    private void getAllStudentsOfChosenCourse(String courseName) {
        LOG.info("Entered method getAllStudentsOfChosenCourse()");
        ArrayList<String> studentsList = courseStudentsServiceImpl.getStudentsListRelatedToCourse(courseName);
          if (applicationMenuValidator.validateCourseName(studentsList) == true) {
            getFormattedStudentList(studentsList, courseName);
            LOG.info("Leave method getAllStudentsOfChosenCourse()");
        } else {
            LOG.error("Entered " + courseName + " is invalid");
            formatter.showMessageEnteredDataIsInvalid();
            LOG.info("Leave method getAllStudentsOfChosenCourse()");
        }
    }

    private void getFormattedStudentList(ArrayList<String> studentsList, String courseName) {
        LOG.info("Enter method getFormattedStudentList()");
        formatter.formatStudentsListOfChosenCourse(courseName, studentsList);
        LOG.info("Leave method getFormattedStudentList()");
    }

    public void addNewStudent(String studentNameAndSurname) {
        LOG.info("Enter method addNewStudent()");
        String[] nameAndSurname = studentNameAndSurname.split(" ");
        try {
            Student student = new Student(nameAndSurname[0], nameAndSurname[1]);
            applicationMenuValidator.validateStudentInsert(studentServiceImpl.addStudent(student));
            LOG.info("Student " + student.getName() + " " + student.getSurname() + " was added");
            formatter.studentAddedToStudentsList();
        } catch (ArrayIndexOutOfBoundsException e) {
            LOG.error("Entered name and surname is invalid");
            formatter.showMessageStudentNameAndSurnameInvalid();
        }
        formatter.showBackToMenuMessage();
        LOG.info("Leave method addNewStudent()");
    }

    public void deleteStudentById(String userInput) {
        LOG.info("Entered deleteStudentById()");
        try {
            int studentId = Integer.parseInt(userInput);
            deleteValidatedStudent(studentServiceImpl.deleteStudentById(studentId));
        } catch (NumberFormatException e) {
            LOG.warn("Entered userInput is invalid");
            formatter.showMessageStudentIdIsInvalid();
        }
        formatter.showBackToMenuMessage();
        LOG.info("Leave deleteStudentById()");
    }

    private void deleteValidatedStudent(Boolean deleteResutl) {
        LOG.info("Entered deleteValidatedStudent()");
        if (applicationMenuValidator.validateStudentDelete(deleteResutl) == true) {
            LOG.warn("Student deleted");
            formatter.showMessageStudentDeleted();
        } else {
            LOG.warn("No student with found warn");
            formatter.showMessageNoSuchStudentFound();
        }
        LOG.info("Leave deleteValidatedStudent() method");
    }

    private List<Student> getStudentList() {
        LOG.info("Enter getStudentList()");
        return studentServiceImpl.getStudentList();
    }

    private Integer studentIdValidation(String userInput) {
        LOG.info("Entered studentIdValidation()");
        int studentId = 0;
        try {
            studentId = Integer.parseInt(userInput);
            LOG.warn("Correct student id");
        } catch (NumberFormatException e) {
            formatter.showMessageStudentIdIsInvalid();
            LOG.warn("Invalid student id");
        }
        return studentId;
    }

    private Integer checkViableCourseId(String userInput) {
        LOG.info("Enter checkViableCourseId()");
        int courseId = 0;
        try {
            courseId = Integer.parseInt(userInput);
            if (courseId > 10) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            LOG.warn("Entered userInput is invalid");
            formatter.showMessageStudentIdIsInvalid();
        }
        return courseId;
    }

    public void addStudentToCourse(int studentId, int courseId) {
        LOG.info("Enter addStudentToCourse()");
        if (courseStudentsServiceImpl.getCoursesIdListByStudent(studentId).contains(courseId)) {
            formatter.showMessageStudentAlreadyAssignedToCourse();
            LOG.warn("Student already assigned to course");
        } else {
            courseStudentsServiceImpl.insertStudentAndCourse(studentId, courseId);
            formatter.showMessageStudentAdded();
            LOG.info("Student assigned to course");
        }
    }

    private ArrayList<Course> getCourseListByStudentId(int studentsId) {
        LOG.info("Enter method getCourseListByStudentId()");
        return courseStudentsServiceImpl.getCourseListByStudentId(studentsId);
    }

    public void removeStudentFromCourseByStudentId(int courseId, int studentId) {
        LOG.info("Enter method removeStudentFromCourseByStudentId()");
        courseStudentsServiceImpl.deleteStudentFromCourseById(courseId,studentId);
        formatter.showMessageStudentWasRemovedFromCourse();
        LOG.info("Student removed from course");
        LOG.info("Leave validateDeleteStudentFromCourse()");
    }
}
