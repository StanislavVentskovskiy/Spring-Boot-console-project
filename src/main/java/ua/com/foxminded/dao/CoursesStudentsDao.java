package ua.com.foxminded.dao;

import java.util.ArrayList;

public interface CoursesStudentsDao {

    void insertStudentAndCourse(int studentId, int courseId);

    ArrayList<String> getStudentsListRelatedToCourseByName(String courseName);


}
