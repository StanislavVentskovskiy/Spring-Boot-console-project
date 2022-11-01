package ua.com.foxminded.util;

import org.springframework.stereotype.Service;
import ua.com.foxminded.model.Student;
import java.util.ArrayList;
import java.util.stream.IntStream;

@Service
public class StudentsGroupsAssignation {
    private ArrayList<Student> studentsList = new ArrayList<>();
    private int studentsWithNoGroup = 170;

    public ArrayList<Student> getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(ArrayList<Student> studentsList) {
        this.studentsList = studentsList;
    }

    public void assignStudentsToGroups(){
        int firstStudent = 0;
        int lastStudent = 0;
        int generatedStudentsNumber;
        for(int index = 1; index <= 10; index++) {
            if(lastStudent < studentsWithNoGroup) {
                generatedStudentsNumber = generateStudentsNumber();
                lastStudent = lastStudent + generatedStudentsNumber;
                insertStudentInRange(index, firstStudent, lastStudent);
                firstStudent = firstStudent + generatedStudentsNumber;
            }
        }
    }

    private void insertStudentInRange(int group, int startIndex, int endIndex) {
        IntStream.range(startIndex,endIndex).forEach(studentIndex ->
            studentsList.get(studentIndex).setGroup(group)
        );
    }

    private int generateStudentsNumber() {
        int studentsInGroup = (int)(Math.random()*(30-10))+10;

        return studentsInGroup;
    }
}
