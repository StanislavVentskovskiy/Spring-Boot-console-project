package ua.com.foxminded.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.dao.CourseDao;
import ua.com.foxminded.dao.repository.CourseRepository;
import ua.com.foxminded.model.Course;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class CourseDaoImpl implements CourseDao {

    @Autowired
    CourseRepository courseRepository;

    private static final Logger LOG = LoggerFactory.getLogger(CourseDaoImpl.class);

    public void addCourseList(ArrayList<Course> coursesList) {
        LOG.info("Enter method insertCourseList()");
        courseRepository.saveAll(coursesList);
        LOG.info("Leave method insertCourseList()");
    }

    @Override
    public ArrayList<Integer> getCoursesIdList() {
        LOG.info("Enter method getCoursesIdList()");
        return courseRepository.getCoursesIdList();
    }

    public void addCourse(final Course course){
        LOG.info("Enter method insertCourse()");
        courseRepository.save(course);
        LOG.info("Leave method insertCourse()");
    }

    public ArrayList<Course> getCourseList(){
        LOG.info("Enter method getCourseList()");
        return (ArrayList<Course>) courseRepository.findAll();
    }
}
