package ua.com.foxminded.yuriy.schoolconsoleapp.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.tables.CoursesColumns;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.tables.StudentsColumns;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

@Component
public class StudentMapper implements RowMapper<Student> {

	private final CourseMapper courseMapper;

	@Autowired
	public StudentMapper(CourseMapper courseMapper) {
		this.courseMapper = courseMapper;
	}

	public StudentMapper() {
		this.courseMapper = new CourseMapper();
	}

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		try {
			Student student = new Student(rs.getInt(StudentsColumns.STUDENT_ID), rs.getInt(StudentsColumns.GROUP_ID),
					rs.getString(StudentsColumns.FIRST_NAME), rs.getString(StudentsColumns.LAST_NAME));
			List<Course> courses = new ArrayList<>();
			Integer courseId = rs.getObject(CoursesColumns.COURSE_ID, Integer.class);
			Course course;
			if (courseId != null) {
				course = courseMapper.mapRow(rs, rowNum);
				courses.add(course);
			}
			student.setCourse(courses);
			return student;
		} catch (DaoException e) {
			throw new DaoException("Failed to create a student object in Student Mapper");
		}
	}

	public Student basicMapRow(ResultSet rs, int rowNum) throws SQLException {
		try {
			return new Student(rs.getInt(StudentsColumns.STUDENT_ID), rs.getInt(StudentsColumns.GROUP_ID),
					rs.getString(StudentsColumns.FIRST_NAME), rs.getString(StudentsColumns.LAST_NAME));
		} catch (DaoException e) {
			throw new DaoException("Failed to create a student object in Student Mapper");
		}
	}
}
