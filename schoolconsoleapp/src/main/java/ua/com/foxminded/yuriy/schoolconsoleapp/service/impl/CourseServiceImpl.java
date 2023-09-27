package ua.com.foxminded.yuriy.schoolconsoleapp.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.CourseDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	
	
	private CourseDao courseDao;
		
	@Autowired
	public CourseServiceImpl(CourseDao courseDao) {
		this.courseDao = courseDao;
	}
	
	public CourseServiceImpl(){		
	}

	@Override
	public List<Course> getAvailableCourses(int studentId) {
		return courseDao.getAvailableCourses(studentId);
	}

	@Override
	public List<Course> getByStudentId(int studentId) {
		return courseDao.getByStudentId(studentId);
	}

	@Override
	public Course getById(int courseId) {
		return courseDao.getById(courseId);
	}

	@Override
	public List<Course> getAllCourses() {
		return courseDao.getAllCourses();
	}
}
