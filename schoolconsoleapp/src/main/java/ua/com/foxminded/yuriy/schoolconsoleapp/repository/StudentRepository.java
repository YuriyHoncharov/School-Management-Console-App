package ua.com.foxminded.yuriy.schoolconsoleapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	@Query("SELECT s FROM Student s LEFT JOIN FETCH s.courses WHERE s.id = :id")
	Student getById(@Param("id") int id);

	@Query("SELECT DISTINCT s FROM Student s LEFT JOIN FETCH s.courses LEFT JOIN FETCH s.group ORDER BY s.id")
	List<Student> getAll();

	int countByGroup(Group group);

	List<Student> getAllByCoursesContains(Course course);		

}
