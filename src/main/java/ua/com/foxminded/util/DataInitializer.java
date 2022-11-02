package ua.com.foxminded.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ua.com.foxminded.controller.LoggingController;
import ua.com.foxminded.dao.impl.CourseDaoImpl;
import ua.com.foxminded.dao.impl.GroupDaoImpl;
import ua.com.foxminded.dao.impl.StudentsDaoImpl;
import ua.com.foxminded.util.generator.CourseGenerator;
import ua.com.foxminded.util.generator.GroupsGenerator;
import ua.com.foxminded.util.generator.StudentsGenerator;

@Service
public class DataInitializer {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private GroupsGenerator groupGenerator;

    @Autowired
    private GroupDaoImpl groupDao;

    @Autowired
    private CourseGenerator courseGenerator;

    @Autowired
    private CourseDaoImpl courseDao;

    @Autowired
    private StudentsGenerator studentsGenerator;

    @Autowired
    private StudentsDaoImpl studentsDaoImpl;

    @Autowired
    private StudentsGroupsAssignation studentAssignation;

    @Autowired
    private StudentsCoursesAssignation studentsCoursesAssignation;

    @Autowired
    private LoggingController loggingController;

    public void initializeApplicationData() {
        groupDao.insertGroupList(groupGenerator.generateGroups());
        courseDao.insertCourseList(courseGenerator.generateCourseList());
        studentAssignation.setStudentsList(studentsGenerator.generateStudentsList());
        studentAssignation.assignStudentsToGroups();
        studentsDaoImpl.insertStudentsList(studentAssignation.getStudentsList());
        studentsCoursesAssignation.setCoursesIdList(courseDao.getCoursesIdList());
        studentsCoursesAssignation.setStudentsIdList(studentsDaoImpl.getStudentsIdList());
        studentsCoursesAssignation.assignCoursesToStudent();
        loggingController.getLogger();
    }
}
