package ua.com.foxminded.yuriy.schoolconsoleapp.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.CourseDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.sqlqueries.SqlCourseQueries;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.mappers.CourseMapper;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;


@Component
public class CourseDaoImpl implements CourseDao {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public CourseDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addAll(List<Course> courses) {
		for (Course course : courses) {
			jdbcTemplate.update(SqlCourseQueries.ADD_ALL, course.getId(), course.getName(), course.getDescription());
		}
	}

	@Override
	public List<Course> getAvailableCourses(int studentId) {
		return jdbcTemplate.query(SqlCourseQueries.GET_AVAILABLE_BY_STUDENT_ID, new Object[] { studentId },
				new CourseMapper());
	}

	@Override
	public List<Course> getByStudentId(int studentId) {
		return jdbcTemplate.query(SqlCourseQueries.GET_COURSES_BY_STUDENT_ID, new Object[] { studentId },
				new CourseMapper());
	}

	@Override
	public void addToStudent(Course course, int studentId) {
		jdbcTemplate.update(SqlCourseQueries.ADD_TO_STUDENT_BY_ID, course.getId(), studentId);
	}

	@Override
	public void deregisterCourse(int courseId, int studentId) {
		jdbcTemplate.update(SqlCourseQueries.DELETE_FROM_STUDENT, studentId, courseId);
	}

	@Override
	public Course getById(int courseId) {
		return jdbcTemplate.queryForObject(SqlCourseQueries.GET_BY_ID, new Object[] { courseId }, new CourseMapper());
	}
}
