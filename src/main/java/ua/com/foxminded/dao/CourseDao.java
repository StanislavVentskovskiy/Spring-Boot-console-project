package ua.com.foxminded.dao;

import ua.com.foxminded.model.Course;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CourseDao {

    void addCourseList(ArrayList<Course> coursesList);

    ArrayList<Integer> getCoursesIdList(ArrayList<Course> courseList);

    List<Course> getCourseList();

    Optional<Course> findById(int id);

    Course updateCourse(int courseId, String courseName, String courseDescription);

    Course addNewCourse(Course course);

    void deleteCourse(int id);
}
