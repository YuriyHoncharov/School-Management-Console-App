package ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl;

import java.util.List;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.dto.StudentDto;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.StudentService;
import ua.com.foxminded.yuriy.schoolconsoleapp.util.InputValidator;

@Component
public class DeleteStudentCommand implements Command {

	private StudentService studentService;
	private StudentDto studentDto;
	public static final Logger logger = LoggerFactory.getLogger(DeleteStudentCommand.class);

	@Autowired
	public DeleteStudentCommand(StudentService studentService, StudentDto studentDto) {
		this.studentService = studentService;
		this.studentDto = studentDto;
	}

	@Override
	public void execute(Scanner sc) {
		System.out.println("Do you want to see the entire list of students?");
		System.out.println("Enter - 1 to confirm and - 2 to continue.");
		if (choiceYesOrNot(sc)) {
			List<Student> allStudents = studentService.getAll();
			List<StudentDto> studentsList = studentDto.studentsListDto(allStudents);
			for (StudentDto studentDto : studentsList) {
				System.out.println(studentDto.toString());
			}
		}
		Student student = getStudent(sc);
		if (student == null) {
			System.out.println("Student with entered ID is not found.");
		} else {
			if (choiceToDelete(sc, student)) {
				studentService.delete(student);
				System.out.println((studentDto.studentToDto(student)).toString() + " - Has been deleted from database.");
				logger.info("Student with ID : {} has been deleted from database", student.getId());
			} else {
				System.out.println("You canceled the operation.");
				logger.warn("User canceled the operation");
			}
		}
	}

	private Student getStudent(Scanner sc) {
		System.out.println("Enter student's ID you want to delete..");
		int id = InputValidator.getNextInt(sc);
		Student student;
		try {
			student = studentService.getById(id);
		} catch (Exception e) {
			logger.warn("The student with following ID was not found in data base : {}", id);
			return null;
		}
		return student;
	}

	private boolean choiceToDelete(Scanner sc, Student student) {
		StudentDto studentPrint = studentDto.studentToDto(student);
		System.out.println(
				studentPrint.toString() + " - Will be deleted from the database. Are you sure you want to confirm?");
		System.out.println("Enter - 1 to confirm and - 2 to cancel.");
		int confirmation = InputValidator.getNextInt(sc);
		return confirmation == 1;
	}

	private boolean choiceYesOrNot(Scanner sc) {
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
