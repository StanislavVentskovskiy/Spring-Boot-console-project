package ua.com.foxminded.service.validaton;

import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class ApplicationMenuValidator {

    public boolean validateIntegerInput(String input){
        try {
            int number = Integer.parseInt(input);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean validateCourseName(ArrayList<String> studentsList) {
        if (!studentsList.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    public boolean validateStudentInsert(int studentInsertingResult){
        if (studentInsertingResult == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateStudentDelete(int studentDeleteResult){
        if (studentDeleteResult == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateStudentId(int studentId, ArrayList<Integer> studentIdList){
        if(studentIdList.contains(studentId)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateDeleteStudentFromCourse(int deleteResult){
        if (deleteResult == 1) {
            return true;
        } else {
            return false;
        }
    }
}
