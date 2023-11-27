package ua.com.foxminded.yuriy.schoolconsoleapp.service;

import java.util.List;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;

public interface StudentService {

	List<Student> getAllByCourse(Course course);

	void delete(Student student);

	int add(Student student);

	Student getById(int id);

	Student getByName(String firstName, String lastName);

	List<Student> getAll();
	
	void update (Student student);
	
	int studentsCountByGroup(Group group);

}
