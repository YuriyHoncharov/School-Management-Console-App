package ua.com.foxminded.yuriy.schoolconsoleapp.service;

import java.util.List;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;

public interface StudentService {

	List<Student> findAllByCourse(Course course);

	Student add();

	int deleteById();	

	int courseIdToDelete();

	void deleteCourse();

	void addCourse(List<Course> courses);

	void deleteCourseById();
}
