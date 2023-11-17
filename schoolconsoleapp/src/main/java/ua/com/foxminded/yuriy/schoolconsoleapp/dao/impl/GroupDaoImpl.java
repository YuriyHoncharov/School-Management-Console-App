package ua.com.foxminded.yuriy.schoolconsoleapp.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.com.foxminded.yuriy.schoolconsoleapp.dao.GroupDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;

@Repository
public class GroupDaoImpl implements GroupDao {

@PersistenceContext
private EntityManager entityManager;
	@Override
	@Transactional
	public void addAll(List<Group> groups) {
		for (Group group : groups) {
			entityManager.persist(group);
		}
	}

	@Override
	public List<Group> getAllLessOrEqual(int studentCount) {
		String jpql = "SELECT g FROM Group g WHERE SIZE (g.students) <= :studentCount";
		return entityManager.createQuery(jpql, Group.class).setParameter("studentCount", studentCount).getResultList();
	}

	@Override
	public int studentsCountByGroupId(int groupId) {
		String jpql = "SELECT COUNT(s) FROM Student s WHERE s.groupId =:groupId";
		return ((Number) entityManager.createQuery(jpql).setParameter("groupId", groupId).getSingleResult()).intValue();
	}

	@Override
	public List<Group> getAll() {
		String jpql = "SELECT g FROM Group g";
		return entityManager.createQuery(jpql, Group.class).getResultList();
	}

	@Override
	public Group getById(int groupId) {
		return entityManager.find(Group.class, groupId);
	}
}
