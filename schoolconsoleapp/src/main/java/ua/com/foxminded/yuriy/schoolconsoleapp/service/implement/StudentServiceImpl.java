package ua.com.foxminded.yuriy.schoolconsoleapp.service.implement;

import java.util.List;

import ua.com.foxminded.yuriy.schoolconsoleapp.dao.StudentDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.implement.StudentDaoImpl;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.StudentService;

public class StudentServiceImpl implements StudentService {

	private StudentDao studentDao = new StudentDaoImpl();

	@Override
	public List<Student> getAllByCourse(String courseName) throws DaoException {
		return studentDao.getAllByCourse(courseName);
	}

	@Override
	public void delete(int id) throws DaoException {
		studentDao.delete(id);

	}

	@Override
	public void add(Student student) throws DaoException {
		studentDao.add(student);
	}

	@Override
	public Student getById(int id) throws DaoException {
		Student student = studentDao.getById(id);
		return student;
	}

	@Override
	public void setGroupById(int id, Group group) {
		studentDao.setGroupById(id, group);
	}

	@Override
	public Student getByName(String firstName, String lastName) {
		Student student = studentDao.getByName(firstName, lastName);
		return student;
	}
}
