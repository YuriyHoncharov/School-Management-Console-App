package ua.com.foxminded.yuriy.schoolconsoleapp.service.implement;

import java.util.List;

import ua.com.foxminded.yuriy.schoolconsoleapp.dao.StudentDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.StudentService;

public class StudentServiceImpl implements StudentService {

	private StudentDao studentDao;

	@Override
	public List<Student> findAllByCourse(String courseName) throws DaoException {
		return studentDao.findAllByCourse(courseName);

	}

	@Override
	public void delete(int id) throws DaoException {
		studentDao.delete(id);
		
	}

}
