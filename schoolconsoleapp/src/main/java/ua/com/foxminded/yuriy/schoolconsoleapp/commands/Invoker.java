package ua.com.foxminded.yuriy.schoolconsoleapp.commands;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.GroupCommandsImpl.GetGroupsByStudentsNumberCommand;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl.AddCourseToStudentCommand;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl.AddStudentCommand;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl.DeleteStudentCommand;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl.DeleteStudentCourseCommand;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl.GetAllStudentsByCourseCommand;

@Component
public class Invoker {

	private AddStudentCommand addStudentCommand;
	private DeleteStudentCommand deleteStudentCommand;
	private GetAllStudentsByCourseCommand findAllStudentsByCourseCommand;
	private AddCourseToStudentCommand addCourseToStudentCommand;
	private DeleteStudentCourseCommand deleteStudentCourseCommand;
	private GetGroupsByStudentsNumberCommand findGroupsByStudentsNumberCommand;
	public final Map<String, Command> commandMap = new HashMap<>();

	@Autowired
	public Invoker(AddStudentCommand addStudentCommand, DeleteStudentCommand deleteStudentCommand,
			GetAllStudentsByCourseCommand findAllStudentsByCourseCommand,
			AddCourseToStudentCommand addCourseToStudentCommand, DeleteStudentCourseCommand deleteStudentCourseCommand,
			GetGroupsByStudentsNumberCommand findGroupsByStudentsNumberCommand) {
		this.addStudentCommand = addStudentCommand;
		this.deleteStudentCommand = deleteStudentCommand;
		this.findAllStudentsByCourseCommand = findAllStudentsByCourseCommand;
		this.addCourseToStudentCommand = addCourseToStudentCommand;
		this.deleteStudentCourseCommand = deleteStudentCourseCommand;
		this.findGroupsByStudentsNumberCommand = findGroupsByStudentsNumberCommand;
	}

	public final Map<String, Command> registerCommands() {

		commandMap.put(addStudentCommand.name(), addStudentCommand);
		commandMap.put(deleteStudentCommand.name(), deleteStudentCommand);
		commandMap.put(findAllStudentsByCourseCommand.name(), findAllStudentsByCourseCommand);
		commandMap.put(addCourseToStudentCommand.name(), addCourseToStudentCommand);
		commandMap.put(deleteStudentCourseCommand.name(), deleteStudentCourseCommand);
		commandMap.put(findGroupsByStudentsNumberCommand.name(), findGroupsByStudentsNumberCommand);

		return commandMap;
	}
}
