package ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.input.InputValidator;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.GroupService;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.StudentService;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.implement.GroupServiceImpl;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.implement.StudentServiceImpl;

public class AddStudentCommand implements Command {
	private StudentService studentService = new StudentServiceImpl();
	private GroupService groupService = new GroupServiceImpl();

	@Override
	public void execute(Scanner sc) {

		System.out.println("Enter student name..");
		String name = InputValidator.isAlphabeticalInput(sc);
		System.out.println("Enter student lastname..");
		String lastName = InputValidator.isAlphabeticalInput(sc);
		Student newStudent = new Student(name, lastName);
		studentService.add(newStudent);
		System.out.println("New student has been added to database.");
		System.out.println("Would you want to insert this student to an exist Group? [ 1 - YES | 2 - NO ]");
		int answer = InputValidator.getValidChoice(sc);

		if (answer == 2) {
			System.out.println(
					"Nice, but remember that it is important to assign a group to the student and to contact the administration as quickly as possible to do it for you.");
		} else {
			System.out.println("Please insert the Group ID you want to assign to your student..");
			List<Group> allGroups = groupService.getAll();
			allGroups.forEach(g -> System.out.println(g.toString()));
			int groupId = InputValidator.getNextInt(sc);
			boolean groupExist = allGroups.stream().anyMatch(group -> group.getId() == groupId);
			if (!groupExist) {
				System.out.println("The group with ID : " + groupId + " does now exist. Please retry");
			} else {
				Group group = groupService.getById(groupId);
				int studentId = (studentService.getByName(name, lastName)).getId();
				studentService.setGroupById(studentId, group);
				System.out.println("Group has been succesfully added to the student.");
			}
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
