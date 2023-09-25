package ua.com.foxminded.yuriy.schoolconsoleapp.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.tables.GroupsColumns;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;

@Component
public class GroupMapper implements RowMapper<Group> {

	@Override
	public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Group(rs.getString(GroupsColumns.GROUP_NAME), rs.getInt(GroupsColumns.GROUP_ID));
	}
}