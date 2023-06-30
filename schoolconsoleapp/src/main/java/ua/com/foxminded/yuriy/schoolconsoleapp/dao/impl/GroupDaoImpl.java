package ua.com.foxminded.yuriy.schoolconsoleapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ua.com.foxminded.yuriy.schoolconsoleapp.config.ConnectionUtil;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.GroupDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.sqlqueries.SqlGroupQueries;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.tables.GroupsColumns;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

public class GroupDaoImpl implements GroupDao {

	public void addAll(List<Group> groups) {

		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SqlGroupQueries.ADD_ALL);
			for (Group group : groups) {
				statement.setInt(1, group.getId());
				statement.setString(2, group.getName());
				try {
					statement.executeUpdate();
				} catch (SQLException e) {
					throw new DaoException("Failed to add " + group.toString() + " to data base : " + e.getMessage());
				}
			}
		} catch (SQLException e) {
			throw new DaoException("Failed to establish connection or execute query while adding groups to the database: "
					+ e.getMessage());
		}

	}

	@Override
	public List<Group> getAllLessOrEqual(int studentCount) {

		List<Group> groups = new ArrayList<>();
		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SqlGroupQueries.GET_BY_LESS_OR_EQUAL_STUDENTS);
			statement.setInt(1, studentCount);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Group group = new Group(rs.getString(GroupsColumns.GROUP_NAME), rs.getInt(GroupsColumns.GROUP_ID));
				groups.add(group);
			}
		} catch (SQLException e) {
			throw new DaoException("Failed to get group list with the following count : " + studentCount);
		}
		return groups;
	}

	@Override
	public int studentsCountByGroupId(int groupId) {

		int count = 0;
		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SqlGroupQueries.GET_STUDENTS_COUNT_BY_GROUP_ID);
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

		List<Group> allGroups = new ArrayList<>();
		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SqlGroupQueries.GET_ALL_GROUPS);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Group group = new Group(rs.getString(GroupsColumns.GROUP_NAME), rs.getInt(GroupsColumns.GROUP_ID));
				allGroups.add(group);
			}
		} catch (SQLException e) {
			throw new DaoException("Failed to get all groups from database.");
		}
		return allGroups;
	}

	@Override
	public Group getById(int groupId) {

		Group group = null;
		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SqlGroupQueries.GET_BY_ID);
			statement.setInt(1, groupId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				group = new Group(rs.getString(GroupsColumns.GROUP_NAME), rs.getInt(GroupsColumns.GROUP_ID));
			}
		} catch (SQLException e) {
			throw new DaoException("Failed to get group by following id :" + groupId);
		}
		return group;
	}
}
