package ua.com.foxminded.yuriy.schoolconsoleapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

	@Query("SELECT c FROM Course c WHERE c.id NOT IN (SELECT cr.id FROM Student s JOIN s.courses cr WHERE s.id = :studentId) ORDER BY c.id")
	List<Course> findAllByStudents_IdNot(int studentId);

	}
