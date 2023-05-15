package ua.com.foxminded.yuriy.schoolconsoleapp.customexception;

import java.sql.SQLException;

public class CustomException extends SQLException{
	
	public CustomException(String message) {
		super(message);
	}

}
