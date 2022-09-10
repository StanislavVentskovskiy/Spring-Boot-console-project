package ua.com.foxminded.dao;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class InsertCourses {
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "4321";

    public Connection connect() throws SQLException {

        return DriverManager.getConnection(url,user,password);
    }

    public void insertCourses(HashMap<String,String> coursesList) {
        String SQL = "INSERT INTO postgres.schoolconsoleapp.courses(course_name, course_description) " + "VALUES(?,?)";

        try(
           Connection connection = connect();
           PreparedStatement statement = connection.prepareStatement(SQL);) {
               for (Map.Entry<String,String> entry : coursesList.entrySet()) {
                   statement.setString(1, String.valueOf(entry.getKey()));
                   statement.setString(2, entry.getValue());
                   statement.executeUpdate();
            }
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
