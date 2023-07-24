package ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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

class DeleteStudentCommandTest {

	@Mock
	private StudentServiceImpl mockStudentService;

	@Mock
	private Scanner mockScanner;

	@InjectMocks
	private DeleteStudentCommand mockDeleteStudentCommand;

	private MockedStatic<InputValidator> mockedStatic;

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
		System.setOut(new PrintStream(originalSystemOut));
	}

	@Test
	void execute_shouldDeleteStudent() {
		int studentId = 10;
		int confirmation = 1;
		Student student = new Student("Name", "Lastname");
		student.setId(studentId);

		when(InputValidator.getNextInt(mockScanner)).thenReturn(studentId, confirmation);
		when(mockStudentService.getById(studentId)).thenReturn(student);

		mockDeleteStudentCommand.execute(mockScanner);

		verify(mockStudentService).getById(studentId);
		verify(mockStudentService).deleteById(student.getId());
	}

	@Test
	void execute_confirmationIsDenied_ShouldPrintRightMessage() {
		int studentId = 10;
		int confirmation = 2;
		Student student = new Student("Name", "Lastname");
		student.setId(studentId);
		String message = "Enter student's ID you want to delete..\r\n"
				+ "Are you sure that you want to delete: [Student ID : 10, Group ID : 0, First Name : Name, Last Name : Lastname]\r\n"
				+ "Enter - 1 to confirm and - 2 to cancel.\r\n"
				+ "You canceled the operation.\r\n"
				+ "";
		when(InputValidator.getNextInt(mockScanner)).thenReturn(studentId, confirmation);
		when(mockStudentService.getById(studentId)).thenReturn(student);
		
		mockDeleteStudentCommand.execute(mockScanner);
		
		verify(mockStudentService).getById(studentId);
		assertEquals(message, outputStreamCaptor.toString());
	}
	
	@Test
	void execute_studentNotFound_ShouldPrintRightMessage() {
		int studentId = 10;
		
		Student student = null;
		String message = "Enter student's ID you want to delete..\r\n"
				+ "Student with entered ID is not found.\r\n"
				+ "";
		when(InputValidator.getNextInt(mockScanner)).thenReturn(studentId);
		when(mockStudentService.getById(studentId)).thenReturn(student);
		
		mockDeleteStudentCommand.execute(mockScanner);
		
		verify(mockStudentService).getById(studentId);
		assertEquals(message, outputStreamCaptor.toString());
	}
}
