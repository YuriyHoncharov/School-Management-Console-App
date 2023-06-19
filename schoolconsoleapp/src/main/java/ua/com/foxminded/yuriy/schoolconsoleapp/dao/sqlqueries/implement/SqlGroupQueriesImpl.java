package ua.com.foxminded.yuriy.schoolconsoleapp.dao.sqlqueries.implement;

import ua.com.foxminded.yuriy.schoolconsoleapp.dao.sqlqueries.SqlGroupQueries;

public class SqlGroupQueriesImpl implements SqlGroupQueries {

	@Override
	public String QUERY_ADD_ALL() {
		return "INSERT INTO groups (group_id, group_name) VALUES (?, ?)";
	}

	@Override
	public String QUERY_GET_BY_LESS_OR_EQUAL_STUDENTS() {
		return "SELECT groups.group_id, groups.group_name, COUNT(students.student_id) AS student_count FROM groups LEFT JOIN students ON groups.group_id = students.group_id GROUP BY groups.group_id, groups.group_name HAVING COUNT(students.student_id) <= ?";
	}

	@Override
	public String QUERY_GET_BY_STUDENTS_COUNT() {
		return "SELECT COUNT(students.student_id) AS student_count FROM groups LEFT JOIN students ON groups.group_id = students.group_id WHERE groups.group_id = ? ";
	}

	@Override
	public String QUURY_GET_ALL_GROUPS() {
		return "SELECT * FROM groups";
	}

	@Override
	public String QUERY_GET_BY_ID() {
		return "SELECT * FROM groups WHERE group_id = ?";
	}

}
