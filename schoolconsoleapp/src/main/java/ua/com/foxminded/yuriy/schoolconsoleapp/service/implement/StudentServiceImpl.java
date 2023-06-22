package ua.com.foxminded.yuriy.schoolconsoleapp.service.implement;

import java.util.List;

import ua.com.foxminded.yuriy.schoolconsoleapp.dao.StudentDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.impl.StudentDaoImpl;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.StudentService;

public class StudentServiceImpl implements StudentService {

	private StudentDao studentDao = new StudentDaoImpl();

	@Override
	public List<Student> getAllByCourse(String courseName) {
		return studentDao.getAllByCourse(courseName);
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
	public void setGroupById(int id, Group group) {
		studentDao.setGroupById(id, group);
	}

	@Override
	public Student getByName(String firstName, String lastName) {
		return studentDao.getByName(firstName, lastName);
	}
}
