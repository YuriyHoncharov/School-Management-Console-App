package ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.dto.StudentDto;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.StudentService;
import ua.com.foxminded.yuriy.schoolconsoleapp.util.InputValidator;

@Component
public class DeleteStudentCourseCommand implements Command {

	private StudentService studentService;
	private StudentDto studentDto;
	public static final Logger logger = LoggerFactory.getLogger(DeleteStudentCourseCommand.class);

	@Autowired
	public DeleteStudentCourseCommand(StudentService studentService, StudentDto studentDto) {
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
			System.out.println("Student with provided ID is not found.");
		} else {
			List<Course> actualCourses = student.getCourses();
			int choosenCourse = choiceCourseToDelete(sc, student);
			if (studentFollowsCourse(actualCourses, choosenCourse)) {
				deleteCourse(student, choosenCourse);
			} else {
				System.out.println(
						"Unable to find the course with the provided ID. Please ensure that the ID is correct and the student is not already unenrolled from this course..");
				logger.warn("Any course has been deleted from Student with ID : {}, because the Course ID is incorrect.", student.getId());
			}
		}
	}

	private Student getStudent(Scanner sc) {
		System.out.println("Please enter student ID..");
		int studentId = InputValidator.getNextInt(sc);
		try {
			Student student = studentService.getById(studentId);
			return student;
		} catch (Exception e) {
			logger.warn("Student with following ID was not found in data base : {}", studentId);
			return null;
		}
	}

	private int choiceCourseToDelete(Scanner sc, Student student) {

		List<Course> actualCourses = student.getCourses();
		for (int i = 0; i < actualCourses.size(); i++) {
			Course course = actualCourses.get(i);
			System.out.println((i + 1) + ". " + course.toString());
		}
		System.out.println("Please enter the course ID from which you wish to remove the student.");
		return InputValidator.getNextInt(sc);

	}

	private boolean studentFollowsCourse(List<Course> actualCourses, int choosenCourse) {
		Optional<Course> desiredCourse = actualCourses.stream().filter(course -> course.getId() == choosenCourse)
				.findFirst();
		return desiredCourse.isPresent();
	}

	private void deleteCourse(Student student, int choosenCourse) {
		student.getCourses().removeIf(course -> course.getId() == choosenCourse);
		studentService.update(student);
		System.out.println("Course has been succesfully removed.");
		logger.info("Course with ID : {} has been removed from Student with ID : {}", choosenCourse, student.getId());
	}

	private boolean choiceYesOrNot(Scanner sc) {
		int confirmation = InputValidator.getNextInt(sc);
		return confirmation == 1;
	}

	@Override
	public String name() {
		return "5";
	}

	@Override
	public String description() {
		return "Deregister student from a course.";
	}
}
