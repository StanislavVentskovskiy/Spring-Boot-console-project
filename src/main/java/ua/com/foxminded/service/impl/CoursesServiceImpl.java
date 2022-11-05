package ua.com.foxminded.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.dao.impl.CourseDaoImpl;
import ua.com.foxminded.model.Course;
import ua.com.foxminded.service.CourseService;
import java.util.ArrayList;
import static ua.com.foxminded.controller.LoggerController.LOG;

@Service
public class CoursesServiceImpl implements CourseService {

    @Autowired
    CourseDaoImpl courseDaoImpl;

    public ArrayList<Course> getAllCoursesNameList(){
        LOG.info("Enter getAllCoursesNameList() method");
        return courseDaoImpl.getCourseList();
    }
}
