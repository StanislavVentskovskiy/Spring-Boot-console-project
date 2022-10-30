package ua.com.foxminded.dao.impl;

import org.junit.Assert;
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

@RunWith(SpringRunner.class)
@Sql(scripts = {"classpath:test-schema.sql", "classpath:test-data.sql"})
@SpringBootTest
public class StudentDaoTest {

    @MockBean
    private Main main;

    @Autowired
    private StudentsDaoImpl studentsDaoImpl;

    private int actual;
    private int expected;
    private Student testStudent;
    private ArrayList<Integer> expectedStudentIdList = new ArrayList<>();
    private int testStudentNumber;
    private int testStudentId;
    private ArrayList<Integer> actualStudentIdList;
    private Student expectedTestStudent;
    private Student actualTestStudent;

    @Before
    public void initTestData(){
        expected = 1;
        testStudentNumber = 2;
        testStudentId = 1;
        testStudent = new Student("test","test");
        expectedStudentIdList.add(1);
        actualTestStudent = new Student("name","surname");
    }

    @Test
    public void testInsertStudent_shouldReturnCorrectStatus(){
        actual = studentsDaoImpl.insertStudent(testStudent);

        Assert.assertTrue(actual == expected);
    }

    @Test
    public void testGetStudentIdList_shouldReturnCorrectList(){
        actualStudentIdList = studentsDaoImpl.getStudentsIdList();
        actual = actualStudentIdList.get(0);

        Assert.assertTrue(actual == expected);
    }

    @Test
    public void testGetGroupsWithEqualOrLessStudentNumber_shouldReturnTestGroup(){
        ArrayList<Group> testGroup = studentsDaoImpl.getGroupsWithEqualOrLessStudentsNumber(testStudentNumber);

        Assert.assertTrue(!(testGroup.isEmpty()));
    }

    @Test
    public void testGetStudentList(){
        ArrayList<Student> testStudentList = studentsDaoImpl.getStudentsList();
        expectedTestStudent = testStudentList.get(0);

        Assert.assertTrue(expectedTestStudent.equals(actualTestStudent));
    }

    @Test
    public void testDeleteStudentById_shouldReturnCorrectStatus(){
        actual = studentsDaoImpl.deleteStudentById(testStudentId);

        Assert.assertTrue(actual == expected);
    }
}
