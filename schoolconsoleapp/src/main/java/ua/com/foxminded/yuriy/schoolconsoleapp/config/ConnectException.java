package ua.com.foxminded.yuriy.schoolconsoleapp.config;

public class ConnectException extends RuntimeException {
	
	public ConnectException () {
		super();
	}
	
	public ConnectException (String message) {
		super(message);
	}
	
	public ConnectException(String message, Throwable cause) {
		super(message, cause);
	}

}
