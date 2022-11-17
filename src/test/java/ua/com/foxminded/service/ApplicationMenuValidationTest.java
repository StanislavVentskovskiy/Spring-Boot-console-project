package ua.com.foxminded.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.foxminded.model.Student;
import ua.com.foxminded.service.validator.Validator;
import java.util.ArrayList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Validator.class)
public class ApplicationMenuValidationTest {

    @Autowired
    Validator applicationMenuValidator;

    private Integer firstTestIntegerStatus = 1;
    private Integer secondTestIntegerStatus = 2;
    private int testInteger = 1;
    private int testInteger2 = 2;
    private String testString = "test";
    private String testNumber = "1";
    private Student testStudent = new Student("test","test");
    private Student testNullStudent;
    private boolean testResult;
    private ArrayList<String> testStringArray = new ArrayList<>();
    private ArrayList<Integer> testIntegerArray = new ArrayList<>();
    private boolean actualStatus;
    private boolean expectedStatus;

    @Before
    public void insertTestArrayData(){
        testStringArray.add("test");
        testIntegerArray.add(1);
        expectedStatus = true;
    }

    @Test
    public void testValidateIntegerInputNumber_shouldReturnTrue(){
        testResult = applicationMenuValidator.validateIntegerInput(testNumber);
        assertTrue(testResult);
    }

    @Test
    public void validateIntegerInputString_shouldReturnFalse(){
        testResult = applicationMenuValidator.validateIntegerInput(testString);
        assertFalse(testResult);
    }

    @Test
    public void testValidateCourseNameInsertNotEmptyArray_shouldReturnTrue(){
        testResult = applicationMenuValidator.validateCourseName(testStringArray);
        assertTrue(testResult);
    }

    @Test
    public void testValidateCourseNameInsertEmptyArray_shouldReturnFalse(){
        testStringArray = new ArrayList<String>();
        testResult = applicationMenuValidator.validateCourseName(testStringArray);
        assertFalse(testResult);
    }

    @Test
    public void testValidateStudentInsertInputNumber_shouldReturnTrue(){
        testResult = applicationMenuValidator.validateStudentInsert(testStudent);
        assertTrue(testResult);
    }

    @Test
    public void testValidateStudentInsertInputNumber_shouldReturnFalse(){
        testResult = applicationMenuValidator.validateStudentInsert(testNullStudent);
        assertFalse(testResult);
    }

    @Test
    public void testValidateStudentDeleteInputNumber_shouldReturnTrue(){
        actualStatus = applicationMenuValidator.validateStudentDelete(expectedStatus);
        assertTrue(actualStatus);
    }

    @Test
    public void testValidateStudentDeleteInputNumber_shouldReturnFalse(){
        actualStatus = applicationMenuValidator.validateStudentDelete(expectedStatus);
        assertFalse(testResult);
    }

    @Test
    public void testValidateStudentDeleteFromCourseInputNumber_shouldReturnFalse(){
        actualStatus = applicationMenuValidator.validateDeleteStudentFromCourse(secondTestIntegerStatus);
        assertTrue(actualStatus);
    }

    @Test
    public void testValidateStudentDeleteFromCourseInputNumber_shouldReturnTrue(){
        testResult = applicationMenuValidator.validateDeleteStudentFromCourse(testInteger);
        assertTrue(testResult);
    }

    @Test
    public void testValidateStudentIdInputFirstNumber_shouldReturnTrue(){
        testResult = applicationMenuValidator.validateStudentId(testInteger, testIntegerArray);
        assertTrue(testResult);
    }

    @Test
    public void testValidateStudentIdInputSecondNumber_shouldReturnFalse(){
        testResult = applicationMenuValidator.validateStudentId(testInteger2, testIntegerArray);
        assertFalse(testResult);
    }
}
