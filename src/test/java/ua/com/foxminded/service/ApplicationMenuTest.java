package ua.com.foxminded.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.foxminded.dao.impl.CourseDaoImpl;
import ua.com.foxminded.dao.impl.CoursesStudentsDaoImpl;
import ua.com.foxminded.dao.impl.StudentsDaoImpl;
import ua.com.foxminded.formatter.Formatter;
import ua.com.foxminded.service.validaton.ApplicationMenuValidator;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationMenu.class)
public class ApplicationMenuTest {

    @MockBean
    private Formatter formatter;

    @MockBean
    private StudentsDaoImpl studentsDao;

    @MockBean
    private CourseDaoImpl courseDao;

    @MockBean
    private CoursesStudentsDaoImpl coursesStudentsDao;

    @MockBean
    private ApplicationMenuValidator applicationMenuValidator;

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
        Mockito.verify(applicationMenuValidator, Mockito.times(1)).validateIntegerInput(Mockito.any());
    }

    @Test
    public void testFindGroupInputString_shouldCallExpectedMethod(){
        applicationMenu.findAllGroupWithEqualOrLessStudentsNumber(testUserWordInput);
        Mockito.verify(formatter, Mockito.times(1)).showMessageEnteredDataIsInvalid();
    }

    @Test
    public void testFindGroupInputEmpty_shouldCallExpectedMethod(){
        applicationMenu.findAllGroupWithEqualOrLessStudentsNumber(testUserEmptyInput);
        Mockito.verify(formatter, Mockito.times(1)).showMessageEnteredDataIsInvalid();
    }

    @Test
    public void testFindStudentsRelatedInputNumber_shouldCallExpectedMethod(){
        applicationMenu.findAllStudentsRelatedToCourseWithGivenName(testUserNumberInput);
        Mockito.verify(formatter, Mockito.times(1)).showMessageEnteredDataIsInvalid();
    }

    @Test
    public void testFindStudentsRelatedInputString_shouldCallExpectedMethod(){
        applicationMenu.findAllStudentsRelatedToCourseWithGivenName(testUserWordInput);
        Mockito.verify(applicationMenuValidator, Mockito.times(1)).validateCourseName(Mockito.any());
    }

    @Test
    public void testFindStudentsRelatedInputEmpty_shouldCallExpectedMethod(){
        applicationMenu.findAllStudentsRelatedToCourseWithGivenName(testUserEmptyInput);
        Mockito.verify(applicationMenuValidator, Mockito.times(1)).validateCourseName(Mockito.any());
    }

    @Test
    public void testAddNewStudentNumberInput_shouldCallExpectedMethod(){
        applicationMenu.addNewStudent(testUserNumberInput);
        Mockito.verify(formatter, Mockito.times(1)).showMessageStudentNameAndSurnameInvalid();
    }

    @Test
    public void testAddNewStudentInputString_shouldCallExpectedMethod(){
        applicationMenu.addNewStudent(testUserWordInput);
        Mockito.verify(formatter, Mockito.times(1)).showMessageStudentNameAndSurnameInvalid();
    }

    @Test
    public void testAddNewStudentInputEmpty_shouldCallExpectedMethod(){
        applicationMenu.addNewStudent(testUserEmptyInput);
        Mockito.verify(formatter, Mockito.times(1)).showMessageStudentNameAndSurnameInvalid();
    }

    @Test
    public void testAddNewStudentInputNameAndSurname_shouldCallExpectedMethod(){
        applicationMenu.addNewStudent(testUserInputNameAndSurname);
        Mockito.verify(studentsDao, Mockito.times(1)).insertStudent(Mockito.any());
    }

    @Test
    public void testDeleteStudentByIdInputNumber_shouldCallExpectedMethod(){
        applicationMenu.deleteStudentById(testUserNumberInput);
        Mockito.verify(formatter, Mockito.times(1)).showMessageNoSuchStudentFound();
    }

    @Test
    public void testDeleteStudentByIdInputString_shouldCallExpectedMethod(){
        applicationMenu.deleteStudentById(testUserWordInput);
        Mockito.verify(formatter, Mockito.times(1)).showMessageStudentIdIsInvalid();
    }

    @Test
    public void testDeleteStudentByIdInputEmpty_shouldCallExpectedMethod(){
        applicationMenu.deleteStudentById(testUserEmptyInput);
        Mockito.verify(formatter, Mockito.times(1)).showMessageStudentIdIsInvalid();
    }

    @Test
    public void testRemoveStudentFromCourseById_shouldCallExpectedMethod(){
       applicationMenu.removeStudentFromCourseByStudentId(testStudentId, testStudentId2);
       Mockito.verify(formatter, Mockito.times(1)).showMessageNoCourseFoundAssignedToCurrentStudent();
   }
}