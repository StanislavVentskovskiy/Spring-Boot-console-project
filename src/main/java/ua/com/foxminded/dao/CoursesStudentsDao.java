package ua.com.foxminded.dao;

import ua.com.foxminded.model.Course;
import ua.com.foxminded.model.Student;
import java.util.ArrayList;
import java.util.List;

public interface CoursesStudentsDao {

    void addStudentAndCourse(int studentId, int courseId);

    List<Student> getStudentsListRelatedToCourseByName(String courseName);

    List<Course> getCourseListByStudentId(int studentId);

    Integer removeStudentFromCourse(int studentId, int courseId);

    List<String> getStudentsNamesAndSurnamesList(ArrayList<Student> studentsListRelatedToCourseByName);
}
