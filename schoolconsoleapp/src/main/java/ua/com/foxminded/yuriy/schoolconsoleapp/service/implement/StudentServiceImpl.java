package ua.com.foxminded.yuriy.schoolconsoleapp.service.implement;

import java.util.List;

import ua.com.foxminded.yuriy.schoolconsoleapp.dao.StudentDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.implement.StudentDaoImpl;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.StudentService;

public class StudentServiceImpl implements StudentService {

	private StudentDao studentDao = new StudentDaoImpl();

	@Override
	public List<Student> findAllByCourse(String courseName) throws DaoException {
		return studentDao.findAllByCourse(courseName);
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
	public Student getInfo(int id) throws DaoException {
		return studentDao.getInfo(id);
	}

	@Override
	public List<Course> availableCourses(int studentId) throws DaoException {
		return studentDao.availableCourses(studentId);
	}

	@Override
	public void addCourse(List<Course>availableCourses, int choosenCourse, int studentId) throws DaoException {
		studentDao.addCourse(availableCourses, choosenCourse, studentId);
	}

	@Override
	public List<Course> actualCourses(int studentId) throws DaoException {
		return studentDao.availableCourses(studentId);
	}

	@Override
	public void deleteCourse(int courseId) throws DaoException {
		studentDao.deleteCourse(courseId);
		
	}

}
