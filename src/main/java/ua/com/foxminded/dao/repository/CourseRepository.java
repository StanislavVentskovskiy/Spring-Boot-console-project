package ua.com.foxminded.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
