package ua.com.foxminded.dao.impl;

import org.w3c.dom.DOMException;
import ua.com.foxminded.dao.StudentDao;
import ua.com.foxminded.exception.DAOException;
import ua.com.foxminded.model.Student;
import ua.com.foxminded.util.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;

public class StudentsDaoImpl implements StudentDao {
    private final Connection connection = ConnectionFactory.getInstance().makeConnection();
    private final String SQL = "INSERT INTO postgres.schoolconsoleapp.students(group_id, first_name, last_name) " + "VALUES(NULLIF(?,'0'), ?, ?)";
    private final String QUERY = "SELECT id FROM postgres.schoolconsoleapp.students";
    private final String groupsWithCertainStudentNumberQUERY =
        "SELECT schoolconsoleapp.groups.group_name,  COUNT(*) AS stud_count\n" +
            "FROM schoolconsoleapp.students\n" +
            "LEFT JOIN schoolconsoleapp.groups ON schoolconsoleapp.students.group_id=schoolconsoleapp.groups.id\n" +
            "WHERE schoolconsoleapp.groups.group_name IS NOT NULL\n" +
            "GROUP BY groups.group_name\n" +
            "HAVING COUNT(*) > 0";
    private final String deleteStudentQUERY = "ALTER TABLE schoolconsoleapp.coursesstudents DROP \n" +
        "   CONSTRAINT student_id;\n" +
        "ALTER TABLE schoolconsoleapp.coursesstudents ADD \n" +
        "   CONSTRAINT student_id \n" +
        "      FOREIGN KEY (student_id )\n" +
        "      REFERENCES schoolconsoleapp.students (id)\n" +
        "      ON DELETE CASCADE;\n" +
        "DELETE FROM schoolconsoleapp.students WHERE id =";

    public void insertStudent(Student student) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setInt(1, student.getGroup());
            statement.setString(2, student.getName());
            statement.setString(3, student.getSurname());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public void insertStudentsList(ArrayList<Student> students) {
        students.forEach((student) -> insertStudent(student));
    }

    public ArrayList<Integer> getStudentsIdList() {
        ArrayList<Integer> studentsIdList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int studentId = resultSet.getInt(1);
                studentsIdList.add(studentId);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return studentsIdList;
    }

    public ArrayList<String> getGroupsWithEqualOrLessStudentsNumber(int studentsNumber) {
        ArrayList<String> groupList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(groupsWithCertainStudentNumberQUERY + studentsNumber);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String groupName = resultSet.getString(1);
                groupList.add(groupName);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return groupList;
    }

    public void deleteStudentById(int studentId) {
        try (PreparedStatement statement = connection.prepareStatement(deleteStudentQUERY + studentId);) {
         int deleteStatus = statement.executeUpdate();
            if (deleteStatus > 0) {
                System.out.println("Student with id " + studentId + " deleted");
            } else {
                System.out.println("No student with " + studentId + " id found");
            }
            connection.close();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }
}
