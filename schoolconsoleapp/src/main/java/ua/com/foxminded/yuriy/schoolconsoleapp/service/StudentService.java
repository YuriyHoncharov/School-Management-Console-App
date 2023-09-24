package ua.com.foxminded.yuriy.schoolconsoleapp.service;

import java.util.List;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;

public interface StudentService {

	List<Student> getAllByCourse(int courseId);

	void deleteById(int id);

	int add(Student student);

	Student getById(int id);

	Student getByName(String firstName, String lastName);

	void setGroupById(int id, Group group);

}
