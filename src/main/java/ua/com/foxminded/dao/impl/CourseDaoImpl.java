package ua.com.foxminded.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.com.foxminded.dao.CourseDao;
import ua.com.foxminded.dao.mapper.CourseMapper;
import ua.com.foxminded.model.Course;
import java.util.ArrayList;

@Component
public class CourseDaoImpl implements CourseDao {
    private final String insertCourseSQL = "INSERT INTO postgres.schoolconsoleapp.courses(course_name, course_description) " + "VALUES(?, ?)";
    private final String coursesIdQUERY = "SELECT id FROM postgres.schoolconsoleapp.courses";
    private final String courseListQUERY = "SELECT * FROM schoolconsoleapp.courses;";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertCourse(final Course course) {
        return jdbcTemplate.update(insertCourseSQL, course.getName(), course.getCourseDescription());
    }

    public void insertCourseList(ArrayList<Course> coursesList) {
        coursesList.forEach((course) ->  insertCourse(course));
    }

    public ArrayList<Integer> getCoursesIdList() {
        return (ArrayList<Integer>) jdbcTemplate.queryForList(coursesIdQUERY, Integer.class);
    }

    public ArrayList<Course> getCourseList(){
        return (ArrayList<Course>) jdbcTemplate.query(courseListQUERY, new CourseMapper());
    }
}
