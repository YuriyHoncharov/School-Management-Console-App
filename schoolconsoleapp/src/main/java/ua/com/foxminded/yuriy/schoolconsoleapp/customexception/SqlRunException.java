package ua.com.foxminded.yuriy.schoolconsoleapp.customexception;

import java.sql.SQLException;

public class SqlRunException extends SQLException{
	
	public SqlRunException(String message) {
		super(message);
	}	

}
