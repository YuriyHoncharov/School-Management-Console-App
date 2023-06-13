package ua.com.foxminded.yuriy.schoolconsoleapp.service;

import java.util.List;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;

public interface GroupService {

	List<Group> findAllLessOrEqual(int courseId);

	int studentsCountByGroupId(int groupId);
	
	List<Group> getAll();
	
	Group getById (int groupId);

}
