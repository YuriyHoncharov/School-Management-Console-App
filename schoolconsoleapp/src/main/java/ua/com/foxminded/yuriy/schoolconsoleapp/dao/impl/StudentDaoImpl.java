package ua.com.foxminded.yuriy.schoolconsoleapp.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.StudentDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.sqlqueries.SqlCourseQueries;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.sqlqueries.SqlStudentQueries;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.tables.StudentsColumns;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.mappers.CourseMapper;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.mappers.StudentMapper;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

@Component
public class StudentDaoImpl implements StudentDao {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public StudentDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void addAll(List<Student> students) {

		int studentId = (getLastIdValue() + 1);

		jdbcTemplate.batchUpdate(SqlStudentQueries.ADD_ALL, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Student student = students.get(i);
				ps.setInt(1, student.getGroupId());
				ps.setString(2, student.getFirstName());
				ps.setString(3, student.getLastName());
			}

			@Override
			public int getBatchSize() {
				return students.size();
			}
		});

		for (Student student : students) {
			student.setId(studentId);
			studentId++;
			List<Course> courses = student.getCourses();
			for (Course course : courses) {
				jdbcTemplate.update(SqlStudentQueries.ADD_COURSES, course.getId(), student.getId());
			}
		}
	}

	@Override
	public List<Student> getAllByCourse(int courseId) {
		return jdbcTemplate.query(SqlStudentQueries.GET_STUDENTS_ON_COURSE, new StudentMapper(), courseId);
	}

	@Override
	public int add(Student student) {
		KeyHolder kh = new GeneratedKeyHolder();

		jdbcTemplate.update(con -> {
			PreparedStatement ps = con.prepareStatement(SqlStudentQueries.ADD_NEW, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, student.getFirstName());
			ps.setString(2, student.getLastName());
			return ps;
		}, kh);
		Integer studentId = (Integer) Objects.requireNonNull(kh.getKeys()).get(StudentsColumns.STUDENT_ID);
		if (studentId != null) {
			return studentId;
		} else {
			throw new DaoException(
					"Failed to add new student : [" + student.getFirstName() + " " + student.getLastName() + "]");
		}
	}

	@Override
	public void deleteById(int id) {
		jdbcTemplate.update(SqlStudentQueries.DELETE, id);
	}

	@Override
	public Student getById(int id) {
		Student student = jdbcTemplate.queryForObject(SqlStudentQueries.GET_BY_ID, new StudentMapper(), id);
		student.setCourse(
				jdbcTemplate.query(SqlCourseQueries.GET_COURSES_BY_STUDENT_ID, new CourseMapper(), student.getId()));
		return student;
	}

	@Override
	public Student getByName(String firstName, String lastName) {
		return jdbcTemplate.queryForObject(SqlStudentQueries.GET_INFO_BY_NAME_LASTNAME, new StudentMapper(), firstName,
				lastName);
	}

	@Override
	public List<Student> getAll() {
		List<Student> students = jdbcTemplate.query(SqlStudentQueries.GET_ALL_STUDENTS, new StudentMapper());
		for (Student student : students) {
			student.setCourse(
					jdbcTemplate.query(SqlCourseQueries.GET_COURSES_BY_STUDENT_ID, new CourseMapper(), student.getId()));
		}
		return students;
	}

	@Override
	public int getLastIdValue() {
		Integer maxIdValue = jdbcTemplate.queryForObject(SqlStudentQueries.GET_LAST_ID_VALUE, Integer.class);

		if (maxIdValue != null) {
			return maxIdValue;
		} else {
			return 0;
		}
	}

	@Override
	@Transactional
	public void update(Student student) {
		jdbcTemplate.update(SqlStudentQueries.UPDATE, student.getGroupId(), student.getFirstName(), student.getLastName(),
				student.getId());
		jdbcTemplate.update(SqlCourseQueries.DELETE_ALL_FROM_STUDENT, student.getId());
		for (Course course : student.getCourses()) {
			jdbcTemplate.update(SqlStudentQueries.ADD_COURSES, course.getId(), student.getId());
		}
	}
}
