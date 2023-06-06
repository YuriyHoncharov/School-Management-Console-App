package ua.com.foxminded.yuriy.schoolconsoleapp.service.implement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ua.com.foxminded.yuriy.schoolconsoleapp.dao.GroupDao;
import ua.com.foxminded.yuriy.schoolconsoleapp.dao.implement.GroupDaoImpl;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;
import ua.com.foxminded.yuriy.schoolconsoleapp.exception.DaoException;
import ua.com.foxminded.yuriy.schoolconsoleapp.service.GroupService;

public class GroupServiceImpl implements GroupService{
	private GroupDao groupDao = new GroupDaoImpl();

	@Override
	public Map<Group, Integer> findAllLessOrEqual(int studentsCount) throws DaoException {
			return groupDao.findAllLessOrEqual(studentsCount);	
	}	
}
   