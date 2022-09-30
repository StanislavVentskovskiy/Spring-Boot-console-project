package ua.com.foxminded.service;

public final class UserMenuMessages {
    public final String initialMenuMessage = "Data initiated please choose request from list below: \n" +
        "type \"a\" - find all groups with less or equal students number \n" +
        "type \"b\" - find all students related to the course with given name \n" +
        "type \"c\" - add new student \n" +
        "type \"d\" - delete student by STUDENT_ID \n" +
        "type \"e\" - add a student to the course(from a list) \n" +
        "type \"f\" - remove the student from one of their course \n" +
        "type \"exit\" - to stop the application. \n";
    public final String enteredDataInvalidMessage = "Entered data is not valid";
    public final String noGroupWithEnteredStudentsNumberMessage = "There are no groups with such student number.";
    public final String groupHaveEnteredStudentsNumberMessage = "Next group got %d students or less:";
    public final String enterStudentsNumberMessage = "Enter students number:";
    public final String enterCourseNameMessage = "Enter course name:";
    public final String studentListOfEnteredCourseMessage = "Next students related to the course %s :\n";
    public final String studentInputMessage = "Please enter student name and surname divided by space:";
    public final String backToMenuMessage = "Choose an option from menu or type \"exit\" to quit:";
    public final String studentsNameInvalidMessage = "Entered student name and surname is invalid";
    public final String studentIdInvalid = "Student id is invalid.";
    public final String enterStudentIdMessage = "Enter student id:";
    public final String chooseStudentFromIdMessage = "Choose student by Id from list above:";
    public final String chooseCourseFromIdMessage = "Choose course by Id from list above:";
    public final String studentAssignedMessage = "Student assigned do course successfully.";
    public final String courseToDeleteMessage = "Choose one course to delete by Id above:";
    public final String studentDeleteMessage = "Student deleted from course.";
    public final String studentAddedMessage = "Student added to list.";
    public final String studentRemovedMessage = "Student removed.";
}
