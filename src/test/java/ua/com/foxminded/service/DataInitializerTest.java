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
import ua.com.foxminded.dao.impl.GroupDaoImplTest;
import ua.com.foxminded.dao.impl.StudentsDaoImpl;
import ua.com.foxminded.util.CourseGenerator;
import ua.com.foxminded.util.GroupsGenerator;
import ua.com.foxminded.util.StudentsGenerator;

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
        Mockito.verify(groupDao, Mockito.times(1)).insertGroupList(Mockito.any());
    }

    @Test
    public void testInsertCourseMethod_shouldBeCalledOnce(){
        Mockito.verify(courseDao, Mockito.times(1)).insertCourseList(Mockito.any());
    }

    @Test
    public void testAssignStudentMethod_shouldBeCalledOnce(){
        Mockito.verify(studentAssignation, Mockito.times(1)).assignStudentsToGroups();
    }

    @Test
    public void testStudentInsertionMethod_shouldBeCalledOnce(){
        Mockito.verify(studentsDaoImpl, Mockito.times(1)).insertStudentsList(Mockito.any());
    }

    @Test
    public void testStudentCourseAssignationMethod_shouldReturnOnce(){
        Mockito.verify(studentsCoursesAssignation, Mockito.times(1)).assignCoursesToStudent();
    }
}
