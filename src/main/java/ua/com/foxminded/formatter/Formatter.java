package ua.com.foxminded.formatter;

import ua.com.foxminded.model.Student;
import ua.com.foxminded.model.Course;
import ua.com.foxminded.service.UserMenuMessages;
import java.util.ArrayList;
import java.util.List;

public class Formatter {
    private UserMenuMessages userMenuMessages = new UserMenuMessages();
    public void showInitialMenu(){
        System.out.println(userMenuMessages.initialMenuMessage);
    }
    public void showMessageEnteredDataIsInvalid(){
        System.out.println(userMenuMessages.enteredDataInvalidMessage);
    }

    public void formatGroupListOutput(int studentsNumber, ArrayList<String> groupsList) {
        if (!groupsList.isEmpty()) {
            System.out.println(String.format(userMenuMessages.groupHaveEnteredStudentsNumberMessage, studentsNumber));
            formatListOfDataToColumn(groupsList);
        } else {
            System.out.println(userMenuMessages.noGroupWithEnteredStudentsNumberMessage);
        }
    }

    public void showMessageEnterStudentNumber() {
        System.out.println(userMenuMessages.enterStudentsNumberMessage);
    }

    public void showMessageEnterCourseName() {
        System.out.println(userMenuMessages.enterCourseNameMessage);
    }

    public void formatStudentsListOfChosenCourse(String courseName, ArrayList<String> studentsList){
        System.out.println(String.format(userMenuMessages.studentListOfEnteredCourseMessage,courseName));
        formatListOfDataToColumn(studentsList);
    }

    public void showMessageStudentInput() {
        System.out.println(userMenuMessages.studentInputMessage);
    }

    public void showBackToMenuMessage(){
        System.out.println(userMenuMessages.backToMenuMessage);
    }

    public void showMessageStudentNameAndSurnameInvalid() {
        System.out.println(userMenuMessages.studentsNameInvalidMessage);
    }

    public void showMessageStudentIdIsInvalid() {
        System.out.println(userMenuMessages.studentIdInvalid);
    }

    public  void showInsertStudentIdText() {
        System.out.println(userMenuMessages.enterStudentIdMessage);
    }

    public void showMessageChooseStudentById() {
        System.out.println(userMenuMessages.chooseStudentFromIdMessage);
    }

    public void showStudentsList(List<Student> students) {
        for(int index = 0; index < students.size(); index++) {
           String studentOutput = students.get(index).getId() + ". " + students.get(index).getName() + " " +students.get(index).getSurname();
            System.out.println(studentOutput);
        }
    }

    public void showMessageChooseCourseById() {
        System.out.println(userMenuMessages.chooseCourseFromIdMessage);
    }

    public void showCoursesList(ArrayList<Course> courses) {
        for(int index = 0; index < courses.size(); index++) {
            String studentOutput = courses.get(index).getCourseId() + ". " + courses.get(index).getName() + " " + courses.get(index).getCourseDescription();
            System.out.println(studentOutput);
        }
    }

    public void showMessageToChooseCourseById() {
        System.out.println(userMenuMessages.courseToDeleteMessage);
    }

    public void showMessageStudentWasRemovedFromCourse() {
        System.out.println(userMenuMessages.studentDeleteMessage);
    }

    public void getCourseNameList(ArrayList<Course> courseList) {
        ArrayList<String> courseNameList = new ArrayList<>();
        for(int index = 0; index < courseList.size(); index++){
            courseNameList.add(courseList.get(index).getName());
        }
        formatListOfDataToColumn(courseNameList);
    }

    public void showMessageStudentAdded(){
        System.out.println(userMenuMessages.studentAddedMessage);
    }

    public void showMessageStudentDeleted(){
        System.out.println(userMenuMessages.studentRemovedMessage);
    }

    private void formatListOfDataToColumn(ArrayList<String> dataList){
        for(int index = 0; index < dataList.size(); index++) {
            System.out.println(dataList.get(index));
        }
    }

    public void showMessageNoSuchStudentFound(){
        System.out.println(userMenuMessages.noSuchStudentFoundMessage);
    }

    public void showMessageStudentAlreadyAssignedToCourse(){
        System.out.println(userMenuMessages.suchStudentAlreadyAssignedToCourse);
    }

    public void showMessageNoCourseFoundAssignedToCurrentStudent(){
        System.out.println(userMenuMessages.noCourseAssignedToStudent);
    }
}
