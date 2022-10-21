package ua.com.foxminded.dao.impl;

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

    @Autowired
    public StudentsDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insertStudent(Student student) {
        int studentInsertionResult = jdbcTemplate.update(insertStudentSQL, student.getGroup(), student.getName(), student.getSurname());
        return studentInsertionResult;
    }

    public void insertStudentsList(ArrayList<Student> students) {
        students.forEach((student) -> insertStudent(student));
    }

    public ArrayList<Integer> getStudentsIdList() {
        return (ArrayList<Integer>) jdbcTemplate.queryForList(getStudentIdQUERY, Integer.class);
    }

    public ArrayList<Group> getGroupsWithEqualOrLessStudentsNumber(int studentsNumber) {
        return (ArrayList<Group>) jdbcTemplate.query(groupsWithCertainStudentNumberQUERY + String.valueOf(studentsNumber), new GroupMapper());
    }

    public ArrayList<String> getGroupsWithEqualOrLessStudentsNumber(ArrayList<Group> groups) {
        ArrayList<String> groupsNames = new ArrayList<>();
        groups.forEach(group -> groupsNames.add(group.getName()));
        return groupsNames;
    }

    public int deleteStudentById(int studentId) {
             jdbcTemplate.update(deleteForeinKeyStudentId);
      return jdbcTemplate.update(deleteStudentQUERY + studentId);
    }

    public ArrayList<Student> getStudentsList(){
        return (ArrayList<Student>) jdbcTemplate.query(studentsListQUERY, new StudentMapper());
    }
}
