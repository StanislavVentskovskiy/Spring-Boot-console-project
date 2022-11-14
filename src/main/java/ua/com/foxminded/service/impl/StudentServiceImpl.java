package ua.com.foxminded.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.dao.impl.StudentsDaoImpl;
import ua.com.foxminded.model.Student;
import ua.com.foxminded.service.StudentService;
import ua.com.foxminded.service.validator.Validator;
import java.util.ArrayList;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentsDaoImpl studentDaoImpl;

    @Autowired
    Validator applicationMenuValidator;

    private static final Logger LOG = LoggerFactory.getLogger(StudentServiceImpl.class);

    public ArrayList<String> getAllGroupsWithEqualOrLessStudents(int studentsNumber) {
        LOG.info("Enter method getAllGroupsWithEqualOrLessStudents()");
        ArrayList<String> groupList = studentDaoImpl.getGroupsWithEqualOrLessStudentsNumber(studentDaoImpl.getGroupsWithEqualOrLessStudentsNumber(studentsNumber));
        LOG.info("Leave method getAllGroupsWithEqualOrLessStudents()");
        return groupList;
    }

    public int addStudent(Student student){
        LOG.info("Entered method addStudent()");
        return studentDaoImpl.addStudent(student);
    }

    public ArrayList<Student> getStudentList(){
        LOG.info("Enter getStudentList()");
        return studentDaoImpl.getStudentsList();
    }

    public int deleteStudentById(int studentId){
        LOG.info("Enter method deleteStudentById()");
        return studentDaoImpl.removeStudentById(studentId);
    }

    public ArrayList<Integer> getStudentIdList(ArrayList<Student> studentsList){
        LOG.info("Enter method getStudentIdList()");
        return studentDaoImpl.getStudentsIdList(studentsList);
    }
}
