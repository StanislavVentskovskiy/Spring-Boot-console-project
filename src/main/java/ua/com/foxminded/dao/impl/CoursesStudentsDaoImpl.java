package ua.com.foxminded.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.com.foxminded.dao.CoursesStudentsDao;
import ua.com.foxminded.dao.mapper.CourseMapper;
import ua.com.foxminded.dao.mapper.StudentMapper;
import ua.com.foxminded.model.Course;
import ua.com.foxminded.model.Student;
import java.util.ArrayList;

@Component
public class CoursesStudentsDaoImpl implements CoursesStudentsDao {
    private JdbcTemplate jdbcTemplate;
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

    @Autowired
    public CoursesStudentsDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insertStudentAndCourse(int studentId, int courseId) {
        return jdbcTemplate.update(insertStudentsSQL, courseId, studentId);
    }

    public ArrayList<Student> getStudentsListRelatedToCourseByName(String courseName) {
        return (ArrayList<Student>) jdbcTemplate.query(studentsRelatedToGivenCourseQUERY + "'" + courseName + "'", new StudentMapper());

    }

    public ArrayList<String> getStudentsNamesAndSurnamesList(ArrayList<Student> students) {
        ArrayList<String> studentNameList = new ArrayList<>();
        students.forEach(student -> studentNameList.add(student.getName() + " " + student.getSurname()));
        return studentNameList;
    }

    public ArrayList<Course> getCourseListByStudentId(int studentId) {

        return (ArrayList<Course>) jdbcTemplate.query(courseListByIdQUERRY + studentId, new CourseMapper());
    }

    public int deleteStudentFromCourseById(int courseId, int studentId) {
       return jdbcTemplate.update(String.format(deleteStudentQUERY, courseId, studentId));
    }

    public ArrayList<Integer> getCoursesIdListByStudent(int studentId){
       return (ArrayList<Integer>) jdbcTemplate.queryForList(courseIdListByStudentId+studentId,Integer.class);
    }
}
