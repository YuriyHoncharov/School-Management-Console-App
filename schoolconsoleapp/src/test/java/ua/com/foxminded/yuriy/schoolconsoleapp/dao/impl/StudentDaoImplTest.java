package ua.com.foxminded.yuriy.schoolconsoleapp.dao.impl;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.sqlqueries.SqlCourseQueries;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.sqlqueries.SqlStudentQueries;

@ExtendWith(MockitoExtension.class)
class StudentDaoImplTest {

	@Mock
	private JdbcTemplate jdbcTemplate;
	@InjectMocks
	private StudentDaoImpl studentDao;

	@Test
	void addAllTest_Success() throws SQLException {
		List<Course> courses = new ArrayList<>();
		courses.add(new Course("Mathematics", "Math Course", 1));
		List<Student> students = new ArrayList<>();
		Student student = new Student("name", "lastname");
		Student student2 = new Student("name2", "lastname2");
		student.setCourse(courses);
		student2.setCourse(courses);
		students.add(student);
		students.add(student2);
		when(jdbcTemplate.queryForObject(SqlStudentQueries.GET_LAST_ID_VALUE, Integer.class)).thenReturn(1);
		when(jdbcTemplate.batchUpdate(eq(SqlStudentQueries.ADD_ALL), any(BatchPreparedStatementSetter.class)))
				.thenAnswer(invocation -> {
					BatchPreparedStatementSetter setter = invocation.getArgument(1);
					PreparedStatement preparedStatement = mock(PreparedStatement.class);
					for (int i = 0; i < students.size(); i++) {
						setter.setValues(preparedStatement, i);
					}
					return new int[students.size()];
				});

		studentDao.addAll(students);
		verify(jdbcTemplate, times(1)).batchUpdate(eq(SqlStudentQueries.ADD_ALL),
				any(BatchPreparedStatementSetter.class));
	}

	@Test
	void deleteByIdTest_Success() throws SQLException {
		int studentId = 1;
		when(jdbcTemplate.update(eq(SqlStudentQueries.DELETE), eq(studentId))).thenReturn(1);
		studentDao.deleteById(studentId);
		verify(jdbcTemplate, times(1)).update(eq(SqlStudentQueries.DELETE), eq(studentId));
	}

	@Test
	void update_Success() throws SQLException {
		Student student = new Student("Name", "Lastname");
		student.setId(1);
		Course course = new Course("Mathematics", "Math Course", 1);
		List<Course> courses = new ArrayList<>();
		courses.add(course);
		student.setCourse(courses);
		when(jdbcTemplate.update(eq(SqlStudentQueries.UPDATE), eq(student.getGroupId()), eq(student.getFirstName()),
				eq(student.getLastName()), eq(student.getId()))).thenReturn(1);
		when(jdbcTemplate.update(eq(SqlCourseQueries.DELETE_ALL_FROM_STUDENT), eq(student.getId()))).thenReturn(1);
		when(jdbcTemplate.update(eq(SqlCourseQueries.ADD_TO_STUDENT_BY_ID), eq(course.getId()), eq(student.getId())))
				.thenReturn(1);
		studentDao.update(student);
		verify(jdbcTemplate, times(1)).update(eq(SqlStudentQueries.UPDATE), eq(student.getGroupId()),
				eq(student.getFirstName()), eq(student.getLastName()), eq(student.getId()));
		verify(jdbcTemplate, times(1)).update(eq(SqlCourseQueries.DELETE_ALL_FROM_STUDENT), eq(student.getId()));
		for (Course c : student.getCourses()) {
			verify(jdbcTemplate, times(1)).update(eq(SqlCourseQueries.ADD_TO_STUDENT_BY_ID), eq(course.getId()),
					eq(student.getId()));
		}
	}

}
