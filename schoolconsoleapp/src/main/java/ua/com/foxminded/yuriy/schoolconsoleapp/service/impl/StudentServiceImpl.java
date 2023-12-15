package ua.com.foxminded.yuriy.schoolconsoleapp.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.logger.CustomLogger;
import ua.com.foxminded.yuriy.schoolconsoleapp.repository.StudentRepository;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository studentRepository;
	private CustomLogger customLogger;

	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository, CustomLogger customLogger) {
		this.studentRepository = studentRepository;
		this.customLogger = customLogger;
	}

	public StudentServiceImpl() {
	}

	@Override
	public List<Student> getAllByCourse(Course course) {
		customLogger.logInfo("User trying to get all students that follows the followind course : " + course.getName());
		return studentRepository.getAllByCoursesContains(course);
	}

	@Override
	@Transactional
	public void delete(Student student) {
		customLogger.logInfo("User trying to delete the following student : " + student.toString());
		studentRepository.delete(student);
	}

	@Override
	@Transactional
	public int add(Student student) {
		customLogger.logInfo("User trying to add the following student to data base : " + student.getFirstName() + " " + student.getLastName());
		studentRepository.save(student);
		return student.getId();
	}

	@Override
	public Student getById(int id) {
		customLogger.logInfo("User trying to get student with followind ID : " + id);
		return studentRepository.getById(id);
	}

	@Override
	public List<Student> getAll() {
		customLogger.logInfo("User trying to get the entire list of Students fropm database");
		return studentRepository.getAll();
	}

	@Override
	@Transactional
	public void update(Student student) {
		customLogger.logInfo("User trying to update the student information");
		studentRepository.save(student);
	}

	@Override
	public int countByGroup(Group group) {
		customLogger.logInfo("User trying to get count of students by group :" + group.toString());
		return studentRepository.countByGroup(group);
	}

	@Override
	@Transactional
	public void saveAll(List<Student> student) {
		studentRepository.saveAll(student);
	}
}
