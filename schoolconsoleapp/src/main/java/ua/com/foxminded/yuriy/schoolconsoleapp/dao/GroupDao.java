package ua.com.foxminded.yuriy.schoolconsoleapp.dao;

import java.util.List;
import ua.com.foxminded.yuriy.schoolconsoleapp.entity.Group;

public interface GroupDao {
	
	List<Group> findAllLessOrEqual (int number) throws DaoException;
	
}
