package ua.com.foxminded.yuriy.schoolconsoleapp.service;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.impl.CourseServiceImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

	@Mock
	private CourseServiceImpl courseService;

	@Test
	void shouldGetAvailableCourses() {
		int studentId = 1;
		List<Course> availableCourses = Arrays.asList(new Course("Math", "Math Description", 1),
				new Course("Physics", "Physics Description", 1));
		when(courseService.getAvailableCourses(studentId)).thenReturn(availableCourses);
		List<Course> resultCourses = courseService.getAvailableCourses(studentId);
		assertEquals(availableCourses.size(), resultCourses.size());
		verify(courseService).getAvailableCourses(studentId);
	}

	@Test
	void shouldGetCourseById() {
		int courseId = 1;
		Course course = new Course("Math", "Math Description", 1);
		when(courseService.getById(courseId)).thenReturn(course);
		Course resultCourse = courseService.getById(courseId);
		assertNotNull(resultCourse);
		assertEquals(courseId, resultCourse.getId());
		verify(courseService).getById(courseId);
	}

	@Test
    void shouldGetAllCourses() {
        List<Course> allCourses = Arrays.asList(new Course("Math", "Math Description", 1),
                new Course("Physics", "Physics Description", 1));
        when(courseService.getAllCourses()).thenReturn(allCourses);
        List<Course> resultCourses = courseService.getAllCourses();
        assertEquals(allCourses.size(), resultCourses.size());
        verify(courseService).getAllCourses();
    }
}
