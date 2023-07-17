package ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.impl.StudentServiceImpl;
import ua.com.foxminded.yuriy.schoolconsoleapp.util.InputValidator;

class DeleteStudentCommandTest {
	
	@Mock
	private StudentServiceImpl mockStudentService;
	
	@Mock
	private Scanner mockScanner;
	
	MockedStatic<InputValidator> mockedStatic;

	@InjectMocks
	private DeleteStudentCommand mockDeleteStudentCommand;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);		
		mockedStatic = mockStatic(InputValidator.class);
		mockDeleteStudentCommand = new DeleteStudentCommand(mockStudentService);
	}

	@AfterEach
	void cleanUp() {
		mockedStatic.close();		
	}
	
	@Test
	void execute_shouldDeleteStudent(){
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
