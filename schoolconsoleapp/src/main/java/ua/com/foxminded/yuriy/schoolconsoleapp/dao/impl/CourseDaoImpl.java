package ua.com.foxminded.yuriy.schoolconsoleapp.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.com.foxminded.yuriy.schoolconsoleapp.dao.CourseDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.sqlqueries.SqlCourseQueries;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.mappers.CourseMapper;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Course;

@Repository
public class CourseDaoImpl implements CourseDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void addAll(List<Course> courses) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		for (Course course : courses) {
			entityManager.persist(course);
		} 
	}

	@Override
	@Transactional
	public List<Course> getAvailableCourses(int studentId) {
		String jpql = "SELECT c FROM Course c WHERE c.id NOT IN (SELECT sc.course.id FROM StudentCourse sc WHERE sc.student.id = :studentId)";
		return entityManager.createQuery(jpql, Course.class).setParameter("studentId", studentId).getResultList();
	}

	@Override
	@Transactional
	public List<Course> getByStudentId(int studentId) {
		String jpql = "SELECT c FROM Course c JOIN FETCH c.students s WHERE s.id = :studentId";
		return entityManager.createQuery(jpql, Course.class).setParameter("studentId", studentId).getResultList();
	}

	@Override
	@Transactional
	public Course getById(int courseId) {
		return entityManager.find(Course.class, courseId);
	}

	@Override
	@Transactional
	public List<Course> getAllCourses() {
		String jpql = "SELECT c FROM Course c";
		return entityManager.createQuery(jpql, Course.class).getResultList();
	}
}
