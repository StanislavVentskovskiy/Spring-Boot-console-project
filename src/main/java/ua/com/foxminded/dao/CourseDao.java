package ua.com.foxminded.dao;

import ua.com.foxminded.model.Course;
import java.util.ArrayList;
import java.util.List;

public interface CourseDao {
    void insertCourse(Course course);

    void insertCourseList(ArrayList<Course> coursesList);

    List<Integer> getCoursesIdList();

    List<Course> getCourseList();
}
