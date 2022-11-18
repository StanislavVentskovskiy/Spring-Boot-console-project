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

    public Student addNewStudent(String name, String surname){
        Student student = new Student(name,surname);
        return studentDaoImpl.addStudent(student);
    }

    public Student updateStudent(int studentId, String name, String surname){
        return studentDaoImpl.updateStudent(studentId, name, surname);
    }

    public ArrayList<String> getAllGroupsWithEqualOrLessStudents(int studentsNumber) {
        LOG.info("Enter method getAllGroupsWithEqualOrLessStudents()");
        ArrayList<String> groupList = studentDaoImpl.getListOfGroupNamesWithEqualOrLessStudentsNumber(studentDaoImpl.getGroupsWithEqualOrLessStudentsNumber(studentsNumber));
        LOG.info("Leave method getAllGroupsWithEqualOrLessStudents()");
        return groupList;
    }

    public Student addStudent(Student student){
        LOG.info("Entered method addStudent()");
        return studentDaoImpl.addStudent(student);
    }

    public ArrayList<Student> getStudentList(){
        LOG.info("Enter getStudentList()");
        return studentDaoImpl.getStudentsList();
    }

    public boolean deleteStudentById(int studentId){
        LOG.info("Enter method deleteStudentById()");
        return studentDaoImpl.removeStudentById(studentId);
    }

    public ArrayList<Integer> getStudentIdList(ArrayList<Student> studentsList){
        LOG.info("Enter method getStudentIdList()");
        return studentDaoImpl.getStudentsIdList(studentsList);
    }
}
