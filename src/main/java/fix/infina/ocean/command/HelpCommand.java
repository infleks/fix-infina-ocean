package fix.infina.ocean.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HelpCommand implements AppCommand {

	private static final Logger logger = LogManager.getLogger(HelpCommand.class);

	@Override
	public void runCommand(String parameter) {
		logger.debug("Avaiable commands:");
		logger.debug("-urt [filename]");
	}

}
