package ua.com.foxminded.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.foxminded.formatter.Formatter;
import ua.com.foxminded.service.impl.CourseStudentsServiceImpl;
import ua.com.foxminded.service.impl.CoursesServiceImpl;
import ua.com.foxminded.service.impl.StudentServiceImpl;
import ua.com.foxminded.service.validator.Validator;
import ua.com.foxminded.util.ApplicationMenu;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationMenu.class)
public class ApplicationMenuTest {

    @MockBean
    private Formatter formatter;

    @MockBean
    private StudentServiceImpl studentServiceImpl;

    @MockBean
    private CoursesServiceImpl coursesServiceImp;

    @MockBean
    private CourseStudentsServiceImpl courseStudentsServiceImpl;

    @MockBean
    private Validator applicationMenuValidator;

    @Autowired
    private ApplicationMenu applicationMenu;

    private String testUserNumberInput = "10";
    private String testUserWordInput = "test";
    private String testUserEmptyInput = "";
    private String testUserInputNameAndSurname = "test test";
    private int testStudentId = 1;
    private int testStudentId2 = 2;

    @Test
    public void testFindGroupInputNumber_shouldCallExpectedMethod(){
        applicationMenu.findAllGroupWithEqualOrLessStudentsNumber(testUserNumberInput);
        verify(applicationMenuValidator, Mockito.times(1)).validateIntegerInput(Mockito.any());
    }

    @Test
    public void testFindGroupInputString_shouldCallExpectedMethod(){
        applicationMenu.findAllGroupWithEqualOrLessStudentsNumber(testUserWordInput);
        verify(formatter, Mockito.times(1)).showMessageEnteredDataIsInvalid();
    }

    @Test
    public void testFindGroupInputEmpty_shouldCallExpectedMethod(){
        applicationMenu.findAllGroupWithEqualOrLessStudentsNumber(testUserEmptyInput);
        verify(formatter, Mockito.times(1)).showMessageEnteredDataIsInvalid();
    }

    @Test
    public void testFindStudentsRelatedInputNumber_shouldCallExpectedMethod(){
        applicationMenu.findAllStudentsRelatedToCourseWithGivenName(testUserNumberInput);
        verify(formatter, Mockito.times(1)).showMessageEnteredDataIsInvalid();
    }

    @Test
    public void testFindStudentsRelatedInputString_shouldCallExpectedMethod(){
        applicationMenu.findAllStudentsRelatedToCourseWithGivenName(testUserWordInput);
        verify(applicationMenuValidator, Mockito.times(1)).validateCourseName(Mockito.any());
    }

    @Test
    public void testFindStudentsRelatedInputEmpty_shouldCallExpectedMethod(){
        applicationMenu.findAllStudentsRelatedToCourseWithGivenName(testUserEmptyInput);
        verify(applicationMenuValidator, Mockito.times(1)).validateCourseName(Mockito.any());
    }

    @Test
    public void testAddNewStudentNumberInput_shouldCallExpectedMethod(){
        applicationMenu.addNewStudent(testUserNumberInput);
        verify(formatter, Mockito.times(1)).showMessageStudentNameAndSurnameInvalid();
    }

    @Test
    public void testAddNewStudentInputString_shouldCallExpectedMethod(){
        applicationMenu.addNewStudent(testUserWordInput);
        verify(formatter, Mockito.times(1)).showMessageStudentNameAndSurnameInvalid();
    }

    @Test
    public void testAddNewStudentInputEmpty_shouldCallExpectedMethod(){
        applicationMenu.addNewStudent(testUserEmptyInput);
        verify(formatter, Mockito.times(1)).showMessageStudentNameAndSurnameInvalid();
    }

    @Test
    public void testAddNewStudentInputNameAndSurname_shouldCallExpectedMethod(){
        applicationMenu.addNewStudent(testUserInputNameAndSurname);
        verify(studentServiceImpl, Mockito.times(1)).addStudent(Mockito.any());
    }

    @Test
    public void testDeleteStudentByIdInputNumber_shouldCallExpectedMethod(){
        applicationMenu.deleteStudentById(testUserNumberInput);
        verify(formatter, Mockito.times(1)).showMessageNoSuchStudentFound();
    }

    @Test
    public void testDeleteStudentByIdInputString_shouldCallExpectedMethod(){
        applicationMenu.deleteStudentById(testUserWordInput);
        verify(formatter, Mockito.times(1)).showMessageStudentIdIsInvalid();
    }

    @Test
    public void testDeleteStudentByIdInputEmpty_shouldCallExpectedMethod(){
        applicationMenu.deleteStudentById(testUserEmptyInput);
        verify(formatter, Mockito.times(1)).showMessageStudentIdIsInvalid();
    }

    @Test
    public void testRemoveStudentFromCourseById_shouldCallExpectedMethod(){
       applicationMenu.removeStudentFromCourseByStudentId(testStudentId, testStudentId2);
       verify(formatter, Mockito.times(1)).showMessageNoCourseFoundAssignedToCurrentStudent();
   }
}
