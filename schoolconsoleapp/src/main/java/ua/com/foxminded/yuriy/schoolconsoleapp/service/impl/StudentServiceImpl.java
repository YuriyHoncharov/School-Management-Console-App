package ua.com.foxminded.yuriy.schoolconsoleapp.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.StudentDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentDao studentDao;

	@Autowired
	public StudentServiceImpl(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public StudentServiceImpl() {
	}

	@Override
	public List<Student> getAllByCourse(int courseId) {
		return studentDao.getAllByCourse(courseId);
	}

	@Override
	public void deleteById(int id) {
		studentDao.deleteById(id);
	}

	@Override
	public int add(Student student) {
		return studentDao.add(student);
	}

	@Override
	public Student getById(int id) {
		return studentDao.getById(id);
	}

	@Override
	public Student getByName(String firstName, String lastName) {
		return studentDao.getByName(firstName, lastName);
	}

	@Override
	public List<Student> getAll() {
		return studentDao.getAll();
	}

	@Override
	public void update(Student student) {
		studentDao.update(student);
	}

}
