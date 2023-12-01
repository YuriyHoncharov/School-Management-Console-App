package ua.com.foxminded.yuriy.schoolconsoleapp.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;


import ua.com.foxminded.yuriy.schoolconsoleapp.dao.GroupDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

@Repository
public class GroupDaoImpl implements GroupDao {

@PersistenceContext
private EntityManager entityManager;

	@Override
	@Transactional
	public void addAll(List<Group> groups) {
	    for (int i = 0; i < groups.size(); i++) {
	        try {
	            entityManager.merge(groups.get(i));
	        } catch (Exception e) {
	            throw new DaoException("Failed to save group to database: " + groups.get(i));
	        }
	        if (i % groups.size() == 0) {
	            entityManager.flush();
	            entityManager.clear();
	        }
	    }
	}

	@Override
	public List<Group> getAllLessOrEqual(int studentCount) {
		String jpql = "SELECT g FROM Group g WHERE (SELECT COUNT(s) FROM Student s WHERE s.group = g) <= :studentCount";
		return entityManager.createQuery(jpql, Group.class).setParameter("studentCount", (long) studentCount).getResultList();
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
