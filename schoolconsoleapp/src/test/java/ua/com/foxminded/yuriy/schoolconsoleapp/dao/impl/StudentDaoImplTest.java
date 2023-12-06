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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StudentDaoImplTest {

	@Mock
	private EntityManager entityManager;
	@InjectMocks
	private StudentDaoImpl studentDao;
	@Mock
	TypedQuery<Student> mockQuery;
	@Mock
	TypedQuery<Long> mockLongQuery;

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
		studentDao.saveAll(students);
		for (Student st : students) {
			verify(entityManager, times(1)).merge(st);
		}
	}

	@Test
	void deleteByIdTest_Success() throws SQLException {
		Student student = new Student("name", "lastname");
		when(entityManager.merge(student)).thenReturn(student);
		studentDao.delete(student);
		verify(entityManager, times(1)).merge(student);
		verify(entityManager, times(1)).remove(student);
	}

	@Test
	void update_Success() throws SQLException {
		Student student = new Student("Name", "Lastname");
		student.setId(1);
		Course course = new Course("Mathematics", "Math Course", 1);
		List<Course> courses = new ArrayList<>();
		courses.add(course);
		student.setCourse(courses);
		when(entityManager.find(Student.class, student.getId())).thenReturn(student);
		studentDao.save(student);
		verify(entityManager, times(1)).merge(student);
		for (Course c : student.getCourses()) {
			verify(entityManager, times(1)).getReference(Course.class, course.getId());
		}
	}

	@Test
	void studentsCountByGroupIdTest_Success() throws SQLException {
		Group group = new Group("AA-11", 1);
		long expectedCount = 20;
		when(entityManager.createQuery(anyString())).thenReturn(mockLongQuery);
		when(mockLongQuery.setParameter(anyString(), eq(group))).thenReturn(mockLongQuery);
		when(mockLongQuery.getSingleResult()).thenReturn(expectedCount);
		int result = studentDao.countByGroup(group);
		assertEquals(expectedCount, result);
	}
}
