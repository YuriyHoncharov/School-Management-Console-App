package ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl;

import java.util.Scanner;

import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.StudentService;
import ua.com.foxminded.yuriy.schoolconsoleapp.util.InputValidator;

public class DeleteStudentCommand implements Command {
	
	private StudentService studentService;

	public DeleteStudentCommand(StudentService studentService) {
		this.studentService = studentService;
	}

	@Override
	public void execute(Scanner sc) {

		Student student = getStudent(sc);
		if (student == null) {
			System.out.println("Student with entered ID is not found.");
		} else {
			if (choiceToDelete(sc, student)) {
				studentService.deleteById(student.getId());
				System.out.println(student.toString() + " has been deleted from database.");
			} else {
				System.out.println("You canceled the operation.");
			}
		}
	}

	private Student getStudent(Scanner sc) {
		System.out.println("Enter student's ID you want to delete..");
		int id = InputValidator.getNextInt(sc);
		return studentService.getById(id);
	}

	private boolean choiceToDelete(Scanner sc, Student student) {
		System.out.println("Are you sure that you want to delete: " + student.toString());
		System.out.println("Enter - 1 to confirm and - 2 to cancel.");
		int confirmation = InputValidator.getNextInt(sc);
		return confirmation == 1;
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
