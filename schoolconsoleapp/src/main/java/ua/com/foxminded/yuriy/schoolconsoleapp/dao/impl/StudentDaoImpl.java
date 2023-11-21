package ua.com.foxminded.yuriy.schoolconsoleapp.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.StudentDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Student;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

@Repository
public class StudentDaoImpl implements StudentDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public void addAll(List<Student> students) {
		try {
			for (int i = 0; i < students.size(); i++) {
				entityManager.merge(students.get(i));
			}
			entityManager.flush();
		} catch (Exception e) {
			throw new DaoException("Failed to add students to the database.");
		}
	}

	@Override
	public List<Student> getAllByCourse(Course course) {
		String jpql = "SELECT s FROM Student s JOIN s.courses c WHERE c.id = :courseId";
		return entityManager.createQuery(jpql, Student.class).setParameter("courseId", course.getId()).getResultList();
	}

	@Transactional
	@Override
	public int add(Student student) {
		entityManager.persist(student);
		return student.getId();
	}

	@Transactional
	@Override
	public void delete(Student student) {
		if (student != null) {
			Student mergedStudent = entityManager.merge(student);
			entityManager.remove(mergedStudent);
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

	@Transactional
	@Override
	public List<Student> getAll() {
		String jpql = "SELECT s FROM Student s LEFT JOIN FETCH s.courses LEFT JOIN FETCH s.group";
		List<Student> studentsResult = entityManager.createQuery(jpql, Student.class).getResultList();
		return studentsResult.stream()
				.collect(Collectors.toMap(Student::getId, Function.identity(), (existing, replacement) -> {
					Set<Course> mergedCourses = new HashSet<>(existing.getCourses());
					mergedCourses.addAll(replacement.getCourses());
					existing.setCourse(new ArrayList<>(mergedCourses));
					return existing;
				})).values().stream().collect(Collectors.toList());
	}

	@Transactional
	@Override
	public void update(Student student) {
		entityManager.merge(student);
		Student managedStudent = entityManager.find(Student.class, student.getId());
		managedStudent.getCourses().clear();

		for (Course course : student.getCourses()) {
			managedStudent.getCourses().add(entityManager.getReference(Course.class, course.getId()));
		}
	}

	@Override
	public int studentsCountByGroupId(Group group) {
		String jpql = "SELECT COUNT(s) FROM Student s WHERE s.group =:group";
		return ((Number) entityManager.createQuery(jpql).setParameter("group", group).getSingleResult()).intValue();
	}
}
