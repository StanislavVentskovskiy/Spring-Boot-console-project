package ua.com.foxminded.service;

import ua.com.foxminded.dao.impl.CourseDaoImpl;
import ua.com.foxminded.dao.impl.GroupDaoImpl;
import ua.com.foxminded.dao.impl.StudentsDaoImpl;
import ua.com.foxminded.util.StudentsGenerator;
import ua.com.foxminded.util.CourseGenerator;
import ua.com.foxminded.util.GroupsGenerator;

public class Init {
    public void initializeApplicationData() {
        SqlScriptRunner sqlScriptRunner = new SqlScriptRunner();
        sqlScriptRunner.runScript();
        GroupsGenerator groupGenerator = new GroupsGenerator();
        GroupDaoImpl groupDao = new GroupDaoImpl();
        groupDao.insertGroupList(groupGenerator.generateGroups());
        CourseGenerator courseGenerator = new CourseGenerator();
        CourseDaoImpl courseDao = new CourseDaoImpl();
        courseDao.insertCourseList(courseGenerator.generateCourseList());
        StudentsGenerator studentsGenerator = new StudentsGenerator();
        StudentsGroupsAssignation studentAssignation = new StudentsGroupsAssignation(studentsGenerator.generateStudentsList());
        studentAssignation.assignStudentsToGroups();
        StudentsDaoImpl studentsDaoImpl = new StudentsDaoImpl();
        studentsDaoImpl.insertStudentsList(studentAssignation.getStudentsList());
        StudentsCoursesAssignation studentsCoursesAssignation = new StudentsCoursesAssignation(studentsDaoImpl.getStudentsIdList(), courseDao.getCoursesIdList());
        studentsCoursesAssignation.assignCoursesToStudent();
    }
}
