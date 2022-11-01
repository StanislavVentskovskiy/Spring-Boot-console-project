package ua.com.foxminded.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.foxminded.model.Student;
import ua.com.foxminded.reader.DataReader;
import ua.com.foxminded.util.generator.StudentsGenerator;
import ua.com.foxminded.util.StudentsGroupsAssignation;
import java.util.ArrayList;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StudentsGroupsAssignation.class, StudentsGenerator.class, DataReader.class})
public class StudentsGroupAssignationTest {
    private ArrayList<Student> testStudents = new ArrayList<>();

    @Autowired
    StudentsGroupsAssignation studentsGroupsAssignation;

    @Autowired
    StudentsGenerator studentsGenerator;

    @Before
    public void initTestData(){
        testStudents = studentsGenerator.generateStudentsList();
        studentsGroupsAssignation.setStudentsList(testStudents);
    }

    @Test
    public void testAddGroupFieldToStudentList_shouldReturnStudentWithGroup(){
        assertTrue( (Integer) studentsGroupsAssignation.getStudentsList().get(1).getGroup() != null);
    }
}
