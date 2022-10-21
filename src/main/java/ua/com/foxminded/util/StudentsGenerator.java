package ua.com.foxminded.util;

import org.springframework.beans.factory.annotation.Autowired;
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
    private DataReader dataReader;

    @Autowired
    public StudentsGenerator(DataReader dataReader) {
        this.dataReader = dataReader;
        try {
            studentsGenerationData =  dataReader.readGeneratedData((Paths.get(PathPropertiesUtil.get("students.name.and.surname.directory"))));
        } catch (IOException e) {
            System.err.println("File reading error.");
        }
    }

    public ArrayList<Student> generateStudentsList(){
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
