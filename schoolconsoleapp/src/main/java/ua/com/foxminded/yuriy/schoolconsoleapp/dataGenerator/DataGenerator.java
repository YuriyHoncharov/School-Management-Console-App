package ua.com.foxminded.yuriy.schoolconsoleapp.dataGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.CourseDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.GroupDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.StudentDao;

@Component
public class DataGenerator {

	public final static String FILE_PATH = "src/main/resources/";
	public final static String DATA_BASE_PATH = "data_base_creation.sql";
	public final static String TABLE_PATH = "tables_creation.sql";
	public final static String DATA_BASE_FILE_PATH = FILE_PATH + DATA_BASE_PATH;
	public final static String TABLE_FILE_PATH = FILE_PATH + TABLE_PATH;

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
