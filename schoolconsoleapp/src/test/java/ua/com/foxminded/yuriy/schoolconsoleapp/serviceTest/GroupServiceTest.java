package ua.com.foxminded.yuriy.schoolconsoleapp.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.GroupDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.impl.GroupServiceImpl;

class GroupServiceTest {

	@Mock
	private GroupDao groupDao;

	@InjectMocks
	private GroupServiceImpl groupService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetAllLessOrEqual() {
		int studentsCount = 5;
		List<Group> groups = new ArrayList<>();
		groups.add(new Group("AA-11", 1));
		groups.add(new Group("BB-22", 2));
		groups.add(new Group("CC-33", 3));
		groups.add(new Group("DD-44", 4));
		when(groupDao.getAllLessOrEqual(studentsCount)).thenReturn(groups);
		List<Group> result = groupService.getAllLessOrEqual(studentsCount);
		assertEquals(groups, result);
		verify(groupDao).getAllLessOrEqual(studentsCount);
	}

	@Test
	void testStudentsCountByGroupId() {
		int groupId = 1;
		int studentCount = 20;
		when(groupDao.studentsCountByGroupId(groupId)).thenReturn(studentCount);
		int result = groupService.studentsCountByGroupId(groupId);
		assertEquals(studentCount, result);
		verify(groupDao).studentsCountByGroupId(groupId);
	}

	@Test
	void testGetAll() {
		List<Group> groups = new ArrayList<>();
		groups.add(new Group("AA-11", 1));
		groups.add(new Group("BB-22", 2));
		groups.add(new Group("CC-33", 3));
		groups.add(new Group("DD-44", 4));
		when(groupDao.getAll()).thenReturn(groups);
		List<Group> result = groupService.getAll();
		assertEquals(groups, result);
		verify(groupDao).getAll();
	}

	@Test
	void testGetById() {
		int groupId = 1;
		Group group = new Group("AA-11", 1);
		when(groupDao.getById(groupId)).thenReturn(group);
		Group result = groupService.getById(groupId);
		assertEquals(group, result);
		verify(groupDao).getById(groupId);
	}
}
