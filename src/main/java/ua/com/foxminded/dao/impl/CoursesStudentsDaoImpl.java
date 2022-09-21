package ua.com.foxminded.dao.impl;

import ua.com.foxminded.dao.CoursesStudentsDao;
import ua.com.foxminded.exception.DAOException;
import ua.com.foxminded.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CoursesStudentsDaoImpl implements CoursesStudentsDao {
    private final Connection connection = ConnectionFactory.getInstance().makeConnection();
    private final String SQL = "INSERT INTO postgres.schoolconsoleapp.coursesstudents(course_id, student_id) " + "VALUES(?, ?)";
    private String studentsRelatedToGivenCourseQUERRY = "SELECT\n" +
        "schoolconsoleapp.students.first_name || ' ' ||\n" +
        "schoolconsoleapp.students.last_name,\n" +
        "schoolconsoleapp.courses.course_name\n" +
        "FROM schoolconsoleapp.students\n" +
        "join schoolconsoleapp.coursesstudents\n" +
        "ON schoolconsoleapp.students.id = schoolconsoleapp.coursesstudents.student_id\n" +
        "join schoolconsoleapp.courses\n" +
        "ON schoolconsoleapp.courses.id = schoolconsoleapp.coursesstudents.course_id\n" +
        "WHERE course_name =";

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
}
