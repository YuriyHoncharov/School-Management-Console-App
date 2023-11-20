package ua.com.foxminded.yuriy.schoolconsoleapp.service;

import java.util.List;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;

public interface StudentService {

	List<Student> getAllByCourse(Course course);

	void deleteById(int id);

	int add(Student student);

	Student getById(int id);

	Student getByName(String firstName, String lastName);

	List<Student> getAll();
	
	void update (Student student);

}
