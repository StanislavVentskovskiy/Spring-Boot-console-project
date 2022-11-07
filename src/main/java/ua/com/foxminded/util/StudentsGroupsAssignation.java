package ua.com.foxminded.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.com.foxminded.model.Student;
import java.util.ArrayList;
import java.util.stream.IntStream;

@Service
public class StudentsGroupsAssignation {
    private ArrayList<Student> studentsList = new ArrayList<>();
    private int studentsWithNoGroup = 170;
    private static final Logger LOG = LoggerFactory.getLogger(StudentsGroupsAssignation.class);

    public ArrayList<Student> getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(ArrayList<Student> studentsList) {
        this.studentsList = studentsList;
    }

    public void assignStudentsToGroups(){
        LOG.info("Enter method assignStudentsToGroups()");
        int firstStudent = 0;
        int lastStudent = 0;
        int generatedStudentsNumber;
        for(int index = 1; index <= 10; index++) {
            if(lastStudent < studentsWithNoGroup) {
                generatedStudentsNumber = generateStudentsNumber();
                lastStudent = lastStudent + generatedStudentsNumber;
                insertStudentInRange(index, firstStudent, lastStudent);
                firstStudent = firstStudent + generatedStudentsNumber;
                LOG.info("Groups added to students");
            }
        }
        LOG.info("Leave method assignStudentsToGroups()");
    }

    private void insertStudentInRange(int group, int startIndex, int endIndex) {
        LOG.info("Enter method insertStudentInRange()");
        IntStream.range(startIndex,endIndex).forEach(studentIndex ->
            studentsList.get(studentIndex).setGroup(group)
        );
        LOG.info("Set " + group + " for student");
        LOG.info("Leave method insertStudentInRange()");
    }

    private int generateStudentsNumber() {
        LOG.info("Enter method generateStudentsNumber()");
        int studentsInGroup = (int)(Math.random()*(30-10))+10;
        LOG.info("Generated number of students in group");
        LOG.info("Leave method generateStudentsNumber()");

        return studentsInGroup;
    }
}
