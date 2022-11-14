package ua.com.foxminded.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.dao.CourseDao;
import ua.com.foxminded.model.Course;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Repository
@Transactional
public class CourseDaoImpl implements CourseDao {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOG = LoggerFactory.getLogger(CourseDaoImpl.class);

    public void addCourseList(ArrayList<Course> coursesList) {
        LOG.info("Enter method insertCourseList()");
        coursesList.forEach((course) ->  addCourse(course));
        LOG.info("Leave method insertCourseList()");
    }

    public void addCourse(final Course course){
        LOG.info("Enter method insertCourse()");
        entityManager.persist(course);
        LOG.info("Leave method insertCourse()");
    }

    public ArrayList<Integer> getCoursesIdList() {
       LOG.info("Enter method getCoursesIdList()");
       return (ArrayList<Integer>) entityManager.createQuery("select course.courseId FROM Course course").getResultList();
    }

    public ArrayList<Course> getCourseList(){
        LOG.info("Enter method getCourseList()");
        ArrayList<Course> courseList = (ArrayList<Course>) entityManager.createQuery("SELECT course FROM Course course").getResultList();
        LOG.info("Leave method getCourseList()");

        return courseList;
    }
}
