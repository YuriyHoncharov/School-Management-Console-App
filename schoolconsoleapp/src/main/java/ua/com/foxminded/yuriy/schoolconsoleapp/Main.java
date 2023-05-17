package ua.com.foxminded.yuriy.schoolconsoleapp;

import java.util.HashMap;
import java.util.List;

import ua.com.foxminded.yuriy.schoolconsoleapp.config.ConnectionUtil;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.CourseDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.GroupDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.StudentDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.dataGenerator.DataGenerator;
import ua.com.foxminded.yuriy.schoolconsoleapp.dataGenerator.RandomDataGenerator;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;

public class Main {

	public static void main(String[] args) throws Exception {
		DataGenerator dataGenerator = new DataGenerator();
		dataGenerator.createDataBase();
		dataGenerator.createTables();
		RandomDataGenerator generate = new RandomDataGenerator();
		// Generate groups

		List<Group> groups = generate.generateGroups();

		// Generate courses
		List<Course> courses = generate.generateCourses();

		// Generate students
		List<Student> students = generate.generateStudents();

		for (Student student : students) {
			System.out.println(student.toString());
		}
		System.out.println(groups.toString());
		System.out.println(courses.size());

		GroupDao groupDao = new GroupDao();
		groupDao.addGroupToDataBase(ConnectionUtil.getConnection(), groups);
		StudentDao studentDao = new StudentDao();
		studentDao.addListOfStudentsToDataBase(ConnectionUtil.getConnection(), students);
		CourseDao courseDao = new CourseDao();
		courseDao.addCourseToDataBase(ConnectionUtil.getConnection(), courses);

	}
}
