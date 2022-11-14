package ua.com.foxminded.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.dao.CoursesStudentsDao;
import ua.com.foxminded.model.Course;
import ua.com.foxminded.model.Student;
import ua.com.foxminded.dao.repository.CourseRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;

@Repository
@Transactional
public class CoursesStudentsDaoImpl implements CoursesStudentsDao {

    @Autowired
    CourseRepository courseRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOG = LoggerFactory.getLogger(CoursesStudentsDaoImpl.class);
    private final String insertStudentsSQL = "INSERT INTO postgres.schoolconsoleapp.coursesstudents(course_id, student_id) " + "VALUES(?, ?)";
    private final String studentsRelatedToGivenCourseQUERY = "SELECT\n" +
        "schoolconsoleapp.students.first_name,\n" +
        "schoolconsoleapp.students.last_name,\n" +
        "schoolconsoleapp.courses.course_name\n," +
        "schoolconsoleapp.students.id,\n" +
        "schoolconsoleapp.students.group_id\n" +
        "FROM schoolconsoleapp.students\n" +
        "join schoolconsoleapp.coursesstudents\n" +
        "ON schoolconsoleapp.students.id = schoolconsoleapp.coursesstudents.student_id \n" +
        "join schoolconsoleapp.courses\n" +
        "ON schoolconsoleapp.courses.id = schoolconsoleapp.coursesstudents.course_id\n" +
        "WHERE course_name =";
    private final String courseListByIdQUERRY = "SELECT schoolconsoleapp.courses.id, schoolconsoleapp.courses.course_name, schoolconsoleapp.courses.course_description\n" +
        "FROM schoolconsoleapp.courses\n" +
        "JOIN schoolconsoleapp.coursesstudents\n" +
        "ON coursesstudents.course_id = courses.id\n" +
        "WHERE coursesstudents.student_id = ";
    private final String deleteStudentQUERY = "DELETE FROM schoolconsoleapp.coursesstudents WHERE course_id = %d AND student_id = %d;";
    private final String courseIdListByStudentId =  "SELECT course_id \n" +
    "FROM schoolconsoleapp.coursesstudents \n" +
    "WHERE student_id =";

    public int addStudentAndCourse(int studentId, int courseId) {
        LOG.info("Enter insertStudentAndCourse() method");
        LOG.info("Leave insertStudentAndCourse() method");
        return entityManager.createNativeQuery(insertStudentsSQL)
            .setParameter(1, courseId)
            .setParameter(2, studentId)
            .executeUpdate();
    }

    public ArrayList<Student> getStudentsListRelatedToCourseByName(String courseName) {
        LOG.info("Enter method getStudentsListRelatedToCourseByName()");
        LOG.info("Leave method getStudentsListRelatedToCourseByName()");
        return (ArrayList<Student>) entityManager.createNativeQuery(studentsRelatedToGivenCourseQUERY + "'" + courseName + "'", Student.class).getResultList();
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
        LOG.info("Leave method getCourseListByStudentId()");
        return (ArrayList<Course>) entityManager.createNativeQuery(courseListByIdQUERRY + studentId, Course.class).getResultList();
    }

    public int removeStudentFromCourse(int courseId, int studentId) {
        LOG.info("Enter method deleteStudentFromCourseById()");
        LOG.info("Leave method deleteStudentFromCourseById()");
        return entityManager.createNativeQuery(String.format(deleteStudentQUERY, courseId, studentId)).executeUpdate();
    }

    public ArrayList<Integer> getCoursesIdListByStudent(int studentId){
        LOG.info("Enter method getCoursesIdListByStudent()");
        ArrayList<Integer> courseIdList = new ArrayList<>();
        ArrayList<Course> courseList = getCourseListByStudentId(studentId);
        courseList.forEach(course -> courseIdList.add(course.getCourseId()));
        LOG.info("Leave method getCoursesIdListByStudent()");

        return courseIdList;
    }
}
