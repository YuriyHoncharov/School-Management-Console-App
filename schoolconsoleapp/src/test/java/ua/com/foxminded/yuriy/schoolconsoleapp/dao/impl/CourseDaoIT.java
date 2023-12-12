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

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.repository.CourseRepository;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = CourseRepository.class))
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(scripts = { "/schema.sql", "/test-data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = { "/test-data-clear.sql" }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class CourseDaoIT {

	@Autowired
	private CourseRepository courseRepository;
	
	@Test
	void injectedComponentAreNotNull() {
		assertThat(courseRepository).isNotNull();
	}
	
	@Test
	void shouldAllCourses() {
		Course course = new Course("Random", "Random", 3);
		Course course2 = new Course("Random2", "Random2", 4);
		List<Course> courses = new ArrayList<>();
		courses.add(course);
		courses.add(course2);
		courseRepository.saveAll(courses);
		assertEquals(4, courseRepository.findAll().size());
	}

	@Test
	void shouldReturnAvailableCourses() {
		int studentId = 1;
		List<Course> availableCourses = courseRepository.findAllByStudentId(studentId);
		Course course = new Course("History", "World History", 2);
		List<Course> courses = new ArrayList<>();
		courses.add(course);
		assertEquals(courses, availableCourses);
	}


	@Test
	void shouldReturnCourseById() {
		int courseId = 2;
		Course course = new Course("History", "World History", 2);
		Course courseFromDb = courseRepository.findById(courseId).orElse(null);
		assertEquals(course, courseFromDb);
	}

	@Test
	void shouldReturnAllCourses() {
		List<Course> courses = courseRepository.findAll();
		assertEquals(2, courses.size());
	}

}
