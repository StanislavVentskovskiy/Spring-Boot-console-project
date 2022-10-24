package ua.com.foxminded.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.com.foxminded.model.Course;
import ua.com.foxminded.reader.DataReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class CourseGenerator {
    private ArrayList<Course> courseList = new ArrayList<>();
    private HashMap<String,String> coursesNamesAndDescription = new HashMap<>();

    @Value("${courses.name.and.description.directory}")
    private String courseAndDescriptionPath;

    @Autowired
    DataReader dataReader;

    public ArrayList<Course> generateCourseList() {
        readDataFromFile();
        for(Map.Entry<String,String> entry: coursesNamesAndDescription.entrySet()) {
            String name = entry.getKey();
            String description = entry.getValue();
            courseList.add(new Course(name,description));
        }

        return courseList;
    }

    private void readDataFromFile(){
        try {
            coursesNamesAndDescription = dataReader.readGeneratedData(Paths.get(courseAndDescriptionPath));
        } catch (IOException e) {
            System.err.println("File reading error.");
        }
    }
}
