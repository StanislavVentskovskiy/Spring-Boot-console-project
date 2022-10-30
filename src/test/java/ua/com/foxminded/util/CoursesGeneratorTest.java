package ua.com.foxminded.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.foxminded.model.Course;
import ua.com.foxminded.reader.DataReader;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CourseGenerator.class, DataReader.class})
public class CoursesGeneratorTest {

    @Value("${test.data.directory}")
    private String testCourseAndDescriptionPath;

    @Autowired
    private CourseGenerator courseGenerator;

    private ArrayList<Course> actualCourseList = new ArrayList<>();
    private ArrayList<Course> expectedCourseList = new ArrayList<>();
    private Course actualCourse;
    private Course expectedCourse;

    @Before
    public void initTestData(){
        courseGenerator.setCourseAndDescriptionPath(testCourseAndDescriptionPath);
        expectedCourse = new Course("test", "test");
        expectedCourseList.add(expectedCourse);
    }

    @Test
    public void testGenerateCourseList_shouldReturnObjectWithSameFields(){
        actualCourseList = courseGenerator.generateCourseList();
        actualCourse = actualCourseList.get(0);

        Assert.assertTrue(expectedCourse.equals(actualCourse));
    }
}
