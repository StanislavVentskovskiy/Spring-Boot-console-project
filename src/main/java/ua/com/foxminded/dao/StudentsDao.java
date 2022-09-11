package ua.com.foxminded.dao;

import ua.com.foxminded.student.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class StudentsDao {
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "4321";
    private ArrayList<Integer> coursesId = new ArrayList<>();

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }

    public int generateStudentsNumber() {
        int studentsInGroup = (int)(Math.random()*(30-10))+10;

        return studentsInGroup;
    }

    public void insertStudent(int group_id, Student student) {
        String SQL = "INSERT INTO postgres.schoolconsoleapp.students(group_id, first_name, last_name) " + "VALUES(?, ?, ?)";

        try(
            Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(SQL);) {
            statement.setInt(1,group_id);
            statement.setString(2,student.getName());
            statement.setString(3,student.getSurname());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertStudent(Student student) {
        String SQL = "INSERT INTO postgres.schoolconsoleapp.students(first_name, last_name) " + "VALUES(?, ?)";

        try(
            Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(SQL);) {
            statement.setString(1,student.getName());
            statement.setString(2,student.getSurname());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertStudentInRange(int groupId, int startIndex, int endIndex, LinkedHashMap<Integer, Student> generatedStudentNames) {
        while(startIndex < endIndex) {
            insertStudent(groupId, generatedStudentNames.get(endIndex));
            endIndex--;
        }
    }

    public void insertStudentInRange(int startIndex, int endIndex, LinkedHashMap<Integer, Student> generatedStudentNames) {
        while(startIndex < endIndex) {
            insertStudent(generatedStudentNames.get(endIndex));
            endIndex--;
        }
    }

    public void insertNumberOfStudents(LinkedHashMap<Integer, Student> generatedStudentNames) {
        int firstStudent = 0;
        int lastStudent = 0;
        int generatedStudentsNumber;

        for (int groupCount = 1; groupCount <= 10; groupCount++) {
            if(lastStudent < 170) {
                generatedStudentsNumber = generateStudentsNumber();
                lastStudent = lastStudent + generatedStudentsNumber;
                insertStudentInRange(groupCount, firstStudent, lastStudent, generatedStudentNames);
                firstStudent = firstStudent + generatedStudentsNumber;
            }
        }
           insertStudentInRange(lastStudent,200,generatedStudentNames);
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
