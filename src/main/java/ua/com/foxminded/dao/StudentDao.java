package ua.com.foxminded.dao;

import ua.com.foxminded.model.Group;
import ua.com.foxminded.model.Student;
import java.util.ArrayList;
import java.util.List;

public interface StudentDao {
    int insertStudent(Student student);

    void insertStudentsList(ArrayList<Student> studentList);

    int deleteStudentById(int id);

    List<Integer> getStudentsIdList();

    List<Group> getGroupsWithEqualOrLessStudentsNumber(int studentsNumber);

    List<Student> getStudentsList();
}
