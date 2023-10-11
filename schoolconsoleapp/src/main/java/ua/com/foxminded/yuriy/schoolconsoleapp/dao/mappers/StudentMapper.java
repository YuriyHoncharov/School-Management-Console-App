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
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DtoException;

@Component
public class StudentMapper implements RowMapper<Student> {

	public StudentMapper() {
	}

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		Student student = new Student();
		try {
			student = new Student(rs.getInt(StudentsColumns.STUDENT_ID), rs.getInt(StudentsColumns.GROUP_ID),
					rs.getString(StudentsColumns.FIRST_NAME), rs.getString(StudentsColumns.LAST_NAME));

		} catch (DtoException e) {
			throw new DtoException("Failed to create student in StudentMapper");
		}
		return student;

	}

}
