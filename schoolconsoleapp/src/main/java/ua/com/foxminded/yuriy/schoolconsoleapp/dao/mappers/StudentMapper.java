package ua.com.foxminded.yuriy.schoolconsoleapp.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.tables.StudentsColumns;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.CourseService;

@Component
public class StudentMapper implements RowMapper<Student> {

	private CourseService courseService;	

	@Autowired
	public StudentMapper(CourseService courseService) {
		this.courseService = courseService;
	}
	public StudentMapper() {}

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		Student student = new Student(rs.getString(StudentsColumns.FIRST_NAME), rs.getString(StudentsColumns.LAST_NAME));
		student.setId(rs.getInt(StudentsColumns.STUDENT_ID));
		student.setGroupId(rs.getInt(StudentsColumns.GROUP_ID));
		List<Course> courses = courseService.getByStudentId(student.getId());
		student.setCourse(courses);
		return student;
	}
}
