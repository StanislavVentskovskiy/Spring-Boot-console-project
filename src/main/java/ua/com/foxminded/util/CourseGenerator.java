package ua.com.foxminded.util;

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

    public ArrayList<Course> generateCourseList() {
        for(Map.Entry<String,String> entry: coursesNamesAndDescription.entrySet()) {
            String name = entry.getKey();
            String description = entry.getValue();
            courseList.add(new Course(name,description));
        }

        return courseList;
    }

    private CourseGenerator() {
        DataReader dataReader = new DataReader();
        try {
            coursesNamesAndDescription = dataReader.readGeneratedData(Paths.get(PathPropertiesUtil.get("courses.name.and.description.directory")));
        } catch (IOException e) {
            System.err.println("File reading error.");
        }
    }
}
