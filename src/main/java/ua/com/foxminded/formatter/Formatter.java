package ua.com.foxminded.formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.model.Student;
import ua.com.foxminded.model.Course;
import ua.com.foxminded.util.UserMenuMessages;
import java.util.ArrayList;
import java.util.List;
import static ua.com.foxminded.controller.LoggerController.LOG;

@Service
public class Formatter {

    @Autowired
    private UserMenuMessages userMenuMessages;

    public void showInitialMenu(){
        LOG.info("Enter method showInitialMenu()");
        System.out.println(userMenuMessages.initialMenuMessage);
        LOG.info("Leave method showInitialMenu()");
    }

    public void showMessageEnteredDataIsInvalid(){
        LOG.info("Enter method showMessageEnteredDataIsInvalid()");
        System.out.println(userMenuMessages.enteredDataInvalidMessage);
        LOG.info("Leave method showMessageEnteredDataIsInvalid()");
    }

    public void formatGroupListOutput(int studentsNumber, ArrayList<String> groupsList) {
        LOG.info("Enter method formatGroupListOutput()");
        if (!groupsList.isEmpty()) {
            System.out.println(String.format(userMenuMessages.groupHaveEnteredStudentsNumberMessage, studentsNumber));
            formatListOfDataToColumn(groupsList);
            LOG.info("Leave method formatGroupListOutput()");
        } else {
            System.out.println(userMenuMessages.noGroupWithEnteredStudentsNumberMessage);
            LOG.info("Leave method formatGroupListOutput()");
        }
    }

    public void showMessageEnterStudentNumber() {
        LOG.info("Enter method showMessageEnterStudentNumber()");
        System.out.println(userMenuMessages.enterStudentsNumberMessage);
        LOG.info("Leave method showMessageEnterStudentNumber()");
    }

    public void showMessageEnterCourseName() {
        LOG.info("Enter method showMessageEnterCourseName()");
        System.out.println(userMenuMessages.enterCourseNameMessage);
        LOG.info("Leave method showMessageEnterCourseName()");
    }

    public void formatStudentsListOfChosenCourse(String courseName, ArrayList<String> studentsList){
        LOG.info("Enter method formatStudentsListOfChosenCourse()");
        System.out.println(String.format(userMenuMessages.studentListOfEnteredCourseMessage,courseName));
        formatListOfDataToColumn(studentsList);
        LOG.info("Leave method formatStudentsListOfChosenCourse()");
    }

    public void showMessageStudentInput() {
        LOG.info("Enter method showMessageStudentInput()");
        System.out.println(userMenuMessages.studentInputMessage);
        LOG.info("Leave method showMessageStudentInput()");
    }

    public void showBackToMenuMessage(){
        LOG.info("Enter method showBackToMenuMessage()");
        System.out.println(userMenuMessages.backToMenuMessage);
        LOG.info("Leave method showBackToMenuMessage()");
    }

    public void showMessageStudentNameAndSurnameInvalid() {
        LOG.info("Enter method showMessageStudentNameAndSurnameInvalid()");
        System.out.println(userMenuMessages.studentsNameInvalidMessage);
        LOG.info("Leave method showMessageStudentNameAndSurnameInvalid()");
    }

    public void showMessageStudentIdIsInvalid() {
        LOG.info("Enter method showMessageStudentIdIsInvalid()");
        System.out.println(userMenuMessages.studentIdInvalid);
        LOG.info("Leave method showMessageStudentIdIsInvalid()");
    }

    public  void showInsertStudentIdText() {
        LOG.info("Enter method showInsertStudentIdText()");
        System.out.println(userMenuMessages.enterStudentIdMessage);
        LOG.info("Leave method showInsertStudentIdText()");
    }

    public void showMessageChooseStudentById() {
        LOG.info("Enter method showMessageChooseStudentById()");
        System.out.println(userMenuMessages.chooseStudentFromIdMessage);
        LOG.info("Leave method showMessageChooseStudentById()");
    }

    public void showStudentsList(List<Student> students) {
        LOG.info("Enter method showStudentsList");
        for(int index = 0; index < students.size(); index++) {
           String studentOutput = students.get(index).getId() + ". " + students.get(index).getName() + " " +students.get(index).getSurname();
            System.out.println(studentOutput);
        }
        LOG.info("Leave method showStudentsList");
    }

    public void showMessageChooseCourseById() {
        LOG.info("Enter method showMessageChooseCourseById()");
        System.out.println(userMenuMessages.chooseCourseFromIdMessage);
        LOG.info("Leave method showMessageChooseCourseById()");
    }

    public void showCoursesList(ArrayList<Course> courses) {
        LOG.info("Enter method showCoursesList()");
        for(int index = 0; index < courses.size(); index++) {
            String studentOutput = courses.get(index).getCourseId() + ". " + courses.get(index).getName() + " " + courses.get(index).getCourseDescription();
            System.out.println(studentOutput);
        }
        LOG.info("Leave method showCoursesList()");
    }

    public void showMessageToChooseCourseById() {
        LOG.info("Enter method showMessageToChooseCourseById()");
        System.out.println(userMenuMessages.courseToDeleteMessage);
        LOG.info("Leave method showMessageToChooseCourseById()");
    }

    public void showMessageStudentWasRemovedFromCourse() {
        LOG.info("Enter method showMessageStudentWasRemovedFromCourse()");
        System.out.println(userMenuMessages.studentDeleteMessage);
        LOG.info("Leave method showMessageStudentWasRemovedFromCourse()");
    }

    public void showMessageStudentAdded(){
        LOG.info("Enter method showMessageStudentAdded()");
        System.out.println(userMenuMessages.studentAddedMessage);
        LOG.info("Leave method showMessageStudentAdded()");
    }

    public void showMessageStudentDeleted(){
        LOG.info("Enter method showMessageStudentDeleted()");
        System.out.println(userMenuMessages.studentRemovedMessage);
        LOG.info("Leave method showMessageStudentDeleted()");
    }

    private void formatListOfDataToColumn(ArrayList<String> dataList){
        LOG.info("Enter method formatListOfDataToColumn()");
        for(int index = 0; index < dataList.size(); index++) {
            System.out.println(dataList.get(index));
        }
        LOG.info("Leave method formatListOfDataToColumn()");
    }

    public void showMessageNoSuchStudentFound(){
        LOG.info("Enter method showMessageNoSuchStudentFound()");
        System.out.println(userMenuMessages.noSuchStudentFoundMessage);
        LOG.info("Leave method showMessageNoSuchStudentFound()");
    }

    public void showMessageStudentAlreadyAssignedToCourse(){
        LOG.info("Enter method showMessageStudentAlreadyAssignedToCourse()");
        System.out.println(userMenuMessages.suchStudentAlreadyAssignedToCourse);
        LOG.info("Leave method showMessageStudentAlreadyAssignedToCourse()");
    }

    public void showMessageNoCourseFoundAssignedToCurrentStudent(){
        LOG.info("Enter method showMessageNoCourseFoundAssignedToCurrentStudent()");
        System.out.println(userMenuMessages.noCourseAssignedToStudent);
        LOG.info("Leave method showMessageStudentAlreadyAssignedToCourse()");
    }

    public void studentAddedToStudentsList(){
        LOG.info("Enter method studentAddedToStudentsList()");
        System.out.println(userMenuMessages.studentAddedToStudentsList);
        LOG.info("Leave method studentAddedToStudentsList()");
    }
}
