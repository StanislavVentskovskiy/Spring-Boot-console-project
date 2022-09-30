package ua.com.foxminded.service;

import ua.com.foxminded.dao.impl.CourseDaoImpl;
import ua.com.foxminded.dao.impl.GroupDaoImpl;
import ua.com.foxminded.dao.impl.StudentsDaoImpl;
import ua.com.foxminded.util.StudentsGenerator;
import ua.com.foxminded.util.CourseGenerator;
import ua.com.foxminded.util.GroupsGenerator;

public class DataInitializer {
    private SqlScriptRunner sqlScriptRunner = new SqlScriptRunner();
    private GroupsGenerator groupGenerator = new GroupsGenerator();
    private GroupDaoImpl groupDao = new GroupDaoImpl();
    private CourseGenerator courseGenerator = new CourseGenerator();
    private CourseDaoImpl courseDao = new CourseDaoImpl();
    private StudentsGenerator studentsGenerator = new StudentsGenerator();
    private StudentsDaoImpl studentsDaoImpl = new StudentsDaoImpl();
    private StudentsGroupsAssignation studentAssignation = new StudentsGroupsAssignation(studentsGenerator.generateStudentsList());
    private StudentsCoursesAssignation studentsCoursesAssignation = new StudentsCoursesAssignation(studentsDaoImpl.getStudentsIdList(), courseDao.getCoursesIdList());

    public DataInitializer() {
    }

    public void initializeApplicationData() {
        sqlScriptRunner.runScript();
        groupDao.insertGroupList(groupGenerator.generateGroups());
        courseDao.insertCourseList(courseGenerator.generateCourseList());
        studentAssignation.assignStudentsToGroups();
        studentsDaoImpl.insertStudentsList(studentAssignation.getStudentsList());
        studentsCoursesAssignation.assignCoursesToStudent();
    }
}
