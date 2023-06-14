package ua.com.foxminded.yuriy.schoolconsoleapp.dao;

import java.util.List;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;

public interface GroupDao {

	List<Group> getAllLessOrEqual(int studentCount);

	int studentsCountByGroupId(int groupId);

	List<Group> getAll();

	Group getById(int groupId);

}
