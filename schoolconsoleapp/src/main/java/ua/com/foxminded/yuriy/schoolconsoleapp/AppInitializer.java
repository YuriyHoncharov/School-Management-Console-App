package ua.com.foxminded.yuriy.schoolconsoleapp;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ua.com.foxminded.yuriy.schoolconsoleapp.dataGenerator.DataGenerator;

@Component
public class AppInitializer implements ApplicationRunner {
	private Flyway flyway;
	private DataGenerator dataGenerator;
	private ConsoleMenu consoleMenu;

	@Autowired
	public AppInitializer(Flyway flyway, DataGenerator dataGenerator, ConsoleMenu consoleMenu) {
		this.flyway = flyway;
		this.dataGenerator = dataGenerator;
		this.consoleMenu = consoleMenu;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		flyway.clean();
		flyway.migrate();
		dataGenerator.initializeAndPopulateTestDatabase();
		consoleMenu.run();
	}
}
