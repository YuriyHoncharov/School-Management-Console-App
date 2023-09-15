package ua.com.foxminded.yuriy.schoolconsoleapp;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Invoker;
import ua.com.foxminded.yuriy.schoolconsoleapp.config.SpringConfig;
import ua.com.foxminded.yuriy.schoolconsoleapp.dataGenerator.DataGenerator;

public class Main {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
		ConsoleMenu consoleMenu = context.getBean("consoleMenu", ConsoleMenu.class);
		consoleMenu.run();
	}
}
