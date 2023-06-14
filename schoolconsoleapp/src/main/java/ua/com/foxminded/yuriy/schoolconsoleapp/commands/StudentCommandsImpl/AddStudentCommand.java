package ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.GroupService;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.StudentService;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.implement.GroupServiceImpl;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.implement.StudentServiceImpl;

public class AddStudentCommand implements Command {
	private StudentService studentService = new StudentServiceImpl();
	private GroupService groupService = new GroupServiceImpl();

	@Override
	public void execute(Scanner sc) throws DaoException {
		Pattern namePattern = Pattern.compile("[A-Za-z]+");
		System.out.println("Enter student name..");

		String name = sc.nextLine();
		while (!namePattern.matcher(name).matches()) {
			System.out.println("Please enter a valid name using alpahbetic characters.");
			name = sc.nextLine();
		}
		System.out.println("Enter student lastname..");

		String lastName = sc.nextLine();
		while (!namePattern.matcher(lastName).matches()) {
			System.out.println("Please enter a valid name using alpahbetic characters.");
			lastName = sc.nextLine();
		}
		Student newStudent = new Student(name, lastName);
		studentService.add(newStudent);
		System.out.println("New student has been added to database.");
		System.out.println("Would you want to insert this student to an exist Group? [ 1 - YES | 2 - NO ]");

		String input = sc.nextLine();

		while (!input.equals("1") && !input.equals("2")) {
			System.out.println("Invalid input. Please enter either 1 or 2:");
			input = sc.nextLine();
		}
		int answer = Integer.parseInt(input);

		if (answer == 2) {
			System.out.println(
					"Nice, but remember that it is important to assign a group to the student and to contact the administration as quickly as possible to do it for you.");
		} else {
			System.out.println("Please insert the Group ID you want to assign to your student..");
			List<Group> allGroups = groupService.getAll();
			allGroups.forEach(g -> System.out.println(g.toString()));

			while (!sc.hasNextInt()) {
				sc.next();
				System.out.println("You should enter a numeric value, please retry.");
			}
			int groupId = sc.nextInt();

			Group group = groupService.getById(groupId);
			int studentId = (studentService.getByName(name, lastName)).getId();
			studentService.setGroupById(studentId, group);
			System.out.println("Group has been succesfully added.");

		}
	}

	@Override
	public String name() {
		return "3";
	}

	@Override
	public String description() {
		return "Add new student.";
	}

}
