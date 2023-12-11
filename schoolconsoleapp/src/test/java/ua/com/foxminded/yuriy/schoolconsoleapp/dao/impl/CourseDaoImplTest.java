package ua.com.foxminded.yuriy.schoolconsoleapp.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;

@ExtendWith(MockitoExtension.class)
public class CourseDaoImplTest {

	@Mock
	private EntityManager entityManager;
	@InjectMocks
	private CourseDaoImpl courseDao;
	@Mock
	private TypedQuery<Course> mockQuery;

	@Test
	void addAllTest_Success() {
		List<Course> courses = new ArrayList<>();
		courses.add(new Course("Mathematics", "Math Course", 1));
		courses.add(new Course("Biology", "Biology Course", 2));

		courseDao.saveAll(courses);

		for (Course course : courses) {
			verify(entityManager, times(1)).merge(course);
		}
	}

	@Test
	void getByStudentIdTest_Success_shouldReturnCoursesList() throws SQLException {
		int studentId = 1;
		List<Course> courses = new ArrayList<>();
		courses.add(new Course("Mathematics", "Math Course", 1));
		courses.add(new Course("Biology", "Biology Course", 2));
		when(entityManager.createQuery(anyString(), eq(Course.class))).thenReturn(mockQuery);
		when(mockQuery.setParameter(anyString(), eq(studentId))).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(courses);		
		List<Course> result = courseDao.findById(studentId);		
		assertEquals(courses, result);
	}

	@Test
	void getByStudentId_Success_shouldReturnList() {
		List<Course> courses = new ArrayList<>();
		courses.add(new Course("Mathematics", "Math Course", 1));
		courses.add(new Course("Biology", "Biology Course", 2));
		when(entityManager.createQuery(anyString(), eq(Course.class))).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(courses);
		List<Course> coursesFromDb = courseDao.findAll();		
		assertEquals(courses, coursesFromDb);
	}
}