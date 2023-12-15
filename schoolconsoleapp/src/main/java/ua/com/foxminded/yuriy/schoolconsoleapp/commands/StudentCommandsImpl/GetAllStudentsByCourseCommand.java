package ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl;

import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.logger.CustomLogger;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.CourseService;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.StudentService;
import ua.com.foxminded.yuriy.schoolconsoleapp.util.InputValidator;

@Component
public class GetAllStudentsByCourseCommand implements Command {

	private StudentService studentService;
	private CourseService courseService;
	private CustomLogger customLogger;

	@Autowired
	public GetAllStudentsByCourseCommand(StudentService studentService, CourseService courseService,
			CustomLogger customLogger) {
		this.studentService = studentService;
		this.courseService = courseService;
		this.customLogger = customLogger;
	}

	@Override
	public void execute(Scanner sc) {
		System.out.println("Please insert the course ID..");
		List<Course> courses = courseService.getAllCourses();
		for (Course course : courses) {
			System.out.println(course.toString());
		}
		int courseId = InputValidator.getNextInt(sc);
		customLogger.logInfo("User want to see all students that follow the course with following ID : " + courseId);
		boolean courseExist = courses.stream().anyMatch(course -> course.getId() == courseId);
		Course course = courses.stream().filter(c -> c.getId() == courseId).findFirst().orElse(null);
		if (courseExist && course != null) {
			List<Student> studentList = studentService.getAllByCourse(course);
			for (Student student : studentList) {
				System.out.println(student.getId() + ". " + student.getFirstName() + " " + student.getLastName());
			}
		} else {
			customLogger.logInfo("Course was not found with followind ID : " + courseId);
			System.out.println("Course not found with the following ID : " + courseId);
		}
	}

	@Override
	public String name() {
		return "6";
	}

	@Override
	public String description() {
		return "Find students by course name.";
	}
}
