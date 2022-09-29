package ua.com.foxminded.dao;

import ua.com.foxminded.model.Course;
import java.util.ArrayList;

public interface CourseDao {

    void insertCourse(Course course);

    void insertCourseList(ArrayList<Course> coursesList);

    ArrayList<Integer> getCoursesIdList();

    ArrayList<Course> getCourseList();
}
