package fix.infina.ocean.worker;

import java.util.Map;

public interface MessageWorker {

	public String work(Map<String, Object> data);

}
