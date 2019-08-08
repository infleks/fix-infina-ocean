package fix.infina.ocean.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fix.infina.ocean.command.HelpCommand;
import fix.infina.ocean.command.ReadURTFileCommand;

public class App {

	private static final Logger logger = LogManager.getLogger(App.class);

	public static void main(String[] args) {
		logger.debug("Hello to Infina-Ocean Integration App....");
		logger.debug("Starting....");
		if (args != null) {
			if (args.length % 2 != 0) {
				new HelpCommand().runCommand(null);
			} else {
				for (int i = 0; i < args.length; i = i + 2) {
					String command = args[i];
					String parameter = args[i + 1];

					if ("-urt".equals(command)) {
						new ReadURTFileCommand().runCommand(parameter);
					}
				}
			}
		}
		logger.debug("App stopped.");
	}

}
