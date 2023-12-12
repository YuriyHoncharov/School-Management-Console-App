package ua.com.foxminded.yuriy.schoolconsoleapp.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.repository.GroupRepository;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.impl.GroupServiceImpl;

@ExtendWith(MockitoExtension.class)
class GroupDaoImplTest {

	@Mock
	private GroupRepository groupRepository;
	@InjectMocks
	private GroupServiceImpl groupServiceImpl;

	@Test
	void getByIdTest_Success() {
		int groupId = 1;
		String groupName = "AA-11";
		Group group = new Group(groupName, groupId);
		when(groupRepository.findById(groupId)).thenReturn(Optional.of(group));
		Group result = groupServiceImpl.getById(groupId);
		assertEquals(group, result);
	}

	@Test
	void getAllLessOrEqualTest_Success_ShouldReturnTrueGroupsList() {
		
		long studentCount = 5;
		Group groupWithFiveStudents = new Group("AA-11", 1);
		List<Group> trueGroups = new ArrayList<>();
		trueGroups.add(groupWithFiveStudents);
		when(groupRepository.getAllLessOrEqual(studentCount)).thenReturn(trueGroups);
		List<Group> result = groupServiceImpl.getAllLessOrEqual(studentCount);
		assertIterableEquals(trueGroups, result);
	}
}
