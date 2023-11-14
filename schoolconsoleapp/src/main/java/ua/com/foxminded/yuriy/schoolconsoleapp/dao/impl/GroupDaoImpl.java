package ua.com.foxminded.yuriy.schoolconsoleapp.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.yuriy.schoolconsoleapp.dao.GroupDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.sqlqueries.SqlGroupQueries;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.mappers.GroupMapper;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;

@Repository
public class GroupDaoImpl implements GroupDao {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public GroupDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void addAll(List<Group> groups) {
		for (Group group : groups) {
			jdbcTemplate.update(SqlGroupQueries.ADD_ALL, group.getId(), group.getName());
		}
	}

	@Override
	public List<Group> getAllLessOrEqual(int studentCount) {

		return jdbcTemplate.query(SqlGroupQueries.GET_BY_LESS_OR_EQUAL_STUDENTS, new GroupMapper(), studentCount);
	}

	@Override
	public int studentsCountByGroupId(int groupId) {
		return jdbcTemplate.queryForObject(SqlGroupQueries.GET_STUDENTS_COUNT_BY_GROUP_ID, Integer.class, groupId);
	}

	@Override
	public List<Group> getAll() {
		return jdbcTemplate.query(SqlGroupQueries.GET_ALL_GROUPS, new GroupMapper());
	}

	@Override
	public Group getById(int groupId) {
		return jdbcTemplate.queryForObject(SqlGroupQueries.GET_BY_ID, new GroupMapper(), groupId);
	}
}
