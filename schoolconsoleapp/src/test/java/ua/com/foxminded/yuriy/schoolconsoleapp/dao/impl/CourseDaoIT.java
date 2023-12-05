package ua.com.foxminded.yuriy.schoolconsoleapp.dao.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import ua.com.foxminded.yuriy.schoolconsoleapp.dao.CourseRepository;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = CourseDaoImpl.class))
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(scripts = { "/schema.sql", "/test-data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = { "/test-data-clear.sql" }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class CourseDaoIT {

	@Autowired
	private CourseRepository courseDao;
	
	@Test
	void injectedComponentAreNotNull() {
		assertThat(courseDao).isNotNull();
	}
	
	@Test
	void shouldAllCourses() {
		Course course = new Course("Random", "Random", 3);
		Course course2 = new Course("Random2", "Random2", 4);
		List<Course> courses = new ArrayList<>();
		courses.add(course);
		courses.add(course2);
		courseDao.addAll(courses);
		assertEquals(4, courseDao.getAllCourses().size());
	}

	@Test
	void shouldReturnAvailableCourses() {
		int studentId = 1;
		List<Course> availableCourses = courseDao.getAvailableCourses(studentId);
		Course course = new Course("History", "World History", 2);
		List<Course> courses = new ArrayList<>();
		courses.add(course);
		assertEquals(courses, availableCourses);
	}

	@Test
	void shouldReturnCoursesByStudentId() {
		int studenId = 3;
		List<Course> coursesFromDb = courseDao.getByStudentId(studenId);
		Course course = new Course("History", "World History", 2);
		List<Course> courses = new ArrayList<>();
		courses.add(course);
		assertEquals(courses, coursesFromDb);
	}

	@Test
	void shouldReturnCourseById() {
		int courseId = 2;
		Course course = new Course("History", "World History", 2);
		Course courseFromDb = courseDao.getById(courseId);
		assertEquals(course, courseFromDb);
	}

	@Test
	void shouldReturnAllCourses() {
		List<Course> courses = courseDao.getAllCourses();
		assertEquals(2, courses.size());
	}

}
