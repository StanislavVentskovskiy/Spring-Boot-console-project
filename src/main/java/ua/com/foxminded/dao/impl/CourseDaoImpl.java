package ua.com.foxminded.dao.impl;

import ua.com.foxminded.dao.CourseDao;
import ua.com.foxminded.exceptions.DAOException;
import ua.com.foxminded.model.Course;
import ua.com.foxminded.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDaoImpl implements CourseDao {
    private final String insertSQL = "INSERT INTO postgres.schoolconsoleapp.courses(course_name, course_description) " + "VALUES(?, ?)";
    private final String coursesIdQUERY = "SELECT id FROM postgres.schoolconsoleapp.courses";
    private final String courseListQUERY = "SELECT * FROM schoolconsoleapp.courses;";

    public void insertCourse(final Course course) {
        try (Connection connection = ConnectionFactory.getInstance().makeConnection();
        PreparedStatement statement = connection.prepareStatement(insertSQL))  {
            statement.setString(1,course.getName());
            statement.setString(2,course.getCourseDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public void insertCourseList(ArrayList<Course> coursesList) {
        coursesList.forEach((course) ->  insertCourse(course));
    }

    public ArrayList<Integer> getCoursesIdList() {
        ArrayList<Integer> getCoursesIdList = new ArrayList<>();
        try(Connection connection = ConnectionFactory.getInstance().makeConnection();
            PreparedStatement statement = connection.prepareStatement(coursesIdQUERY);
            ResultSet resultSet = statement.executeQuery()) {
            while(resultSet.next()) {
                int studentId = resultSet.getInt(1);
                getCoursesIdList.add(studentId);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }

        return getCoursesIdList;
    }

    public ArrayList<Course> getCourseList(){
        ArrayList<Course> courses = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getInstance().makeConnection();
            PreparedStatement statement = connection.prepareStatement(courseListQUERY);
            ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int courseId = resultSet.getInt(1);
                String courseName = resultSet.getString(2);
                String courseDescription = resultSet.getString(3);
                Course course = new Course(courseName, courseDescription);
                course.setCourseId(courseId);
                courses.add(course);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return courses;
    }
}
