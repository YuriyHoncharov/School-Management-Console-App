package ua.com.foxminded.yuriy.schoolconsoleapp.dao;
import java.util.List;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

public interface StudentDao {
	
	List<Student> findAllByCourse (Course course) throws DaoException;
	void addNew (Student student) throws DaoException;
	void delete (int id) throws DaoException;
//	void addCourse (Student student, List<Course>courses) throws DaoException;
	void addCourse (List<Course>courses) throws DaoException;
	void deleteCourse () throws DaoException;
}
