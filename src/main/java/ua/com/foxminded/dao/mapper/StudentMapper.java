package ua.com.foxminded.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ua.com.foxminded.model.Student;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student student = new Student(resultSet.getString("first_name"), resultSet.getString("last_name"));
        student.setId(resultSet.getInt("id"));
        student.setGroup(resultSet.getInt("group_id"));
        return student;
    }
}
