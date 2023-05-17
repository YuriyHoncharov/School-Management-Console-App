package ua.com.foxminded.yuriy.schoolconsoleapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;

public class GroupDao {

	public void addGroupToDataBase(Connection connection, List<Group> groups) {

		String sqlQuery = "INSERT INTO groups (group_id, group_name) VALUES (?, ?)";
		try {
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
			e.printStackTrace();
		}
	}

	public static void selectStudentsWithLessOrEqual(Statement statement) throws SQLException {

		String QUERY_SELECT_LESS_OR_EQUAL_STUDENTS = "SELECT groups.group_id, groups.group_name, COUNT(students.student_id) AS student_count\r\n"
				+ "FROM groups\r\n" + "LEFT JOIN students ON groups.group_id = students.group_id\r\n"
				+ "GROUP BY groups.group_id, groups.group_name\r\n" + "HAVING COUNT(students.student_id) <= (\r\n"
				+ "  SELECT COUNT(student_id)\r\n" + "  FROM students\r\n" + "  WHERE group_id = groups.group_id\r\n" + ")";

		ResultSet resultSet = statement.executeQuery(QUERY_SELECT_LESS_OR_EQUAL_STUDENTS);
		while (resultSet.next()) {
			int groupId = resultSet.getInt("group_id");
			String groupName = resultSet.getString("group_name");
			int studentCount = resultSet.getInt("student_count");

			System.out.println("Group ID: " + groupId + ", Group Name: " + groupName + ", Student Count: " + studentCount);

		}
		resultSet.close();

	}

}
