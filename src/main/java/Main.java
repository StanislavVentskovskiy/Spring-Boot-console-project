import ua.com.foxminded.dao.*;
import ua.com.foxminded.menu.ApplicationMenu;
import ua.com.foxminded.reader.CoursesDataReader;
import ua.com.foxminded.datagenerator.GroupGenerator;
import ua.com.foxminded.datagenerator.StudentsGenerator;
import ua.com.foxminded.scriptrunner.SqlScriptRunner;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        SqlScriptRunner sqlScriptRunner = new SqlScriptRunner();
        GroupGenerator groupGenerator = new GroupGenerator();
        GroupsDao insertGroups = new GroupsDao();
        CoursesDataReader coursesDataReader = new CoursesDataReader();
        CoursesDao insertCourses = new CoursesDao();
        StudentsGenerator studentsGenerator = new StudentsGenerator();
        StudentsDao insertStudents = new StudentsDao();
        ApplicationMenu applicationMenu = new ApplicationMenu();

        sqlScriptRunner.runScript();
        groupGenerator.generateTenGroups();
        insertGroups.insertData(groupGenerator.getGroupIdNameLink());
        insertCourses.insertAllCourses(coursesDataReader.getCoursesNameAndDescription());
        studentsGenerator.generateStudentsList(studentsGenerator.createNameList(), studentsGenerator.createSurnameList());
        insertStudents.insertNumberOfStudents(studentsGenerator.generateStudentsList(studentsGenerator.createNameList(), studentsGenerator.createSurnameList()));
        insertStudents.insertSomeCourseToOneStudent();
        applicationMenu.callMenu();
    }
}
