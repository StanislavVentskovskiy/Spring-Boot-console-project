package ua.com.foxminded.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.dao.impl.CoursesStudentsDaoImpl;
import ua.com.foxminded.model.Course;
import ua.com.foxminded.service.CoursesStudentsService;
import java.util.ArrayList;

@Service
public class CourseStudentsServiceImpl implements CoursesStudentsService {

    @Autowired
    CoursesStudentsDaoImpl coursesStudentsDaoImpl;

    public ArrayList<String> getStudentsListRelatedToCourse(String courseName){
        ArrayList<String> studentList = coursesStudentsDaoImpl.getStudentsNamesAndSurnamesList(coursesStudentsDaoImpl.getStudentsListRelatedToCourseByName(courseName));
        return studentList;
    }

    public ArrayList<Integer> getCoursesIdListByStudent(int studentId){
        return coursesStudentsDaoImpl.getCoursesIdListByStudent(studentId);
    }

    public int insertStudentAndCourse(int studentId, int courseId){
        return coursesStudentsDaoImpl.insertStudentAndCourse(studentId,courseId);
    }

    public ArrayList<Course> getCourseListByStudentId(int studentId) {
        return coursesStudentsDaoImpl.getCourseListByStudentId(studentId);
    }

    public int deleteStudentFromCourseById(int courseId, int studentId){
        return coursesStudentsDaoImpl.deleteStudentFromCourseById(courseId, studentId);
    }
}
