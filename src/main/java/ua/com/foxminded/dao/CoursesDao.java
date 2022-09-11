package ua.com.foxminded.dao;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class CoursesDao {
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "4321";

    public Connection connect() throws SQLException {

        return DriverManager.getConnection(url,user,password);
    }

    public void insertAllCourses(HashMap<String, String> coursesList) {
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

    public HashMap<Integer,String> getCoursesAndId() {
        HashMap<Integer,String> coursesIdAndName = new HashMap<>();
        String SQL = "SELECT id, course_name FROM postgres.schoolconsoleapp.courses";

        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SQL)) {

            while(rs.next()) {
                coursesIdAndName.put(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return coursesIdAndName;
    }
}
