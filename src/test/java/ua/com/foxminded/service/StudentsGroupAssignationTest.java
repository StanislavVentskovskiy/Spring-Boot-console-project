package ua.com.foxminded.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.foxminded.model.Student;
import ua.com.foxminded.reader.DataReader;
import ua.com.foxminded.util.StudentsGenerator;
import java.util.ArrayList;

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
        Assert.assertTrue( (Integer) studentsGroupsAssignation.getStudentsList().get(1).getGroup() != null);
    }
}
