package ua.com.foxminded.yuriy.schoolconsoleapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ua.com.foxminded.yuriy.schoolconsoleapp.config.ConnectionUtil;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.StudentDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.sqlqueries.SqlStudentQueries;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.tables.StudentsColumns;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

@Component
public class StudentDaoImpl implements StudentDao {

	public void addAll(List<Student> students) {

		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SqlStudentQueries.ADD_ALL,
					Statement.RETURN_GENERATED_KEYS);
			PreparedStatement coursesStatement = connection.prepareStatement(SqlStudentQueries.ADD_COURSES);
			for (Student student : students) {
				statement.setInt(1, student.getGroupId());
				statement.setString(2, student.getFirstName());
				statement.setString(3, student.getLastName());
				try {
					statement.executeUpdate();
				} catch (SQLException e) {
					throw new DaoException("Failed to add : " + student.toString() + " to database.");
				}	
				ResultSet generatedKeys = statement.getGeneratedKeys();
				if (generatedKeys.next()) {
					student.setId(generatedKeys.getInt(1));
				}
				generatedKeys.close();
				List<Course> courses = student.getCourses();
				for (Course course : courses) {
					coursesStatement.setInt(1, course.getId());
					coursesStatement.setInt(2, student.getId());
					coursesStatement.executeUpdate();
				}
			}
		} catch (SQLException e) {
			throw new DaoException("Failed to add students to Data Base: " + e.getMessage());
		}
	}

	@Override
	public List<Student> getAllByCourse(String courseName) {
		List<Student> studentsOfCourse = new ArrayList<>();
		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SqlStudentQueries.GET_STUDENTS_ON_COURSE);
			statement.setString(1, courseName);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Student student = new Student(rs.getString(StudentsColumns.FIRST_NAME),
						rs.getString(StudentsColumns.LAST_NAME));
				student.setId(rs.getInt(StudentsColumns.STUDENT_ID));
				studentsOfCourse.add(student);
			}
		} catch (SQLException e) {
			throw new DaoException("Failed to find students that follow the course : " + courseName);
		}
		return studentsOfCourse;
	}

	@Override
	public int add(Student student) {
		int id = 0;
		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SqlStudentQueries.ADD_NEW,
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, student.getFirstName());
			statement.setString(2, student.getLastName());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new DaoException(
					"Failed to add the new student : [" + student.getFirstName() + " " + student.getLastName() + "]");
		}
		return id;
	}

	@Override
	public void deleteById(int id) {

		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SqlStudentQueries.DELETE);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Failed to delete the student with the following ID : " + id);
		}
	}

	@Override
	public Student getById(int id) {

		Student student = null;
		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SqlStudentQueries.GET_INFO_BY_ID);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				student = new Student(rs.getString(StudentsColumns.FIRST_NAME), rs.getString(StudentsColumns.LAST_NAME));
				student.setId(rs.getInt(StudentsColumns.STUDENT_ID));
				student.setGroupId(rs.getInt(StudentsColumns.GROUP_ID));
			}
		} catch (SQLException e) {
			throw new DaoException("Failed to get student with the following ID : " + id);
		}
		return student;
	}

	@Override
	public void setGroupById(int id, Group group) {

		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SqlStudentQueries.SET_GROUP_ID);
			statement.setInt(1, group.getId());
			statement.setInt(2, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Failed to add group " + group.getName() + " to the student : " + id + " | ");
		}
	}

	@Override
	public Student getByName(String firstName, String lastName) {

		Student student = null;
		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SqlStudentQueries.GET_INFO_BY_NAME_LASTNAME);
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				student = new Student(firstName, lastName);
				student.setId(rs.getInt(StudentsColumns.STUDENT_ID));
				student.setGroupId(rs.getInt(StudentsColumns.GROUP_ID));
			}
		} catch (SQLException e) {
			throw new DaoException("Failed to find the following student : [" + firstName + " " + lastName + "]");
		}
		return student;
	}
}
