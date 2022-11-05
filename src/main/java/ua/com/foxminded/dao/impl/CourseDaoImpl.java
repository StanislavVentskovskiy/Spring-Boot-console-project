package ua.com.foxminded.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.com.foxminded.dao.CourseDao;
import ua.com.foxminded.dao.mapper.CourseMapper;
import ua.com.foxminded.model.Course;
import java.util.ArrayList;
import static ua.com.foxminded.controller.LoggerController.LOG;

@Component
public class CourseDaoImpl implements CourseDao {
    private final String insertCourseSQL = "INSERT INTO postgres.schoolconsoleapp.courses(course_name, course_description) " + "VALUES(?, ?)";
    private final String coursesIdQUERY = "SELECT id FROM postgres.schoolconsoleapp.courses";
    private final String courseListQUERY = "SELECT * FROM schoolconsoleapp.courses;";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertCourse(final Course course) {
        LOG.debug("Sql statement: " + insertCourseSQL + " " + course.getName() + " " + course.getCourseDescription());
        return jdbcTemplate.update(insertCourseSQL, course.getName(), course.getCourseDescription());
    }

    public void insertCourseList(ArrayList<Course> coursesList) {
        LOG.info("Enter method insertCourseList()");
        coursesList.forEach((course) ->  insertCourse(course));
        LOG.info("Leave method insertCourseList()");
    }

    public ArrayList<Integer> getCoursesIdList() {
        LOG.debug("Sql statement: " + coursesIdQUERY);
        return (ArrayList<Integer>) jdbcTemplate.queryForList(coursesIdQUERY, Integer.class);
    }

    public ArrayList<Course> getCourseList(){
        LOG.debug("Sql statement: " + courseListQUERY);
        return (ArrayList<Course>) jdbcTemplate.query(courseListQUERY, new CourseMapper());
    }
}
