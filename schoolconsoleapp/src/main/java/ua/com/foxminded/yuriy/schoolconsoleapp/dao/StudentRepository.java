package ua.com.foxminded.yuriy.schoolconsoleapp.dao;

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

	@Query("SELECT s FROM Student s WHERE s.firstName=:firstName AND s.lastName=:lastName")
	Student getByName(@Param("firstName") String firstName, @Param("lastName") String lastName);

	@Query("SELECT DISTINCT s FROM Student s LEFT JOIN FETCH s.courses LEFT JOIN FETCH s.group ORDER BY s.id")
	List<Student> getAll();

	@Query("SELECT COUNT(s) FROM Student s WHERE s.group =:group")
	int studentsCountByGroup(@Param("group") Group group);

	List<Student> findAllByCoursesContains(Course course);

	void delete(Student student);
	
	default void addAll(List<Student> students) {
		saveAll(students);
	}

	default int addStudent(Student student) {
		save(student);
		return student.getId();
	}

	default void update(Student student) {
		save(student);
	}
}
