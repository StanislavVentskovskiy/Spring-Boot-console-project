package ua.com.foxminded.dao;

import ua.com.foxminded.student.Student;
import java.sql.*;
import java.util.LinkedHashMap;

public class InsertStudents {
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "4321";

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

        for (int groupCount = 0; groupCount < 10; groupCount++) {
            if(lastStudent < 170) {
                generatedStudentsNumber = generateStudentsNumber();
                lastStudent = lastStudent + generatedStudentsNumber;
                insertStudentInRange(groupCount, firstStudent, lastStudent, generatedStudentNames);
                firstStudent = firstStudent + generatedStudentsNumber;
            }
        }
           insertStudentInRange(lastStudent,200,generatedStudentNames);
    }
}
