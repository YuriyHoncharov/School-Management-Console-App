package ua.com.foxminded.yuriy.schoolconsoleapp.commands;

import java.util.Scanner;

import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

public interface Command {

	void execute(Scanner sc) throws DaoException;

	String name();

	String description();

}
