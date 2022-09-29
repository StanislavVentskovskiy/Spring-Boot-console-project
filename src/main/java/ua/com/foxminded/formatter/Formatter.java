package ua.com.foxminded.formatter;

import ua.com.foxminded.model.Student;
import ua.com.foxminded.model.Course;
import ua.com.foxminded.service.UserMenuMessages;
import java.util.ArrayList;

public class Formatter {

    UserMenuMessages userMenuMessages = new UserMenuMessages();

    public void showInitialMenu(){
        System.out.println(userMenuMessages.getInitialMenuMessage());
    }

    public void showMessageEnteredDataIsInvalid(){
        System.out.println(userMenuMessages.getEnteredDataInvalidMessage());
    }

    public void formatGroupListOutput(int studentsNumber, ArrayList<String> groupsList) {
        if (!groupsList.isEmpty()) {
            System.out.println(userMenuMessages.getGroupHaveEnteredStudentsNumberMessage(studentsNumber));
            formatListOfDataToColumn(groupsList);
        } else {
            System.out.println(userMenuMessages.getNoGroupWithEnteredStudentsNumberMessage());
        }
    }

    public void showMessageEnterStudentNumber() {
        System.out.println(userMenuMessages.getEnterStudentsNumberMessage());
    }

    public void showMessageEnterCourseName() {
        System.out.println(userMenuMessages.getEnterCourseNameMessage());
    }

    public void formatStudentsListOfChosenCourse(String courseName, ArrayList<String> studentsList){
        System.out.println(userMenuMessages.getStudentListOfEnteredCourseMessage(courseName));
        formatListOfDataToColumn(studentsList);
    }

    public void showMessageStudentInput() {
        System.out.println(userMenuMessages.getStudentInputMessage());
    }

    public void showBackToMenuMessage(){
        System.out.println(userMenuMessages.getBackToMenuMessage());
    }

    public void showMessageStudentNameAndSurnameInvalid() {
        System.out.println(userMenuMessages.getStudentsNameInvalidMessage());
    }

    public void showMessageStudentIdIsInvalid() {
        userMenuMessages.getStudentIdInvalid();
    }

    public  void showInsertStudentIdText() {
        System.out.println(userMenuMessages.getEnterStudentIdMessage());
    }

    public void showMessageChooseStudentById() {
        System.out.println(userMenuMessages.getChooseStudentFromIdMessage());
    }

    public void showStudentsList(ArrayList<Student> students) {
        for(int index = 0; index < students.size(); index++) {
           String studentOutput = students.get(index).getId() + ". " + students.get(index).getName() + " " +students.get(index).getSurname();
            System.out.println(studentOutput);
        }
    }

    public void showMessageChooseCourseById() {
        System.out.println(userMenuMessages.getChooseCourseFromIdMessage());
    }

    public void showCoursesList(ArrayList<Course> courses) {
        for(int index = 0; index < courses.size(); index++) {
            String studentOutput = courses.get(index).getCourseId() + ". " + courses.get(index).getName() + " " + courses.get(index).getCourseDescription();
            System.out.println(studentOutput);
        }
    }

    public void showMessageStudentAddedToCourse(){
        System.out.println(userMenuMessages.getStudentAssignedMessage());
    }

    public void showMessageToChooseCourseById() {
        System.out.println(userMenuMessages.getCourseToDeleteMessage());
    }
    public void showMessageStudentWasRemovedFromCourse() {
        System.out.println(userMenuMessages.getStudentDeleteMessage());
    }

    public void getCourseNameList(ArrayList<Course> courseList) {
        ArrayList<String> courseNameList = new ArrayList<>();
        for(int index = 0; index < courseList.size(); index++){
            courseNameList.add(courseList.get(index).getName());
        }
        formatListOfDataToColumn(courseNameList);
    }

    public void showMessageStudentAdded(){
        System.out.println(userMenuMessages.getStudentAddedMessage());
    }

    public void showMessageStudentDeleted(){
        System.out.println(userMenuMessages.getStudentRemovedMessage());
    }

    private void formatListOfDataToColumn(ArrayList<String> dataList){
        for(int index = 0; index < dataList.size(); index++) {
            System.out.println(dataList.get(index));
        }
    }
}

