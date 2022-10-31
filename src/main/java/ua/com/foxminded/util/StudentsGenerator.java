package ua.com.foxminded.util;

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

    @Autowired
    private DataReader dataReader;

    public void setNameAndSurnamePath(String nameAndSurnamePath) {
        this.nameAndSurnamePath = nameAndSurnamePath;
    }

    @Value("${students.name.and.surname.directory}")
    private String nameAndSurnamePath;

    private void readDataFromFile(){
        try {
            studentsGenerationData =  dataReader.readGeneratedData((Paths.get(nameAndSurnamePath)));
        } catch (IOException e) {
            System.err.println("File reading error.");
        }
    }

    public ArrayList<Student> generateStudentsList(){
        readDataFromFile();
        for(int index = 0; index < studentsNumber; index++) {
            generateNameList();
            generateSurnameList();
            Student student = new Student(nameList.get(0),surnameList.get(0));
            studentList.add(student);
        }

        return studentList;
    }

    private void generateNameList(){
        nameList = new ArrayList<>(studentsGenerationData.keySet());
        Collections.shuffle(nameList);
    }

    private void  generateSurnameList(){
        surnameList = new ArrayList<>(studentsGenerationData.values());
        Collections.shuffle(surnameList);
    }
}
