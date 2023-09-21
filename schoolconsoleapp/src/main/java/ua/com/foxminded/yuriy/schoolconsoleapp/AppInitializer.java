package ua.com.foxminded.yuriy.schoolconsoleapp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ua.com.foxminded.yuriy.schoolconsoleapp.dataGenerator.DataGenerator;

@Component
public class AppInitializer implements ApplicationRunner{

	private DataGenerator dataGenerator;
	private ConsoleMenu consoleMenu;

	@Autowired
	public AppInitializer(DataGenerator dataGenerator, ConsoleMenu consoleMenu) {
		this.dataGenerator = dataGenerator;
		this.consoleMenu = consoleMenu;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		dataGenerator.initializeAndPopulateTestDatabase();
		consoleMenu.run();
	}
}
