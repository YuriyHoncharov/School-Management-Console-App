package ua.com.foxminded.yuriy.schoolconsoleapp.service;

import java.util.List;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;

public interface GroupService {

	List<Group> getAllLessOrEqual(int studentsCount);

	List<Group> getAll();

	Group getById(int groupId);

}
