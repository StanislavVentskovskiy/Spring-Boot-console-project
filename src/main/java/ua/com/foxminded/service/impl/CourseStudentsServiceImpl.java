package ua.com.foxminded.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOG = LoggerFactory.getLogger(CourseStudentsServiceImpl.class);

    public ArrayList<String> getStudentsListRelatedToCourse(String courseName){
        LOG.info("Enter method getStudentsListRelatedToCourse()");
        ArrayList<String> studentList = coursesStudentsDaoImpl.getStudentsNamesAndSurnamesList(coursesStudentsDaoImpl.getStudentsListRelatedToCourseByName(courseName));
        LOG.info("Leave method getStudentsListRelatedToCourse()");
        return studentList;
    }

    public ArrayList<Integer> getCoursesIdListByStudent(int studentId){
        LOG.info("Enter method getCoursesIdListByStudent()");
        return coursesStudentsDaoImpl.getCoursesIdListByStudent(studentId);
    }

    public void insertStudentAndCourse(int studentId, int courseId){
        LOG.info("Enter method insertStudentAndCourse()");
        coursesStudentsDaoImpl.addStudentAndCourse(studentId,courseId);
        LOG.info("Exit method insertStudentAndCourse()");
    }

    public ArrayList<Course> getCourseListByStudentId(int studentId) {
        LOG.info("Enter method getCourseListByStudentId()");
        return coursesStudentsDaoImpl.getCourseListByStudentId(studentId);
    }

    public Integer deleteStudentFromCourseById(int courseId, int studentId){
        LOG.info("Enter method deleteStudentFromCourseById()");
        return coursesStudentsDaoImpl.removeStudentFromCourse(courseId, studentId);
    }
}
