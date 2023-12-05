package ua.com.foxminded.yuriy.schoolconsoleapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

	default void addAll(List<Course> courses) {
		saveAll(courses);
	}

	@Query("SELECT c FROM Course c WHERE c.id NOT IN (SELECT cr.id FROM Student s JOIN s.courses cr WHERE s.id = :studentId) ORDER BY c.id")
	List<Course> getAvailableCourses(@Param("studentId") int studentId);

	@Query("SELECT c FROM Course c WHERE c.id IN (SELECT cr.id FROM Student s JOIN s.courses cr WHERE s.id = :studentId) ORDER BY c.id")
	List<Course> getByStudentId(@Param("studentId") int studentId);

	default Course getById(int courseId) {
		return findById(courseId).orElse(null);
	}

	default List<Course> getAllCourses() {
		return findAll();
	}
}
