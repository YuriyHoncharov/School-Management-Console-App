package ua.com.foxminded.yuriy.schoolconsoleapp.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.tables.CoursesColumns;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;


@Component
public class CourseMapper implements RowMapper<Course> {

	@Override
	public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
		Course course = new Course();
		try {
			course = new Course(rs.getString(CoursesColumns.COURSE_NAME), rs.getString(CoursesColumns.COURSE_DESCRIPTION),
					rs.getInt(CoursesColumns.COURSE_ID));
		} catch (DaoException e) {
			throw new DaoException("Failed to create course in CourseMapper");
		}
		return course;
	}
}
