package ua.com.foxminded.yuriy.schoolconsoleapp.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.repository.CourseRepository;
import ua.com.foxminded.yuriy.schoolconsoleapp.repository.StudentRepository;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.impl.StudentServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StudentDaoImplTest {

	@Mock
	private StudentRepository studentRepository;
	@InjectMocks
	private StudentServiceImpl studentServiceImpl;

	@Test
	void addAllTest_Success() throws SQLException {
		List<Course> courses = new ArrayList<>();
		courses.add(new Course("Mathematics", "Math Course", 1));
		List<Student> students = new ArrayList<>();
		Student student = new Student("name", "lastname");
		Student student2 = new Student("name2", "lastname2");
		student.setCourse(courses);
		student2.setCourse(courses);
		students.add(student);
		students.add(student2);
		when(studentRepository.saveAll(students)).thenReturn(students);
		List<Student> result = studentRepository.saveAll(students);
		assertEquals(students, result);
		assertEquals(2, result.size());
	}

	@Test
	void deleteByIdTest_Success() throws SQLException {
		int studentId = 1;
		Student student = new Student("name", "lastname");
		student.setId(studentId);
		when(studentRepository.getById(studentId)).thenReturn(student);
		Student studentTest = studentServiceImpl.getById(studentId);
		studentServiceImpl.delete(studentTest);
		verify(studentRepository, times(1)).getById(studentId);
		verify(studentRepository, times(1)).delete(studentTest);
	}

	@Test
	void update_Success() throws SQLException {
		Student student = new Student("Name", "Lastname");
		student.setId(1);
		Course course = new Course("Mathematics", "Math Course", 1);
		List<Course> courses = new ArrayList<>();
		courses.add(course);
		student.setCourse(courses);
		when(studentRepository.save(student)).thenReturn(student);
		studentServiceImpl.update(student);
		verify(studentRepository, times(1)).save(student);
	}

	@Test
	void studentsCountByGroupIdTest_Success() throws SQLException {
		Group group = new Group("AA-11", 1);
		int expectedCount = 20;
		when(studentRepository.countByGroup(group)).thenReturn(expectedCount);
		int result = studentServiceImpl.countByGroup(group);
		assertEquals(expectedCount, result);
	}
}
