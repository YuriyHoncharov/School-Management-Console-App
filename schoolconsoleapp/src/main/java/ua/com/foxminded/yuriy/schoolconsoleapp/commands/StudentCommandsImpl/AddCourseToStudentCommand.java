package ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl;

import java.util.Scanner;

import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;

public class AddCourseToStudentCommand implements Command {

	@Override
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the student's id...");
		int studentId = scanner.nextInt();
	}

	@Override
	public String name() {
		return "4";
	}

	@Override
	public String description() {
		return "Add course to the student.";
	}

}
