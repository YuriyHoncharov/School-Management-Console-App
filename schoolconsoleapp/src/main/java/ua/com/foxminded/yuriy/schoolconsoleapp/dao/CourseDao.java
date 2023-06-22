package ua.com.foxminded.yuriy.schoolconsoleapp.dao;

import java.util.List;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

public interface CourseDao {

	void addAll(List<Course> courses);

	List<Course> getAvailableCourses(int studentId);

	List<Course> getCoursesByStudentId(int studentId);

	void addCourse(Course selectedCourse, int studentId);

	void deregisterCourse(int studentId, int courseId);

	Course getById(int courseId);

}
