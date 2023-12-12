package ua.com.foxminded.yuriy.schoolconsoleapp.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.repository.StudentRepository;
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
		return studentRepository.getAllByCoursesContains(course);
	}

	@Override
	@Transactional
	public void delete(Student student) {
		studentRepository.delete(student);
	}

	@Override
	@Transactional
	public int add(Student student) {
		studentRepository.save(student);
		return student.getId();
	}

	@Override
	public Student getById(int id) {
		return studentRepository.getById(id);
	}

	@Override
	public List<Student> getAll() {
		return studentRepository.getAll();
	}

	@Override
	@Transactional
	public void update(Student student) {
		studentRepository.save(student);
	}

	@Override
	public int countByGroup(Group group) {
		return studentRepository.countByGroup(group);
	}

	@Override
	@Transactional
	public void saveAll(List<Student> student) {
		studentRepository.saveAll(student);
	}
}
