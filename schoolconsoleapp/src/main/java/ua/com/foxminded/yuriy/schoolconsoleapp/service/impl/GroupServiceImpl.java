package ua.com.foxminded.yuriy.schoolconsoleapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.logger.CustomLogger;
import ua.com.foxminded.yuriy.schoolconsoleapp.repository.GroupRepository;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

	private GroupRepository groupRepository;
	private CustomLogger customLogger;

	@Autowired
	public GroupServiceImpl(GroupRepository groupRepository, CustomLogger customLogger) {
		this.groupRepository = groupRepository;
		this.customLogger = customLogger;
	}

	@Override
	public List<Group> getAllLessOrEqual(Long studentsCount) {
		customLogger.logInfo("User trying to get a list of group with following student count : " + studentsCount);
		return groupRepository.getAllLessOrEqual(studentsCount);
	}

	@Override
	public List<Group> getAll() {
		customLogger.logInfo("User trying to get all Group list");
		return new ArrayList<>(groupRepository.findAll());
	}

	@Override
	public Group getById(int groupId) {
		Optional<Group> optionalGroup = groupRepository.findById(groupId);
		customLogger.logInfo("User trying to get group with following ID : " + groupId);
		return optionalGroup.orElse(null);
	}
}
