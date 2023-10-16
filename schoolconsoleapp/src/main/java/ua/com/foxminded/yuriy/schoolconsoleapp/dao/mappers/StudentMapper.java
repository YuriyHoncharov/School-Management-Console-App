package ua.com.foxminded.yuriy.schoolconsoleapp.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.tables.CoursesColumns;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.tables.StudentsColumns;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;


@Component
public class StudentMapper implements RowMapper<Student> {

	public StudentMapper() {
	}

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		
//		Student student = new Student();
//		CourseMapper courseMapper = new CourseMapper();
//		try {
//		while (rs.next()) {
//			if (rs.getInt(StudentsColumns.STUDENT_ID) == student.getId()
//					&& rs.getString(CoursesColumns.COURSE_NAME) != null) {
//				Course course = courseMapper.mapRow(rs, 0);
//				List<Course> courses = student.getCourses();
//				courses.add(course);
//				student.setCourse(courses);
//			} else {
//				student = new Student(rs.getInt(StudentsColumns.STUDENT_ID), rs.getInt(StudentsColumns.GROUP_ID),
//						rs.getString(StudentsColumns.FIRST_NAME), rs.getString(StudentsColumns.LAST_NAME));
//				Course course = courseMapper.mapRow(rs, 0);
//				List<Course> courses = student.getCourses();
//				courses.add(course);
//				student.setCourse(courses);
//			}
//			}
//		} catch (DaoException e) {
//			throw new DaoException("Failed to create a student in Student Mapper");
//		}
//		return student;
		Student student = new Student();
		while (rs.next()) {
			try {
		student = new Student(rs.getInt(StudentsColumns.STUDENT_ID), rs.getInt(StudentsColumns.GROUP_ID),
				rs.getString(StudentsColumns.FIRST_NAME), rs.getString(StudentsColumns.LAST_NAME));
		CourseMapper courseMapper = new CourseMapper();
		if (rs.getString(CoursesColumns.COURSE_NAME) != null) {
			Course course = courseMapper.mapRow(rs, 0);
			List<Course>courses = student.getCourses();
			courses.add(course);
			student.setCourse(courses);
		}
			} catch (DaoException e) {
				throw new DaoException("Failed to get Student in Student Mapper");
			}
		}
		return student;

	}

}
