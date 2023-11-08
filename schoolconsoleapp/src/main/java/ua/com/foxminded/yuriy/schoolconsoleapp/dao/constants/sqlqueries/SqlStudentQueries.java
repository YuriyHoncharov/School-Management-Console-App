package ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.sqlqueries;

public class SqlStudentQueries {

	public static String ADD_ALL = "INSERT INTO students(group_id, first_name, last_name) VALUES (?, ?, ?)";

	public static String GET_STUDENTS_ON_COURSE = "SELECT students.student_id, students.first_name, students.last_name, students.group_id, NULL AS course_id FROM students INNER JOIN students_courses ON students.student_id = students_courses.student_id INNER JOIN courses ON students_courses.course_id = courses.course_id WHERE courses.course_id = ?";

	public static String ADD_NEW = "INSERT INTO students (first_name, last_name) VALUES (?, ?);";

	public static String DELETE = "DELETE FROM students WHERE student_id = ?";

	public static String GET_BY_ID = "SELECT students.* , courses.* FROM students LEFT JOIN students_courses ON students.student_id = students_courses.student_id LEFT JOIN courses ON students_courses.course_id = courses.course_id WHERE students.student_id = ?";

	public static String SET_GROUP_ID = "UPDATE students SET group_id = ? WHERE student_id = ?";

	public static String GET_INFO_BY_NAME_LASTNAME = "SELECT * FROM students WHERE first_name = ? AND last_name = ?";

	public static String GET_ALL_STUDENTS = "SELECT students.*, courses.* FROM students LEFT JOIN students_courses ON students.student_id = students_courses.student_id LEFT JOIN courses ON students_courses.course_id = courses.course_id ORDER BY students.student_id ASC";

	public static String GET_LAST_ID_VALUE = "SELECT max(student_id) from students";

	public static String UPDATE = "UPDATE students SET group_id = ?, first_name = ?, last_name = ? WHERE student_id = ?";

}
