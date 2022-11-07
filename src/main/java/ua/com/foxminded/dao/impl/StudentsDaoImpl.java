package ua.com.foxminded.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.com.foxminded.dao.StudentDao;
import ua.com.foxminded.dao.mapper.GroupMapper;
import ua.com.foxminded.dao.mapper.StudentMapper;
import ua.com.foxminded.model.Group;
import ua.com.foxminded.model.Student;
import java.util.ArrayList;

@Component
public class StudentsDaoImpl implements StudentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String insertStudentSQL = "INSERT INTO postgres.schoolconsoleapp.students(group_id, first_name, last_name) " + "VALUES(NULLIF(?,'0'), ?, ?)";
    private final String getStudentIdQUERY = "SELECT id FROM postgres.schoolconsoleapp.students";
    private final String groupsWithCertainStudentNumberQUERY =
            "SELECT schoolconsoleapp.groups.group_name,  COUNT(*) AS stud_count\n" +
            "FROM schoolconsoleapp.students\n" +
            "LEFT JOIN schoolconsoleapp.groups ON schoolconsoleapp.students.group_id=schoolconsoleapp.groups.id\n" +
            "WHERE schoolconsoleapp.groups.group_name IS NOT NULL\n" +
            "GROUP BY groups.group_name\n" +
            "HAVING COUNT(*) <=";
    private final String deleteStudentQUERY =  "DELETE FROM schoolconsoleapp.students WHERE id =";
    private final String deleteForeinKeyStudentId =
            "ALTER TABLE schoolconsoleapp.coursesstudents DROP\n" +
            "CONSTRAINT student_id;" +
            "ALTER TABLE schoolconsoleapp.coursesstudents ADD\n" +
            "CONSTRAINT student_id\n" +
            "FOREIGN KEY (student_id )\n" +
            "REFERENCES schoolconsoleapp.students (id)\n" +
            "ON DELETE CASCADE;";
    private final String studentsListQUERY = "SELECT * FROM schoolconsoleapp.students";
    private static final Logger LOG = LoggerFactory.getLogger(StudentsDaoImpl.class);

    public int insertStudent(Student student) {
        LOG.debug("SQL statement: " + " " + insertStudentSQL + " " + student.getGroup() + " " + student.getName() + " " + student.getSurname());
        int studentInsertionResult = jdbcTemplate.update(insertStudentSQL, student.getGroup(), student.getName(), student.getSurname());
        return studentInsertionResult;
    }

    public void insertStudentsList(ArrayList<Student> students) {
        LOG.info("Enter method + insertStudentsList()");
        students.forEach((student) -> insertStudent(student));
        LOG.info("Leave method + insertStudentsList()");
    }

    public ArrayList<Integer> getStudentsIdList() {
        LOG.debug("Sql statement: " + getStudentIdQUERY);
        return (ArrayList<Integer>) jdbcTemplate.queryForList(getStudentIdQUERY, Integer.class);
    }

    public ArrayList<Group> getGroupsWithEqualOrLessStudentsNumber(int studentsNumber) {
        LOG.debug("Sql statement: " + groupsWithCertainStudentNumberQUERY + " " + studentsNumber);
        return (ArrayList<Group>) jdbcTemplate.query(groupsWithCertainStudentNumberQUERY + studentsNumber, new GroupMapper());
    }

    public ArrayList<String> getGroupsWithEqualOrLessStudentsNumber(ArrayList<Group> groups) {
        LOG.info("Enter method getGroupsWithEqualOrLessStudentsNumber()");
        ArrayList<String> groupsNames = new ArrayList<>();
        groups.forEach(group -> groupsNames.add(group.getName()));
        LOG.info("Leave method getGroupsWithEqualOrLessStudentsNumber()");
        return groupsNames;
    }

    public int deleteStudentById(int studentId) {
        LOG.debug("Sql statement: " + deleteForeinKeyStudentId);
        jdbcTemplate.update(deleteForeinKeyStudentId);
        LOG.debug("Sql statement: " + deleteStudentQUERY + " " + studentId);
        return jdbcTemplate.update(deleteStudentQUERY + studentId);
    }

    public ArrayList<Student> getStudentsList(){
        LOG.debug("Sql statement: " + studentsListQUERY);
        return (ArrayList<Student>) jdbcTemplate.query(studentsListQUERY, new StudentMapper());
    }
}
