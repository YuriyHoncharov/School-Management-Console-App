package ua.com.foxminded.yuriy.schoolconsoleapp.dao.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.mappers.CourseMapper;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.mappers.StudentMapper;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;

@JdbcTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(scripts = { "/schema.sql", "/test-data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = { "/test-data-clear.sql" }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class StudentDaoIT {

	private StudentDaoImpl studentDao;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@BeforeEach
	void setUp() {
		studentDao = new StudentDaoImpl(jdbcTemplate, new CourseMapper(), new StudentMapper(new CourseMapper()));
	}

	@Test
	void shouldAddAllStudents() {
		Student student = new Student(10, 1, "TestName", "TestLastName");
		List<Student> students = new ArrayList<>();
		students.add(student);
		studentDao.addAll(students);
		assertEquals(7, studentDao.getAll().size());
	}

	@Test
	void shouldGetAllStudents() {
		List<Student> students = studentDao.getAll();
		assertEquals(6, students.size());
	}

	@Test
	void shouldGetAllByCourse() {
		int courseId = 2;
		List<Student> studentOnCourse = studentDao.getAllByCourse(courseId);
		Student student1 = new Student(3, 1, "Bob", "Johnson");
		Student student2 = new Student(5, 2, "Michael", "Wilson");
		Student student3 = new Student(6, 2, "Sarah", "Lee");
		assertEquals(student1, studentOnCourse.get(0));
		assertEquals(student2, studentOnCourse.get(1));
		assertEquals(student3, studentOnCourse.get(2));
	}

	@Test
	void shouldAddStudentCorrectly() {
		Student student = new Student("TestName", "TestLastName");
		int studentId = studentDao.add(student);
		assertNotNull(studentId);
		Student insertedStudent = studentDao.getById(studentId);
		assertEquals(student.getFirstName(), insertedStudent.getFirstName());
		assertEquals(student.getLastName(), insertedStudent.getLastName());
	}

	@Test
	void shouldDeleteStudentById() {
		int studentId = 1;
		studentDao.deleteById(studentId);
		Student deletedStudent = studentDao.getById(studentId);
		assertNull(deletedStudent);
	}

	@Test
	void shouldReturnStudentById() {
		int studentId = 3;
		Student studentFromDb = studentDao.getById(studentId);
		Student student = new Student(3, 1, "Bob", "Johnson");
		Course course = new Course("History", "World History", 2);
		List<Course> courses = new ArrayList<>();
		courses.add(course);
		student.setCourse(courses);
		assertEquals(student, studentFromDb);
	}

	@Test
	void shouldGetStudentByName() {
		Student student = studentDao.getByName("Bob", "Johnson");
		Student studentFromDb = new Student(3, 1, "Bob", "Johnson");
		assertEquals(studentFromDb, student);
	}

	@Test
	void shouldUpdateStudentInfoCorrectly() {
		Student student = new Student(1, 2, "New Name", "New Last Name");
		Course course = new Course("History", "World History", 2);
		List<Course> courses = new ArrayList<>();
		courses.add(course);
		student.setCourse(courses);
		studentDao.update(student);
		Student updatedStudent = studentDao.getById(1);
		assertEquals(student, updatedStudent);
	}
}
