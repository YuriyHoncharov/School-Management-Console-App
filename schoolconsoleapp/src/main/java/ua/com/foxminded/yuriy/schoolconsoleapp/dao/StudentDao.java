package ua.com.foxminded.yuriy.schoolconsoleapp.dao;

import java.util.List;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;

public interface StudentDao {

	List<Student> getAllByCourse(String courseName);

	int add(Student student);

	void deleteById(int id);

	Student getById(int id);

	Student getByName(String firstName, String lastName);

	void setGroupById(int id, Group group);

	void addAll(List<Student> students);

}
