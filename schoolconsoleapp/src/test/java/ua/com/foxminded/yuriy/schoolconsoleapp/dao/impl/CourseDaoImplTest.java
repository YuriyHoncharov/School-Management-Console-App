package ua.com.foxminded.yuriy.schoolconsoleapp.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.tree.RowMapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.sqlqueries.SqlCourseQueries;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.tables.CoursesColumns;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.mappers.CourseMapper;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;

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

		courseDao.addAll(courses);

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
		List<Course> result = courseDao.getByStudentId(studentId);		
		assertEquals(courses, result);
	}

	@Test
	void getByStudentId_Success_shouldReturnList() {
		List<Course> courses = new ArrayList<>();
		courses.add(new Course("Mathematics", "Math Course", 1));
		courses.add(new Course("Biology", "Biology Course", 2));
		when(entityManager.createQuery(anyString(), eq(Course.class))).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(courses);
		List<Course> coursesFromDb = courseDao.getAllCourses();		
		assertEquals(courses, coursesFromDb);
	}
}