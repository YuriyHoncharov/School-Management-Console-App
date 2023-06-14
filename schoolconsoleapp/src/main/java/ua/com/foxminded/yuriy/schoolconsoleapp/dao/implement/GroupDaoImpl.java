package ua.com.foxminded.yuriy.schoolconsoleapp.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ua.com.foxminded.yuriy.schoolconsoleapp.config.ConnectionUtil;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.GroupDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

public class GroupDaoImpl implements GroupDao {

	public void addAll(List<Group> groups) throws DaoException {

		String sqlQuery = "INSERT INTO groups (group_id, group_name) VALUES (?, ?)";
		try (Connection connection = ConnectionUtil.getConnection()) {
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
			throw new DaoException("Failed to add groups to data base : " + e.getMessage());
		}
	}

	@Override
	public List<Group> getAllLessOrEqual(int studentCount) throws DaoException {

		String QUERY_SELECT_LESS_OR_EQUAL_STUDENTS = "SELECT groups.group_id, groups.group_name, COUNT(students.student_id) AS student_count "
				+ "FROM groups " + "LEFT JOIN students ON groups.group_id = students.group_id "
				+ "GROUP BY groups.group_id, groups.group_name " + "HAVING COUNT(students.student_id) <= ?";
		List<Group> groups = new ArrayList<>();
		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_LESS_OR_EQUAL_STUDENTS);
			statement.setInt(1, studentCount);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int groupId = rs.getInt("group_id");
				String groupName = rs.getString("group_name");
				Group group = new Group(groupName, groupId);
				groups.add(group);
			}
		} catch (SQLException e) {
			throw new DaoException("Failed to get group list with the following count : " + studentCount);
		}
		return groups;
	}

	@Override
	public int studentsCountByGroupId(int groupId) throws DaoException {
		String QUERY_SELECT_STUDENTS_COUNT = "SELECT COUNT(students.student_id) AS student_count " + "FROM groups "
				+ "LEFT JOIN students ON groups.group_id = students.group_id " + "WHERE groups.group_id = ? ";
		int count = 0;
		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_STUDENTS_COUNT);
			statement.setInt(1, groupId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				count = rs.getInt("student_count");
			}
		} catch (SQLException e) {
			throw new DaoException("Failed to get a student count of group with following ID : " + groupId);
		}
		return count;
	}

	@Override
	public List<Group> getAll() {
		String QUURY_SELECT_ALL_GROUPS = "SELECT * FROM groups";
		List<Group> allGroups = new ArrayList<>();
		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(QUURY_SELECT_ALL_GROUPS);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int group_id = rs.getInt("group_id");
				String group_name = rs.getString("group_name");
				Group group = new Group(group_name, group_id);
				allGroups.add(group);
			}
		} catch (SQLException e) {
			throw new DaoException("Failed to get all groups from database.");
		}
		return allGroups;
	}

	@Override
	public Group getById(int groupId) {
		String QUERY_GET_GROUP_BY_ID = "SELECT * FROM groups WHERE group_id = ?";
		Group group = new Group("", 0);
		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(QUERY_GET_GROUP_BY_ID);
			statement.setInt(1, groupId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("group_id");
				String name = rs.getString("group_name");
				group.setId(id);
				group.setName(name);
			}

		} catch (SQLException e) {
			throw new DaoException("Failed to get group by following id :" + groupId);
		}
		return group;
	}
}
