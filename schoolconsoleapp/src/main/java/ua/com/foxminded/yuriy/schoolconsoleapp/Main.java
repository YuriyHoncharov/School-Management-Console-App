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
		
		
		
		
	}
}
