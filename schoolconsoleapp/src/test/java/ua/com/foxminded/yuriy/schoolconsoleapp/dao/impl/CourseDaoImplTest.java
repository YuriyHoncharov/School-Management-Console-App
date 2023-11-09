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

@ExtendWith(MockitoExtension.class)
public class CourseDaoImplTest {

	@Mock
	private JdbcTemplate jdbcTemplate;
	@InjectMocks
	private CourseDaoImpl courseDao;

	@Test
	void addAllTest_Success() throws SQLException {
		List<Course> courses = new ArrayList<>();
		courses.add(new Course("Mathematics", "Math Course", 1));
		courses.add(new Course("Biology", "Biology Course", 2));
		when(jdbcTemplate.update(anyString(), any(), any(), any())).thenReturn(1);
		courseDao.addAll(courses);
		verify(jdbcTemplate, times(courses.size())).update(eq(SqlCourseQueries.ADD_ALL), anyInt(), anyString(),
				anyString());

	}

	@Test
	void getByStudentIdTest_Success_shouldReturnCoursesList() throws SQLException {
		int studentId = 1;
		List<Course> courses = new ArrayList<>();
		courses.add(new Course("Mathematics", "Math Course", 1));
		courses.add(new Course("Biology", "Biology Course", 2));
		when(jdbcTemplate.query(eq(SqlCourseQueries.GET_COURSES_BY_STUDENT_ID), any(CourseMapper.class), eq(studentId)))
				.thenReturn(courses);
		List<Course> result = courseDao.getByStudentId(studentId);
		verify(jdbcTemplate, times(1)).query(eq(SqlCourseQueries.GET_COURSES_BY_STUDENT_ID), any(CourseMapper.class),
				eq(studentId));
		assertEquals(courses, result);
	}

	@Test
	void addToStudentTest_Success_shouldAddCourseToStudent() throws SQLException {
		int studentId = 10;
		Course course = mock(Course.class);
		when(mockStatement.executeUpdate()).thenReturn(1);
		courseDao.addToStudent(course, studentId);
		verify(mockConnection, times(1)).prepareStatement(SqlCourseQueries.ADD_TO_STUDENT_BY_ID);
		verify(mockStatement, times(1)).setInt(1, course.getId());
		verify(mockStatement, times(1)).setInt(2, studentId);
		verify(mockStatement, times(1)).executeUpdate();

	}
}