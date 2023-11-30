package ua.com.foxminded.yuriy.schoolconsoleapp.dao.impl;


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
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.StudentDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = StudentDaoImpl.class))
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(scripts = { "/schema.sql", "/test-data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = { "/test-data-clear.sql" }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class StudentDaoIT {

	@Autowired
	private StudentDao studentDao;

	@Test
	void injectedComponentAreNotNull() {
		assertThat(studentDao).isNotNull();
	}

	@Test
	void shouldAddAllStudents() {
		Group group = new Group("AA-11", 1);
		Student student = new Student(10, group, "TestName", "TestLastName");
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
		List<Student> studentOnCourse = studentDao.getAllByCourse(course);

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
		Group group = new Group("Group X", 1);
		Student student = new Student(1, group, "John", "Doe");
		studentDao.delete(student);
		Student deletedStudent = studentDao.getById(studentId);
		assertNull(deletedStudent);
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

		Student studentFromDb = studentDao.getById(studentId);
		assertEquals(student, studentFromDb);
	}

	@Test
	void shouldGetStudentByName() {
		Student student = studentDao.getByName("Bob", "Johnson");
		Group group = new Group("Group X", 1);
		Course course = new Course("History", "World History", 2);
		List<Course> courses = new ArrayList<>();
		courses.add(course);
		Student studentFromDb = new Student(3, group, "Bob", "Johnson");
		studentFromDb.setCourse(courses);
		assertEquals(studentFromDb, student);
	}

	@Test
	void shouldUpdateStudentInfoCorrectly() {
		Group group = new Group("Group Y", 2);
		Student student = new Student(1, group, "New Name", "New Last Name");
		Course course = new Course("History", "World History", 2);
		List<Course> courses = new ArrayList<>();
		courses.add(course);
		student.setCourse(courses);
		studentDao.update(student);
		Student updatedStudent = studentDao.getById(1);
		assertEquals(student, updatedStudent);
	}
}
