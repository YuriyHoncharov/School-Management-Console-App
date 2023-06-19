package ua.com.foxminded.yuriy.schoolconsoleapp.commands;

import java.util.Scanner;

public interface Command {

	void execute(Scanner sc);

	String name();

	String description();

}
