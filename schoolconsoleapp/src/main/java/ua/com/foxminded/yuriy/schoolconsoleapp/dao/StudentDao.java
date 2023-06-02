package ua.com.foxminded.yuriy.schoolconsoleapp.dao;
import java.util.List;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

public interface StudentDao {
	
	List<Student> findAllByCourse (String courseName) throws DaoException;
	void add (Student student) throws DaoException;
	void delete (int id) throws DaoException;
	List<Course> availableCourses (int studentId) throws DaoException;
	void addCourse(List<Course>availableCourses, int choosenCourse, int studentId) throws DaoException;
	List<Course> selectCourses(int studentId) throws DaoException;
	void deleteCourse (int courseId) throws DaoException;
	Student getInfo (int id) throws DaoException;
}
