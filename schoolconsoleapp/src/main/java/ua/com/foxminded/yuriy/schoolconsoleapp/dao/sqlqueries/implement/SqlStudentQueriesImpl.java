package ua.com.foxminded.yuriy.schoolconsoleapp.dao.sqlqueries.implement;

import ua.com.foxminded.yuriy.schoolconsoleapp.dao.sqlqueries.SqlStudentQueries;

public class SqlStudentQueriesImpl implements SqlStudentQueries {

	@Override
	public String QUERY_ADD_ALL() {
		return "INSERT INTO students(group_id, first_name, last_name) VALUES (?, ?, ?)";
	}

	@Override
	public String QUERY_ADD_COURSES() {
		return "INSERT INTO students_courses (course_id, student_id) VALUES (?, ?)";
	}

	@Override
	public String QUERY_GET_STUDENTS_ON_COURSE() {
		return "SELECT students.student_id, students.first_name, students.last_name FROM students INNER JOIN students_courses ON students.student_id = students_courses.student_id INNER JOIN courses ON students_courses.course_id = courses.course_id WHERE courses.course_name = ?";
	}

	@Override
	public String QUERY_ADD_NEW() {
		return "INSERT INTO students (first_name, last_name) VALUES (?, ?)";
	}

	@Override
	public String QUERY_DELETE() {
		return "DELETE FROM students WHERE student_id = ?";
	}

	@Override
	public String QUERY_GET_INFO_BY_ID() {
		return "SELECT * FROM students WHERE student_id = ?";
	}

	@Override
	public String QUERY_SET_GROUP_ID() {
		return "UPDATE students SET group_id = ? WHERE student_id = ?";
	}

	@Override
	public String QUERY_GET_INFO_BY_NAME_LASTNAME() {
		return "SELECT * FROM students WHERE first_name = ? AND last_name = ?";
	}
}
