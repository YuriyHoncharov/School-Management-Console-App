package ua.com.foxminded.yuriy.schoolconsoleapp.dao.impl;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
	private JdbcTemplate jdbcTemplate;
	@InjectMocks
	private GroupDaoImpl groupDao;

	@Test
	void getByIdTest_Succes() throws SQLException {
		int groupId = 1;
		String groupName = "AA-11";
		Group group = new Group(groupName, groupId);
		when(jdbcTemplate.queryForObject(eq(SqlGroupQueries.GET_BY_ID), any(GroupMapper.class), eq(groupId)))
				.thenReturn(group);
		groupDao.getById(groupId);
		verify(jdbcTemplate, times(1)).queryForObject(eq(SqlGroupQueries.GET_BY_ID), any(GroupMapper.class), eq(groupId));
	}

	@Test
	void studentsCountByGroupIdTest_Success() throws SQLException {
		int groupId = 1;
		int expectedCount = 20;
		when(jdbcTemplate.queryForObject(eq(SqlGroupQueries.GET_STUDENTS_COUNT_BY_GROUP_ID), eq(Integer.class),
				eq(groupId))).thenReturn(expectedCount);
		groupDao.studentsCountByGroupId(groupId);
		verify(jdbcTemplate, times(1)).queryForObject(eq(SqlGroupQueries.GET_STUDENTS_COUNT_BY_GROUP_ID),
				eq(Integer.class), eq(groupId));
	}

	@Test
	void getAllLessOrEqualTest_Success_ShouldReturnTrueGroupsList() throws SQLException {
		int studentCount = 5;
		Group groupWithFiveStudents = new Group("AA-11", 1);
		List<Group> trueGroups = new ArrayList<>();
		trueGroups.add(groupWithFiveStudents);
		when(jdbcTemplate.query(eq(SqlGroupQueries.GET_BY_LESS_OR_EQUAL_STUDENTS), any(GroupMapper.class),
				eq(studentCount))).thenReturn(trueGroups);
		groupDao.getAllLessOrEqual(studentCount);
		verify(jdbcTemplate, times(1)).query(eq(SqlGroupQueries.GET_BY_LESS_OR_EQUAL_STUDENTS), any(GroupMapper.class),
				eq(studentCount));
	}
}
