package ua.com.foxminded.yuriy.schoolconsoleapp.dao;

import java.util.List;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;

public interface CourseDao {

	void addAll(List<Course> courses);

	List<Course> getAvailableCourses(int studentId);

	List<Course> getByStudentId(int studentId);

	Course getById(int courseId);

	List<Course> getAllCourses();

}
