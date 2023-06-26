package ua.com.foxminded.yuriy.schoolconsoleapp.serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ua.com.foxminded.yuriy.schoolconsoleapp.dao.StudentDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.impl.StudentServiceImpl;

class StudentServiceTest {

	@Mock
	private StudentDao studentDao;

	@InjectMocks
	private StudentServiceImpl studentService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetAllByCourse() {
		String courseName = "Mathematics";
		List<Student> students = new ArrayList<>();
		students.add(new Student("John", "Biden"));
		students.add(new Student("Walker", "Biden"));
		students.add(new Student("Eddy", "Biden"));
		when(studentDao.getAllByCourse(courseName)).thenReturn(students);
		List<Student> result = studentService.getAllByCourse(courseName);
		assertEquals(students, result);
		verify(studentDao).getAllByCourse(courseName);
	}

	@Test
	void testDeleteById() {
		int id = 1;
		studentService.deleteById(id);
		verify(studentDao).deleteById(id);
	}

	@Test
	void testSetGroupById() {
		int id = 1;
		Group group = new Group("AA-11", 1);
		studentService.setGroupById(id, group);
		verify(studentDao).setGroupById(id, group);
	}

	@Test
	void setGetByName() {
		String firstName = "Anna";
		String lastName = "Anna";
		Student student = new Student("Anna", "Anna");
		when(studentDao.getByName(firstName, lastName)).thenReturn(student);
		Student result = studentService.getByName(firstName, lastName);
		assertEquals(student, result);
		verify(studentDao).getByName(firstName, lastName);
	}

	@Test
	void testGetById() {
		int id = 10;
		Student student = new Student("Anna", "Anna");
		student.setId(10);
		when(studentDao.getById(id)).thenReturn(student);
		Student result = studentService.getById(id);
		assertEquals(student, result);
		verify(studentDao).getById(id);
	}

	@Test
	void testAdd() {
		int id = 1;
		Student student = new Student("Me", "You");
		when(studentDao.add(student)).thenReturn(id);
		int result = studentService.add(student);
		assertEquals(id, result);
		verify(studentDao).add(student);
	}
}
