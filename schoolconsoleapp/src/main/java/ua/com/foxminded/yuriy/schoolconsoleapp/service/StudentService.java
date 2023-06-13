package ua.com.foxminded.yuriy.schoolconsoleapp.service;

import java.util.List;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

public interface StudentService {

	List<Student> findAllByCourse(String courseName);

	void delete(int id);

	void add(Student student);

	Student getById(int id);
	
	Student getByName (String firstName, String lastName);
	
	void setGroupById(int id, Group group);

}
