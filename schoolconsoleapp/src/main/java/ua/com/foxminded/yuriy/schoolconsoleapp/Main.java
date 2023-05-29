package ua.com.foxminded.yuriy.schoolconsoleapp;

import java.util.HashMap;
import java.util.List;

import ua.com.foxminded.yuriy.schoolconsoleapp.config.ConnectionUtil;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.implement.CourseDaoImpl;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.implement.GroupDaoImpl;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.implement.StudentDaoImpl;
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
		
		CourseDaoImpl courseDao = new CourseDaoImpl();
		courseDao.addAll(courses);
		GroupDaoImpl groupDao = new GroupDaoImpl();
		groupDao.addAll(groups);
		StudentDaoImpl studentDao = new StudentDaoImpl();
		studentDao.addAll(students);
		
		
		for (Student student : students) {
			System.out.println(student.toString());
		}
		
		studentDao.addCourse(courses);

		
		System.out.println(groups.toString());
		System.out.println(courses.size());

		

	}
}
