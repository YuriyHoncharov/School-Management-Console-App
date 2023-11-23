package ua.com.foxminded.yuriy.schoolconsoleapp.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.CourseDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

@Repository
public class CourseDaoImpl implements CourseDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void addAll(List<Course> courses) {
		for (int i = 0; i < courses.size(); i++) {
			try {
				entityManager.merge(courses.get(i));
			} catch (Exception e) {
				throw new DaoException("Failed to save course to Data Base : " + courses.get(i));
			}
			if (i % courses.size() == 0) {
				entityManager.flush();
				entityManager.clear();
			}
		}
	}

	@Override
	public List<Course> getAvailableCourses(int studentId) {
		String jpql = "SELECT c FROM Course c WHERE c.id NOT IN (SELECT cr.id FROM Student s JOIN s.courses cr WHERE s.id = :studentId) ORDER BY c.id";
		return entityManager.createQuery(jpql, Course.class).setParameter("studentId", studentId).getResultList();
	}

	@Override
	public List<Course> getByStudentId(int studentId) {
		String jpql = "SELECT c FROM Course c JOIN FETCH c.students s WHERE s.id = :studentId ORDER BY c.id";
		return entityManager.createQuery(jpql, Course.class).setParameter("studentId", studentId).getResultList();
	}

	@Override
	public Course getById(int courseId) {
		return entityManager.find(Course.class, courseId);
	}

	@Override
	public List<Course> getAllCourses() {
		String jpql = "SELECT c FROM Course c";
		return entityManager.createQuery(jpql, Course.class).getResultList();
	}
}
