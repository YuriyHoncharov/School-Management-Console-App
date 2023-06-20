package ua.com.foxminded.yuriy.schoolconsoleapp.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.com.foxminded.yuriy.schoolconsoleapp.config.ConnectionUtil;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.CourseDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.sqlqueries.SqlCourseQueries;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

public class CourseDaoImpl implements CourseDao {

	@Override
	public void addAll(List<Course> courses) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SqlCourseQueries.ADD_ALL);
			courses.stream().forEach(course -> {
				try {
					statement.setInt(1, course.getId());
					statement.setString(2, course.getName());
					statement.setString(3, course.getDescription());
					statement.executeUpdate();
				} catch (SQLException e) {
					throw new DaoException(
							"Failed to add course [" + course.getName() + "] to Data Base : " + e.getMessage());
				}
			});
		} catch (SQLException e) {
			throw new DaoException(
					"Failed to establish connection or execute query while adding course list to the database: "
							+ e.getMessage());
		}
	}

	@Override
	public List<Course> getAvailableCourses(int studentId) {
		List<Course> allCourses = new ArrayList<>();
		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SqlCourseQueries.GET_AVAILABLE_BY_STUDENT_ID);
			statement.setInt(1, studentId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Course course = new Course(rs.getString("course_name"), rs.getString("course_description"),
						rs.getInt("course_id"));
				allCourses.add(course);
			}
		} catch (SQLException e) {
			throw new DaoException("Failed to retrieve available courses for student with ID : " + studentId);
		}
		return allCourses;
	}

	@Override
	public List<Course> getCoursesByStudentId(int studentId) {
		List<Course> allCourses = new ArrayList<>();
		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SqlCourseQueries.GET_COURSES_BY_STUDENT_ID);
			statement.setInt(1, studentId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Course course = new Course(rs.getString("course_name"), rs.getString("course_description"),
						rs.getInt("course_id"));
				allCourses.add(course);
			}
		} catch (SQLException e) {
			throw new DaoException("Failed to retrieve courses of student with ID : " + studentId);
		}
		return allCourses;
	}

	@Override
	public void addCourse(Course course, int studentId) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SqlCourseQueries.ADD_TO_STUDENT_BY_ID);
			statement.setInt(1, course.getId());
			statement.setInt(2, studentId);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(
					"Failed to add course : [" + course.toString() + "] to the student with ID : " + studentId);
		}
	}

	@Override
	public void deleteCourse(int courseId, int studentId) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SqlCourseQueries.DELETE_FROM_STUDENT);
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
		Course course = null;
		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SqlCourseQueries.GET_BY_ID);
			statement.setInt(1, courseId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				course = new Course(rs.getString("course_name"), rs.getString("course_description"),
						rs.getInt("course_id"));
			} else {
				throw new DaoException("Course was not found with the following ID : " + courseId);
			}
		} catch (SQLException e) {
			throw new DaoException("Failed to get Course with the following ID : " + courseId);
		}
		return course;
	}
}
