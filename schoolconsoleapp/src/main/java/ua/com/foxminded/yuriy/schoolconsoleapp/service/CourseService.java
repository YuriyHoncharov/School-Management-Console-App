package ua.com.foxminded.yuriy.schoolconsoleapp.service;

import java.util.List;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

public interface CourseService {

	List<Course> availableCourses(int studentId);

	void addCourse(Course selectedCourse, int studentId);

	List<Course> actualCourses(int studentId);

	void deleteCourse(int studentId, int courseId);

}
