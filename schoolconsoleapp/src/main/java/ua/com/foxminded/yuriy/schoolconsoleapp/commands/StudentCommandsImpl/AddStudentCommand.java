package ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.logger.CustomLogger;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.GroupService;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.StudentService;
import ua.com.foxminded.yuriy.schoolconsoleapp.util.InputValidator;

@Component
public class AddStudentCommand implements Command {

	private StudentService studentService;
	private GroupService groupService;
	private CustomLogger customLogger;

	@Autowired
	public AddStudentCommand(StudentService studentService, GroupService groupService, CustomLogger customLogger) {
		this.studentService = studentService;
		this.groupService = groupService;
		this.customLogger = customLogger;
	}

	@Override
	public void execute(Scanner sc) {

		Student newStudent = createStudent(sc);
		if (groupAssignDecision(sc)) {
			customLogger.logInfo("User decieded to insert the created Student to an existing Group");
			assignStudentToGroup(sc, newStudent);
		} else {
			System.out.println(
					"Nice, but remember that it is important to assign a group to the student and to contact the administration as quickly as possible to do it for you.");
		}
	}

	private Student createStudent(Scanner sc) {
		System.out.println("Enter student name..");
		String name = InputValidator.isAlphabeticalInput(sc);
		System.out.println("Enter student lastname..");
		String lastName = InputValidator.isAlphabeticalInput(sc);
		Student newStudent = new Student(name, lastName);
		int studentId = studentService.add(newStudent);
		newStudent.setId(studentId);
		System.out.println("New student has been added to database.");
		return newStudent;
	}

	boolean groupAssignDecision(Scanner sc) {
		System.out.println("Would you want to insert this student to an exist Group? [ 1 - YES | 2 - NO ]");
		int answer = InputValidator.getValidChoice(sc);
		return answer == 1;
	}

	private void assignStudentToGroup(Scanner sc, Student student) {

		System.out.println("Please insert the Group ID you want to assign to your student..");
		List<Group> allGroups = groupService.getAll();

		allGroups.forEach(g -> System.out.println(g.toString()));
		int groupId = InputValidator.getNextInt(sc);
		customLogger.logInfo("User wanted to insert the student to the Group with following ID : " + groupId);
		boolean groupExist = allGroups.stream().anyMatch(group -> group.getId() == groupId);
		Group groupToAssign = allGroups.stream().filter(gr -> gr.getId() == groupId).findFirst().orElse(null);
		if (!groupExist) {
			System.out.println("The group with ID : " + groupId + " does now exist. Please retry");
			customLogger.logInfo("The group was not assigned because is not exist with following ID : " + groupId);
		} else {
			student.setGroup(groupToAssign);
			studentService.update(student);
			System.out.println("Group has been succesfully added to the student.");
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
