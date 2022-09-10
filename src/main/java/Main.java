import ua.com.foxminded.dao.*;
import ua.com.foxminded.reader.CoursesDataReader;
import ua.com.foxminded.datagenerator.GroupGenerator;
import ua.com.foxminded.datagenerator.StudentsGenerator;
import ua.com.foxminded.scriptrunner.SqlScriptRunner;

public class Main {
    public static void main(String[] args) {
        SqlScriptRunner sqlScriptRunner = new SqlScriptRunner();
        GroupGenerator groupGenerator = new GroupGenerator();
        InsertGroups insertGroups = new InsertGroups();
        CoursesDataReader coursesDataReader = new CoursesDataReader();
        InsertCourses insertCourses = new InsertCourses();
        StudentsGenerator studentsGenerator = new StudentsGenerator();
        InsertStudents insertStudents = new InsertStudents();
        StudentsAssignation studentsAssignation = new StudentsAssignation();

        sqlScriptRunner.runScript();
        groupGenerator.generateTenGroups();
        insertGroups.insertGroups(groupGenerator.getGroupIdNameLink());
        insertCourses.insertCourses(coursesDataReader.getCoursesNameAndDescription());
        studentsGenerator.generateStudentsList(studentsGenerator.createNameList(), studentsGenerator.createSurnameList());
        insertStudents.insertNumberOfStudents(studentsGenerator.generateStudentsList(studentsGenerator.createNameList(), studentsGenerator.createSurnameList()));
        studentsAssignation.insertSomeCourseToOneStudent();
    }
}
