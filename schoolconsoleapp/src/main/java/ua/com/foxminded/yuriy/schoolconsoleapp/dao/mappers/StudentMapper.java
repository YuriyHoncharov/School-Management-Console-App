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

@Component
public class StudentMapper implements RowMapper<Student> {

	public StudentMapper() {
	}

//	@Override
//	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
//		Student student = new Student(rs.getString(StudentsColumns.FIRST_NAME), rs.getString(StudentsColumns.LAST_NAME));
//		student.setId(rs.getInt(StudentsColumns.STUDENT_ID));
//		student.setGroupId(rs.getInt(StudentsColumns.GROUP_ID));
//		List<Course> courses = new ArrayList<>();
//		while (rs.next()) {
//			courses.add(new Course(rs.getString(CoursesColumns.COURSE_NAME),
//					rs.getString(CoursesColumns.COURSE_DESCRIPTION), rs.getInt(CoursesColumns.COURSE_ID)));
//		}
//		student.setCourse(courses);
//		return student;
//	}

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
	    Student student = new Student(rs.getString(StudentsColumns.FIRST_NAME), rs.getString(StudentsColumns.LAST_NAME));
	    student.setId(rs.getInt(StudentsColumns.STUDENT_ID));
	    student.setGroupId(rs.getInt(StudentsColumns.GROUP_ID));

	    List<Course> courses = new ArrayList<>();
	    courses.add(new Course(rs.getString(CoursesColumns.COURSE_NAME), rs.getString(CoursesColumns.COURSE_DESCRIPTION),
	            rs.getInt(CoursesColumns.COURSE_ID)));

	    if (rs.isLast()) {
	        student.setCourse(courses);
	        return student;
	    } else {
	        do {
	            rs.next();
	            int nextStudentId = rs.getInt(StudentsColumns.STUDENT_ID);
	            if (student.getId() == nextStudentId) {
	                courses.add(new Course(rs.getString(CoursesColumns.COURSE_NAME),
	                        rs.getString(CoursesColumns.COURSE_DESCRIPTION), rs.getInt(CoursesColumns.COURSE_ID)));
	            } else {
	            	rs.previous();
	                break;
	            }
	        } while (true);

	        student.setCourse(courses);
	        return student;
	    }
	}
}
