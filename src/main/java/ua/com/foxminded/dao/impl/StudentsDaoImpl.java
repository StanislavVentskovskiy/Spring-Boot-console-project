package ua.com.foxminded.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.dao.StudentDao;
import ua.com.foxminded.model.Group;
import ua.com.foxminded.model.Student;
import ua.com.foxminded.dao.repository.StudentRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Repository
@Transactional
public class StudentsDaoImpl implements StudentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    StudentRepository studentRepository;

    private static final Logger LOG = LoggerFactory.getLogger(StudentsDaoImpl.class);
    private final String insertStudentSQL = "INSERT INTO postgres.schoolconsoleapp.students(group_id, first_name, last_name) " + "VALUES(NULLIF(?,'0'), ?, ?)";
    private final String groupsWithCertainStudentNumberQUERY =
            "SELECT schoolconsoleapp.groups.group_name, schoolconsoleapp.groups.id, COUNT(*) AS stud_count\n" +
            "FROM schoolconsoleapp.students\n" +
            "LEFT JOIN schoolconsoleapp.groups ON schoolconsoleapp.students.group_id=schoolconsoleapp.groups.id\n" +
            "WHERE schoolconsoleapp.groups.group_name IS NOT NULL\n" +
            "GROUP BY groups.group_name, groups.id\n" +
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

    public int addStudent(final Student student) {
        LOG.info("Enter addStudent() method");
        entityManager.createNativeQuery(insertStudentSQL)
            .setParameter(1,student.getGroup())
            .setParameter(2,student.getName())
            .setParameter(3,student.getSurname())
            .executeUpdate();
        LOG.info("Leave addStudent() method");

        return student.getId();
    }

    public void addStudentList(ArrayList<Student> students) {
        LOG.info("Enter method + insertStudentsList()");
        students.forEach((student) -> addStudent(student));
        LOG.info("Leave method + insertStudentsList()");
    }

    public ArrayList<Integer> getStudentsIdList(ArrayList<Student> studentsList) {
        LOG.info("Enter method getStudentsIdList()");
        ArrayList<Integer> studentsIdList = new ArrayList<>();
        studentsList.forEach(student -> studentsIdList.add(student.getId()));
        LOG.info("Leave method getStudentsIdList()");

        return studentsIdList;
    }

    public ArrayList<Group> getGroupsWithEqualOrLessStudentsNumber(int studentsNumber) {
        LOG.info("Enter method getGroupsWithEqualOrLessStudentsNumber()");
        LOG.info("Leave method getGroupsWithEqualOrLessStudentsNumber()");

        return (ArrayList<Group>) entityManager.createNativeQuery(groupsWithCertainStudentNumberQUERY+ " " + studentsNumber, Group.class).getResultList();
    }

    public ArrayList<String> getGroupsWithEqualOrLessStudentsNumber(ArrayList<Group> groups) {
        LOG.info("Enter method getGroupsWithEqualOrLessStudentsNumber()");
        ArrayList<String> groupsNames = new ArrayList<>();
        groups.forEach(group -> groupsNames.add(group.getName()));
        LOG.info("Leave method getGroupsWithEqualOrLessStudentsNumber()");

        return groupsNames;
    }

    public int removeStudentById(int studentId) {
        LOG.info("Enter method deleteStudentById()");
        entityManager.createNativeQuery(deleteForeinKeyStudentId).executeUpdate();
        LOG.info("Leave method deleteStudentById()");

        return entityManager.createNativeQuery(deleteStudentQUERY+studentId).executeUpdate();
    }

    public ArrayList<Student> getStudentsList(){
        LOG.info("Enter method getStudentsList()");
        LOG.info("Leave method getStudentsList()");
        return (ArrayList<Student>) entityManager.createQuery("select student from Student student", Student.class).getResultList();
    }
}
