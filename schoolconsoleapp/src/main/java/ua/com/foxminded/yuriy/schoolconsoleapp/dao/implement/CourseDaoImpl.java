package ua.com.foxminded.yuriy.schoolconsoleapp.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import ua.com.foxminded.yuriy.schoolconsoleapp.config.ConnectionUtil;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.CourseDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

public class CourseDaoImpl implements CourseDao {

	@Override
	public boolean addAll(List<Course> courses) throws DaoException {
		String sqlQuery = "INSERT INTO courses (course_id, course_name, course_description) VALUES (?, ?, ?)";
		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sqlQuery);

			for (int i = 0; i < courses.size(); i++) {
				int id = courses.get(i).getId();
				statement.setInt(1, id);
				String name = courses.get(i).getName();
				statement.setString(2, name);
				String description = courses.get(i).getDescription();
				statement.setString(3, description);
				statement.executeUpdate();
			}
			statement.close();
		} catch (SQLException e) {
			throw new DaoException("Adding courses failed: " + e.getMessage());
		}
		return true;
	}
}
