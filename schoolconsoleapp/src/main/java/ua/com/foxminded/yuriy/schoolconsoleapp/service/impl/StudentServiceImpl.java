package ua.com.foxminded.yuriy.schoolconsoleapp.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.StudentRepository;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository studentRepository;

	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public StudentServiceImpl() {
	}

	@Override
	public List<Student> getAllByCourse(Course course) {
		return studentRepository.findAllByCoursesContains(course);
	}

	@Override
	public void delete(Student student) {
		studentRepository.delete(student);
	}

	@Override
	public int add(Student student) {
		return studentRepository.addStudent(student);
	}

	@Override
	public Student getById(int id) {
		return studentRepository.getById(id);
	}

	@Override
	public Student getByName(String firstName, String lastName) {
		return studentRepository.getByName(firstName, lastName);
	}

	@Override
	public List<Student> getAll() {
		return studentRepository.getAll();
	}

	@Override
	public void update(Student student) {
		studentRepository.update(student);
	}
	
	@Override
	public int studentsCountByGroup(Group group) {
		return studentRepository.studentsCountByGroup(group);
	}

}
