package ua.com.foxminded.yuriy.schoolconsoleapp.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.tables.StudentsColumns;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;

@Component
public class StudentMapper implements RowMapper<Student> {

	public StudentMapper() {
	}

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		Student student = new Student(rs.getString(StudentsColumns.FIRST_NAME), rs.getString(StudentsColumns.LAST_NAME));
		student.setId(rs.getInt(StudentsColumns.STUDENT_ID));
		student.setGroupId(rs.getInt(StudentsColumns.GROUP_ID));
		return student;
	}
}
