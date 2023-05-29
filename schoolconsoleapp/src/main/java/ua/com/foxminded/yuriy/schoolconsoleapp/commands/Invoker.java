package ua.com.foxminded.yuriy.schoolconsoleapp.commands;

import java.util.HashMap;
import java.util.Map;

import ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl.AddCourseToStudentCommand;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl.AddStudentCommand;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl.DeleteStudentCommand;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl.DeleteStudentCourseCommand;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl.FindAllStudentsByCourseCommand;

public class Invoker {

	public static final Map<String, Command> studentCommandMap = new HashMap<>();
	public static final Map<String, Command> courseCommandMap = new HashMap<>();
	public static final Map<String, Command> groupCommandMap = new HashMap<>();

	public static void registerStudentCommands() {
		AddStudentCommand addStudentCommand = new AddStudentCommand();
		DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand();
		FindAllStudentsByCourseCommand findAllStudentsByCourseCommand = new FindAllStudentsByCourseCommand();
		AddCourseToStudentCommand addCourseToStudentCommand = new AddCourseToStudentCommand();
		DeleteStudentCourseCommand deleteStudentCourseCommand = new DeleteStudentCourseCommand();

		studentCommandMap.put(addStudentCommand.name(), addStudentCommand);
		studentCommandMap.put(deleteStudentCommand.name(), deleteStudentCommand);
		studentCommandMap.put(findAllStudentsByCourseCommand.name(), findAllStudentsByCourseCommand);
		studentCommandMap.put(addCourseToStudentCommand.name(), addCourseToStudentCommand);
		studentCommandMap.put(deleteStudentCourseCommand.name(), deleteStudentCourseCommand);
	}

	public static void registerCourseCommands() {
		FindAllStudentsByCourseCommand findAllStudentsByCourseCommand = new FindAllStudentsByCourseCommand();
		courseCommandMap.put(findAllStudentsByCourseCommand.name(), findAllStudentsByCourseCommand);

	}

	public static void registerGroupCommands() {
		AddCourseToStudentCommand addCourseToStudentCommand = new AddCourseToStudentCommand();
		groupCommandMap.put(addCourseToStudentCommand.name(), addCourseToStudentCommand);

	}

}
