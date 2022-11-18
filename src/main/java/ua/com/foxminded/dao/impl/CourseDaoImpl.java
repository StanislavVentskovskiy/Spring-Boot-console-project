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
import java.util.Optional;

@Repository
@Transactional
public class CourseDaoImpl implements CourseDao {

    @Autowired
    CourseRepository courseRepository;

    private static final Logger LOG = LoggerFactory.getLogger(CourseDaoImpl.class);

    public Course updateCourse(int courseId, String courseName, String courseDescription){
        LOG.info("Enter updateCourse() method");
        Course course = courseRepository.findById(courseId).orElse(null);
        course.setName(courseName);
        course.setCourseDescription(courseDescription);
        LOG.info("Leave updateCourse() method");

        return courseRepository.save(course);
    }

    public Optional<Course> findById(int id){
        LOG.info("Enter findById() method");
        return courseRepository.findById(id);
    }

    public Course addNewCourse(Course course){
        LOG.info("Enter addNewCourse() method");
        return courseRepository.save(course);
    }

    public void deleteCourse(int id){
        LOG.info("Enter deleteCourse() method");
        courseRepository.deleteById(id);
        LOG.info("Leave deleteCourse() method");
    }

    public void addCourseList(ArrayList<Course> coursesList) {
        LOG.info("Enter method insertCourseList()");
        courseRepository.saveAll(coursesList);
        LOG.info("Leave method insertCourseList()");
    }

    @Override
    public ArrayList<Integer> getCoursesIdList(ArrayList<Course> courseList) {
        LOG.info("Enter method getCoursesIdList()");
        ArrayList<Integer> courseIdList =  new ArrayList<Integer>();
        courseList.forEach(course -> courseIdList.add(course.getCourseId()));
        return courseIdList;
    }

    public ArrayList<Course> getCourseList(){
        LOG.info("Enter method getCourseList()");
        return (ArrayList<Course>) courseRepository.findAll();
    }
}
