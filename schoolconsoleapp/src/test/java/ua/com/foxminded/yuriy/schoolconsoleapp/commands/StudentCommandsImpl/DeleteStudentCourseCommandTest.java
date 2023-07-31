package ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;
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

class DeleteStudentCourseCommandTest {

	@Mock
	private CourseServiceImpl mockCourseService;

	@Mock
	private StudentServiceImpl mockStudentService;

	@Mock
	private Scanner mockScanner;

	@InjectMocks
	private DeleteStudentCourseCommand mockDeleteStudentCourseCommand;

	private MockedStatic<InputValidator> mockedStatic;

	private PrintStream originaPrintStream;
	private ByteArrayOutputStream outputStreamCaptor;

	@BeforeEach
	void setUp() {
		mockedStatic = mockStatic(InputValidator.class);
		originaPrintStream = System.out;
		outputStreamCaptor = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor));

	}

	@AfterEach
	void cleanUp() {
		mockedStatic.close();
		System.setOut(originaPrintStream);
	}

	@Test
	void execute_StudentFoundAndCourseExists_ShouldDeleteCourse() {
		int studentId = 1;
		int courseId = 2;
		Student student = new Student("FirstName", "LastName");
		student.setId(studentId);
		List<Course> actual = new ArrayList<>();
		Course course = new Course("Name", "Description", courseId);
		actual.add(course);

		when(InputValidator.getNextInt(mockScanner)).thenReturn(studentId, courseId);
		when(mockStudentService.getById(studentId)).thenReturn(student);
		when(mockCourseService.getByStudentId(student.getId())).thenReturn(actual);

		mockDeleteStudentCourseCommand.execute(mockScanner);

		verify(mockStudentService).getById(studentId);
		verify(mockCourseService).deregisterCourse(courseId, student.getId());
	}
	
	@Test
	void execute_StudentNotFound_ShouldPrintRightMessage() {
		int studentId = 1;
		Student student = null;
		String message = "Please enter student ID..\r\n"
				+ "Student with provided ID is not found.\r\n"
				+ "";
		
		when(InputValidator.getNextInt(mockScanner)).thenReturn(studentId);
		when(mockStudentService.getById(studentId)).thenReturn(student);
		

		mockDeleteStudentCourseCommand.execute(mockScanner);

		verify(mockStudentService).getById(studentId);
		assertEquals(message, outputStreamCaptor.toString());
	}
	
	@Test
	void execute_selectedCourseNotFound_ShouldPrintRightMessage() {
		int studentId = 1;
		int courseId = 2;
		int nonExistingCourseId = 99;
		Student student = new Student("FirstName", "LastName");
		student.setId(studentId);
		List<Course> actual = new ArrayList<>();
		Course course = new Course("Name", "Description", courseId);
		actual.add(course);
		String message = "Please enter student ID..\r\n"
				+ "1. [Course ID : 2 | Course Name : Name | Description : Description]\r\n"
				+ "Please enter the course ID from which you wish to remove the student.\r\n"
				+ "Unable to find the course with the provided ID. Please ensure that the ID is correct and the student is not already unenrolled from this course..\r\n"
				+ "";

		when(InputValidator.getNextInt(mockScanner)).thenReturn(studentId, nonExistingCourseId);
		when(mockStudentService.getById(studentId)).thenReturn(student);
		when(mockCourseService.getByStudentId(student.getId())).thenReturn(actual);

		mockDeleteStudentCourseCommand.execute(mockScanner);

		verify(mockStudentService).getById(studentId);
		assertEquals(message, outputStreamCaptor.toString());
	}

}
