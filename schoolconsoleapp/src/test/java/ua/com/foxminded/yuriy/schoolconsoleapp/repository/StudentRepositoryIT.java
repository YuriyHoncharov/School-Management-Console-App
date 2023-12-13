package ua.com.foxminded.yuriy.schoolconsoleapp.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.repository.StudentRepository;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.StudentService;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
		StudentRepository.class, StudentService.class }))
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(scripts = { "/schema.sql", "/test-data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = { "/test-data-clear.sql" }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class StudentRepositoryIT {

	@Autowired
	private StudentRepository studentRepository;

	@Test
	void injectedComponentAreNotNull() {
		assertThat(studentRepository).isNotNull();
	}

	@Test
	void shouldGetAllStudents() {
		List<Student> students = studentRepository.getAll();
		assertEquals(6, students.size());
	}

	@Test
	void shouldGetAllByCourse() {
		Course course = new Course("History", "World History", 2);
		List<Course> courses = new ArrayList<>();
		courses.add(course);

		Group group = new Group("Group X", 1);
		Group group2 = new Group("Group Y", 2);

		Student student1 = new Student(3, group, "Bob", "Johnson");
		Student student2 = new Student(5, group2, "Michael", "Wilson");
		Student student3 = new Student(6, group2, "Sarah", "Lee");
		student1.setCourse(courses);
		student2.setCourse(courses);
		student3.setCourse(courses);
		List<Student> studentOnCourse = studentRepository.getAllByCoursesContains(course);

		assertEquals(student1, studentOnCourse.get(0));
		assertEquals(student2, studentOnCourse.get(1));
		assertEquals(student3, studentOnCourse.get(2));
	}

	@Test
	void shouldReturnStudentById() {
		int studentId = 3;

		Group group = new Group("Group X", 1);
		Student student = new Student(3, group, "Bob", "Johnson");
		Course course = new Course("History", "World History", 2);
		List<Course> courses = new ArrayList<>();
		courses.add(course);
		student.setCourse(courses);

		Student studentFromDb = studentRepository.getById(studentId);
		assertEquals(student, studentFromDb);
	}
	
	@Test
	void shouldReturnCorrectCountNumber() {
		Group group = new Group("Group X", 1);
		int count = studentRepository.countByGroup(group);
		assertEquals(3, count);
	}

}
