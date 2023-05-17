package ua.com.foxminded.yuriy.schoolconsoleapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;

public class StudentDao {

	public void addListOfStudentsToDataBase(Connection connection, List<Student> students) {
		String sqlQuery = "INSERT INTO students(group_id, first_name, last_name) VALUES (?, ?, ?)";

		try {
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			for (int i = 0; i < students.size(); i++) {
				int groupId = students.get(i).getGroupId();
				String FirstName = students.get(i).getFirstName();
				String LastName = students.get(i).getLastName();
				statement.setInt(1, groupId);
				statement.setString(2, FirstName);
				statement.setString(3, LastName);
				statement.executeUpdate();
			}
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	String QUERY_SELECT_STUDENTS_ON_COURSE = "SELECT students.student_id, students.first_name, students.last_name\r\n"
			+ "FROM students\r\n" + "INNER JOIN students_course ON students.student_id = students_course.student_id\r\n"
			+ "INNER JOIN courses ON students_course.course_id = courses.course_id\r\n" + "WHERE courses.course_name = ?";

}
