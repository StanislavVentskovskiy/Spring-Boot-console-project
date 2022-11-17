package ua.com.foxminded.dao.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.foxminded.Main;
import ua.com.foxminded.model.Group;
import ua.com.foxminded.model.Student;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@Sql(scripts = {"classpath:test-schema.sql", "classpath:test-data.sql"})
@SpringBootTest
public class StudentDaoTest {

    @MockBean
    private Main main;

    @Autowired
    private StudentsDaoImpl studentsDaoImpl;

    private Student testStudent;
    private ArrayList<Integer> expectedStudentIdList = new ArrayList<>();
    private int testStudentNumber;
    private int testStudentId;
    private Student expectedTestStudent;
    private Student actualTestStudent;
    private boolean actualRemoveStatus;
    private boolean expectedRemoveStatus;

    @Before
    public void initTestData(){
        testStudentNumber = 2;
        testStudentId = 1;
        testStudent = new Student("test","test");
        expectedStudentIdList.add(1);
        actualTestStudent = new Student("name","surname");
        expectedRemoveStatus = true;
    }

    @Test
    public void testInsertStudent_shouldReturnCorrectStatus(){
        actualTestStudent = studentsDaoImpl.addStudent(testStudent);

        assertEquals(actualTestStudent, testStudent);
    }

    @Test
    public void testGetGroupsWithEqualOrLessStudentNumber_shouldReturnTestGroup(){
        ArrayList<Group> testGroup = studentsDaoImpl.getGroupsWithEqualOrLessStudentsNumber(testStudentNumber);

        assertTrue(!(testGroup.isEmpty()));
    }

    @Test
    public void testGetStudentList(){
        ArrayList<Student> testStudentList = studentsDaoImpl.getStudentsList();
        expectedTestStudent = testStudentList.get(0);

        assertTrue(expectedTestStudent.equals(actualTestStudent));
    }

    @Test
    public void testDeleteStudentById_shouldReturnCorrectStatus(){
        actualRemoveStatus = studentsDaoImpl.removeStudentById(testStudentId);

        assertTrue(actualRemoveStatus == expectedRemoveStatus);
    }
}
