package ua.com.foxminded.yuriy.schoolconsoleapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Integer> {

	default void addAll(List<Group> groups) {
		saveAll(groups);
	}

	@Query("SELECT g FROM Group g WHERE (SELECT COUNT(s) FROM Student s WHERE s.group = g) <= :studentCount")
	List<Group> getAllLessOrEqual(@Param("studentCount") int studentCount);

	default List<Group> getAll() {
		return findAll();
	}

	default Group getById(int groupId) {
		return findById(groupId).orElse(null);
	}

}
