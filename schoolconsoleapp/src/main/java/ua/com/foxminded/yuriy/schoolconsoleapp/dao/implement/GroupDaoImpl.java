package ua.com.foxminded.yuriy.schoolconsoleapp.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ua.com.foxminded.yuriy.schoolconsoleapp.config.ConnectionUtil;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.GroupDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.sqlqueries.SqlGroupQueries;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.sqlqueries.implement.SqlGroupQueriesImpl;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

public class GroupDaoImpl implements GroupDao {

	SqlGroupQueries groupQueries = new SqlGroupQueriesImpl();
	String QUERY_ADD_ALL = groupQueries.QUERY_ADD_ALL();
	String QUERY_GET_BY_LESS_OR_EQUAL_STUDENTS = groupQueries.QUERY_GET_BY_LESS_OR_EQUAL_STUDENTS();
	String QUERY_GET_BY_STUDENTS_COUNT = groupQueries.QUERY_GET_BY_STUDENTS_COUNT();
	String QUURY_GET_ALL_GROUPS = groupQueries.QUURY_GET_ALL_GROUPS();
	String QUERY_GET_BY_ID = groupQueries.QUERY_GET_BY_ID();

	public void addAll(List<Group> groups) {

		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(QUERY_ADD_ALL);
			for (int i = 0; i < groups.size(); i++) {
				int id = groups.get(i).getId();
				statement.setInt(1, id);
				String name = groups.get(i).getName();
				statement.setString(2, name);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			throw new DaoException("Failed to add groups to data base : " + e.getMessage());
		}
	}

	@Override
	public List<Group> getAllLessOrEqual(int studentCount) {

		List<Group> groups = new ArrayList<>();
		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(QUERY_GET_BY_LESS_OR_EQUAL_STUDENTS);
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
	public int studentsCountByGroupId(int groupId) {

		int count = 0;
		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(QUERY_GET_BY_STUDENTS_COUNT);
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
			PreparedStatement statement = connection.prepareStatement(QUURY_GET_ALL_GROUPS);
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

		Group group = null;
		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(QUERY_GET_BY_ID);
			statement.setInt(1, groupId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("group_id");
				String name = rs.getString("group_name");
				group = new Group(name, id);
			}
		} catch (SQLException e) {
			throw new DaoException("Failed to get group by following id :" + groupId);
		}
		return group;
	}
}
