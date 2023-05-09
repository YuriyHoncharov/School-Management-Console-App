package ua.com.foxminded.yuriy.schoolconsoleapp;

import java.util.HashMap;
import java.util.List;

import ua.com.foxminded.yuriy.schoolconsoleapp.dataGenerator.DataGenerator;
import ua.com.foxminded.yuriy.schoolconsoleapp.dataGenerator.RandomDataGenerator;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;

public class Main {

//	public static void main(String[] args) throws Exception {
//		DataGenerator dataGenerator = new DataGenerator();
//		dataGenerator.createDataBase();
//		dataGenerator.createTables();
//		RandomDataGenerator generate = new RandomDataGenerator();
//	// Generate groups
//	    List<Group> groups = generate.generateGroups(3);
//
//	    // Generate courses
//	    List<Course> courses = generate.generateCourses();
//
//	    // Generate students
//	    List<Student> students = generate.generateStudents(10);
//
//	    // Assign students to groups
//	    generate.assignStudentsToGroup(students, groups);
//
//	    // Assign courses to students
//	    generate.assignCourses(students, courses, 3);
//
//	    // Print the assigned courses for each student
//	    for (Student student : students) {
//	        System.out.println("Student: " + student.getFirstName() + " " + student.getLastName());
//	        System.out.println("Assigned Courses:");
//	        for (Course course : student.getCourses()) {
//	            System.out.println(course.getName() + " - " + course.getDescription());
//	        }
//	        System.out.println();
//	    }		
//	}
}
