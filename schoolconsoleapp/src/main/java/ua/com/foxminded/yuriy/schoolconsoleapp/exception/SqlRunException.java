package ua.com.foxminded.yuriy.schoolconsoleapp.exception;

import java.sql.SQLException;

public class SqlRunException extends RuntimeException {

	public SqlRunException(String message) {
		super(message);
	}
}
