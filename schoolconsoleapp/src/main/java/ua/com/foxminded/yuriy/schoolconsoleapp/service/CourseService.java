package ua.com.foxminded.yuriy.schoolconsoleapp.service;

import java.util.List;
import java.util.Optional;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;

public interface CourseService {

	List<Course> getAvailableCourses(int studentId);

	Course getById(int courseId);
	
	List<Course> getAllCourses();

}
