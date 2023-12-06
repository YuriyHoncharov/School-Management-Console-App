package ua.com.foxminded.yuriy.schoolconsoleapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Integer> {
	
	@Query("SELECT g FROM Group g WHERE (SELECT COUNT(s) FROM Student s WHERE s.group = g) <= :studentCount")
	List<Group> getAllLessOrEqual(@Param("studentCount") Long studentCount);
	
}
