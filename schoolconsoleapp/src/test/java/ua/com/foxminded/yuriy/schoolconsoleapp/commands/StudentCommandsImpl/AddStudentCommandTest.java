package ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Any;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.impl.GroupServiceImpl;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.impl.StudentServiceImpl;
import ua.com.foxminded.yuriy.schoolconsoleapp.util.InputValidator;

class AddStudentCommandTest {
	
	@Mock
	private StudentServiceImpl mockStudentService;
	
	@Mock	
	private GroupServiceImpl mockGroupService;
	
	@Mock
	private Scanner mockScanner;
	
	MockedStatic<InputValidator> mockedStatic;
	
	@InjectMocks
	private AddStudentCommand mockAddStudentCommand;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		mockedStatic = mockStatic(InputValidator.class);
		mockAddStudentCommand = new AddStudentCommand(mockStudentService, mockGroupService);
	}
	
	@AfterEach
	void cleanUp() {
		mockedStatic.close();
	}
	
	

	@Test
	void execute_RightInputs_ShouldAddStudent() {
	    String name = "Ivan";
	    String lastName = "Honk";
	    int studentId = 1;

	    Student student = new Student(name, lastName);

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

	    verify(mockStudentService,times(1)).add(any(Student.class));
	    verify(mockStudentService,times(1)).setGroupById(anyInt(), any(Group.class));
	}
}
