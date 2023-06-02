package ua.com.foxminded.yuriy.schoolconsoleapp.service;

import java.util.List;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

public interface StudentService {

	List<Student> findAllByCourse(String courseName) throws DaoException;
	void delete(int id) throws DaoException;
	void add (Student student) throws DaoException;
	Student getInfo (int id) throws DaoException;
	//Add Courses
	List<Course> availableCourses (int studentId) throws DaoException;
	void addCourse (List<Course>availableCourses, int choosenCourse, int studentId) throws DaoException;
	//Delete courses
	List<Course> actualCourses(int studentId) throws DaoException;
	void deleteCourse (int courseId) throws DaoException;
}
		