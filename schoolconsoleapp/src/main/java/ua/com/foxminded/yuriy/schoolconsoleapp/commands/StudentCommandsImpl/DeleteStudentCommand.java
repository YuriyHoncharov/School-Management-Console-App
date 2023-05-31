package ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl;

import java.util.Scanner;

import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.StudentDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

public class DeleteStudentCommand implements Command {
	private StudentDao studentDao;

	@Override
	public void execute() throws DaoException {
		System.out.println("Enter student's ID you want to delete..");
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
		Student student = studentDao.getInfo(id);
		System.out.println(
				"Selected student is: " + student.getId() + ". " + student.getFirstName() + " " + student.getLastName());
		System.out.println("Enter - 1 to confirm and - 2 to cancel.");
		int confirmation = sc.nextInt();
		if (confirmation == 1) {
			studentDao.delete(id);
		} else {
			System.out.println("You canceled the operation.");
		}
		sc.close();
	}

	@Override
	public String name() {
		return "2";
	}

	@Override
	public String description() {

		return "Delete student by student's ID.";
	}

}
