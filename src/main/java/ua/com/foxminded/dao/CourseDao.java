package ua.com.foxminded.dao;

import ua.com.foxminded.model.Course;
import java.util.ArrayList;
import java.util.List;

public interface CourseDao {

    void addCourseList(ArrayList<Course> coursesList);

    ArrayList<Integer> getCoursesIdList();

    List<Course> getCourseList();
}
