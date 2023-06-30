package ua.com.foxminded.yuriy.schoolconsoleapp.dao.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import ua.com.foxminded.yuriy.schoolconsoleapp.config.ConnectionUtil;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.sqlqueries.SqlGroupQueries;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.constants.tables.GroupsColumns;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;

class GroupDaoImplTest {
	@Mock
	private Connection mockConnection;
	@Mock
	private PreparedStatement mockStatement;
	@Mock
	private ResultSet mockResultSet;

	private MockedStatic<ConnectionUtil> mockedStatic;
	private GroupDaoImpl groupDao;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		mockedStatic = mockStatic(ConnectionUtil.class);
		when(ConnectionUtil.getConnection()).thenReturn(mockConnection);
		groupDao = new GroupDaoImpl();

	}
	
	@AfterEach
	void cleanUp() {
		mockedStatic.close();
	}

	@Test
	void getByIdTest_Succes() throws SQLException {
		int groupId = 1;
		String groupName = "AA-11";
		when(mockConnection.prepareStatement(SqlGroupQueries.GET_BY_ID)).thenReturn(mockStatement);
		when(mockStatement.executeQuery()).thenReturn(mockResultSet);
		when(mockResultSet.next()).thenReturn(true);
		when(mockResultSet.getString(GroupsColumns.GROUP_NAME)).thenReturn(groupName);
		when(mockResultSet.getInt(GroupsColumns.GROUP_ID)).thenReturn(groupId);
		Group group = groupDao.getById(groupId);
		verify(mockConnection).prepareStatement(SqlGroupQueries.GET_BY_ID);
		verify(mockStatement).setInt(1, groupId);
		verify(mockStatement).executeQuery();
		verify(mockResultSet).next();
		verify(mockResultSet).getString(GroupsColumns.GROUP_NAME);
		verify(mockResultSet).getInt(GroupsColumns.GROUP_ID);
		assertNotNull(group);
		assertEquals(groupName, group.getName());
		assertEquals(groupId, group.getId());
	}
	
	@Test
	void studentsCountByGroupIdTest_Success() throws SQLException {
		int groupId = 1;
		int expectedCount = 20;
		when(mockConnection.prepareStatement(SqlGroupQueries.GET_STUDENTS_COUNT_BY_GROUP_ID)).thenReturn(mockStatement);
		when(mockStatement.executeQuery()).thenReturn(mockResultSet);
		when(mockResultSet.next()).thenReturn(true);
		when(mockResultSet.getInt("student_count")).thenReturn(expectedCount);
		int count = groupDao.studentsCountByGroupId(groupId);
		verify(mockConnection).prepareStatement(SqlGroupQueries.GET_STUDENTS_COUNT_BY_GROUP_ID);
		verify(mockStatement).setInt(1, groupId);
		verify(mockStatement).executeQuery();
		verify(mockResultSet).next();
		verify(mockResultSet).getInt("student_count");
		assertEquals(expectedCount, count);
	}

}
