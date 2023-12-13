package ua.com.foxminded.yuriy.schoolconsoleapp.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = CourseRepository.class))
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(scripts = { "/schema.sql", "/test-data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = { "/test-data-clear.sql" }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class CourseRepositoryIT {

	@Autowired
	private CourseRepository courseRepository;
	
	@Test
	void injectedComponentAreNotNull() {
		assertThat(courseRepository).isNotNull();
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
}
