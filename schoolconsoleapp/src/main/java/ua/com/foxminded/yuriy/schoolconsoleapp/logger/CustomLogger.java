package ua.com.foxminded.yuriy.schoolconsoleapp.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomLogger {

	private static final Logger logger = LoggerFactory.getLogger(CustomLogger.class);
	
	public void logCommand(String command) {
		logger.info("User executed command : {}" ,command );
	}

}
