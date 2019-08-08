package fix.infina.ocean.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fix.infina.ocean.worker.OceanURTMessageWorker;

public class ReadURTFileCommand implements AppCommand {

	private static final Logger logger = LogManager.getLogger(ReadURTFileCommand.class);

	@Override
	public void runCommand(String parameter) {
		logger.debug("Reading file " + parameter);
		List<String> messageList = prepareURTList(parameter);
		File outputFile = createOutputFile();
		writeOutputFile(outputFile, messageList);
	}

	private List<String> prepareURTList(String fileName) {
		List<String> messageList = new ArrayList<>();
		File file = new File(fileName);
		if (file.exists()) {
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				String line;
				String[] keyArr = null;
				while ((line = reader.readLine()) != null) {
					if (line.isEmpty()) {
						continue;
					}
					String[] arr = line.split("\\,");
					if (keyArr == null) {
						keyArr = arr;
					} else {
						Map<Integer, Object> map = new HashMap<>();
						for (int i = 0; i < arr.length; i++) {
							map.put(Integer.parseInt(keyArr[i].trim()), arr[i].trim());
						}
						OceanURTMessageWorker worker = new OceanURTMessageWorker();
						String message = worker.work(map);
						messageList.add(message);
					}
				}
			} catch (Exception e) {
				logger.error("Error with file read. Exception: {}", e);
			}
		} else {
			logger.error("Cant find file in working dir");
		}
		return messageList;
	}

	private File createOutputFile() {
		File file = new File("URTMessageFile.txt");
		try {
			if (file.createNewFile()) {
				logger.info("Output file created.");
			} else {
				file.delete();
				logger.info("Output file deleted.");
				file.createNewFile();
				logger.info("Output file created.");
			}
		} catch (Exception e) {
			logger.error("Cant create output file. Exception: {}", e);
		}
		return file;
	}

	private void writeOutputFile(File file, List<String> list) {
		try (FileWriter writer = new FileWriter(file)) {
			for (String message : list) {
				writer.write(message + "\n");
			}
			logger.info("Output file updated.");
		} catch (IOException e) {
			logger.error("Cant write output file. Exception: {}", e);
		}
	}

}
