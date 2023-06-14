package ua.com.foxminded.yuriy.schoolconsoleapp.dao;

import java.util.List;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

public interface CourseDao {

	void addAll(List<Course> courses) throws DaoException;

	List<Course> getAvailableCourses(int studentId) throws DaoException;

	List<Course> getCoursesByStudentId(int studentId) throws DaoException;

	void addCourse(Course selectedCourse, int studentId) throws DaoException;

	void deleteCourse(int studentId, int courseId) throws DaoException;
}
