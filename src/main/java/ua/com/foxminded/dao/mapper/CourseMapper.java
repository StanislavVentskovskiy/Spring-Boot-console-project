package ua.com.foxminded.dao.mapper;

import ua.com.foxminded.model.Course;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseMapper implements RowMapper<Course> {

    @Override
    public Course mapRow(ResultSet resultSet, int i) throws SQLException {
        Course course = new Course(resultSet.getString("course_name"), resultSet.getString("course_description"));
        course.setCourseId(resultSet.getInt("id"));
        return course;
    }
}
