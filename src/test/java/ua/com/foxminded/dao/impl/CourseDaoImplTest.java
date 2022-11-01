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
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@Sql(scripts = {"classpath:test-schema.sql", "classpath:test-data.sql"})
@SpringBootTest
public class CourseDaoImplTest {

    @MockBean
    Main main;

    @Autowired
    CourseDaoImpl courseDaoImpl;

    private int actualInsertStatus;
    private int expectedInsertStatus;
    private int expectedCourseId;
    private int actualCourseId;
    private Course testCourse;
    private ArrayList<Integer> testCourseIdList;
    private ArrayList<Course> testCourseList;
    private Course actualCourse;
    private Course expectedCourse;

    @Before
    public void initTestData(){
        expectedInsertStatus = 1;
        expectedCourseId = 1;
        actualCourseId = 1;
        testCourse = new Course("test","test");
        expectedCourse = new Course("testCourseName", "testCourseDescription");
    }

    @Test
    public void testInsertCourse_shouldReturnCorrectStatus(){
        actualInsertStatus = courseDaoImpl.insertCourse(testCourse);

        assertEquals(actualInsertStatus, expectedInsertStatus);
    }

    @Test
    public void testGetCourseIdList(){
        testCourseIdList = courseDaoImpl.getCoursesIdList();
        actualCourseId = testCourseIdList.get(0);

        assertEquals(expectedCourseId, actualCourseId);
    }

    @Test
    public void testGetCourseList(){
        testCourseList = courseDaoImpl.getCourseList();
        actualCourse = testCourseList.get(0);

        assertEquals(expectedCourse, actualCourse);
    }
}
