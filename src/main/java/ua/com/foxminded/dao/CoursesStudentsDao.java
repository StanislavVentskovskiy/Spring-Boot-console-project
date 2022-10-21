package ua.com.foxminded.dao;

import ua.com.foxminded.model.Course;
import ua.com.foxminded.model.Student;
import java.util.ArrayList;

public interface CoursesStudentsDao {
    int insertStudentAndCourse(int studentId, int courseId);

    ArrayList<Student> getStudentsListRelatedToCourseByName(String courseName);

    ArrayList<Course> getCourseListByStudentId(int studentId);

    int deleteStudentFromCourseById(int studentId, int courseId);

    ArrayList<String> getStudentsNamesAndSurnamesList(ArrayList<Student> studentsListRelatedToCourseByName);
}
