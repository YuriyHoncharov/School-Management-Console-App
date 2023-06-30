package ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.sqlqueries;

public class SqlGroupQueries {

	public static String ADD_ALL = "INSERT INTO groups (group_id, group_name) VALUES (?, ?)";

	public static String GET_BY_LESS_OR_EQUAL_STUDENTS = "SELECT groups.group_id, groups.group_name, COUNT(students.student_id) AS student_count FROM groups LEFT JOIN students ON groups.group_id = students.group_id GROUP BY groups.group_id, groups.group_name HAVING COUNT(students.student_id) <= ?";

	public static String GET_STUDENTS_COUNT_BY_GROUP_ID = "SELECT COUNT(students.student_id) AS student_count FROM groups LEFT JOIN students ON groups.group_id = students.group_id WHERE groups.group_id = ? ";

	public static String GET_ALL_GROUPS = "SELECT * FROM groups";

	public static String GET_BY_ID = "SELECT * FROM groups WHERE group_id = ?";

}
