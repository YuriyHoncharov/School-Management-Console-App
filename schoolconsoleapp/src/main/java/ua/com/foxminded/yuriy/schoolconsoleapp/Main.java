package ua.com.foxminded.yuriy.schoolconsoleapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import ua.com.foxminded.yuriy.schoolconsoleapp.config.ConnectionUtil;
import ua.com.foxminded.yuriy.schoolconsoleapp.dataGenerator.DataGenerator;

public class Main {

	public static void main(String[] args) throws Exception {
		DataGenerator dataGeneration = new DataGenerator(ConnectionUtil.getConnection());
		dataGeneration.createDataBase();
		dataGeneration.createTables();

	}
}
