package ua.com.foxminded.yuriy.schoolconsoleapp.service.implement;

import java.util.List;

import ua.com.foxminded.yuriy.schoolconsoleapp.dao.CourseDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.implement.CourseDaoImpl;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.CourseService;

public class CourseServiceImpl implements CourseService {

	private CourseDao courseDao = new CourseDaoImpl();

	@Override
	public List<Course> availableCourses(int studentId) throws DaoException {
		return courseDao.getAvailableCourses(studentId);
	}

	@Override
	public void addCourse(Course selectedCourse, int studentId) throws DaoException {
		courseDao.addCourse(selectedCourse, studentId);
	}

	@Override
	public List<Course> actualCourses(int studentId) throws DaoException {
		return courseDao.getCoursesByStudentId(studentId);
	}

	@Override
	public void deleteCourse(int studentId, int courseId) throws DaoException {
		courseDao.deleteCourse(studentId, courseId);

	}

}
