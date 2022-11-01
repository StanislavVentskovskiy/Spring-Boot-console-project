package ua.com.foxminded.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.dao.impl.StudentsDaoImpl;
import ua.com.foxminded.model.Student;
import ua.com.foxminded.service.StudentService;
import ua.com.foxminded.service.validator.Validator;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentsDaoImpl studentDaoImpl;

    @Autowired
    Validator applicationMenuValidator;

    public ArrayList<String> getAllGroupsWithEqualOrLessStudents(int studentsNumber) {
        ArrayList<String> groupList = studentDaoImpl.getGroupsWithEqualOrLessStudentsNumber(studentDaoImpl.getGroupsWithEqualOrLessStudentsNumber(studentsNumber));
        return groupList;

    }

    public int addStudent(Student student){
        return studentDaoImpl.insertStudent(student);
    }

    public List<Student> getStudentList(){
        return studentDaoImpl.getStudentsList();
    }

    public int deleteStudentById(int studentId){
        return studentDaoImpl.deleteStudentById(studentId);
    }

    public ArrayList<Integer> getStudentIdList(){
        return studentDaoImpl.getStudentsIdList();
    }
}
