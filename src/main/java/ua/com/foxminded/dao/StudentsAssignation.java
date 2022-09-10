package ua.com.foxminded.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class StudentsAssignation {
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "4321";
    private ArrayList<Integer> coursesId = new ArrayList<>();

    public Connection connect() throws SQLException {

        return DriverManager.getConnection(url,user,password);
    }

    private ArrayList<Integer> createCourseIdList() {
        for (int index = 1; index <= 10; index++) {
            coursesId.add(index);
        }
        Collections.shuffle(coursesId);

        return coursesId;
    }

    public int generateCoursesNumber() {
        int coursesNumber = (int)(Math.random()*(4-1))+1;

        return coursesNumber;
    }

    public void insertSomeCourseToOneStudent(){
        int generatedCoursesNumber = 0;
        int coursesCount = 0;

        for(int studentId = 1; studentId <= 200; studentId++) {
            generatedCoursesNumber = generateCoursesNumber();
            coursesCount = generatedCoursesNumber;

                while(coursesCount > 0) {
                    insertStudentAndCourses(createCourseIdList().get(coursesCount),studentId);
                    coursesCount--;
                }
        }
    }

    public void insertStudentAndCourses(int course_id, int student_id) {
        String SQL = "INSERT INTO postgres.schoolconsoleapp.coursesstudents(course_id, student_id) " + "VALUES(?, ?)";
        try(
                Connection connection = connect();
                PreparedStatement statement = connection.prepareStatement(SQL);) {
                statement.setInt(1,course_id);
                statement.setInt(2,student_id);
                statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
