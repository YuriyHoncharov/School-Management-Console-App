package ua.com.foxminded.yuriy.schoolconsoleapp.repository;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.logger.CustomLogger;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.impl.GroupServiceImpl;

@ExtendWith(MockitoExtension.class)
class GroupRepositoryTest {

	@Mock
	private GroupRepository groupRepository;
	@Mock
	private CustomLogger customLogger;
	@InjectMocks
	private GroupServiceImpl groupServiceImpl;

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
