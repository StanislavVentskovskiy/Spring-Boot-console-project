package ua.com.foxminded.formatter.dao;

import ua.com.foxminded.model.Course;

import java.util.ArrayList;

public interface CoursesStudentsDao {

    void insertStudentAndCourse(int studentId, int courseId);

    ArrayList<String> getStudentsListRelatedToCourseByName(String courseName);

    ArrayList<Course> getCourseListByStudentId(int studentId);

    void deleteStudentFromCourseById(int studentId, int courseId);
}
