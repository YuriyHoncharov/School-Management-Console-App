package ua.com.foxminded.yuriy.schoolconsoleapp.service;

import java.util.List;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;

public interface CourseService {

	List<Course> getAvailableCourses(int studentId);

	void addToStudent(Course selectedCourse, int studentId);

	List<Course> getByStudentId(int studentId);

	void deregisterCourse(int studentId, int courseId);

	Course getById(int courseId);
	
	List<Course> getAllCourses();

}
