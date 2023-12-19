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
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.dto.StudentDto;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.impl.StudentServiceImpl;
import ua.com.foxminded.yuriy.schoolconsoleapp.util.InputValidator;

@ExtendWith(MockitoExtension.class)

class DeleteStudentCommandTest {

	@Mock
	private StudentServiceImpl mockStudentService;

	@Mock
	private Scanner mockScanner;

	@Mock
	private StudentDto mockStudentDto;

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
		List<Course> courses = new ArrayList<>();
		courses.add(new Course("Math", "Math Course", 1));
		Student student = new Student("Name", "Lastname");
		student.setId(studentId);
		student.setCourse(courses);
		StudentDto studentPrint = new StudentDto();
		studentPrint.setFirstName("Name");
		studentPrint.setLastName("Lastname");
		studentPrint.setId(student.getId());
		studentPrint.setCourses(student.getCourses());

		when(InputValidator.getNextInt(mockScanner)).thenReturn(2, studentId, confirmation);
		when(mockStudentService.getById(studentId)).thenReturn(student);
		when(mockStudentDto.studentToDto(student)).thenReturn(studentPrint);

		mockDeleteStudentCommand.execute(mockScanner);

		verify(mockStudentService).getById(studentId);
		verify(mockStudentService).delete(student);
	}

	@Test
	void execute_confirmationIsDenied_ShouldPrintRightMessage() {
		int studentId = 10;
		int confirmation = 2;
		Student student = new Student("Name", "Lastname");
		student.setId(studentId);
		StudentDto studentPrint = new StudentDto();
		studentPrint.setFirstName("Name");
		studentPrint.setLastName("Lastname");
		studentPrint.setId(studentId);
		String message = "Do you want to see the entire list of students?\r\n"
				+ "Enter - 1 to confirm and - 2 to continue.\r\n" + "Enter student's ID you want to delete..\r\n"
				+ "ID : 10  | Name : Name Lastname        | Group ID : N/A | Courses : No courses are currently assigned to this student. - Will be deleted from the database. Are you sure you want to confirm?\r\n"
				+ "Enter - 1 to confirm and - 2 to cancel.\r\n" + "You canceled the operation.\r\n" + "";
		when(InputValidator.getNextInt(mockScanner)).thenReturn(2, studentId, confirmation);
		when(mockStudentService.getById(studentId)).thenReturn(student);
		when(mockStudentDto.studentToDto(student)).thenReturn(studentPrint);
		mockDeleteStudentCommand.execute(mockScanner);

		verify(mockStudentService).getById(studentId);
		assertEquals(message, outputStreamCaptor.toString());
	}

	@Test
	void execute_studentNotFound_ShouldPrintRightMessage() {
		int studentId = 10;

		Student student = null;
		String message = "Do you want to see the entire list of students?\r\n"
				+ "Enter - 1 to confirm and - 2 to continue.\r\n" + "Enter student's ID you want to delete..\r\n"
				+ "Student with entered ID is not found.\r\n" + "";
		when(InputValidator.getNextInt(mockScanner)).thenReturn(studentId);
		when(mockStudentService.getById(studentId)).thenReturn(student);

		mockDeleteStudentCommand.execute(mockScanner);

		verify(mockStudentService).getById(studentId);
		assertEquals(message, outputStreamCaptor.toString());
	}
}
