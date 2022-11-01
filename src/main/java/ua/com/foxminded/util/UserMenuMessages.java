package ua.com.foxminded.util;

import org.springframework.stereotype.Service;

@Service
public final class UserMenuMessages {
    public static final String initialMenuMessage = "\nData initiated please choose request from list below: \n" +
        "type \"a\" - find all groups with less or equal students number. \n" +
        "type \"b\" - find all students related to the course with given name. \n" +
        "type \"c\" - add new student. \n" +
        "type \"d\" - delete student by student id. \n" +
        "type \"e\" - add a student to the course(from a list). \n" +
        "type \"f\" - remove the student from one of their course. \n" +
        "type \"exit\" - to stop the application. \n";
    public static final String enteredDataInvalidMessage = "Entered data is not valid.";
    public static final String noGroupWithEnteredStudentsNumberMessage = "There are no groups with such student number.";
    public static final String groupHaveEnteredStudentsNumberMessage = "Next group got %d students or less:";
    public static final String enterStudentsNumberMessage = "Enter students number:";
    public static final String enterCourseNameMessage = "Enter course name:";
    public static final String studentListOfEnteredCourseMessage = "Next students related to the course %s :\n";
    public static final String studentInputMessage = "Please enter student name and surname divided by space:";
    public static final String backToMenuMessage = "Choose an option from menu or type \"exit\" to quit:";
    public static final String studentsNameInvalidMessage = "Entered student name and surname is invalid.";
    public static final String studentIdInvalid = "Student id is invalid.";
    public static final String enterStudentIdMessage = "Enter student id:";
    public static final String chooseStudentFromIdMessage = "Choose student by Id from list above:";
    public static final String chooseCourseFromIdMessage = "Choose course by Id from list above:";
    public static final String courseToDeleteMessage = "Choose one course to delete by Id above:";
    public static final String studentDeleteMessage = "Student deleted from course.";
    public static final String studentAddedMessage = "Student assigned do course successfully.";
    public static final String studentRemovedMessage = "Student removed.";
    public static final String noSuchStudentFoundMessage = "No such student found.";
    public static final String suchStudentAlreadyAssignedToCourse = "This student already assigned to chosen course.";
    public static final String noCourseAssignedToStudent = "No such course assigned to student.";
}
