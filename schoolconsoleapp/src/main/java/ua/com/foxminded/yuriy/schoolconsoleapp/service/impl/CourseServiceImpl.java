package ua.com.foxminded.yuriy.schoolconsoleapp.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.repository.CourseRepository;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.CourseService;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

	private CourseRepository courseRepository;

	@Autowired
	public CourseServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@Override
	public List<Course> getAvailableCourses(int studentId) {
		return courseRepository.findAllByStudents_IdNot(studentId);
	}

	@Override
	public Course getById(int courseId) {
		Optional<Course> optionalCourse = courseRepository.findById(courseId);
		return optionalCourse.orElse(null);
	}

	@Override
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}
}
