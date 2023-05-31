package ua.com.foxminded.yuriy.schoolconsoleapp.service.implement;

import java.util.List;

import ua.com.foxminded.yuriy.schoolconsoleapp.dao.GroupDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.GroupService;

public class GroupServiceImpl implements GroupService{
	private GroupDao groupDao; 

	@Override
	public List<Group> findAllLessOrEqual(int studentsCount) throws DaoException {
		return groupDao.findAllLessOrEqual(studentsCount);	
	}	
}
