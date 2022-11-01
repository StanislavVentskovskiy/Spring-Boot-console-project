package ua.com.foxminded.service;

import ua.com.foxminded.model.Course;
import java.util.ArrayList;

public interface CoursesStudentsService {

    ArrayList<String> getStudentsListRelatedToCourse(String courseName);

    ArrayList<Integer> getCoursesIdListByStudent(int studentId);

    int insertStudentAndCourse(int studentId, int courseId);

    ArrayList<Course> getCourseListByStudentId(int studentId);

    int deleteStudentFromCourseById(int courseId, int studentId);
}
