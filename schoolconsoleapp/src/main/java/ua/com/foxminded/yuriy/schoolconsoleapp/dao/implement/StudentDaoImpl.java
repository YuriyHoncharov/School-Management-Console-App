package ua.com.foxminded.yuriy.schoolconsoleapp.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import ua.com.foxminded.yuriy.schoolconsoleapp.config.ConnectionUtil;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.StudentDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.dataGenerator.RandomDataGenerator;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

public class StudentDaoImpl implements StudentDao {

	public void addAll(List<Student> students) throws DaoException {
		String sqlQuery = "INSERT INTO students(group_id, first_name, last_name) VALUES (?, ?, ?)";
		String sqlQueryCourses = "INSERT INTO students_courses (course_id, student_id) VALUES (?, ?)";
		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement coursesStatement = connection.prepareStatement(sqlQueryCourses);
			for (int i = 0; i < students.size(); i++) {
				int groupId = students.get(i).getGroupId();
				String FirstName = students.get(i).getFirstName();
				String LastName = students.get(i).getLastName();
				statement.setInt(1, groupId);
				statement.setString(2, FirstName);
				statement.setString(3, LastName);
				statement.executeUpdate();
				ResultSet generatedKeys = statement.getGeneratedKeys();
				if (generatedKeys.next()) {
					int studentId = generatedKeys.getInt(1);
					students.get(i).setId(studentId);
				}
				generatedKeys.close();
				List<Course> courses = students.get(i).getCourses();
				for (Course course : courses) {
					int courseId = course.getId();
					int studentId = students.get(i).getId();
					coursesStatement.setInt(1, courseId);
					coursesStatement.setInt(2, studentId);
					coursesStatement.executeUpdate();
				}
			}
			statement.close();
			coursesStatement.close();
		} catch (SQLException e) {
			throw new DaoException("Failed to add students: " + e.getMessage());
		}
	}

	@Override
	public List<Student> findAllByCourse(String courseName) throws DaoException {

		String QUERY_SELECT_STUDENTS_ON_COURSE = "SELECT students.student_id, students.first_name, students.last_name\r\n"
				+ "FROM students\r\n" + "INNER JOIN students_course ON students.student_id = students_course.student_id\r\n"
				+ "INNER JOIN courses ON students_course.course_id = courses.course_id\r\n"
				+ "WHERE courses.course_name = ?";
		List<Student> studentsOfCourse = new ArrayList<>();

		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_STUDENTS_ON_COURSE);
			statement.setString(1, courseName);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				int id = rs.getInt("student_id");
				Student student = new Student(firstName, lastName);
				student.setId(id);
				studentsOfCourse.add(student);
			}
		} catch (SQLException e) {
			throw new DaoException("Failed to find students" + e.getMessage());
		}
		return studentsOfCourse;
	}

	@Override
	public void add(Student student) throws DaoException {
		String QUERY_ADD_NEW_STUDENT = "INSERT INTO students (first_name, last_name) VALUES (?, ?, ?)";
		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(QUERY_ADD_NEW_STUDENT);
			statement.setString(1, student.getFirstName());
			statement.setString(2, student.getLastName());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Failed to add new student" + e.getMessage());
		}
	}

	@Override
	public void delete(int id) throws DaoException {
		String QUERY_DELETE_STUDENT = "DELETE FROM students WHERE student_id = ?";

		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(QUERY_DELETE_STUDENT);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Failed to delete the student: " + e.getMessage());
		}

	}

	@Override
	public List<Course> availableCourses(int studentId) throws DaoException {

		RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
		List<Course> allCourses = randomDataGenerator.generateCourses();

		String QUERY_SELECT_STUDENT_USING_ID = "SELECT * FROM courses WHERE (course_id) IN (\r\n"
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
				allCourses.removeIf(c -> c.equals(course));
			}
		} catch (SQLException e) {
			throw new DaoException("Failed to add a course");
		}
		return allCourses;
	}

	@Override
	public void addCourse(List<Course> availableCourses, int choosenCourse, int studentId) throws DaoException {

		Course selectedCourse = availableCourses.get(choosenCourse - 1);
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
		String SQL_QUERY_GET_COURSES = "SELECT * FROM courses\r\n" + "WHERE (course_id) IN (\r\n" + "SELECT course_id\r\n"
				+ "FROM students_courses\r\n" + "WHERE student_id = ?)";
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
	public void deleteCourse(int courseId) throws DaoException {

		String QUERY_DELETE_COURSE = "DELETE FROM students_courses\r\n" + "WHERE (course_id, student_id) IN (\r\n"
				+ "SELECT course_id, student_id\r\n" + "FROM students_courses\r\n" + "WHERE student_id = ?\r\n"
				+ "GROUP BY course_id, student_id\r\n" + "HAVING course_id = ?" + ")";

		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(QUERY_DELETE_COURSE);
			statement.setInt(1, courseId);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Failed to delete random course: " + e.getMessage());
		}
	}

	@Override
	public Student getInfo(int id) throws DaoException {
		String QUERY_GET_STUDENT_INFO = "SELECT * FROM students\r\n" + "WHERE student_id = ?";
		Student student = new Student("", "");
		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(QUERY_GET_STUDENT_INFO);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				int studentId = rs.getInt("student_id");
				student.setFirstName(firstName);
				student.setLastName(lastName);
				student.setId(studentId);
			}
		} catch (SQLException e) {
			throw new DaoException("Failed to find student: " + e.getMessage());
		}
		return student;

	}

}
