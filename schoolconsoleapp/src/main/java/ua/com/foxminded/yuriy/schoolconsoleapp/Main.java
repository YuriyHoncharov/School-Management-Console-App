package ua.com.foxminded.yuriy.schoolconsoleapp;

import java.util.HashMap;
import java.util.List;

import ua.com.foxminded.yuriy.schoolconsoleapp.dataGenerator.DataGenerator;
import ua.com.foxminded.yuriy.schoolconsoleapp.dataGenerator.RandomDataGenerator;

public class Main {

	public static void main(String[] args) throws Exception {
		DataGenerator dataGeneration = new DataGenerator();
		dataGeneration.createDataBase();
		dataGeneration.createTables();
		RandomDataGenerator generate = new RandomDataGenerator();
		
		List<String> groups = generate.getGroups(20);
		List<String> students = generate.getStudent(200);
		List<String> courses = generate.getCoursesList();
		
		HashMap<String, List<String>> filledGroups = generate.fillGroup(groups, students);
		
	}
}
