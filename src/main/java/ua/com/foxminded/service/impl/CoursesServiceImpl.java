package ua.com.foxminded.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.dao.impl.CourseDaoImpl;
import ua.com.foxminded.model.Course;
import ua.com.foxminded.service.CourseService;
import java.util.ArrayList;

@Service
public class CoursesServiceImpl implements CourseService {

    @Autowired
    CourseDaoImpl courseDaoImpl;

    private static final Logger LOG = LoggerFactory.getLogger(CoursesServiceImpl.class);

    public Course addNewCourse(String name, String courseDescription){
        Course course = new Course(name,courseDescription);
        return courseDaoImpl.addNewCourse(course);
    }

    public Course updateCourse(int courseId, String courseName, String courseDescription){
        return courseDaoImpl.updateCourse(courseId, courseName, courseDescription);
    }

    public void deleteCourse(int id){
        courseDaoImpl.deleteCourse(id);
    }

    public ArrayList<Course> getAllCoursesNameList(){
        LOG.info("Enter getAllCoursesNameList() method");
        return courseDaoImpl.getCourseList();
    }
}
