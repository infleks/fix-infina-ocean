package fix.infina.ocean.worker;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import fix.infina.ocean.app.util.Utils;
import fix.infina.ocean.model.OceanURTMessage;

public class OceanURTMessageWorker implements MessageWorker {

	// public static final Logger logger = LogManager.getLogger(OceanURTMessageWorker.class);

	@SuppressWarnings("rawtypes")
	@Override
	public String work(Map<String, Object> data) {
		if (data == null) {
			// logger.debug("Input is null");
			return null;
		}
		OceanURTMessage message = new OceanURTMessage();
		Iterator<Entry<String, Object>> entries = data.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry entry = (Map.Entry) entries.next();
			String key = (String) entry.getKey();
			Object value = entry.getValue();
			if (value == null) {
				continue;
			}
			Utils.addMessageTo(message, Integer.parseInt(key), value);
		}
		return message.toString();
	}

}
