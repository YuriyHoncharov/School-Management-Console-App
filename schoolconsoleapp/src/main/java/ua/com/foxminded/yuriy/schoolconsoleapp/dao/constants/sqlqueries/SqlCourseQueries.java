package ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.sqlqueries;

public class SqlCourseQueries {

	public static String GET_BY_ID = "SELECT * FROM courses WHERE course_id = ?";

	public static String DELETE_ALL_FROM_STUDENT = "DELETE FROM students_courses WHERE student_id = ?";

	public static String ADD_TO_STUDENT_BY_ID = "INSERT INTO students_courses (course_id, student_id) VALUES (?, ?)";

	public static String GET_COURSES_BY_STUDENT_ID = "SELECT * FROM courses WHERE (course_id) IN (SELECT course_id FROM students_courses WHERE student_id = ?)";

	public static String GET_AVAILABLE_BY_STUDENT_ID = "SELECT * FROM courses WHERE (course_id) NOT IN (SELECT course_id FROM students_courses WHERE student_id = ?)";

	public static String ADD_ALL = "INSERT INTO courses (course_id, course_name, course_description) VALUES (?, ?, ?)";
	
	public static String GET_ALL_COURSES = "SELECT * FROM courses";
}
