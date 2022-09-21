package ua.com.foxminded.dao.impl;

import ua.com.foxminded.dao.CourseDao;
import ua.com.foxminded.exception.DAOException;
import ua.com.foxminded.model.Course;
import ua.com.foxminded.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDaoImpl implements CourseDao {
    private final Connection connection = ConnectionFactory.getInstance().makeConnection();
    private final String SQL = "INSERT INTO postgres.schoolconsoleapp.courses(course_name, course_description) " + "VALUES(?, ?)";
    private final String QUERY = "SELECT id FROM postgres.schoolconsoleapp.courses";

    public void insertCourse(Course course) {
        try{
            PreparedStatement statement = connection.prepareStatement(SQL);
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
        java.util.ArrayList<Integer> getCoursesIdList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int studentId = resultSet.getInt(1);
                getCoursesIdList.add(studentId);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }

        return getCoursesIdList;
    }
}
