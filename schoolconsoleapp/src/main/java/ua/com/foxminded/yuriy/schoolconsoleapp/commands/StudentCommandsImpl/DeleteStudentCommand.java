package ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl;

import java.util.Scanner;

import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.StudentDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.StudentService;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.implement.StudentServiceImpl;

public class DeleteStudentCommand implements Command {
	private StudentService studentService = new StudentServiceImpl();

	@Override
	public void execute() throws DaoException {
		System.out.println("Enter student's ID you want to delete..");
		Scanner sc = new Scanner(System.in);

		while (!sc.hasNextInt()) {
			sc.next();
			System.out.println("You should enter a numeric value, please retry.");
		}

		int id = sc.nextInt();
		Student student = studentService.getById(id);

		if (student.getFirstName() == null) {
			System.out.println("Student with following ID : " + "[ " + id + " ] is not found.");
		} else {
			System.out.println("Are you sure that you want to delete: " + studentService.getById(id).toString());
			System.out.println("Enter - 1 to confirm and - 2 to cancel.");
			while (!sc.hasNextInt()) {
				sc.next();
				System.out.println("You should enter a numeric value, please retry.");
			}
			int confirmation = sc.nextInt();
			if (confirmation == 1) {
				studentService.delete(id);
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
