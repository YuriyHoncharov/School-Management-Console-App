package ua.com.foxminded.yuriy.schoolconsoleapp.dataGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.com.foxminded.yuriy.schoolconsoleapp.repository.CourseRepository;
import ua.com.foxminded.yuriy.schoolconsoleapp.repository.GroupRepository;
import ua.com.foxminded.yuriy.schoolconsoleapp.repository.StudentRepository;

@Component
public class DataGenerator {

	private CourseRepository courseDao;
	private GroupRepository groupDao;
	private StudentRepository studentDao;

	public DataGenerator() {
	}

	@Autowired
	public DataGenerator(CourseRepository courseDao, GroupRepository groupDao, StudentRepository studentDao) {
		this.courseDao = courseDao;
		this.groupDao = groupDao;
		this.studentDao = studentDao;

	}

	public void initializeAndPopulateTestDatabase() {
		RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
		groupDao.saveAll(randomDataGenerator.generateGroups());
		courseDao.saveAll(randomDataGenerator.generateCourses());
		studentDao.saveAll(randomDataGenerator.generateStudents());
	}
}
