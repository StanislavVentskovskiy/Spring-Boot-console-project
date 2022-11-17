package ua.com.foxminded.service;

import ua.com.foxminded.model.Student;
import java.util.ArrayList;
import java.util.List;

public interface StudentService {

    ArrayList<String> getAllGroupsWithEqualOrLessStudents(int studentsNumber);

    Student addStudent(Student student);

    List<Student> getStudentList();

    boolean deleteStudentById(int studentId);

    ArrayList<Integer> getStudentIdList(ArrayList<Student> studentsList);
}
