package ua.com.foxminded.yuriy.schoolconsoleapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;

public class CourseDao {
	
public void addCourseToDataBase(Connection connection, List<Course> courses) {
		
		String sqlQuery = "INSERT INTO courses (course_id, course_name) VALUES (?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			
			for (int i = 0; i < courses.size(); i++) {
				int id = courses.get(i).getId();
				statement.setInt(1, id);
				String name = courses.get(i).getName();
				statement.setString(2, name);
				statement.executeUpdate();
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
