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
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.impl.GroupServiceImpl;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.impl.StudentServiceImpl;
import ua.com.foxminded.yuriy.schoolconsoleapp.util.InputValidator;

@ExtendWith(MockitoExtension.class)

class AddStudentCommandTest {

	@Mock
	private StudentServiceImpl mockStudentService;

	@Mock
	private GroupServiceImpl mockGroupService;

	@Mock
	private Scanner mockScanner;

	private MockedStatic<InputValidator> mockedStatic;

	@InjectMocks
	private AddStudentCommand mockAddStudentCommand;

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
	void execute_RightInputs_ShouldAddStudent() {
		String name = "Ivan";
		String lastName = "Honk";
		int studentId = 1;
		int answer = 1;
		int groupId = 2;
		Group group = new Group("random", groupId);
		List<Group> all = new ArrayList<>();
		all.add(group);

		when(InputValidator.isAlphabeticalInput(mockScanner)).thenReturn(name, lastName);
		when(InputValidator.getValidChoice(mockScanner)).thenReturn(answer);
		when(InputValidator.getNextInt(mockScanner)).thenReturn(groupId);
		when(mockGroupService.getAll()).thenReturn(all);
		when(mockGroupService.getById(groupId)).thenReturn(group);
		when(mockStudentService.add(any(Student.class))).thenReturn(studentId);

		mockAddStudentCommand.execute(mockScanner);

		verify(mockStudentService, times(1)).add(any(Student.class));
		verify(mockStudentService, times(1)).setGroupById(anyInt(), any(Group.class));
	}

	@Test
	void execute_noGroupAssign_ShouldPrintRightMessage() {
		String name = "Ivan";
		String lastName = "Honk";
		int answer = 2;
		String message = "Enter student name..\r\n" + "Enter student lastname..\r\n"
				+ "New student has been added to database.\r\n"
				+ "Would you want to insert this student to an exist Group? [ 1 - YES | 2 - NO ]\r\n"
				+ "Nice, but remember that it is important to assign a group to the student and to contact the administration as quickly as possible to do it for you.\r\n"
				+ "";
		when(InputValidator.isAlphabeticalInput(mockScanner)).thenReturn(name, lastName);
		when(InputValidator.getValidChoice(mockScanner)).thenReturn(answer);

		mockAddStudentCommand.execute(mockScanner);

		verify(mockStudentService, times(1)).add(any(Student.class));
		assertEquals(message, outputStreamCaptor.toString());
	}

	@Test
	void execute_groupNotExist_ShouldPrintRightMessage() {
		String name = "Ivan";
		String lastName = "Honk";
		int studentId = 1;
		int answer = 1;
		int groupId = 2;
		int notExistingGroupId = 3;
		Group group = new Group("random", groupId);
		List<Group> all = new ArrayList<>();
		all.add(group);
		String message = "Enter student name..\r\n" + "Enter student lastname..\r\n"
				+ "New student has been added to database.\r\n"
				+ "Would you want to insert this student to an exist Group? [ 1 - YES | 2 - NO ]\r\n"
				+ "Please insert the Group ID you want to assign to your student..\r\n"
				+ "[Group ID: 2 |  Group Name : random]\r\n" + "The group with ID : 3 does now exist. Please retry\r\n"
				+ "";

		when(InputValidator.isAlphabeticalInput(mockScanner)).thenReturn(name, lastName);
		when(InputValidator.getValidChoice(mockScanner)).thenReturn(answer);
		when(InputValidator.getNextInt(mockScanner)).thenReturn(notExistingGroupId);
		when(mockGroupService.getAll()).thenReturn(all);		
		when(mockStudentService.add(any(Student.class))).thenReturn(studentId);

		mockAddStudentCommand.execute(mockScanner);

		verify(mockStudentService, times(1)).add(any(Student.class));
		assertEquals(message, outputStreamCaptor.toString());
	}
}
