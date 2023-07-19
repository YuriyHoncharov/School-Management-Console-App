package ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
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
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.impl.StudentServiceImpl;
import ua.com.foxminded.yuriy.schoolconsoleapp.util.InputValidator;

@ExtendWith(MockitoExtension.class)

class GetAllStudentsByCourseCommandTest {

	@Mock
	private StudentServiceImpl mockStudentService;

	@Mock
	private Scanner mockScanner;

	@InjectMocks
	private GetAllStudentsByCourseCommand mockGetAllStudentsByCourseCommand;
	
	MockedStatic<InputValidator> mockedStatic;
	
	private PrintStream originalSystemOut;
	private ByteArrayOutputStream outputStreamCaptor;

	@BeforeEach
	void setUp() {
		mockedStatic = mockStatic(InputValidator.class);
		originalSystemOut = System.out;
		outputStreamCaptor = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor));
	}

	@AfterEach
	void cleanUp() {
		mockedStatic.close();
		System.setOut(originalSystemOut);
	}

	@Test
	void execute_courseExist_ShouldPrintAllStudens() {

		String courseName = "Math";
		List<Student> studentList = new ArrayList<>();
		Student student = new Student("random", "student");
		student.setId(1);
		studentList.add(student);

		when(InputValidator.isAlphabeticalInput(mockScanner)).thenReturn(courseName);
		when(mockStudentService.getAllByCourse(courseName)).thenReturn(studentList);

		mockGetAllStudentsByCourseCommand.execute(mockScanner);

		String expectedOutput = "Please enter the course name.." + System.lineSeparator() + "1. random student"
				+ System.lineSeparator();
		assertEquals(expectedOutput, outputStreamCaptor.toString());
	}
}
