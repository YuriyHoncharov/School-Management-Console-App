package ua.com.foxminded.yuriy.schoolconsoleapp.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import ua.com.foxminded.yuriy.schoolconsoleapp.config.ConnectionUtil;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.StudentDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

public class StudentDaoImpl implements StudentDao {

	public void addAll(List<Student> students) throws DaoException {
		String sqlQuery = "INSERT INTO students(group_id, first_name, last_name) VALUES (?, ?, ?)";

		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			for (int i = 0; i < students.size(); i++) {
				int groupId = students.get(i).getGroupId();
				String FirstName = students.get(i).getFirstName();
				String LastName = students.get(i).getLastName();
				statement.setInt(1, groupId);
				statement.setString(2, FirstName);
				statement.setString(3, LastName);
				statement.executeUpdate();
			}
			statement.close();
		} catch (SQLException e) {
			throw new DaoException("Failed to add students: " + e.getMessage());
		}
	}

	@Override
	public List<Student> findAllByCourse(Course course) throws DaoException {

		String QUERY_SELECT_STUDENTS_ON_COURSE = "SELECT students.student_id, students.first_name, students.last_name\r\n"
				+ "FROM students\r\n" + "INNER JOIN students_course ON students.student_id = students_course.student_id\r\n"
				+ "INNER JOIN courses ON students_course.course_id = courses.course_id\r\n"
				+ "WHERE courses.course_name = ?";
		List<Student> studentsOfCourse = new ArrayList<>();

		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_STUDENTS_ON_COURSE);
			statement.setString(1, course.getName());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				int id = rs.getInt("student_id");
				Student student = new Student(firstName, lastName, id);
				studentsOfCourse.add(student);
			}
		} catch (SQLException e) {
			throw new DaoException("Failed to find students" + e.getMessage());
		}
		return studentsOfCourse;
	}

	@Override
	public void addNew(Student student) throws DaoException {
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

//	@Override
//	public void addCourse(Student student, List<Course> courses) throws DaoException {
//		Random random = new Random();
//		String QUERY_ADD_COURSE = "INSERT INTO students_courses (course_id, student_id) VALUES (? ,?)";
//
//		try (Connection connection = ConnectionUtil.getConnection()) {
//			PreparedStatement statement = connection.prepareStatement(QUERY_ADD_COURSE);
//			int randomIdCourse = courses.get(random.nextInt(courses.size())).getId();
//			statement.setInt(1, randomIdCourse);
//			statement.setInt(2, student.getId());
//			statement.executeUpdate();
//
//		} catch (SQLException e) {
//			throw new DaoException("Failed to assign the course: " + e.getMessage());
//		}
//	}

	@Override
	public void addCourse(List<Course> courses) throws DaoException {

		List<Course> coursesToAdd = new ArrayList<>(courses);
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Please enter the student's id...");
			int studentId = scanner.nextInt();

			String QUERY_SELECT_STUDENT_USING_ID = "SELECT * FROM courses WHERE (course_id) IN (\r\n"
					+ "SELECT course_id FROM students_courses WHERE student_id = ?";
			try (Connection connection = ConnectionUtil.getConnection()) {
				PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_STUDENT_USING_ID);
				statement.setInt(1, studentId);
				ResultSet rs = statement.executeQuery();
				if (rs.next()) {
					int id = rs.getInt("course_id");
					String courseName = rs.getString("course_name");
					String courseDescription = rs.getString("course_description");
					Course course = new Course(courseName, courseDescription, id);
					if (coursesToAdd.contains(course)) {
						coursesToAdd.remove(course);
					}
				} else {
					System.out.println("This student does not exist.");

				}
			} catch (SQLException e) {
				throw new DaoException("Failed to add a course");
			}
			System.out.println("Please select the course to add...");
			for (int i = 0; i <= coursesToAdd.size(); i++) {
				Course course = coursesToAdd.get(i);
				System.out.println((i + 1) + ". " + course.getName() + " | " + course.getDescription());
			}
			int chosenCourse = scanner.nextInt();

			if (chosenCourse - 1 >= coursesToAdd.size()) {
				throw new DaoException("This number is missing, please choose the course from the list");
			}

			Course selectedCourse = coursesToAdd.get(chosenCourse - 1);
			try (Connection connection = ConnectionUtil.getConnection()) {
				String QUERY_ADD_COURSE = "INSERT INTO students_courses (course_id, student_id) VALUES (?, ?)";
				PreparedStatement statement = connection.prepareStatement(QUERY_ADD_COURSE);
				statement.setInt(1, selectedCourse.getId());
				statement.setInt(2, studentId);
				statement.executeUpdate();
				System.out.println("Course has been succesfuly added to the student.");
			} catch (SQLException e) {
				throw new DaoException("Failed to add the new course");
			}
		}
	}

	@Override
	public void deleteRandomCourse(Student student) throws DaoException {
		String QUERY_DELETE_RANDOM_COURSE = "DELETE FROM students_courses \r\n" + "WHERE (course_id, student_id) IN (\r\n"
				+ "SELECT course_id, student_id\r\n" + "FROM students_courses\r\n" + "WHERE student_id = ?\r\n"
				+ "GROUP BY course_id, student_id\r\n" + "ORDER BY random()\r\n" + "LIMIT 1\r\n" + ")";

		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(QUERY_DELETE_RANDOM_COURSE);
			statement.setInt(1, student.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Failed to delete random course: " + e.getMessage());
		}

	}

}
