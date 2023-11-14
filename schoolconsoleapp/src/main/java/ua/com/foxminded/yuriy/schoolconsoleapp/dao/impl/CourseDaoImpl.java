package ua.com.foxminded.yuriy.schoolconsoleapp.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.com.foxminded.yuriy.schoolconsoleapp.dao.CourseDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.sqlqueries.SqlCourseQueries;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.mappers.CourseMapper;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;

@Repository
public class CourseDaoImpl implements CourseDao {
	
	private final SessionFactory sessionFactory;
	private final Session session;

	@Autowired
	public CourseDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		this.session = sessionFactory.getCurrentSession();
	}

	@Override
	@Transactional
	public void addAll(List<Course> courses) {
		for (Course course : courses) {
			session.save(course);
		}
	}

	@Override
	public List<Course> getAvailableCourses(int studentId) {
		return jdbcTemplate.query(SqlCourseQueries.GET_AVAILABLE_BY_STUDENT_ID, new CourseMapper(), studentId);
	}

	@Override
	public List<Course> getByStudentId(int studentId) {
		return jdbcTemplate.query(SqlCourseQueries.GET_COURSES_BY_STUDENT_ID, new CourseMapper(), studentId);
	}

	@Override
	public Course getById(int courseId) {
		return jdbcTemplate.queryForObject(SqlCourseQueries.GET_BY_ID, new CourseMapper(), courseId);
	}

	@Override
	public List<Course> getAllCourses() {
		return jdbcTemplate.query(SqlCourseQueries.GET_ALL_COURSES, new CourseMapper());
	}
}
