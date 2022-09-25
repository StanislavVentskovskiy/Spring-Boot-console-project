package ua.com.foxminded.formatter.dao.impl;

import ua.com.foxminded.formatter.dao.CoursesStudentsDao;
import ua.com.foxminded.formatter.dao.DAOException;
import ua.com.foxminded.model.Course;
import ua.com.foxminded.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CoursesStudentsDaoImpl implements CoursesStudentsDao {
    private final Connection connection = ConnectionFactory.getInstance().makeConnection();
    private final String SQL = "INSERT INTO postgres.schoolconsoleapp.coursesstudents(course_id, student_id) " + "VALUES(?, ?)";
    private final String studentsRelatedToGivenCourseQUERRY = "SELECT\n" +
        "schoolconsoleapp.students.first_name || ' ' ||\n" +
        "schoolconsoleapp.students.last_name,\n" +
        "schoolconsoleapp.courses.course_name\n" +
        "FROM schoolconsoleapp.students\n" +
        "join schoolconsoleapp.coursesstudents\n" +
        "ON schoolconsoleapp.students.id = schoolconsoleapp.coursesstudents.student_id\n" +
        "join schoolconsoleapp.courses\n" +
        "ON schoolconsoleapp.courses.id = schoolconsoleapp.coursesstudents.course_id\n" +
        "WHERE course_name =";

    private final String courseListByIdQUERRY = "SELECT schoolconsoleapp.courses.id, schoolconsoleapp.courses.course_name, schoolconsoleapp.courses.course_description\n" +
        "FROM schoolconsoleapp.courses\n" +
        "JOIN schoolconsoleapp.coursesstudents\n" +
        "ON coursesstudents.course_id = courses.id\n" +
        "WHERE coursesstudents.student_id = ";


    public final String deleteStudentQUERY = "DELETE FROM schoolconsoleapp.coursesstudents WHERE course_id = %d AND student_id = %d;";



    public void insertStudentAndCourse(int studentId, int courseId) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setInt(1,courseId);
            statement.setInt(2,studentId);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    public ArrayList<String> getStudentsListRelatedToCourseByName(String courseName){
        ArrayList<String> studentsList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(studentsRelatedToGivenCourseQUERRY + "'" + courseName + "'");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String studentName = resultSet.getString(1);
                studentsList.add(studentName);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }

        return studentsList;
    }

    public ArrayList<Course> getCourseListByStudentId(int studentId) {
        ArrayList<Course> courseList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(courseListByIdQUERRY + studentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int courseId = resultSet.getInt(1);
                String courseName = resultSet.getString(2);
                String courseDescription = resultSet.getString(3);
                Course course = new Course(courseName, courseDescription);
                course.setCourseId(courseId);
                courseList.add(course);

            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return courseList;
    }

    public void deleteStudentFromCourseById(int courseId, int studentId){
        String currentDeleteStudentQUERY = String.format(deleteStudentQUERY,courseId,studentId);
        try (PreparedStatement statement = connection.prepareStatement(currentDeleteStudentQUERY);) {
           statement.executeUpdate();

        } catch (Exception e) {
            throw new DAOException(e);
        }
    }
}
