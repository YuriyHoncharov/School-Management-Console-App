package ua.com.foxminded.yuriy.schoolconsoleapp.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;

public interface StudentDao {

	List<Student> getAllByCourse(Course course);

	int add(Student student);

	void delete(Student student);

	Student getById(int id);

	Student getByName(String firstName, String lastName);

	void addAll(List<Student> students);

	List<Student> getAll();
	
	void update(Student student);
	
	int studentsCountByGroup(Group group);

}
