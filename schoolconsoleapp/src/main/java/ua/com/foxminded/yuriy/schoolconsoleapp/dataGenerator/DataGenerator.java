package ua.com.foxminded.yuriy.schoolconsoleapp.dataGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.CourseRepository;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.GroupRepository;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.StudentRepository;

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
		groupDao.addAll(randomDataGenerator.generateGroups());
		courseDao.addAll(randomDataGenerator.generateCourses());
		studentDao.addAll(randomDataGenerator.generateStudents());
	}
}
