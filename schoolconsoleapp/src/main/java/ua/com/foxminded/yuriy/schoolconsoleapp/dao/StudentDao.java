package ua.com.foxminded.yuriy.schoolconsoleapp.dao;

import java.util.List;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;

public interface StudentDao {

	List<Student> getAllByCourse(Course course);

	int add(Student student);

	void deleteById(int id);

	Student getById(int id);

	Student getByName(String firstName, String lastName);

	void addAll(List<Student> students);

	List<Student> getAll();
	
	void update(Student student);

}
