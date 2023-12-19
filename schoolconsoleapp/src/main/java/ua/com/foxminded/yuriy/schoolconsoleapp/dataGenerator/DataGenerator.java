package ua.com.foxminded.yuriy.schoolconsoleapp.dataGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.com.foxminded.yuriy.schoolconsoleapp.repository.CourseRepository;
import ua.com.foxminded.yuriy.schoolconsoleapp.repository.GroupRepository;
import ua.com.foxminded.yuriy.schoolconsoleapp.repository.StudentRepository;

@Component
public class DataGenerator {

	private CourseRepository courseRepository;
	private GroupRepository groupRepository;
	private StudentRepository studentRepository;

	public DataGenerator() {
	}

	@Autowired
	public DataGenerator(CourseRepository courseDao, GroupRepository groupDao, StudentRepository studentDao) {
		this.courseRepository = courseDao;
		this.groupRepository = groupDao;
		this.studentRepository = studentDao;

	}

	public void initializeAndPopulateTestDatabase() {
		RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
		groupRepository.saveAll(randomDataGenerator.generateGroups());
		courseRepository.saveAll(randomDataGenerator.generateCourses());
		studentRepository.saveAll(randomDataGenerator.generateStudents());
	}
}
