package ua.com.foxminded.yuriy.schoolconsoleapp.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.com.foxminded.yuriy.schoolconsoleapp.config.ConnectionUtil;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.CourseDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

public class CourseDaoImpl implements CourseDao {

	@Override
	public void addAll(List<Course> courses) throws DaoException {
		String sqlQuery = "INSERT INTO courses (course_id, course_name, course_description) VALUES (?, ?, ?)";
		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sqlQuery);

			for (int i = 0; i < courses.size(); i++) {
				int id = courses.get(i).getId();
				statement.setInt(1, id);
				String name = courses.get(i).getName();
				statement.setString(2, name);
				String description = courses.get(i).getDescription();
				statement.setString(3, description);
				statement.executeUpdate();
			}
			statement.close();
		} catch (SQLException e) {
			throw new DaoException("Adding courses failed: " + e.getMessage());
		}
	}

	@Override
	public List<Course> getAvailableCourses(int studentId) throws DaoException {

		List<Course> allCourses = new ArrayList<>();

		String QUERY_SELECT_STUDENT_USING_ID = "SELECT * FROM courses WHERE (course_id) NOT IN ( "
				+ "SELECT course_id FROM students_courses WHERE student_id = ?)";
		try (Connection connection = ConnectionUtil.getConnection()) {

			PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_STUDENT_USING_ID);
			statement.setInt(1, studentId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("course_id");
				String courseName = rs.getString("course_name");
				String courseDescription = rs.getString("course_description");
				Course course = new Course(courseName, courseDescription, id);
				allCourses.add(course);
			}
		} catch (SQLException e) {
			throw new DaoException("Failed to add a course");
		}
		return allCourses;
	}

	@Override
	public List<Course> getCoursesByStudentId(int studentId) throws DaoException {

		List<Course> allCourses = new ArrayList<>();

		String QUERY_SELECT_STUDENT_USING_ID = "SELECT * FROM courses WHERE (course_id) IN ( "
				+ "SELECT course_id FROM students_courses WHERE student_id = ?)";
		try (Connection connection = ConnectionUtil.getConnection()) {

			PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_STUDENT_USING_ID);
			statement.setInt(1, studentId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("course_id");
				String courseName = rs.getString("course_name");
				String courseDescription = rs.getString("course_description");
				Course course = new Course(courseName, courseDescription, id);
				allCourses.add(course);
			}
		} catch (SQLException e) {
			throw new DaoException("Failed to add a course");
		}
		return allCourses;
	}

	@Override
	public void addCourse(Course selectedCourse, int studentId) throws DaoException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			String QUERY_ADD_COURSE = "INSERT INTO students_courses (course_id, student_id) VALUES (?, ?)";
			PreparedStatement statement = connection.prepareStatement(QUERY_ADD_COURSE);
			statement.setInt(1, selectedCourse.getId());
			statement.setInt(2, studentId);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Failed to add the new course");
		}
	}

	@Override
	public List<Course> selectCourses(int studentId) throws DaoException {

		List<Course> actualCourses = new ArrayList<>();
		String SQL_QUERY_GET_COURSES = "SELECT * FROM courses " + "WHERE (course_id) IN ( " + "SELECT course_id "
				+ "FROM students_courses " + "WHERE student_id = ?)";
		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SQL_QUERY_GET_COURSES);
			statement.setInt(1, studentId);
			ResultSet coursesList = statement.executeQuery();

			while (coursesList.next()) {
				int id = coursesList.getInt("course_id");
				String courseName = coursesList.getString("course_name");
				String courseDescription = coursesList.getString("course_description");
				Course course = new Course(courseName, courseDescription, id);
				actualCourses.add(course);
			}

		} catch (SQLException e1) {
			throw new DaoException("Failed to remove course from student's course list.");
		}
		return actualCourses;
	}

	@Override
	public void deleteCourse(int courseId, int studentId) throws DaoException {

		String QUERY_DELETE_COURSE = "DELETE FROM students_courses " + "WHERE (course_id, student_id) IN ( "
				+ "SELECT course_id, student_id " + "FROM students_courses " + "WHERE student_id = ? "
				+ "GROUP BY course_id, student_id " + "HAVING course_id = ?" + ")";

		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(QUERY_DELETE_COURSE);
			statement.setInt(1, studentId);
			statement.setInt(2, courseId);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Failed to delete course: " + e.getMessage());
		}
	}
}
