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
			throw new DaoException("Failed to add course list to Data Base : " + e.getMessage());
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
			throw new DaoException("Failed to retrieve available courses for student with ID : " + studentId);
		}
		return allCourses;
	}

	@Override
	public List<Course> getCoursesByStudentId(int studentId) throws DaoException {

		List<Course> allCourses = new ArrayList<>();

		String QUERY_SELECT_COURSES_BY_STUDENT_ID = "SELECT * FROM courses WHERE (course_id) IN ( "
				+ "SELECT course_id FROM students_courses WHERE student_id = ?)";
		try (Connection connection = ConnectionUtil.getConnection()) {

			PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_COURSES_BY_STUDENT_ID);
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
			throw new DaoException("Failed to retrieve courses of student with ID : " + studentId);
		}
		return allCourses;
	}

	@Override
	public void addCourse(Course course, int studentId) throws DaoException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			String QUERY_ADD_COURSE = "INSERT INTO students_courses (course_id, student_id) VALUES (?, ?)";
			PreparedStatement statement = connection.prepareStatement(QUERY_ADD_COURSE);
			statement.setInt(1, course.getId());
			statement.setInt(2, studentId);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(
					"Failed to add course : [" + course.toString() + "] to the student with ID : " + studentId);
		}
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
			throw new DaoException(
					"Failed to delete a course with ID : " + courseId + " from student with ID : " + studentId);
		}
	}

	@Override
	public Course getById(int courseId) {
		String QUERY_GET_COURSE_BY_ID = "SELECT * FROM courses WHERE course_id = ?";
		Course course;
		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(QUERY_GET_COURSE_BY_ID);
			statement.setInt(1, courseId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				String name = rs.getString("course_name");
				String description = rs.getString("course_description");
				int id = rs.getInt("course_id");
				course = new Course(name, description, id);
			} else {
				throw new DaoException("Course was not found with the following ID : " + courseId);
			}
		} catch (SQLException e) {
			throw new DaoException("Failed to get Course with the following ID : " + courseId);
		}
		return course;
	}
}
