package ua.com.foxminded.yuriy.schoolconsoleapp.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StudentRepositoryTest {

	@Mock
	private StudentRepository studentRepository;
	@InjectMocks
	private StudentServiceImpl studentServiceImpl;

	@Test
	void getAllTest_Success() {
		List<Course> courses = new ArrayList<>();
		courses.add(new Course("Mathematics", "Math Course", 1));
		List<Student> students = new ArrayList<>();
		Student student = new Student("name", "lastname");
		Student student2 = new Student("name2", "lastname2");
		student.setCourse(courses);
		student2.setCourse(courses);
		students.add(student);
		students.add(student2);
		when(studentRepository.getAll()).thenReturn(students);
		List<Student> result = studentRepository.getAll();
		assertEquals(students, result);
		assertEquals(2, result.size());
		assertEquals(students.get(0), result.get(0));
		assertEquals(students.get(1), result.get(1));
	}

	@Test
	void getById_shouldReturnCorrectStudentObject_Success() {
		List<Course> courses = new ArrayList<>();
		courses.add(new Course("Mathematics", "Math Course", 1));
		Student student = new Student("name", "lastname");
		student.setCourse(courses);
		int studentId = 1;
		when(studentRepository.getById(1)).thenReturn(student);
		Student studentTest = studentRepository.getById(studentId);
		assertEquals(student, studentTest);
	}

	@Test
	void studentsCountByGroupIdTest_Success() {
		Group group = new Group("AA-11", 1);
		int expectedCount = 20;
		when(studentRepository.countByGroup(group)).thenReturn(expectedCount);
		int result = studentServiceImpl.countByGroup(group);
		assertEquals(expectedCount, result);
	}

	@Test
	void getAllByCoursesContains_shouldReturnCorrectStudentList_Success() {
		List<Student> students = new ArrayList<>();
		Student student = new Student("name", "lastname");
		Student student2 = new Student("name2", "lastname2");
		students.add(student);
		students.add(student2);
		Course course = new Course("Mathematics", "Math Course", 1);
		when(studentRepository.getAllByCoursesContains(course)).thenReturn(students);
		List<Student> result = studentRepository.getAllByCoursesContains(course);
		assertEquals(students.get(0), result.get(0));
		assertEquals(students.get(1), result.get(1));
	}
}
