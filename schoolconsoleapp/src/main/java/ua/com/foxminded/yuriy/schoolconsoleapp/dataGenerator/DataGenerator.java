package ua.com.foxminded.yuriy.schoolconsoleapp.dataGenerator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.CourseDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.GroupDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.StudentDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.SqlRunException;
import ua.com.foxminded.yuriy.schoolconsoleapp.util.FileHandler;

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
	private JdbcTemplate jdbcTemplate;

	public DataGenerator() {
	}

	@Autowired
	public DataGenerator(CourseDao courseDao, GroupDao groupDao, StudentDao studentDao, JdbcTemplate jdbcTemplate) {
		this.courseDao = courseDao;
		this.groupDao = groupDao;
		this.studentDao = studentDao;
		this.jdbcTemplate = jdbcTemplate;
	}

	public void createDataBase() {

		String urlCreateDataBase = "jdbc:postgresql://localhost:5432/";
		String user = "postgres";
		String password = "1234";
		try (Connection connection = DriverManager.getConnection(urlCreateDataBase, user, password)) {
			PreparedStatement statement = connection.prepareStatement(FileHandler.readFile(DATA_BASE_FILE_PATH));
			statement.execute();
		} catch (Exception e) {
			throw new ua.com.foxminded.yuriy.schoolconsoleapp.exception.ConnectException(
					"Failed to create database, connection error: " + e.getMessage());
		}
		System.out.println("Database created.");
	}

	public void createTables() {

		runSQLScript(FileHandler.readFile(TABLE_FILE_PATH));
		System.out.println("Tables were created.");
	}

	public void runSQLScript(String sqlQuery) {
		try {
			jdbcTemplate.execute(sqlQuery);
		} catch (Exception e) {
			throw new SqlRunException("An error occured: " + e.getMessage());
		}
	}

	public void initializeAndPopulateTestDatabase() {
		createDataBase();
		createTables();
		RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
		groupDao.addAll(randomDataGenerator.generateGroups());
		courseDao.addAll(randomDataGenerator.generateCourses());
		studentDao.addAll(randomDataGenerator.generateStudents());
	}
}
