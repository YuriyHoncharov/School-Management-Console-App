package ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import ua.com.foxminded.yuriy.schoolconsoleapp.commands.Command;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.CourseService;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.StudentService;
import ua.com.foxminded.yuriy.schoolconsoleapp.util.InputValidator;

@Component
public class DeleteStudentCourseCommand implements Command {
	// TO DO
	private CourseService courseService;
	private StudentService studentService;

	@Autowired
	public DeleteStudentCourseCommand(CourseService courseService, StudentService studentService) {
		this.courseService = courseService;
		this.studentService = studentService;
	}

	@Override
	public void execute(Scanner sc) {
		System.out.println("Do you want to see the entire list of students?");
		System.out.println("Enter - 1 to confirm and - 2 to continue.");
		if (choiceYesOrNot(sc)) {
			List<Student> allStudents = studentService.getAll();
			for (Student student : allStudents) {
				System.out.println(student.toString());
			}
		}
		Student student = getStudent(sc);
		if (student == null) {
			System.out.println("Student with provided ID is not found.");
		} else {
			List<Course> actualCourses = courseService.getByStudentId(student.getId());
			int choosenCourse = choiceCourseToDelete(sc, student);
			if (studentFollowsCourse(actualCourses, choosenCourse)) {
				deleteCourse(student, choosenCourse);
			} else {
				System.out.println(
						"Unable to find the course with the provided ID. Please ensure that the ID is correct and the student is not already unenrolled from this course..");
			}
		}
	}

	private Student getStudent(Scanner sc) {
		System.out.println("Please enter student ID..");
		int studentId = InputValidator.getNextInt(sc);
		try {
			Student student = studentService.getById(studentId);
			return student;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	private int choiceCourseToDelete(Scanner sc, Student student) {

		List<Course> actualCourses = courseService.getByStudentId(student.getId());
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
		courseService.deregisterCourse(choosenCourse, student.getId());
		System.out.println("Course has been succesfully removed.");
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
