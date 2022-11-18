package ua.com.foxminded.service;

import ua.com.foxminded.model.Course;
import java.util.ArrayList;

public interface CourseService {

    ArrayList<Course> getAllCoursesNameList();

    Course addNewCourse(String name, String courseDescription);

    void deleteCourse(int id);

    Course updateCourse(int courseId, String courseName, String courseDescription);
}
