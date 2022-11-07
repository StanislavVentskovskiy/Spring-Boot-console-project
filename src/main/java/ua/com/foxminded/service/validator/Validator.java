package ua.com.foxminded.service.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class Validator {
    private static final Logger LOG = LoggerFactory.getLogger(Validator.class);

    public boolean validateIntegerInput(String input){
        LOG.info("Enter method validateIntegerInput()");
        try {
            int number = Integer.parseInt(input);
            LOG.info("Leave method validateIntegerInput()");
            return true;
        } catch (Exception e){
            LOG.warn("User integer input is invalid");
            LOG.info("Leave method validateIntegerInput()");
            return false;
        }
    }

    public boolean validateCourseName(ArrayList<String> studentsList) {
        LOG.info("Enter method validateCourseName()");
        if (!studentsList.isEmpty()) {
            LOG.info("Leave method validateCourseName()");
            return true;
        } else {
            LOG.info("Leave method validateCourseName()");
            return false;
        }
    }

    public boolean validateStudentInsert(int studentInsertingResult){
        LOG.info("Enter method validateStudentInsert()");
        if (studentInsertingResult == 1) {
            LOG.info("Leave method validateStudentInsert()");
            return true;
        } else {
            LOG.info("Leave method validateStudentInsert()");
            return false;
        }
    }

    public boolean validateStudentDelete(int studentDeleteResult){
        LOG.info("Enter method validateStudentDelete()");
        if (studentDeleteResult == 1) {
            LOG.info("Leave method validateStudentDelete()");
            return true;
        } else {
            LOG.info("Leave method validateStudentDelete()");
            return false;
        }
    }

    public boolean validateStudentId(int studentId, ArrayList<Integer> studentIdList){
        LOG.info("Entered  validateStudentId()");
        if(studentIdList.contains(studentId)) {
            LOG.info("Leave validateStudentId()");
            return true;
        } else {
            LOG.info("Leave validateStudentId()");
            return false;
        }

    }

    public boolean validateDeleteStudentFromCourse(int deleteResult){
        LOG.info("Enter validateDeleteStudentFromCourse()");
        if (deleteResult == 1) {
            LOG.info("Leave validateDeleteStudentFromCourse()");
            return true;
        } else {
            LOG.info("Leave validateDeleteStudentFromCourse()");
            return false;
        }
    }
}
