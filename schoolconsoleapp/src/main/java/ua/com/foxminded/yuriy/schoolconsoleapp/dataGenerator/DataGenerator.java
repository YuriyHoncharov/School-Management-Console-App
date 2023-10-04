package ua.com.foxminded.yuriy.schoolconsoleapp.dataGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.CourseDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.GroupDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.StudentDao;

@Component
public class DataGenerator {

	private CourseDao courseDao;
	private GroupDao groupDao;
	private StudentDao studentDao;

	public DataGenerator() {
	}

	@Autowired
	public DataGenerator(CourseDao courseDao, GroupDao groupDao, StudentDao studentDao) {
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
