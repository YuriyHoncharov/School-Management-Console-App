package ua.com.foxminded.yuriy.schoolconsoleapp.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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

import org.hibernate.service.spi.InjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.sqlqueries.SqlGroupQueries;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.mappers.GroupMapper;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;

@ExtendWith(MockitoExtension.class)
class GroupDaoImplTest {

	@Mock
	private EntityManager entityManager;
	@InjectMocks
	private GroupDaoImpl groupDao;
	@InjectMocks
	private StudentDaoImpl studentDao;
	@Mock
	private TypedQuery<Group> mockQuery;
	

	@Test
	void getByIdTest_Success() {
		int groupId = 1;
		String groupName = "AA-11";
		Group group = new Group(groupName, groupId);
		when(entityManager.find(Group.class, groupId)).thenReturn(group);
		groupDao.getById(groupId);
		verify(entityManager, times(1)).find(Group.class, groupId);
	}

	@Test
	void studentsCountByGroupIdTest_Success() throws SQLException {
		Group group = new Group("AA-11", 1);
		int expectedCount = 20;
		String jpql = "SELECT COUNT(s) FROM Student s WHERE s.group =:group";
		when((Number)entityManager.createQuery(jpql)).thenReturn((Number) mockQuery);

		when(mockQuery.setParameter("group", group)).thenReturn(mockQuery);

		when(mockQuery.getSingleResult()).thenReturn((long) expectedCount);
		int result = studentDao.studentsCountByGroupId(group);
		assertEquals(expectedCount, result);
	}

	@Test
	void getAllLessOrEqualTest_Success_ShouldReturnTrueGroupsList() {
		String jpql = "SELECT g FROM Group g WHERE (SELECT COUNT(s) FROM Student s WHERE s.group = g) <= :studentCount";
		int studentCount = 5;
		Group groupWithFiveStudents = new Group("AA-11", 1);
		List<Group> trueGroups = new ArrayList<>();
		trueGroups.add(groupWithFiveStudents);

		when(entityManager.createQuery(jpql, (Group.class))).thenReturn(mockQuery);

		when(mockQuery.setParameter("studentCount", (long) studentCount)).thenReturn(mockQuery);

		when(mockQuery.getResultList()).thenReturn(trueGroups);

		List<Group> result = groupDao.getAllLessOrEqual(studentCount);
		assertIterableEquals(trueGroups, result);
	}
}
