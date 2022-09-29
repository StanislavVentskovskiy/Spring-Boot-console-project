package ua.com.foxminded.service;

public final class UserMenuMessages {
    private final String initialMenuMessage = "Data initiated please choose request from list below: \n" +
        "type \"a\" - find all groups with less or equal students number \n" +
        "type \"b\" - find all students related to the course with given name \n" +
        "type \"c\" - add new student \n" +
        "type \"d\" - delete student by STUDENT_ID \n" +
        "type \"e\" - add a student to the course(from a list) \n" +
        "type \"f\" - remove the student from one of their course \n" +
        "type \"exit\" - to stop the application. \n";
    private final String enteredDataInvalidMessage = "Entered data is not valid";
    private final String noGroupWithEnteredStudentsNumberMessage = "There are no groups with such student number.";
    private final String groupHaveEnteredStudentsNumberMessage = "Next group got %d students or less:";
    private final String enterStudentsNumberMessage = "Enter students number:";
   private final String enterCourseNameMessage = "Enter course name:";
    private final String studentListOfEnteredCourseMessage = "Next students related to the course %s :\n";
    private final String studentInputMessage = "Please enter student name and surname divided by space:";
    private final String backToMenuMessage = "Choose an option from menu or type \"exit\" to quit:";
    private final String studentsNameInvalidMessage = "Entered student name and surname is invalid";
    private final String studentIdInvalid = "Student id is invalid.";
    private final String enterStudentIdMessage = "Enter student id:";
    private final String chooseStudentFromIdMessage = "Choose student by Id from list above:";
    private final String chooseCourseFromIdMessage = "Choose course by Id from list above:";
    private final String studentAssignedMessage = "Student assigned do course successfully.";
    private final String courseToDeleteMessage = "Choose one course to delete by Id above:";
    private final String studentDeleteMessage = "Student deleted from course.";
    private final String studentAddedMessage = "Student added to list.";
    private final String studentRemovedMessage = "Student removed.";

    public String getInitialMenuMessage() {
        return initialMenuMessage;
    }

    public String getEnteredDataInvalidMessage() {
        return enteredDataInvalidMessage;
    }

    public String getGroupHaveEnteredStudentsNumberMessage(int studentNumber){
        return String.format(groupHaveEnteredStudentsNumberMessage, studentNumber);
    }

    public String getNoGroupWithEnteredStudentsNumberMessage() {
        return noGroupWithEnteredStudentsNumberMessage;
    }

    public String getEnterStudentsNumberMessage() {
        return enterStudentsNumberMessage;
    }

    public String getEnterCourseNameMessage() {
        return enterCourseNameMessage;
    }

    public String getStudentListOfEnteredCourseMessage(String courseName) {
        return String.format(studentListOfEnteredCourseMessage, courseName);
    }

    public String getStudentInputMessage() {
        return studentInputMessage;
    }

    public String getBackToMenuMessage() {
        return backToMenuMessage;
    }

    public String getStudentsNameInvalidMessage() {
        return studentsNameInvalidMessage;
    }

    public String getStudentIdInvalid() {
        return studentIdInvalid;
    }

    public String getEnterStudentIdMessage() {
        return enterStudentIdMessage;
    }

    public String getChooseStudentFromIdMessage() {
        return chooseStudentFromIdMessage;
    }

    public String getChooseCourseFromIdMessage() {
        return chooseCourseFromIdMessage;
    }

    public String getStudentAssignedMessage() {
        return studentAssignedMessage;
    }

    public String getCourseToDeleteMessage() {
        return courseToDeleteMessage;
    }

    public String getStudentDeleteMessage() {
        return studentDeleteMessage;
    }

    public String getStudentAddedMessage() {
        return studentAddedMessage;
    }

    public String getStudentRemovedMessage() {
        return studentRemovedMessage;
    }
}
