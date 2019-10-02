package fix.infina.ocean.worker;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.junit.Test;

public class OceanURTMessageWorkerTest {

	@Test
	public void withNull() {
		OceanURTMessageWorker worker = new OceanURTMessageWorker();
		String message = worker.work(null);
		assertEquals(null, message);
	}

	@Test
	public void withMap() {
		Date date = new Date();
		String dateText = new SimpleDateFormat("yyyyMMdd").format(date);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HH:mm:ss.SSS");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC+3"));
		String timestampText = sdf.format(new Date(timestamp.getTime()));

		Map<String, Object> map = new HashMap<>();
		map.put("1", 1);
		map.put("2", "Value for 2");
		map.put("3", 'A');
		map.put("4", true);
		map.put("5", new BigDecimal("1.234"));
		map.put("6", new Double(1.2345));
		map.put("7", new Date());
		map.put("8", timestamp);

		OceanURTMessageWorker worker = new OceanURTMessageWorker();
		String message = worker.work(map);

		assertEquals("8=FIX.4.49=6135=URT1=12=Value for 23=A4=Y5=1.2346=1.23457=" + dateText + "8="
				+ timestampText + "10=187", message);
	}

}
