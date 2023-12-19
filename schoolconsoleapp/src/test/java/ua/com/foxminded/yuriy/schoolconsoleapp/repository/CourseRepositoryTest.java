package ua.com.foxminded.yuriy.schoolconsoleapp.repository;

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
public class CourseRepositoryTest {

	@Mock
	private CourseRepository courseRepository;
	@InjectMocks
	private CourseServiceImpl courseServiceImpl;
	@Mock
	private TypedQuery<Course> mockQuery;

	@Test
	void findAllByStudentId_shouldReturnCorrectCoursesList_Success() {
		List<Course> courses = new ArrayList<>();
		courses.add(new Course("Mathematics", "Math Course", 1));
		courses.add(new Course("Biology", "Biology Course", 2));
		int studentId = 10;
		when(courseRepository.findAllByStudentId(studentId)).thenReturn(courses);
		List<Course> result = courseRepository.findAllByStudentId(studentId);
		assertEquals(courses, result);
	}
}