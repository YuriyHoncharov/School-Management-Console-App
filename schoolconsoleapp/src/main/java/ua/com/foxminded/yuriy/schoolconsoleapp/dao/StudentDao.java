package ua.com.foxminded.yuriy.schoolconsoleapp.dao;

import java.util.List;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

public interface StudentDao {

	List<Student> findAllByCourse(String courseName) throws DaoException;

	void add(Student student) throws DaoException;

	void delete(int id) throws DaoException;

	Student getInfo(int id) throws DaoException;

	

}
