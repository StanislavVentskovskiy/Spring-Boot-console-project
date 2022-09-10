package ua.com.foxminded.datagenerator;

import ua.com.foxminded.reader.DataReader;
import ua.com.foxminded.student.Student;
import ua.com.foxminded.util.PathPropertiesUtil;
import java.nio.file.Paths;
import java.util.*;

public class StudentsGenerator {
    private HashMap<String,String> studentsGenerationData = new HashMap<>();
    private LinkedHashMap<Integer,Student> generatedStudentNames = new LinkedHashMap<>();
    private List<String> nameList = new ArrayList<>();
    private List<String> surnameList = new ArrayList<>();

    public StudentsGenerator() {
        DataReader dataReader = new DataReader();
        studentsGenerationData =  dataReader.readGeneratedData((Paths.get(PathPropertiesUtil.get("students.name.and.surname.directory"))));
    }

    public List<String> createNameList(){
        nameList = new ArrayList<>(studentsGenerationData.keySet());
        return nameList;
    }

    public List<String> createSurnameList(){
        surnameList = new ArrayList<>(studentsGenerationData.values());
        return surnameList;
    }

    public LinkedHashMap<Integer,Student> generateStudentsList(List<String> names, List<String> surnames) {
        Collections.shuffle(surnames);
        Collections.shuffle(names);
            for(int numberOfStudents = 1; numberOfStudents <= 200; numberOfStudents++) {
                Student student = new Student(names.get((int)(Math.random()*(20-1))), surnames.get((int)(Math.random()*(20-1))));
                generatedStudentNames.put(numberOfStudents,student);
            }

        return generatedStudentNames;
    }
}
