package ua.com.foxminded.yuriy.schoolconsoleapp.commands.StudentCommandsImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
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
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.CourseService;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.StudentService;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.impl.CourseServiceImpl;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.impl.StudentServiceImpl;
import ua.com.foxminded.yuriy.schoolconsoleapp.util.InputValidator;

class DeleteStudentCourseCommandTest {
	
   @Mock
   private CourseServiceImpl mockCourseService;

   @Mock
   private StudentServiceImpl mockStudentService;

   @Mock
   private Scanner mockScanner;

   @InjectMocks
   private DeleteStudentCourseCommand mockDeleteStudentCourseCommand;
   
   MockedStatic<InputValidator> mockedStatic;
   
   @BeforeEach
   void setUp() {
       MockitoAnnotations.openMocks(this);
       mockedStatic = mockStatic(InputValidator.class);
       mockDeleteStudentCourseCommand = new DeleteStudentCourseCommand(mockCourseService, mockStudentService);
   }

   @AfterEach
   void cleanUp() {
       mockedStatic.close();
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

       when(InputValidator.getNextInt(mockScanner)).thenReturn(studentId);
       when(mockStudentService.getById(studentId)).thenReturn(student);
       when(mockCourseService.getByStudentId(student.getId())).thenReturn(actual);
       when(InputValidator.getNextInt(mockScanner)).thenReturn(courseId);

       mockDeleteStudentCourseCommand.execute(mockScanner);

       verify(mockStudentService).getById(studentId);
       verify(mockCourseService).deregisterCourse(courseId, student.getId());
   }
}
