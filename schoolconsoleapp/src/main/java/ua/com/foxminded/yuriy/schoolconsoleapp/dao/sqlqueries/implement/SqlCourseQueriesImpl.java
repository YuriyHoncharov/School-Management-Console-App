package ua.com.foxminded.yuriy.schoolconsoleapp.dao.sqlqueries.implement;

import ua.com.foxminded.yuriy.schoolconsoleapp.dao.sqlqueries.SqlCourseQueries;

public class SqlCourseQueriesImpl implements SqlCourseQueries {

	@Override
	public String QUERY_GET_BY_ID() {
		return "SELECT * FROM courses WHERE course_id = ?";
	}

	@Override
	public String QUERY_DELETE_FROM_STUDENT() {
		return "DELETE FROM students_courses WHERE (course_id, student_id) IN (SELECT course_id, student_id "
				+ "FROM students_courses WHERE student_id = ? GROUP BY course_id, student_id " + "HAVING course_id = ?)";
	}

	@Override
	public String QUERY_ADD_TO_STUDENT_BY_ID() {
		return "INSERT INTO students_courses (course_id, student_id) VALUES (?, ?)";
	}

	@Override
	public String QUERY_GET_COURSES_BY_STUDENT_ID() {
		return "SELECT * FROM courses WHERE (course_id) IN (SELECT course_id FROM students_courses WHERE student_id = ?)";
	}

	@Override
	public String QUERY_GET_AVAILABLE_BY_STUDENT_ID() {
		return "SELECT * FROM courses WHERE (course_id) NOT IN (SELECT course_id FROM students_courses WHERE student_id = ?)";
	}

	@Override
	public String QUERY_ADD_ALL() {
		return "INSERT INTO courses (course_id, course_name, course_description) VALUES (?, ?, ?)";
	}
}
