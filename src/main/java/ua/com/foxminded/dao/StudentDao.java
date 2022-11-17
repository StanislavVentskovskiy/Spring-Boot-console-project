package ua.com.foxminded.dao;

import ua.com.foxminded.model.Group;
import ua.com.foxminded.model.Student;
import java.util.ArrayList;
import java.util.List;

public interface StudentDao {

    Student addStudent(Student student);

    void addStudentList(ArrayList<Student> studentList);

    boolean removeStudentById(int id);

    ArrayList<Integer> getStudentsIdList(ArrayList<Student> studentList);

    List<Group> getGroupsWithEqualOrLessStudentsNumber(int studentsNumber);

    List<Student> getStudentsList();
}
