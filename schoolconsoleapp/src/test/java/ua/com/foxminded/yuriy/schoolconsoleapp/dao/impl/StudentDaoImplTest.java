package ua.com.foxminded.yuriy.schoolconsoleapp.dao.impl;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import ua.com.foxminded.yuriy.schoolconsoleapp.config.ConnectionUtil;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.sqlqueries.SqlStudentQueries;

class StudentDaoImplTest {

	@Mock
	private Connection mockConnection;
	@Mock
	private PreparedStatement mockStatement;
	@Mock
	private PreparedStatement mockCourseStatement;
	@Mock
	private ResultSet mockGeneratedKeys;

	private StudentDaoImpl studentDao;
	private MockedStatic<ConnectionUtil> mockedStatic;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		mockedStatic = mockStatic(ConnectionUtil.class);
		when(ConnectionUtil.getConnection()).thenReturn(mockConnection);
		studentDao = new StudentDaoImpl();
	}

	@AfterEach
	void cleanUp() {
		mockedStatic.close();
	}

	@Test
	void addAllTest_Success() throws SQLException {
		  when(mockConnection.prepareStatement(SqlStudentQueries.ADD_ALL, Statement.RETURN_GENERATED_KEYS)).thenReturn(mockStatement);
		  when(mockConnection.prepareStatement(SqlStudentQueries.ADD_COURSES)).thenReturn(mockCourseStatement);
		  when(mockStatement.getGeneratedKeys()).thenReturn(mockGeneratedKeys);
		  when(mockGeneratedKeys.next()).thenReturn(true, true);
		  when(mockGeneratedKeys.getInt(1)).thenReturn(1, 2);
		  
		  		  
		  List<Student> students = new ArrayList<>();
		  Student student = mock(Student.class);
		  Student student2 = mock(Student.class);
		  Course course = mock(Course.class);
		  Course course2 = mock(Course.class);
		  List<Course>courses = new ArrayList<>();
		  courses.add(course);
		  courses.add(course2);
		  when(student.getGroupId()).thenReturn(1);
		  when(student.getFirstName()).thenReturn("John");
		  when(student.getLastName()).thenReturn("Doe");
		  when(student.getCourses()).thenReturn(courses);
		  
		  when(student2.getGroupId()).thenReturn(2);
		  when(student2.getFirstName()).thenReturn("Lia");
		  when(student2.getLastName()).thenReturn("Manelli");
		  when(student2.getCourses()).thenReturn(courses);
		  students.add(student);
		  students.add(student2);		  
		  
		  
		  studentDao.addAll(students);
		  
		  verify(mockConnection, times(1)).prepareStatement(SqlStudentQueries.ADD_ALL, Statement.RETURN_GENERATED_KEYS);
		  verify(mockConnection, times(1)).prepareStatement(SqlStudentQueries.ADD_COURSES);
		  verify(mockStatement, times(2)).setInt(anyInt(), anyInt());
		  verify(mockStatement, times(4)).setString(anyInt(), anyString());		 
		  verify(mockStatement, times(2)).executeUpdate();
		  verify(mockStatement, times(2)).getGeneratedKeys();
		  verify(mockGeneratedKeys, times(2)).next();
		  verify(mockGeneratedKeys, times(2)).getInt(1);
		  verify(mockCourseStatement, times(8)).setInt(anyInt(), anyInt());
		  verify(mockCourseStatement, times(4)).executeUpdate();
	}

	@Test
	void deleteByIdTest_Success() throws SQLException {
		int studentId = 10;
		when(mockConnection.prepareStatement(SqlStudentQueries.DELETE)).thenReturn(mockStatement);
		studentDao.deleteById(studentId);
		verify(mockStatement).setInt(1, studentId);
		verify(mockStatement).executeUpdate();
	}

	@Test
	void setGroupByIdTest_Success() throws SQLException {
		int studentId = 1;
		Group group = mock(Group.class);
		when(group.getId()).thenReturn(1);
		when(mockConnection.prepareStatement(SqlStudentQueries.SET_GROUP_ID)).thenReturn(mockStatement);
		studentDao.setGroupById(studentId, group);
		verify(mockStatement).setInt(1, group.getId());
		verify(mockStatement).setInt(2, studentId);
		verify(mockStatement).executeUpdate();
	}
}
