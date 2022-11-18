package ua.com.foxminded.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.dao.CoursesStudentsDao;
import ua.com.foxminded.dao.repository.StudentRepository;
import ua.com.foxminded.model.Course;
import ua.com.foxminded.model.Student;
import ua.com.foxminded.dao.repository.CourseRepository;
import java.util.ArrayList;

@Repository
@Transactional
public class CoursesStudentsDaoImpl implements CoursesStudentsDao {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    private static final Logger LOG = LoggerFactory.getLogger(CoursesStudentsDaoImpl.class);

    public void addStudentAndCourse(int studentId, int courseId) {
        LOG.info("Enter insertStudentAndCourse() method");
        courseRepository.addStudentAndCourse(courseId,studentId);
        LOG.info("Leave insertStudentAndCourse() method");
    }

    public ArrayList<Student> getStudentsListRelatedToCourseByName(String courseName) {
        LOG.info("Enter method getStudentsListRelatedToCourseByName()");
        return studentRepository.getStudentsListRelatedToCourseByName(courseName);
    }

    public ArrayList<String> getStudentsNamesAndSurnamesList(ArrayList<Student> students) {
        LOG.info("Enter method getStudentsNamesAndSurnamesList()");
        ArrayList<String> studentNameList = new ArrayList<>();
        students.forEach(student -> studentNameList.add(student.getName() + " " + student.getSurname()));
        LOG.info("Leave method getStudentsNamesAndSurnamesList()");

        return studentNameList;
    }

    public ArrayList<Course> getCourseListByStudentId(int studentId) {
        LOG.info("Enter method getCourseListByStudentId()");
        return courseRepository.getCourseListByStudentId(studentId);
    }

    public void removeStudentFromCourse(int courseId, int studentId) {
        LOG.info("Enter method deleteStudentFromCourseById()");
        courseRepository.removeStudentFromCourse(courseId,studentId);
    }

    public ArrayList<Integer> getCoursesIdListByStudent(int studentId){
        LOG.info("Enter method getCoursesIdListByStudent()");
        ArrayList<Integer> courseIdList = new ArrayList<>();
        ArrayList<Course> courseList = courseRepository.getCourseListByStudentId(studentId);
        courseList.forEach(course -> courseIdList.add(course.getCourseId()));
        LOG.info("Leave method getCoursesIdListByStudent()");

        return courseIdList;
    }
}
