package ua.com.foxminded.util.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.com.foxminded.reader.DataReader;
import ua.com.foxminded.model.Student;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

@Service
public class StudentsGenerator {
    private ArrayList<Student> studentList = new ArrayList<>();
    private HashMap<String,String> studentsGenerationData = new HashMap<>();
    private List<String> nameList = new ArrayList<>();
    private List<String> surnameList = new ArrayList<>();
    private final int studentsNumber = 200;
    private static final Logger LOG = LoggerFactory.getLogger(StudentsGenerator.class);

    @Autowired
    private DataReader dataReader;

    @Value("${students.name.and.surname.directory}")
    private String nameAndSurnamePath;

    public void setNameAndSurnamePath(String nameAndSurnamePath) {
        this.nameAndSurnamePath = nameAndSurnamePath;
    }

    private void readDataFromFile(){
        LOG.info("Entered method readDataFromFile()");
        try {
            studentsGenerationData =  dataReader.readGeneratedData((Paths.get(nameAndSurnamePath)));
            LOG.info("Uploaded data from file");
        } catch (IOException e) {
            LOG.error("File with data cannot be read");
            System.err.println("File reading error.");
        }
        LOG.info("Leave method readDataFromFile()");
    }

    public ArrayList<Student> generateStudentsList(){
        LOG.info("Enter method generateStudentsList()");
        readDataFromFile();
        for(int index = 0; index < studentsNumber; index++) {
            generateNameList();
            generateSurnameList();
            Student student = new Student(nameList.get(0),surnameList.get(0));
            studentList.add(student);
            LOG.info("Generated student from data in file");
        }
        LOG.info("Leave method generateStudentList()");

        return studentList;
    }

    private void generateNameList(){
        LOG.info("Enter method generateNameList()");
        nameList = new ArrayList<>(studentsGenerationData.keySet());
        Collections.shuffle(nameList);
        LOG.info("Shuffled students name list");
        LOG.info("Leave method generateNameList()");
    }

    private void  generateSurnameList(){
        LOG.info("Enter method generateSurnameList()");
        surnameList = new ArrayList<>(studentsGenerationData.values());
        Collections.shuffle(surnameList);
        LOG.info("Shuffled surname list of students");
        LOG.info("Leave generateSurnameList()");
    }
}
