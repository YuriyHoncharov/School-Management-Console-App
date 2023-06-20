package ua.com.foxminded.yuriy.schoolconsoleapp.dao.sqlqueries;

public class SqlStudentQueries {

	public static String ADD_ALL = "INSERT INTO students(group_id, first_name, last_name) VALUES (?, ?, ?)";

	public static String ADD_COURSES = "INSERT INTO students_courses (course_id, student_id) VALUES (?, ?)";

	public static String GET_STUDENTS_ON_COURSE = "SELECT students.student_id, students.first_name, students.last_name FROM students INNER JOIN students_courses ON students.student_id = students_courses.student_id INNER JOIN courses ON students_courses.course_id = courses.course_id WHERE courses.course_name = ?";

	public static String ADD_NEW = "INSERT INTO students (first_name, last_name) VALUES (?, ?)";

	public static String DELETE = "DELETE FROM students WHERE student_id = ?";

	public static String GET_INFO_BY_ID = "SELECT * FROM students WHERE student_id = ?";

	public static String SET_GROUP_ID = "UPDATE students SET group_id = ? WHERE student_id = ?";

	public static String GET_INFO_BY_NAME_LASTNAME = "SELECT * FROM students WHERE first_name = ? AND last_name = ?";

}
