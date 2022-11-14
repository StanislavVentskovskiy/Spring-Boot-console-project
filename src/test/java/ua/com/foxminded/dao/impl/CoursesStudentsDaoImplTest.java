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
import ua.com.foxminded.model.Course;
import ua.com.foxminded.model.Student;
import java.util.ArrayList;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@Sql(scripts = {"classpath:test-schema.sql", "classpath:test-data.sql"})
@SpringBootTest
public class CoursesStudentsDaoImplTest {

    @MockBean
    private Main main;

    @Autowired
    CoursesStudentsDaoImpl coursesStudentsDaoImpl;

    private int testStudentId;
    private int testCourseId;
    private int expectedStatus;
    private int actualStatus;
    private String testStudentName;
    private String testStudentSurname;
    private String testCourseName;
    private ArrayList<Student> testStudentArray;
    private ArrayList<Student> testStudentArrayToGetNamesAndSurnames = new ArrayList<>();
    private String expectedStudentNameAndSurname;
    private String actualStudentNameAndSurname;
    private ArrayList<String> testStudentStringArray;
    private Student actualStudent;
    private Student expectedStudent;
    private ArrayList<Course> testCourseList;
    private Course expectedCourse;
    private Course actualCourse;
    private ArrayList<Integer> testCourseIdList;
    private int actualTestCourseId;

    @Before
    public void initTestData(){
        testStudentId = 1;
        testCourseId = 1;
        expectedStatus = 1;
        testStudentName = "testName";
        testStudentSurname = "testSurname";
        testCourseName = "testCourseName";
        expectedStudentNameAndSurname = "testName testSurname";
        testStudentArrayToGetNamesAndSurnames.add(new Student(testStudentName,testStudentSurname));
        expectedStudent = new Student("name","surname");
        expectedCourse = new Course("testCourseName","testCourseDescription");
    }

    @Test
    public void testInsertStudentAndCourseTest_shouldReturnCorrectStatus(){
        actualStatus = coursesStudentsDaoImpl.addStudentAndCourse(testStudentId, testCourseId);

        assertTrue(expectedStatus == actualStatus);
    }

    @Test
    public void testGetStudentsListRelatedToCourseByName_shouldReturnStudent(){
        testStudentArray = coursesStudentsDaoImpl.getStudentsListRelatedToCourseByName(testCourseName);
        actualStudent = testStudentArray.get(0);

        assertTrue(actualStudent.equals(expectedStudent));
    }

    @Test
    public void testGetStudentNamesAndSurnames_shouldReturnCorrectString(){
        testStudentStringArray = coursesStudentsDaoImpl.getStudentsNamesAndSurnamesList(testStudentArrayToGetNamesAndSurnames);
        actualStudentNameAndSurname = testStudentStringArray.get(0);

        assertTrue(expectedStudentNameAndSurname.equals(actualStudentNameAndSurname));
    }

    @Test
    public void testGetCourseListByStudentId_shouldReturnCourse(){
        testCourseList = coursesStudentsDaoImpl.getCourseListByStudentId(testStudentId);
        actualCourse = testCourseList.get(0);

        assertTrue(actualCourse.equals(expectedCourse));
    }

    @Test
    public void testGetCoursesIdListByStudent_shouldReturnCourseId(){
        testCourseIdList = coursesStudentsDaoImpl.getCoursesIdListByStudent(testStudentId);
        actualTestCourseId = testCourseIdList.get(0);

        assertTrue(testCourseId == actualTestCourseId);
    }
}
