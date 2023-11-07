package ua.com.foxminded.yuriy.schoolconsoleapp.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;

@JdbcTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(scripts = { "/schema.sql", "/test-data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = { "/test-data-clear.sql" }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class GroupDaoIT {

	private GroupDaoImpl groupDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@BeforeEach
	void setUp() {
		groupDao = new GroupDaoImpl(jdbcTemplate);
	}
	
	@Test
	void shouldgetAllLessOrEqual() {
		int studentCount = 3;
		List<Group>groups = groupDao.getAllLessOrEqual(studentCount);
		assertEquals(2, groups.size());
	}
	
	@Test
	void shouldReturnStudentsCountByGroupId() {
		int groupId = 1;
		int studentsCount = groupDao.studentsCountByGroupId(groupId);
		assertEquals(3, studentsCount);
	}
	
	@Test
	void shouldReturnAllGroups() {
		List<Group>groups = groupDao.getAll();
		assertEquals(2, groups.size());
	}
	
	@Test
	void shouldReturnGroupById() {
		int groupId = 1;
		Group groupFromDb = groupDao.getById(groupId);
		Group group = new Group("Group X", 1);
		assertEquals(group, groupFromDb);		
	}	
}
