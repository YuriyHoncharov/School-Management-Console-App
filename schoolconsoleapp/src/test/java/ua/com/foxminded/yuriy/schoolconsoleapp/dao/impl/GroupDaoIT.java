package ua.com.foxminded.yuriy.schoolconsoleapp.dao.impl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.GroupDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.StudentDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {StudentDaoImpl.class, GroupDaoImpl.class}))
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(scripts = { "/schema.sql", "/test-data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = { "/test-data-clear.sql" }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class GroupDaoIT {
	
	@Autowired
	private GroupDao groupDao;
	@Autowired
	private StudentDao studentDao;
	
	@Test
	void shouldgetAllLessOrEqual() {
		int studentCount = 3;
		List<Group>groups = groupDao.getAllLessOrEqual(studentCount);
		assertEquals(2, groups.size());
	}
	
	@Test
	void shouldReturnStudentsCountByGroupId() {
		Group group = new Group("Group X", 1);
		int studentsCount = studentDao.studentsCountByGroup(group);
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
