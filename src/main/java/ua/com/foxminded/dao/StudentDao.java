package ua.com.foxminded.dao;

import ua.com.foxminded.model.Student;
import java.util.ArrayList;

public interface StudentDao {
    void insertStudent(Student student);

    void insertStudentsList(ArrayList<Student> studentList);

    void deleteStudentById(int id);

    ArrayList<Integer> getStudentsIdList();

    ArrayList<String> getGroupsWithEqualOrLessStudentsNumber(int studentsNumber);

    ArrayList<Student> getStudentsList();
}
