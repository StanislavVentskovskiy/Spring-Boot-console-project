package ua.com.foxminded.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.foxminded.dao.impl.CoursesStudentsDaoImpl;
import ua.com.foxminded.util.StudentsCoursesAssignation;
import java.util.ArrayList;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StudentsCoursesAssignation.class, CoursesStudentsDaoImpl.class})
public class StudentCourseAssignationTest {
    private ArrayList<Integer> testStudentsIdList = new ArrayList<>();
    private ArrayList<Integer> testCourseIdList = new ArrayList<>();

    @MockBean
    private CoursesStudentsDaoImpl coursesStudentsDao;

    @Autowired
    private StudentsCoursesAssignation studentsCoursesAssignation;

    @Before
    public void insertTestData() {
        for (int index = 0; index <= 10; index++) {
            testCourseIdList.add(index);
        }
        studentsCoursesAssignation.setCoursesIdList(testCourseIdList);
    }

    @Before
    public void insertTestData2(){
        for (int index = 0; index <= 200; index++){
            testStudentsIdList.add(index);
        }
        studentsCoursesAssignation.setStudentsIdList(testStudentsIdList);
    }

    @Test
    public void testAssignCourseToStudent_shouldCallInsertDBMethod(){
        studentsCoursesAssignation.assignCoursesToStudent();
        verify(coursesStudentsDao, Mockito.atLeastOnce()).addStudentAndCourse(Mockito.anyInt(), Mockito.anyInt());
    }
}
