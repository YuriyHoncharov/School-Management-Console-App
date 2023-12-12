package ua.com.foxminded.yuriy.schoolconsoleapp.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.repository.CourseRepository;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.impl.CourseServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CourseDaoImplTest {

	@Mock
	private CourseRepository courseRepository;
	@InjectMocks
	private CourseServiceImpl courseServiceImpl;
	@Mock
	private TypedQuery<Course> mockQuery;

	@Test
	void addAllTest_Success() {
		List<Course> courses = new ArrayList<>();
		courses.add(new Course("Mathematics", "Math Course", 1));
		courses.add(new Course("Biology", "Biology Course", 2));
		when(courseRepository.saveAll(courses)).thenReturn(courses);
		List<Course> result = courseRepository.saveAll(courses);
		assertEquals(courses, result);
	}

	@Test
	void getByStudentId_Success_shouldReturnCorrectCourse() {
		int courseId = 1;
		Course course = new Course("Mathematics", "Math Course", 1);
		when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
		Course courseTest = courseServiceImpl.getById(courseId);
		assertEquals(course, courseTest);
	}
}