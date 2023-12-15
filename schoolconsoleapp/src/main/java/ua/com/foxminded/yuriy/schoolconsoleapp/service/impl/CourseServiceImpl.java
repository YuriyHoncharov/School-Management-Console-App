package ua.com.foxminded.yuriy.schoolconsoleapp.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.logger.CustomLogger;
import ua.com.foxminded.yuriy.schoolconsoleapp.repository.CourseRepository;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	private CourseRepository courseRepository;
	private CustomLogger customLogger;

	@Autowired
	public CourseServiceImpl(CourseRepository courseRepository, CustomLogger customLogger) {
		this.courseRepository = courseRepository;
		this.customLogger = customLogger;
	}

	@Override
	public List<Course> getAvailableCourses(int studentId) {
		customLogger.logInfo("User trying to get available courses for student with following ID : " + studentId);
		return courseRepository.findAllByStudentId(studentId);

	}

	@Override
	public Course getById(int courseId) {
		Optional<Course> optionalCourse = courseRepository.findById(courseId);
		customLogger.logInfo("User trying to get course with following ID : " + courseId);
		return optionalCourse.orElse(null);
	}

	@Override
	public List<Course> getAllCourses() {
		customLogger.logInfo("User trying to get all courses list");
		return courseRepository.findAll();
	}
}
