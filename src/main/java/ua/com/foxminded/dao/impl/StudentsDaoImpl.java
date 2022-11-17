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
import java.util.ArrayList;

@Repository
@Transactional
public class StudentsDaoImpl implements StudentDao {

    @Autowired
    StudentRepository studentRepository;

    private static final Logger LOG = LoggerFactory.getLogger(StudentsDaoImpl.class);

    public Student addStudent(final Student student) {
        LOG.info("Enter addStudent() method");
        return studentRepository.save(student);
    }

    public void addStudentList(ArrayList<Student> students) {
        LOG.info("Enter method + insertStudentsList()");
        studentRepository.saveAll(students);
        LOG.info("Leave method + insertStudentsList()");
    }

    public ArrayList<Integer> getStudentsIdList(ArrayList<Student> studentsList) {
        LOG.info("Enter method getStudentsIdList()");
        return studentRepository.getStudentIdList();
    }

    public ArrayList<Group> getGroupsWithEqualOrLessStudentsNumber(int studentsNumber) {
        LOG.info("Enter method getGroupsWithEqualOrLessStudentsNumber()");
        return (ArrayList<Group>) studentRepository.getGroupsWithEqualOrLessStudentsNumber(studentsNumber);
    }

    public ArrayList<String> getGroupsWithEqualOrLessStudentsNumber(ArrayList<Group> groups) {
        LOG.info("Enter method getGroupsWithEqualOrLessStudentsNumber()");
        ArrayList<String> groupsNames = new ArrayList<>();
        groups.forEach(group -> groupsNames.add(group.getName()));
        LOG.info("Leave method getGroupsWithEqualOrLessStudentsNumber()");

        return groupsNames;
    }

    public boolean removeStudentById(int studentId) {
        LOG.info("Enter method deleteStudentById()");
        if (studentRepository.existsById(studentId)) {
            studentRepository.deleteById(studentId);
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Student> getStudentsList(){
        LOG.info("Enter method getStudentsList()");
        return (ArrayList<Student>) studentRepository.findAll();
    }
}
