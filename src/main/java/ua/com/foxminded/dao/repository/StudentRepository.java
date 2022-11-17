package ua.com.foxminded.dao.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.model.Group;
import ua.com.foxminded.model.Student;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("select student.id from Student student")
    ArrayList<Integer> getStudentIdList();

    @Query(value = "SELECT schoolconsoleapp.groups.group_name, schoolconsoleapp.groups.id, COUNT(*) AS stud_count\n" +
        "FROM schoolconsoleapp.students\n" +
        "LEFT JOIN schoolconsoleapp.groups ON schoolconsoleapp.students.group_id=schoolconsoleapp.groups.id\n" +
        "WHERE schoolconsoleapp.groups.group_name IS NOT NULL\n" +
        "GROUP BY groups.group_name, groups.id\n" +
        "HAVING COUNT(*) <= :count", nativeQuery = true)
    List<Group> getGroupsWithEqualOrLessStudentsNumber(@Param("count") int count);

    @Query(value ="SELECT\n" +
        "schoolconsoleapp.students.first_name,\n" +
        "schoolconsoleapp.students.last_name,\n" +
        "schoolconsoleapp.courses.course_name\n," +
        "schoolconsoleapp.students.id,\n" +
        "schoolconsoleapp.students.group_id\n" +
        "FROM schoolconsoleapp.students\n" +
        "join schoolconsoleapp.coursesstudents\n" +
        "ON schoolconsoleapp.students.id = schoolconsoleapp.coursesstudents.student_id \n" +
        "join schoolconsoleapp.courses\n" +
        "ON schoolconsoleapp.courses.id = schoolconsoleapp.coursesstudents.course_id\n" +
        "WHERE course_name = :courseName", nativeQuery = true)
    ArrayList<Student> getStudentsListRelatedToCourseByName(@Param("courseName") String courseName);
}
