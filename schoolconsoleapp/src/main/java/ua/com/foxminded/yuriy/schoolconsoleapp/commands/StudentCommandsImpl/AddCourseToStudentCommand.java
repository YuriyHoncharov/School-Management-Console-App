package ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.dto.StudentDto;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.CourseService;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.StudentService;
import ua.com.foxminded.yuriy.schoolconsoleapp.util.InputValidator;

@Component
public class AddCourseToStudentCommand implements Command {

	private CourseService courseService;
	private StudentService studentService;
	private StudentDto studentDto;

	@Autowired
	public AddCourseToStudentCommand(CourseService courseService, StudentService studentService, StudentDto studentDto) {
		this.courseService = courseService;
		this.studentService = studentService;
		this.studentDto = studentDto;
	}

	public static final Logger logger = LoggerFactory.getLogger(AddCourseToStudentCommand.class);

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
			List<Course> courses = availableCourses(student);
			int choosenCourse = InputValidator.getNextInt(sc);

			boolean courseExist = courses.stream().anyMatch(course -> course.getId() == choosenCourse);
			if (!courseExist) {
				System.out.println("This number is missing, please choose the course from the list");
				logger.warn("Course has not been added because a course with following ID : {} - does not exist.", choosenCourse);
			} else {
				addCourseToStudent(student, choosenCourse);
				logger.info("Course with ID : {} has been added to the student with ID : {}", choosenCourse, student.getId());
			}
		}
	}

	private Student getStudent(Scanner sc) {
		System.out.println("Please enter the student's id...");
		int studentId = InputValidator.getNextInt(sc);

		Student student;
		try {
			student = studentService.getById(studentId);
		} catch (Exception e) {
			logger.warn("Student with following ID was not found :{} " ,studentId);
			return null;
		}
		return student;
	}

	private List<Course> availableCourses(Student student) {
		List<Course> availableCourses = courseService.getAvailableCourses(student.getId());
		System.out.println("Please enter Course ID you want to add...");
		for (Course course : availableCourses) {
			System.out.println(course.toString());
		}
		return availableCourses;
	}

	private void addCourseToStudent(Student student, int choosenCourse) {
		Course selectedCourse = courseService.getById(choosenCourse);
		List<Course> courses = student.getCourses();
		courses.add(selectedCourse);
		student.setCourse(courses);
		studentService.update(student);
		System.out.println("Course has been succesfuly added to the student.");
	}

	private boolean choiceYesOrNot(Scanner sc) {
		int confirmation = InputValidator.getNextInt(sc);
		return confirmation == 1;
	}

	@Override
	public String name() {
		return "2";
	}

	@Override
	public String description() {
		return "Add course to the student.";
	}
}
