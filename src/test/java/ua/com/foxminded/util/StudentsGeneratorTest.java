package ua.com.foxminded.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.foxminded.model.Student;
import ua.com.foxminded.reader.DataReader;
import ua.com.foxminded.util.generator.StudentsGenerator;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StudentsGenerator.class, DataReader.class})
public class StudentsGeneratorTest {
    private ArrayList<Student> expectedStudentList = new ArrayList<>();
    private ArrayList<Student> actualStudentList = new ArrayList<>();
    private Student expectedStudent;
    private Student actualStudent;

    @Autowired
    StudentsGenerator studentsGenerator;

    @Value("${test.data.directory}")
    private String testDataPath;

    @Before
    public void initTestData(){
        studentsGenerator.setNameAndSurnamePath(testDataPath);
        expectedStudent = new Student("test","test");
        expectedStudentList.add(expectedStudent);

    }

    @Test
    public void testGetStudentObject_shouldReturnObjectWithSameFields(){
        actualStudentList = studentsGenerator.generateStudentsList();
        actualStudent = actualStudentList.get(0);

        Assert.assertTrue(expectedStudent.equals(actualStudent));
    }
}
