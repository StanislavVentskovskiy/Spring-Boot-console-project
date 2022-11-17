package ua.com.foxminded.dao.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.model.Course;
import java.util.ArrayList;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select course.courseId from Course course")
    ArrayList<Integer> getCoursesIdList();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO schoolconsoleapp.coursesstudents(course_id, student_id)" + "VALUES( :courseId, :studId)", nativeQuery = true)
    void addStudentAndCourse(
        @Param("courseId") int courseId,
        @Param("studId") int studId
        );

    @Query(value = "SELECT schoolconsoleapp.courses.id, schoolconsoleapp.courses.course_name, schoolconsoleapp.courses.course_description\n" +
        "FROM schoolconsoleapp.courses\n" +
        "JOIN schoolconsoleapp.coursesstudents\n" +
        "ON coursesstudents.course_id = courses.id\n" +
        "WHERE coursesstudents.student_id = :studentId", nativeQuery = true)
    ArrayList<Course> getCourseListByStudentId(@Param("studentId") int studentId);

    @Query(value = "DELETE FROM schoolconsoleapp.coursesstudents WHERE course_id = :courseId AND student_id = :studentId RETURNING 1;", nativeQuery = true)
    Integer removeStudentFromCourse(
        @Param("courseId") int courseId,
        @Param("studentId") int studentId);
}
