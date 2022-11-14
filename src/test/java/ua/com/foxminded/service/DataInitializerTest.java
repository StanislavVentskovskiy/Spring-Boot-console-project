package ua.com.foxminded.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.foxminded.dao.impl.CourseDaoImpl;
import ua.com.foxminded.dao.impl.GroupDaoImpl;
import ua.com.foxminded.dao.impl.StudentsDaoImpl;
import ua.com.foxminded.util.DataInitializer;
import ua.com.foxminded.util.StudentsCoursesAssignation;
import ua.com.foxminded.util.StudentsGroupsAssignation;
import ua.com.foxminded.util.generator.CourseGenerator;
import ua.com.foxminded.util.generator.GroupsGenerator;
import ua.com.foxminded.util.generator.StudentsGenerator;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataInitializer.class)
public class DataInitializerTest {

    @MockBean
    JdbcTemplate jdbcTemplate;

    @MockBean
    private GroupsGenerator groupGenerator;

    @MockBean
    private GroupDaoImpl groupDao;

    @MockBean
    private CourseGenerator courseGenerator;

    @MockBean
    private CourseDaoImpl courseDao;

    @MockBean
    private StudentsGenerator studentsGenerator;

    @MockBean
    private StudentsDaoImpl studentsDaoImpl;

    @MockBean
    private StudentsGroupsAssignation studentAssignation;

    @MockBean
    private StudentsCoursesAssignation studentsCoursesAssignation;

    @Autowired
    DataInitializer dataInitializer;

    @Before
    public void initTestData(){
        dataInitializer.initializeApplicationData();
    }

    @Test
    public void testInsertGroupMethod_shouldBeCalledOnce(){
        verify(groupDao, Mockito.times(1)).addGroupList(Mockito.any());
    }

    @Test
    public void testInsertCourseMethod_shouldBeCalledOnce(){
        verify(courseDao, Mockito.times(1)).addCourseList(Mockito.any());
    }

    @Test
    public void testAssignStudentMethod_shouldBeCalledOnce(){
        verify(studentAssignation, Mockito.times(1)).assignStudentsToGroups();
    }

    @Test
    public void testStudentInsertionMethod_shouldBeCalledOnce(){
        verify(studentsDaoImpl, Mockito.times(1)).addStudentList(Mockito.any());
    }

    @Test
    public void testStudentCourseAssignationMethod_shouldReturnOnce(){
        verify(studentsCoursesAssignation, Mockito.times(1)).assignCoursesToStudent();
    }
}
