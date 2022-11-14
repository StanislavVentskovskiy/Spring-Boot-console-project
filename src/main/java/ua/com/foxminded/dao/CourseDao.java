package ua.com.foxminded.dao;

import ua.com.foxminded.model.Course;
import java.util.ArrayList;
import java.util.List;

public interface CourseDao {

    void addCourse(Course course);

    void addCourseList(ArrayList<Course> coursesList);

    List<Integer> getCoursesIdList();

    List<Course> getCourseList();
}
