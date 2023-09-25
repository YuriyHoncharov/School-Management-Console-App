package ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.impl.CourseServiceImpl;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.impl.StudentServiceImpl;
import ua.com.foxminded.yuriy.schoolconsoleapp.util.InputValidator;

@ExtendWith(MockitoExtension.class)

class AddCourseToStudentCommandTest {

	private final PrintStream originalSystemOut = System.out;
	private final ByteArrayOutputStream outPutStream = new ByteArrayOutputStream();

	@Mock
	private CourseServiceImpl mockCourseService;

	@Mock
	private StudentServiceImpl mockStudentService;

	@Mock
	private Scanner mockScanner;
	
	private MockedStatic<InputValidator> mockedStatic;

	@InjectMocks
	private AddCourseToStudentCommand mockAddCourseToStudentCommand;

	@BeforeEach
	void setUp() {
		System.setOut(new PrintStream(outPutStream));
		mockedStatic = mockStatic(InputValidator.class);		
	}

	@AfterEach
	void cleanUp() {
		mockedStatic.close();
		System.setOut(originalSystemOut);
	}

	@Test
	void execute_StudentNotFound_ShouldPrintCorrectMessage() {

		int studentId = 10;
		Student studentNull = null;
		Course course = any(Course.class);
		when(InputValidator.getNextInt(mockScanner)).thenReturn(studentId);
		when(mockStudentService.getById(anyInt())).thenReturn(studentNull);

		mockAddCourseToStudentCommand.execute(mockScanner);

		String printedMessage = outPutStream.toString().trim();
		String[] lines = printedMessage.split("\\r?\\n");
		assertEquals("Please enter the student's id...", lines[0]);
		assertEquals("Student with provided ID is not found.", lines[1]);
		verify(mockCourseService, times(0)).addToStudent(course, studentId);

	}

	@Test
	void execute_StudentFoundAndCourseExists_CourseShouldBeAddedToStudent() {

		int studentId = 1;
		Student student = new Student("Annabel", "Mira");
		student.setId(studentId);

		List<Course> availableCourses = new ArrayList<>();
		int courseId = 2;
		Course course = new Course("Math", "Math Course", courseId);
		availableCourses.add(course);
		int chosenCourse = courseId;

		when(InputValidator.getNextInt(mockScanner)).thenReturn(1, 2);
		when(mockStudentService.getById(studentId)).thenReturn(student);
		when(mockCourseService.getAvailableCourses(student.getId())).thenReturn(availableCourses);
		when(mockCourseService.getById(chosenCourse)).thenReturn(course);

		mockAddCourseToStudentCommand.execute(mockScanner);

		String printedMessage = outPutStream.toString().trim();
		String[] lines = printedMessage.split("\\r?\\n");
		assertEquals("Please enter the student's id...", lines[0]);
		assertEquals("Please enter Course ID you want to add...", lines[1]);
		assertEquals("[Course ID : 2 | Course Name : Math | Description : Math Course]", lines[2]);
		assertEquals("Course has been succesfuly added to the student.", lines[3]);

		verify(mockCourseService, times(1)).addToStudent(course, studentId);
	}

	@Test

	void execute_CourseIdIsMissing_ShouldPrintRightErrorMessage() {
		
		int studentId = 10;
		Student student = new Student("Annabel", "Mira");
		student.setId(studentId);
		int courseId = 1;
		when(InputValidator.getNextInt(mockScanner)).thenReturn(studentId);
		when(mockStudentService.getById(studentId)).thenReturn(student);
		
		List<Course> availableCourses = new ArrayList<>();
		Course course = new Course("Math", "Math Course", courseId);
		availableCourses.add(course);		
		
		when(mockCourseService.getAvailableCourses(student.getId())).thenReturn(availableCourses);		
		mockAddCourseToStudentCommand.execute(mockScanner);

		String printedMessage = outPutStream.toString().trim();
		String[] lines = printedMessage.split("\\r?\\n");
		assertEquals("Please enter the student's id...", lines[0]);
		assertEquals("Please enter Course ID you want to add...", lines[1]);
		assertEquals("[Course ID : 1 | Course Name : Math | Description : Math Course]", lines[2]);
		assertEquals("This number is missing, please choose the course from the list", lines[3]);
		verify(mockCourseService, times(0)).addToStudent(course, studentId);
	}
}