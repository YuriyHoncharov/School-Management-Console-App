package ua.com.foxminded.yuriy.schoolconsoleapp.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import ua.com.foxminded.yuriy.schoolconsoleapp.config.ConnectionUtil;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.DaoException;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.GroupDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;

public class GroupDaoImpl implements GroupDao{

	public void addAll(List<Group> groups) throws DaoException {

		String sqlQuery = "INSERT INTO groups (group_id, group_name) VALUES (?, ?)";
		try (Connection connection = ConnectionUtil.getConnection()){
			PreparedStatement statement = connection.prepareStatement(sqlQuery);

			for (int i = 0; i < groups.size(); i++) {
				int id = groups.get(i).getId();
				statement.setInt(1, id);
				String name = groups.get(i).getName();
				statement.setString(2, name);
				statement.executeUpdate();
			}
			statement.close();
		} catch (SQLException e) {
			throw new DaoException("Failed to add groups: " + e.getMessage());
		}
	}

	@Override
	public List<Group> findAllLessOrEqual(int number) throws DaoException {
		
		String QUERY_SELECT_LESS_OR_EQUAL_STUDENTS = "SELECT groups.group_id, groups.group_name " +
			    "FROM groups " +
			    "LEFT JOIN students ON groups.group_id = students.group_id " +
			    "GROUP BY groups.group_id, groups.group_name " +
			    "HAVING COUNT(students.student_id) <= ?";
		try (Connection connection = ConnectionUtil.getConnection()){
			PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_LESS_OR_EQUAL_STUDENTS);
			ResultSet rs = statement.executeQuery();
			statement.setInt(1, number);
			while(rs.next()) {
				int groupId = rs.getInt("group_id");
				String groupName = rs.getString("group_name");
				int studentsCount = rs.getInt("students.student_id");
						
			}
		
			
		} catch (SQLException e) {
			throw new DaoException("Failed to get groups list: " + e.getMessage());
		}
		return null;
	}
	
}
