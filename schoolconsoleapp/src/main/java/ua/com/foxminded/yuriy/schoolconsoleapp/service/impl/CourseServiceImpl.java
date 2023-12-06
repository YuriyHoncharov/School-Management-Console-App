package ua.com.foxminded.yuriy.schoolconsoleapp.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.repository.CourseRepository;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	
	
	private CourseRepository courseRepository;
		
	@Autowired
	public CourseServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	@Override
	public List<Course> getAvailableCourses(int studentId) {
		return courseRepository.getAvailableCourses(studentId);
	}

	@Override
	public List<Course> getByStudentId(int studentId) {
		return courseRepository.getById(studentId);
	}

	@Override
	public Course getById(int courseId) {
		return courseRepository.findById(courseId);
	}

	@Override
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}
}
