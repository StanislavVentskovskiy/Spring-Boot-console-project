package ua.com.foxminded.service;

import ua.com.foxminded.model.Student;
import java.util.ArrayList;
import java.util.List;

public interface StudentService {

    ArrayList<String> getAllGroupsWithEqualOrLessStudents(int studentsNumber);

    int addStudent(Student student);

    List<Student> getStudentList();

    int deleteStudentById(int studentId);

    ArrayList<Integer> getStudentIdList();
}
