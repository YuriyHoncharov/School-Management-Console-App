package ua.com.foxminded.yuriy.schoolconsoleapp.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.StudentDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;

@Repository
public class StudentDaoImpl implements StudentDao {

	@PersistenceContext
	private EntityManager entityManager;

	public void addAll(List<Student> students) {
		for (Student st : students) {
			entityManager.persist(st);
			for (Course c : st.getCourses()) {
				entityManager.persist(c);
			}
		}
	}

	@Override
	public List<Student> getAllByCourse(int courseId) {
		String jpql = "SELECT s FROM Students s JOIN s.courses c WHERE c.id = :courseId";
		return entityManager.createQuery(jpql, Student.class).setParameter("courseId", courseId).getResultList();
	}

	@Override
	public int add(Student student) {
		entityManager.persist(student);
		return student.getId();
	}

	@Override
	public void deleteById(int id) {
		Student student = entityManager.find(Student.class, id);
		if (student != null) {
			entityManager.remove(student);
		}
	}

	@Override
	public Student getById(int id) {
		return entityManager.find(Student.class, id);
	}

	@Override
	public Student getByName(String firstName, String lastName) {
		String jpql = "SELECT s FROM Student s WHERE s.firstName=:fistName AND s.lastName=:lastName";
		return entityManager.createQuery(jpql, Student.class).setParameter("firstName", firstName)
				.setParameter("lastName", lastName).getSingleResult();
	}

	@Override
	public List<Student> getAll() {
		String jpql = "SELECT s FROM Student s LEFT JOIN FETCH s.courses";
		return entityManager.createQuery(jpql, Student.class).getResultList();
	}

	@Override
	@Transactional
	public void update(Student student) {
		entityManager.merge(student);
		Student managedStudent = entityManager.find(Student.class, student.getId());
		managedStudent.getCourses().clear();

		for (Course course : student.getCourses()) {
			managedStudent.getCourses().add(entityManager.getReference(Course.class, course.getId()));
		}
	}
}
