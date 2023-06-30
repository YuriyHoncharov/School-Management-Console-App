package ua.com.foxminded.yuriy.schoolconsoleapp.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import ua.com.foxminded.yuriy.schoolconsoleapp.config.ConnectionUtil;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.sqlqueries.SqlCourseQueries;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.tables.CoursesColumns;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;

public class CourseDaoImplTest {

	@Mock
	private Connection mockConnection;
	@Mock
	private PreparedStatement mockStatement;
	@Mock
	private ResultSet mockResultSet;

	private MockedStatic<ConnectionUtil> mockedStatic;
	private CourseDaoImpl courseDao;

	@BeforeEach
	void setUp() throws SQLException {
		MockitoAnnotations.openMocks(this);
		mockedStatic = mockStatic(ConnectionUtil.class);
		when(ConnectionUtil.getConnection()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		courseDao = new CourseDaoImpl();
	}

	@AfterEach
	void cleanUp() {
		mockedStatic.close();
	}

	@Test
	void addAllTest_Success() throws SQLException {
		List<Course> courses = new ArrayList<>();
		courses.add(new Course("Mathematics", "Math Course", 1));
		courses.add(new Course("Biology", "Biology Course", 2));
		when(mockStatement.executeUpdate()).thenReturn(1);
		courseDao.addAll(courses);
		verify(mockConnection, times(1)).prepareStatement(SqlCourseQueries.ADD_ALL);
		verify(mockStatement, times(courses.size())).setInt(eq(1), anyInt());
		verify(mockStatement, times(courses.size())).setString(eq(2), anyString());
		verify(mockStatement, times(courses.size())).setString(eq(3), anyString());
		verify(mockStatement, times(courses.size())).executeUpdate();
	}

	@Test
	void getByStudentIdTest_Success_shouldReturnCoursesList() throws SQLException {
		int studentId = 1;
		List<Course> courses = new ArrayList<>();
		courses.add(new Course("Mathematics", "Math Course", 1));
		courses.add(new Course("Biology", "Biology Course", 2));
		when(mockStatement.executeQuery()).thenReturn(mockResultSet);
		when(mockResultSet.next()).thenReturn(true, true, false);
		when(mockResultSet.getString(CoursesColumns.COURSE_NAME)).thenReturn("Mathematics").thenReturn("Biology");
		when(mockResultSet.getString(CoursesColumns.COURSE_DESCRIPTION)).thenReturn("Math Course")
				.thenReturn("Biology Course");
		when(mockResultSet.getInt(CoursesColumns.COURSE_ID)).thenReturn(1).thenReturn(2);
		List<Course> result = courseDao.getByStudentId(studentId);
		assertEquals(courses.size(), result.size());
		verify(mockConnection, times(1)).prepareStatement(SqlCourseQueries.GET_COURSES_BY_STUDENT_ID);
		verify(mockStatement, times(1)).setInt(eq(1), anyInt());
		verify(mockStatement, times(1)).executeQuery();
		verify(mockResultSet, times(3)).next();
		verify(mockResultSet, times(2)).getString(CoursesColumns.COURSE_NAME);
		verify(mockResultSet, times(2)).getString(CoursesColumns.COURSE_DESCRIPTION);
		verify(mockResultSet, times(2)).getInt(CoursesColumns.COURSE_ID);
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