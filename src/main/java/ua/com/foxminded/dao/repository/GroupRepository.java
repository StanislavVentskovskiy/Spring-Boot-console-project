package ua.com.foxminded.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.model.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
