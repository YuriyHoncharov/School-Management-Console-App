package ua.com.foxminded.yuriy.schoolconsoleapp.customexception;

import java.sql.SQLException;

public class SqlExecuteException extends SQLException{
	
	public SqlExecuteException(String message) {
		super(message);
	}

}
