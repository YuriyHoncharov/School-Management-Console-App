package ua.com.foxminded.yuriy.schoolconsoleapp.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ua.com.foxminded.yuriy.schoolconsoleapp.dao.CourseDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.impl.CourseServiceImpl;

public class CourseServiceTest {

	@Mock
	private CourseDao courseDao;

	@InjectMocks
	private CourseServiceImpl courseService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testAvailableCourses() {
		int studentId = 123;
		List<Course> courses = new ArrayList<>();
		courses.add(new Course("Mathematics", "Math Course", 1));
		courses.add(new Course("Biology", "Biology Course", 2));
		
		when(courseDao.getAvailableCourses(studentId)).thenReturn(courses);
		List<Course> result = courseService.getAvailableCourses(studentId);
		
		assertEquals(courses, result);
		verify(courseDao).getAvailableCourses(studentId);
	}

	@Test
	public void testActualCourses() {
		int studentId = 123;
		List<Course> actualCourses = new ArrayList<>();
		actualCourses.add(new Course("Mathematics", "Math Course", 1));

		when(courseDao.getByStudentId(studentId)).thenReturn(actualCourses);
		List<Course> result = courseService.getByStudentId(studentId);
		assertEquals(actualCourses, result);
		verify(courseDao).getByStudentId(studentId);
	}

	@Test
	public void testDeregisterCourse() {
		int studentId = 123;
		int courseId = 1;
		courseService.deregisterCourse(studentId, courseId);
		verify(courseDao).deregisterCourse(studentId, courseId);
	}

	@Test
	public void testAddCourse() {
		Course selectedCourse = new Course("Mathematics", "Math Course", 1);
		int studentId = 123;
		courseService.addCourse(selectedCourse, studentId);
		verify(courseDao).addCourse(selectedCourse, studentId);
	}

	@Test
	public void testGetById() {
		int courseId = 1;
		Course course = new Course("Mathematics", "Math Course", 1);
		when(courseDao.getById(courseId)).thenReturn(course);
		Course result = courseService.getById(courseId);		
		assertEquals(course, result);
		verify(courseDao).getById(courseId);
	}
}
