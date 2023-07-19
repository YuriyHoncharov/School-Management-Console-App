package ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl;

import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
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

	MockedStatic<InputValidator> mockedStatic;

	@BeforeEach
	void setUp() {
		mockedStatic = mockStatic(InputValidator.class);
	}

	@AfterEach
	void cleanUp() {
		mockedStatic.close();
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
}
