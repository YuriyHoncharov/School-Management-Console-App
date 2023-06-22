package ua.com.foxminded.yuriy.schoolconsoleapp.service;

import java.util.List;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;

public interface CourseService {

	List<Course> availableCourses(int studentId);

	void addCourse(Course selectedCourse, int studentId);

	List<Course> actualCourses(int studentId);

	void deregisterCourse(int studentId, int courseId);

	Course getById(int courseId);

}
