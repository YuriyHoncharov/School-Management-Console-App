package ua.com.foxminded.yuriy.schoolconsoleapp;

import java.util.HashMap;
import java.util.List;

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
	    List<Group> groups = generate.generateGroups(10);

	    // Generate courses
	    List<Course> courses = generate.generateCourses();

	    // Generate students
	    List<Student> students = generate.generateStudents(200);
	    
	   generate.assignId(groups);
	    
	    generate.assignStudentsToGroups(students, groups);
	    
	    for (Student student : students) {
			System.out.println(student.toString());
		}
	    
	    
				System.out.println(groups.toString());
			

	
	}
}
