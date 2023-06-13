package ua.com.foxminded.yuriy.schoolconsoleapp.dao;

import java.util.List;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;


public interface StudentDao {

	List<Student> findAllByCourse(String courseName);

	void add(Student student);

	void delete(int id);

	Student getById(int id);
	
	Student getByName (String firstName, String lastName);
	
	void setGroupById(int id, Group group);
	

}
