package ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl;

import java.util.Scanner;

import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.input.InputValidator;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.StudentService;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.implement.StudentServiceImpl;

public class DeleteStudentCommand implements Command {
	private StudentService studentService = new StudentServiceImpl();

	@Override
	public void execute(Scanner sc) {
		System.out.println("Enter student's ID you want to delete..");

		int id = InputValidator.getNextInt(sc);
		Student student = studentService.getById(id);

		if (student.getFirstName() == null) {
			System.out.println("Student with following ID : " + "[ " + id + " ] is not found.");
		} else {
			System.out.println("Are you sure that you want to delete: " + student.toString());
			System.out.println("Enter - 1 to confirm and - 2 to cancel.");
			int confirmation = InputValidator.getNextInt(sc);
			if (confirmation == 1) {
				studentService.delete(id);
				System.out.println(student.toString() + " has been deleted from database.");
			} else {
				System.out.println("You canceled the operation.");
			}
		}
	}

	@Override
	public String name() {
		return "4";
	}

	@Override
	public String description() {
		return "Delete student by student's ID.";
	}
}
