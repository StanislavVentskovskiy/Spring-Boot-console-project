package ua.com.foxminded.util;

import ua.com.foxminded.model.Course;
import ua.com.foxminded.reader.DataReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CourseGenerator {
    private ArrayList<Course> courseList = new ArrayList<>();
    private HashMap<String,String> coursesNamesAndDescription = new HashMap<>();

    public CourseGenerator() {
        DataReader dataReader = new DataReader();
        coursesNamesAndDescription = dataReader.readGeneratedData(Paths.get(PathPropertiesUtil.get("courses.name.and.description.directory")));
    }

    public ArrayList<Course> generateCourseList() {
        for(Map.Entry<String,String> entry: coursesNamesAndDescription.entrySet()) {
            String name = entry.getKey();
            String description = entry.getValue();
            courseList.add(generateCourse(name,description));
        }

        return courseList;
    }

    private Course generateCourse(String name, String description) {
        Course course = new Course(name, description);

        return course;
    }
}
