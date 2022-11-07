package ua.com.foxminded.util.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOG = LoggerFactory.getLogger(CourseGenerator.class);

    @Value("${courses.name.and.description.directory}")
    private String courseAndDescriptionPath;

    @Autowired
    private DataReader dataReader;

    public void setCourseAndDescriptionPath(String courseAndDescriptionPath) {
        this.courseAndDescriptionPath = courseAndDescriptionPath;
    }

    public ArrayList<Course> generateCourseList() {
        LOG.info("Enter method generateCourseList()");
        readDataFromFile();
        for(Map.Entry<String,String> entry: coursesNamesAndDescription.entrySet()) {
            String name = entry.getKey();
            String description = entry.getValue();
            courseList.add(new Course(name,description));
            LOG.info("Prepared map of courses");
        }
        LOG.info("Leave method generateCourseList()");
        return courseList;
    }

    private void readDataFromFile(){
        LOG.info("Enter method readDataFromFile()");
        try {
            coursesNamesAndDescription = dataReader.readGeneratedData(Paths.get(courseAndDescriptionPath));
            LOG.info("Uploaded data from file");
        } catch (IOException e) {
            LOG.error("File cannot be read");
            System.err.println("File reading error.");
        }
        LOG.info("Leave method readDataFromFile()");
    }
}
