package ua.com.foxminded.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.foxminded.service.validator.Validator;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Validator.class)
public class ApplicationMenuValidationTest {

    @Autowired
    Validator applicationMenuValidator;

    private int testInteger = 1;
    private int testInteger2 = 2;
    private String testString = "test";
    private String testNumber = "1";
    private boolean testResult;
    private ArrayList<String> testStringArray = new ArrayList<>();
    private ArrayList<Integer> testIntegerArray = new ArrayList<>();

    @Before
    public void insertTestArrayData(){
        testStringArray.add("test");
        testIntegerArray.add(1);
    }

    @Test
    public void testValidateIntegerInputNumber_shouldReturnTrue(){
        testResult = applicationMenuValidator.validateIntegerInput(testNumber);
        Assert.assertTrue(testResult);
    }

    @Test
    public void validateIntegerInputString_shouldReturnFalse(){
        testResult = applicationMenuValidator.validateIntegerInput(testString);
        Assert.assertFalse(testResult);
    }

    @Test
    public void testValidateCourseNameInsertNotEmptyArray_shouldReturnTrue(){
        testResult = applicationMenuValidator.validateCourseName(testStringArray);
        Assert.assertTrue(testResult);
    }

    @Test
    public void testValidateCourseNameInsertEmptyArray_shouldReturnFalse(){
        testStringArray = new ArrayList<String>();
        testResult = applicationMenuValidator.validateCourseName(testStringArray);
        Assert.assertFalse(testResult);
    }

    @Test
    public void testValidateStudentInsertInputNumber_shouldReturnTrue(){
        testResult = applicationMenuValidator.validateStudentInsert(testInteger);
        Assert.assertTrue(testResult);
    }

    @Test
    public void testValidateStudentInsertInputNumber_shouldReturnFalse(){
        testResult = applicationMenuValidator.validateStudentInsert(testInteger2);
        Assert.assertFalse(testResult);
    }

    @Test
    public void testValidateStudentDeleteInputNumber_shouldReturnTrue(){
        testResult = applicationMenuValidator.validateStudentDelete(testInteger);
        Assert.assertTrue(testResult);
    }

    @Test
    public void testValidateStudentDeleteInputNumber_shouldReturnFalse(){
        testResult = applicationMenuValidator.validateStudentDelete(testInteger2);
        Assert.assertFalse(testResult);
    }

    @Test
    public void testValidateStudentDeleteFromCourseInputNumber_shouldReturnFalse(){
        testResult = applicationMenuValidator.validateDeleteStudentFromCourse(testInteger2);
        Assert.assertFalse(testResult);
    }

    @Test
    public void testValidateStudentDeleteFromCourseInputNumber_shouldReturnTrue(){
        testResult = applicationMenuValidator.validateDeleteStudentFromCourse(testInteger);
        Assert.assertTrue(testResult);
    }

    @Test
    public void testValidateStudentIdInputFirstNumber_shouldReturnTrue(){
        testResult = applicationMenuValidator.validateStudentId(testInteger, testIntegerArray);
        Assert.assertTrue(testResult);
    }

    @Test
    public void testValidateStudentIdInputSecondNumber_shouldReturnFalse(){
        testResult = applicationMenuValidator.validateStudentId(testInteger2, testIntegerArray);
        Assert.assertFalse(testResult);
    }
}
